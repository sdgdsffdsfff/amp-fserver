package com.wisedu.amp.fserver.web.restful.runtime.task;

import java.util.Date;

/**
 * 任务查询响应体
 * 
 * @author ych
 *
 */
public class TaskQueryResponse {
	/**
	 * 应用编号
	 */
	private String appId;
	/**
	 * 任务编号
	 */
	private String taskId;

	/**
	 * 业务关联键
	 */
	private String bizKey;
	/**
	 * 表单相对URI
	 */
	private String formUri;
	/**
	 * 任务处理人
	 */
	private String assignee;
	/**
	 * 任务主题
	 */
	private String subject;
	/**
	 * 流程实例编号
	 */
	private String processInstanceId;
	/**
	 * 流程名称
	 */
	private String processName;
	/**
	 * 流程编号
	 */
	private String processKey;
	/**
	 * 任务创建时间
	 */
	private Date createTime;
	/**
	 * 节点编号
	 */
	private String nodeId;
	/**
	 * 节点名称
	 */
	private String nodeName;

	/**
	 * 任务优先级
	 */
	private int priority;

	/**
	 * 流程发起人
	 */
	private String initiator;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the bizKey
	 */
	public String getBizKey() {
		return bizKey;
	}

	/**
	 * @param bizKey
	 *            the bizKey to set
	 */
	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getFormUri() {
		return formUri;
	}

	public void setFormUri(String formUri) {
		this.formUri = formUri;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	@Override
	public String toString() {
		return "TaskQueryResponse [appId=" + appId + ", taskId=" + taskId
				+ ", bizKey=" + bizKey + ", formUri=" + formUri + ", assignee="
				+ assignee + ", subject=" + subject + ", processInstanceId="
				+ processInstanceId + ", processName=" + processName
				+ ", processKey=" + processKey + ", createTime=" + createTime
				+ ", nodeId=" + nodeId + ", nodeName=" + nodeName
				+ ", priority=" + priority + ", initiator=" + initiator + "]";
	}

}
