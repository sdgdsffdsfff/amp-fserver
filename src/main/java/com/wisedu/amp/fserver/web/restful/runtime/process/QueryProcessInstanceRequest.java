package com.wisedu.amp.fserver.web.restful.runtime.process;

import java.util.Date;

import com.wisedu.amp.fserver.web.restful.PageRequest;

/**
 * 流程实例查询请求体
 * 
 * @author ych
 *
 */
public class QueryProcessInstanceRequest extends PageRequest {

	/**
	 * 流程编号
	 */
	private String processKey;

	/**
	 * 创建开始时间
	 */
	private Date createTimeStart;
	/**
	 * 创建结束时间
	 */
	private Date createTimeEnd;
	/**
	 * 流程名模糊查询
	 */
	private String processNameLike;
	/**
	 * 流程实例编号
	 */
	private String processInstanceId;
	/**
	 * 流程提交人
	 */
	private String initiator;

	/**
	 * 任务主题模糊查询
	 */
	private String subjectLike;

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getProcessNameLike() {
		return processNameLike;
	}

	public void setProcessNameLike(String processNameLike) {
		this.processNameLike = processNameLike;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getSubjectLike() {
		return subjectLike;
	}

	public void setSubjectLike(String subjectLike) {
		this.subjectLike = subjectLike;
	}

	@Override
	public String toString() {
		return "QueryProcessInstanceRequest [processKey=" + processKey
				+ ", createTimeStart=" + createTimeStart + ", createTimeEnd="
				+ createTimeEnd + ", processNameLike=" + processNameLike
				+ ", processInstanceId=" + processInstanceId + ", initiator="
				+ initiator + ", subjectLike=" + subjectLike + "]";
	}

}
