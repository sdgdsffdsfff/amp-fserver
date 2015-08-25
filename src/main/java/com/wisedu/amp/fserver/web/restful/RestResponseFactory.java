package com.wisedu.amp.fserver.web.restful;

import java.util.ArrayList;
import java.util.List;

import org.foxbpm.engine.impl.Context;
import org.foxbpm.engine.runtime.ProcessInstance;
import org.foxbpm.engine.task.Task;
import org.springframework.stereotype.Component;

import com.wisedu.amp.fserver.util.OperationUtil;
import com.wisedu.amp.fserver.web.restful.runtime.process.ProcessInstanceQueryResponse;
import com.wisedu.amp.fserver.web.restful.runtime.process.ProcessInstanceResponse;
import com.wisedu.amp.fserver.web.restful.runtime.task.TaskQueryResponse;

/**
 * REST 响应工厂，所有请求的响应都由此工厂产生
 * 
 * @author zengxianping
 *
 */
@Component
public class RestResponseFactory {

	/**
	 * 创建启动流程实例响应
	 * 
	 * @param processInstance
	 * @return 流程实例响应对象
	 */
	public ProcessInstanceResponse createStartProcessResponse(
			ProcessInstance processInstance) {
		ProcessInstanceResponse result = new ProcessInstanceResponse();
		result.setOperationId(OperationUtil.generateOperationId());
		result.setProcessInstanceId(processInstance.getId());
		result.setProcessLocation(processInstance.getProcessLocation());

		return result;
	}

	/**
	 * 创建查询流程实例响应
	 * 
	 * @param processInstances
	 *            流程实例集合
	 * @param pageRequest
	 *            查询流程实例请求
	 * @return 流程实例响应对象
	 */
	public PageResponse<ProcessInstanceQueryResponse> createQueryProcessResponse(
			List<ProcessInstance> processInstances, PageRequest pageRequest,
			long total) {
		PageResponse<ProcessInstanceQueryResponse> result = new PageResponse<ProcessInstanceQueryResponse>();

		List<ProcessInstanceQueryResponse> processQueryResponses = new ArrayList<ProcessInstanceQueryResponse>();
		if (processInstances != null && processInstances.size() > 0) {
			for (ProcessInstance processInstance : processInstances) {
				ProcessInstanceQueryResponse processInstanceQueryResponse = new ProcessInstanceQueryResponse();
				processInstanceQueryResponse.setAppId(Context.getAppId());
				processInstanceQueryResponse.setCreateTime(processInstance
						.getStartTime());
				processInstanceQueryResponse.setUpdateTime(processInstance
						.getUpdateTime());
				processInstanceQueryResponse.setStartAuthor(processInstance
						.getStartAuthor());
				// TODO
				processInstanceQueryResponse.setFormUri("");

				processInstanceQueryResponse
						.setProcessInstanceId(processInstance.getId());
				processInstanceQueryResponse.setProcessKey(processInstance
						.getProcessDefinitionKey());
				// TODO
				processInstanceQueryResponse.setProcessName("");
				processInstanceQueryResponse.setSubject(processInstance
						.getSubject());
				processInstanceQueryResponse.setProcessLocation(processInstance
						.getProcessLocation());
				processInstanceQueryResponse.setInstanceStatus(processInstance
						.getInstanceStatus());
				processQueryResponses.add(processInstanceQueryResponse);
			}
		}

		result.setData(processQueryResponses);
		result.setTotal(processQueryResponses.size());
		result.setPageIndex(pageRequest.getPageIndex());
		result.setPageSize(pageRequest.getPageSize());
		return result;
	}

	/**
	 * 创建待办任务响应实体
	 * 
	 * @param tasks
	 *            任务列表
	 * @param pageRequest
	 *            查询任务请求
	 * @return
	 */
	public PageResponse<TaskQueryResponse> createToDoTasksResponse(
			List<Task> tasks, PageRequest pageRequest, long total) {
		PageResponse<TaskQueryResponse> result = new PageResponse<TaskQueryResponse>();
		List<TaskQueryResponse> taskQueryResponses = new ArrayList<TaskQueryResponse>();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				TaskQueryResponse taskQueryResponse = new TaskQueryResponse();
				taskQueryResponse.setAppId(Context.getAppId());
				taskQueryResponse.setSubject(task.getSubject());
				taskQueryResponse.setTaskId(task.getId());
				taskQueryResponse.setBizKey(task.getBizKey());
				taskQueryResponse.setAssignee(task.getAssignee());
				taskQueryResponse.setCreateTime(task.getCreateTime());
				taskQueryResponse.setFormUri(task.getFormUri());
				taskQueryResponse.setNodeId(task.getNodeId());
				taskQueryResponse.setNodeName(task.getNodeName());
				taskQueryResponse.setProcessInstanceId(task
						.getProcessDefinitionId());
				taskQueryResponse.setProcessKey(task.getProcessDefinitionKey());
				taskQueryResponse.setProcessName(task
						.getProcessDefinitionName());
				taskQueryResponse.setInitiator(task.getProcessInitiator());
				taskQueryResponse.setPriority(task.getPriority());

				taskQueryResponses.add(taskQueryResponse);
			}

		}
		result.setData(taskQueryResponses);
		result.setTotal(tasks.size());
		result.setPageIndex(pageRequest.getPageIndex());
		result.setPageSize(pageRequest.getPageSize());
		return result;
	}

	/**
	 * 创建任务处理响应实体
	 * 
	 * @param tasks
	 * @return
	 */
	public OperationResponse createTaskHandleResponse(
			ProcessInstance processInstance) {
		OperationResponse result = new OperationResponse();
		result.setOperationId(OperationUtil.generateOperationId());
		result.setProcessLocation(processInstance.getProcessLocation());
		return result;
	}
}
