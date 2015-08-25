package com.wisedu.amp.fserver.web.restful.runtime.task;

import java.util.Map;

import com.wisedu.amp.fserver.web.restful.ActionRequest;

/**
 * 处理任务（完成、退回）请求体
 * 
 * @author MAENLIANG
 *
 */
public class HandleTaskRequest extends ActionRequest {

	/**
	 * 任务处理人
	 */
	private String assignee;

	/**
	 * 退回的目标节点
	 */
	private String nodeId;

	/**
	 * 流程变量(持久化)
	 */
	private Map<String, Object> variables;

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HandleTaskRequest [assignee=" + assignee + ", nodeId=" + nodeId
				+ ", variables=" + variables + "]";
	}

}
