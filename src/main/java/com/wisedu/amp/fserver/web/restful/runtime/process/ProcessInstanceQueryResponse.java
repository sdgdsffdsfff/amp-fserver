package com.wisedu.amp.fserver.web.restful.runtime.process;

import java.util.Date;

/**
 * 流程实例查询响应体
 * 
 * @author MAENLIANG
 *
 */
public class ProcessInstanceQueryResponse {

	/**
	 * 应用编号
	 */
	private String appId;
	/**
	 * 表单相对URI
	 */
	private String formUri;
	/**
	 * 流程主题
	 */
	private String subject;

	/**
	 * 流程发起人
	 */
	private String startAuthor;
	/**
	 * 流程实例编号
	 */
	private String processInstanceId;
	/**
	 * 流程名称
	 */
	private String processName;
	/**
	 * 流程唯一键
	 */
	private String processKey;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 流程上次更新时间
	 */
	private Date updateTime;

	/**
	 * 流程当前所在节点信息
	 */
	private String processLocation;
	/**
	 * 流程状态
	 */
	private String instanceStatus;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getFormUri() {
		return formUri;
	}

	public void setFormUri(String formUri) {
		this.formUri = formUri;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStartAuthor() {
		return startAuthor;
	}

	public void setStartAuthor(String startAuthor) {
		this.startAuthor = startAuthor;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProcessLocation() {
		return processLocation;
	}

	public void setProcessLocation(String processLocation) {
		this.processLocation = processLocation;
	}

	public String getInstanceStatus() {
		return instanceStatus;
	}

	public void setInstanceStatus(String instanceStatus) {
		this.instanceStatus = instanceStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProcessInstanceQueryResponse [appId=" + appId + ", formUri="
				+ formUri + ", subject=" + subject + ", processInstanceId="
				+ processInstanceId + ", processName=" + processName
				+ ", processKey=" + processKey + ", createTime=" + createTime
				+ "]";
	}

}
