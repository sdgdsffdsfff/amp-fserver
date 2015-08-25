package com.wisedu.amp.fserver.web.restful.runtime.task;

import java.util.Date;
import java.util.List;

import com.wisedu.amp.fserver.web.restful.PageRequest;

/**
 * 查询待办任务请求体
 * <p>
 * 人工任务的任务分配
 * </p>
 * <ol>
 * <li><b>选择用户</b>：使用场景一般为比较复杂的任务处理人计算，可以由应用计算好之后通过变量的形式传递给流程</li>
 * <li><b>选择用户组</b>：由应用通过getGroupsByRoleId(String roleId)的服务计算好之后传递给流程引擎</li>
 * <li>
 * <b>选择属性&选择用户组</b>：如（计算机学院&院系管理员），多用于以下场景：数据范围过滤，如学生请假，需要由学生所在班级的班主任用户组进行审批
 * ，这里的属性可选字段有（班级、院系）。</li>
 * <li><b>提交人</b>：使用场景：流程中有环节是需要提交人办理的，如“销假、上交报销单、变更信息确认”等</li>
 * </ol>
 * 
 * @author zengxianping
 *
 */
public class QueryTaskRequest extends PageRequest {

	/**
	 * 任务处理人
	 */
	private String assignee;

	/**
	 * 用户组编号集合
	 */
	private List<String> groupIds;

	// //////////////人工任务任务分配：选择属性&选择用户组////////////////////////

	/**
	 * 院系编号
	 */
	private String deptId;
	/**
	 * 班级编号
	 */
	private String classId;

	// /////////////////////////////////////////////////////////////////////

	/**
	 * 流程定义编号
	 */
	private String processKey;
	/**
	 * 流程实例编号
	 */
	private String processInstanceId;

	/**
	 * 任务启动人
	 */
	private String initiator;

	/**
	 * 任务主题模糊查询
	 */
	private String subjectLike;
	/**
	 * 节点编号
	 */
	private String nodeId;
	/**
	 * 节点名模糊查询
	 */
	private String nodeNameLike;
	/**
	 * 流程名模糊查询
	 */
	private String processNameLike;
	/**
	 * 创建开始时间
	 */
	private Date createTimeStart;
	/**
	 * 创建结束时间
	 */
	private Date createTimeEnd;

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeNameLike() {
		return nodeNameLike;
	}

	public void setNodeNameLike(String nodeNameLike) {
		this.nodeNameLike = nodeNameLike;
	}

	public String getProcessNameLike() {
		return processNameLike;
	}

	public void setProcessNameLike(String processNameLike) {
		this.processNameLike = processNameLike;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryTaskRequest [assignee=" + assignee + ", groupIds="
				+ groupIds + ", deptId=" + deptId + ", classId=" + classId
				+ ", processKey=" + processKey + ", processInstanceId="
				+ processInstanceId + ", initiator=" + initiator + ", nodeId="
				+ nodeId + ", nodeNameLike=" + nodeNameLike
				+ ", processNameLike=" + processNameLike + ", createTimeStart="
				+ createTimeStart + ", createTimeEnd=" + createTimeEnd + "]";
	}

}
