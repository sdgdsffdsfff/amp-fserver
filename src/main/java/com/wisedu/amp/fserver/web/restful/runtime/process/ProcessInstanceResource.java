package com.wisedu.amp.fserver.web.restful.runtime.process;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.foxbpm.engine.RuntimeService;
import org.foxbpm.engine.TaskService;
import org.foxbpm.engine.exception.FoxBPMException;
import org.foxbpm.engine.impl.identity.Authentication;
import org.foxbpm.engine.impl.task.command.ExpandTaskCommand;
import org.foxbpm.engine.impl.util.StringUtil;
import org.foxbpm.engine.runtime.ProcessInstance;
import org.foxbpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wisedu.amp.fserver.exception.ProcessException;
import com.wisedu.amp.fserver.exception.ProcessIllegalArgumentException;
import com.wisedu.amp.fserver.exception.ProcessObjectNotFoundException;
import com.wisedu.amp.fserver.util.ExceptionUtil;
import com.wisedu.amp.fserver.web.restful.PageRequest;
import com.wisedu.amp.fserver.web.restful.PageResponse;
import com.wisedu.amp.fserver.web.restful.RestResponseFactory;

/**
 * 流程实例资源操作接口
 * 
 * @author zengxianping
 *
 */
@RestController
@RequestMapping("/runtime/process-instances")
public class ProcessInstanceResource {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RestResponseFactory restResponseFactory;

	/**
	 * 启动流程，并提交第一个节点的任务信息
	 * 
	 * @param startProcessRequest
	 *            启动流程请求实体
	 * @return 流程实例响应
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ProcessInstanceResponse startProcess(
			@RequestBody StartProcessRequest startProcessRequest) {
		// 参数检测
		if (StringUtils.isEmpty(startProcessRequest.getProcessKey())) {
			throw new ProcessIllegalArgumentException("参数异常:流程定义键不能为空！");
		}
		if (StringUtils.isEmpty(startProcessRequest.getBizKey())) {
			throw new ProcessIllegalArgumentException("参数异常:业务关联键不能为空！");
		}
		if (StringUtils.isEmpty(startProcessRequest.getInitiator())) {
			throw new ProcessIllegalArgumentException("参数异常:启动人不能为空！");
		}

		ProcessInstance processInstance = null;
		try {
			// 设置流程引擎操作人
			Authentication.setAuthenticatedUserId(startProcessRequest
					.getInitiator());

			// 启动并提交任务
			ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
			expandTaskCommand.setProcessDefinitionKey(startProcessRequest
					.getProcessKey());
			expandTaskCommand.setCommandType("startandsubmit");
			expandTaskCommand.setBusinessKey(startProcessRequest.getBizKey());
			expandTaskCommand.setInitiator(startProcessRequest.getInitiator());
			expandTaskCommand.setPersistenceVariables(startProcessRequest
					.getVariables());
			processInstance = taskService.expandTaskComplete(expandTaskCommand,
					null);

		} catch (Exception e) {
			// 流程定义未找到
			// 需要将流程引擎状态码转换成流程服务中心能识别的异常
			if (e instanceof FoxBPMException) {
				String exceptionCode = ExceptionUtil
						.extractExceptionCode((FoxBPMException) e);
				if ("10602101".equals(exceptionCode)) {
					throw new ProcessObjectNotFoundException("未找到流程定义");
				} else if ("10700002".equals(exceptionCode)) {
					throw new ProcessException(
							((FoxBPMException) e).getLocalizedMessage());
				}
			} else {
				throw new ProcessException("启动流程出错！", e);
			}

		}

		return restResponseFactory.createStartProcessResponse(processInstance);
	}

	/**
	 * 流程实例查询
	 * 
	 * @param httpRequest
	 *            http请求
	 * @param httpResponse
	 *            http响应
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public PageResponse queryProcessInstance(HttpServletRequest httpRequest) {
		QueryProcessInstanceRequest queryProcessInstanceRequest = constructQueryProcessInstanceRequest(httpRequest);

		List<ProcessInstance> processInstances = null;
		ProcessInstanceQuery processInstanceQuery = runtimeService
				.createProcessInstanceQuery();
		processInstanceQuery.processDefinitionKey(queryProcessInstanceRequest
				.getProcessKey());
		processInstanceQuery.subjectLike(queryProcessInstanceRequest
				.getSubjectLike());
		processInstanceQuery
				.processDefinitionNameLike(queryProcessInstanceRequest
						.getProcessNameLike());
		processInstanceQuery.processInstanceId(queryProcessInstanceRequest
				.getProcessInstanceId());
		processInstanceQuery.initiator(queryProcessInstanceRequest
				.getInitiator());
		processInstanceQuery.startTimeBefore(queryProcessInstanceRequest
				.getCreateTimeStart());
		processInstanceQuery.startTimeAfter(queryProcessInstanceRequest
				.getCreateTimeEnd());

		// 目前只支持根据开始时间进行排序
		processInstanceQuery.orderByStartTime();
		if (StringUtil.equals(queryProcessInstanceRequest.getSort(),
				PageRequest.SORT_DESC)) {
			processInstanceQuery.desc();
		} else {
			processInstanceQuery.asc();
		}

		processInstances = processInstanceQuery.listPagination(
				queryProcessInstanceRequest.getPageIndex(),
				queryProcessInstanceRequest.getPageSize());

		return restResponseFactory.createQueryProcessResponse(processInstances,
				queryProcessInstanceRequest);
	}

	/**
	 * 方法名：
	 * <p>
	 * 功能说明：从http请求体中构建查询流程请求实体
	 * </p>
	 * 
	 * @param httpRequest
	 * @return
	 */
	private QueryProcessInstanceRequest constructQueryProcessInstanceRequest(
			HttpServletRequest httpRequest) {
		QueryProcessInstanceRequest request = new QueryProcessInstanceRequest();
		try {
			request.setProcessKey(httpRequest.getParameter("processKey"));
			if (StringUtils.isNotEmpty(httpRequest
					.getParameter("createTimeStart"))) {
				request.setCreateTimeStart(DateUtils.parseDate(
						httpRequest.getParameter("createTimeStart"),
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotEmpty(httpRequest
					.getParameter("createTimeEnd"))) {
				request.setCreateTimeStart(DateUtils.parseDate(
						httpRequest.getParameter("createTimeEnd"),
						"yyyy-MM-dd HH:mm:ss"));
			}
			request.setProcessNameLike(httpRequest
					.getParameter("processNameLike"));
			request.setProcessInstanceId(httpRequest
					.getParameter("processInstanceId"));
			request.setInitiator(httpRequest.getParameter("initiator"));
			// /////////////////////分页相关////////////////////////////////////

			if (StringUtils.isNotEmpty((httpRequest.getParameter("pageIndex")))) {
				request.setPageIndex(Integer.valueOf(httpRequest
						.getParameter("pageIndex")));
			}
			if (StringUtils.isNotEmpty(httpRequest.getParameter("pageSize"))) {
				request.setPageSize(Integer.valueOf(httpRequest
						.getParameter("pageSize")));
			}

			request.setOrderBy(httpRequest.getParameter("orderBy"));
			request.setSort(httpRequest.getParameter("sort"));
			// /////////////////////分页相关////////////////////////////////////
		} catch (ParseException e) {
			throw new ProcessIllegalArgumentException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new ProcessIllegalArgumentException(e.getMessage());
		}

		return request;
	}

}
