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
import com.wisedu.amp.fserver.web.restful.runtime.process.QueryProcessInstanceRequest;
import com.wisedu.amp.fserver.web.restful.runtime.task.QueryTaskRequest;
import com.wisedu.amp.fserver.web.restful.runtime.task.TaskQueryResponse;

/**
 * REST 响应工厂，所有请求的响应都由此工厂产生
 * <p>
 * 响应 描述
 * </p>
 * <ul>
 * <li>200 - Ok 操作成功，响应返回（GET和PUT请求）。</li>
 * <li>201 - Created 操作成功，实体已创建，并返回到响应体中（POST请求）。</li>
 * <li>204 - No content 操作成功，实体已删除，不会返回响应体（DELETE请求）。</li>
 * <li>400 - Bad request 请求参数有误，当前请求无法被服务器理解。</li>
 * <li>401 - Unauthorized
 * 操作失败。操作要求设置Authentication头部。如果请求中已经设置了头部，对应的凭证是无效的或者用户不允许执行这个操作。</li>
 * <li>403 - Forbidden
 * 禁止操作，不要重试。这不是认证和授权的问题，这是禁止操作。比如：删除一个执行中流程的任务是不允许的，无论用户或流程任务的状态。</li>
 * <li>404 - Not found 操作失败。找不到请求的资源。</li>
 * <li>405 - Method not allowed 操作失败。使用的资源方法不允许调用。比如：想更新（PUT）已部署的资源会返回405结果。</li>
 * <li>409 - Conflict 操作失败。更新其他操作应更新的资源，会导致更新不合法。也可以表示一个结合中新创建的资源的id已经存在了。</li>
 * <li>415 - Unsupported Media Type
 * 操作失败。请求体包含了不支持的媒体类型。当请求体的JSON中包含未知的属性或值时，也会返回这个响应，一般是因为无法处理的错误格式或类型。</li>
 * <li>500 - Internal server error 操作失败。执行操作时出现了预期外的异常。响应体中包含错误的细节。</li>
 * </ul>
 * 
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
	 * @param queryProcessInstanceRequest
	 *            查询流程实例请求
	 * @return 流程实例响应对象
	 */
	public PageResponse createQueryProcessResponse(
			List<ProcessInstance> processInstances,
			QueryProcessInstanceRequest queryProcessInstanceRequest) {

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
				//TODO
				processInstanceQueryResponse.setFormUri("");

				processInstanceQueryResponse
						.setProcessInstanceId(processInstance.getId());
				processInstanceQueryResponse.setProcessKey(processInstance
						.getProcessDefinitionKey());
				//TODO
				processInstanceQueryResponse.setProcessName("");
				processInstanceQueryResponse.setSubject(processInstance
						.getSubject());
				processInstanceQueryResponse.setProcessLocation(processInstance.getProcessLocation());
				processInstanceQueryResponse.setInstanceStatus(processInstance.getInstanceStatus());
				processQueryResponses.add(processInstanceQueryResponse);
			}
		}
		PageResponse result = new PageResponse();
		result.setData(processQueryResponses);
		result.setTotal(processQueryResponses.size());
		result.setPageIndex(queryProcessInstanceRequest.getPageIndex());
		result.setPageSize(queryProcessInstanceRequest.getPageSize());
		return result;
	}

	/**
	 * 创建待办任务响应实体
	 * 
	 * @param tasks
	 *            任务列表
	 * @param queryTaskRequest
	 *            查询任务请求
	 * @return
	 */
	public PageResponse createToDoTasksResponse(List<Task> tasks,
			QueryTaskRequest queryTaskRequest) {
		PageResponse result = new PageResponse();
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
		result.setPageIndex(queryTaskRequest.getPageIndex());
		result.setPageSize(queryTaskRequest.getPageSize());
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
