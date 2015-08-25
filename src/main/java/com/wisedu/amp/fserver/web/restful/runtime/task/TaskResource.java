package com.wisedu.amp.fserver.web.restful.runtime.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.foxbpm.engine.RuntimeService;
import org.foxbpm.engine.TaskService;
import org.foxbpm.engine.impl.entity.GroupEntity;
import org.foxbpm.engine.impl.identity.Authentication;
import org.foxbpm.engine.impl.task.command.ExpandTaskCommand;
import org.foxbpm.engine.impl.util.StringUtil;
import org.foxbpm.engine.runtime.ProcessInstance;
import org.foxbpm.engine.runtime.ProcessInstanceQuery;
import org.foxbpm.engine.task.Task;
import org.foxbpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisedu.amp.fserver.exception.ProcessIllegalArgumentException;
import com.wisedu.amp.fserver.exception.ProcessObjectNotFoundException;
import com.wisedu.amp.fserver.exception.ProcessOperationNotSupportedException;
import com.wisedu.amp.fserver.exception.ProcessTaskAlreadyClaimedException;
import com.wisedu.amp.fserver.web.restful.OperationResponse;
import com.wisedu.amp.fserver.web.restful.PageRequest;
import com.wisedu.amp.fserver.web.restful.PageResponse;
import com.wisedu.amp.fserver.web.restful.RestResponseFactory;

/**
 * 任务资源操作接口
 * 
 * @author zengxianping
 *
 */
@RestController
@RequestMapping("/runtime/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RestResponseFactory restResponseFactory;

	/**
	 * 查询待办任务列表 备注：因为查待办列表的请求参数比较复杂（多且有数组），所以这里不完全按照RESTful规范，而是将请求方法设为：post
	 * 
	 * @param QueryTaskRequest
	 *            查询任务请求体
	 * @return 待办任务列表
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PageResponse<TaskQueryResponse> queryToDoTasks(
			@RequestBody QueryTaskRequest queryTaskRequest) {
		// 检查参数
		if (StringUtils.isEmpty(queryTaskRequest.getAssignee())) {
			throw new ProcessIllegalArgumentException("请求中必须包含任务处理人！");
		}

		// 构造任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee(queryTaskRequest.getAssignee());
		taskQuery.taskCandidateUser(queryTaskRequest.getAssignee());
		taskQuery.processDefinitionKey(queryTaskRequest.getProcessKey());
		taskQuery.initiator(queryTaskRequest.getInitiator());
		taskQuery.taskSubjectLike(queryTaskRequest.getSubjectLike());
		taskQuery.nodeId(queryTaskRequest.getNodeId());
		taskQuery.processDefinitionNameLike(queryTaskRequest
				.getProcessNameLike());
		taskQuery.processInstanceId(queryTaskRequest.getProcessInstanceId());
		taskQuery.taskCreatedBefore(queryTaskRequest.getCreateTimeStart());
		taskQuery.taskCreatedAfter(queryTaskRequest.getCreateTimeEnd());
		addCandidateGroups(taskQuery, queryTaskRequest);

		// 查询待办任务
		taskQuery.taskNotEnd();

		// 设置排序字段，目前只支持根据创建时间排序
		taskQuery.orderByTaskCreateTime();
		// 设置排序类型
		if (StringUtils.equals(queryTaskRequest.getSort(),
				PageRequest.SORT_DESC)) {
			taskQuery.desc();
		} else {
			taskQuery.asc();
		}

		// 查询结果
		long total = taskQuery.count();
		List<Task> tasks = new ArrayList<Task>();
		if (total > 0) {
			tasks = taskQuery.listPagination(queryTaskRequest.getPageIndex(),
					queryTaskRequest.getPageSize());

		}

		return restResponseFactory.createToDoTasksResponse(tasks,
				queryTaskRequest, total);
	}

	/**
	 * 添加组实体列表
	 * 
	 * @param request
	 * @return
	 */
	private void addCandidateGroups(TaskQuery taskQuery,
			QueryTaskRequest request) {
		List<String> groupIds = request.getGroupIds();
		if (groupIds != null && groupIds.size() > 0) {
			for (String groupId : groupIds) {
				// TODO
				taskQuery.addCandidateGroup(new GroupEntity(groupId, "good",
						"dept", request.getDeptId()));
				taskQuery.addCandidateGroup(new GroupEntity(groupId, "good",
						"class", request.getClassId()));
			}
		}
	}

	/**
	 * 处理任务 完成、退回
	 * 
	 * @param taskId
	 *            任务编号
	 * @param handleTaskRequest
	 *            处理任务请求实体
	 * @return
	 */
	@RequestMapping(value = "/{taskId}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public OperationResponse handleTask(@PathVariable("taskId") String taskId,
			@RequestBody HandleTaskRequest handleTaskRequest) {
		// 参数检查
		if (StringUtils.isEmpty(taskId)) {
			throw new ProcessIllegalArgumentException("请求中必须包含任务编号！");
		}

		Task task = taskService.findTask(taskId);
		if (task == null) {
			// 任务没有找到
			throw new ProcessObjectNotFoundException("编号为：" + taskId + "的任务不存在");
		} else if (task.hasEnded()) {
			// 任务已经被处理
			throw new ProcessTaskAlreadyClaimedException("编号为：" + taskId
					+ "的任务已经被处理");
		}

		Authentication.setAuthenticatedUserId(handleTaskRequest.getAssignee());

		String action = handleTaskRequest.getAction();
		// 处理任务
		if (StringUtil.equals(HandleTaskRequest.ACTION_COMPLETE, action)) {
			// 完成任务
			taskService
					.complete(taskId, null, handleTaskRequest.getVariables());

		} else if (StringUtil.equals(HandleTaskRequest.ACTION_ROLLBACK, action)) {
			// 退回任务
			rollbackTask(taskId, handleTaskRequest);
		} else {
			throw new ProcessOperationNotSupportedException("该操作暂不支持");
		}

		ProcessInstanceQuery createProcessInstanceQuery = runtimeService
				.createProcessInstanceQuery();
		ProcessInstance processInstance = createProcessInstanceQuery
				.processInstanceId(task.getProcessInstanceId()).singleResult();

		return restResponseFactory.createTaskHandleResponse(processInstance);
	}

	/**
	 * 方法名：退回指定任务
	 * <p>
	 * 功能说明：
	 * </p>
	 * 
	 * @param taskId
	 * @param handleTaskRequest
	 * @param assignee
	 */
	private void rollbackTask(String taskId, HandleTaskRequest handleTaskRequest) {
		// 通过扩展命令的形式执行任务退回
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// TODO 目前先退回到指定的处理者
		expandTaskCommand.setCommandType("rollBack_assignee");
		expandTaskCommand.setTaskComment("任务退回");
		expandTaskCommand.setTaskId(taskId);

		Map<String, Object> taskParams = new HashMap<String, Object>();
		// rollBackNodeId 退回到哪个节点
		taskParams.put("rollBackNodeId", handleTaskRequest.getNodeId());
		// rollBackAssignee 退回到处理人
		taskParams.put("rollBackAssignee", handleTaskRequest.getAssignee());

		expandTaskCommand.setParamMap(taskParams);

		taskService.expandTaskComplete(expandTaskCommand, Void.class);
	}

}
