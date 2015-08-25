package com.wisedu.amp.fserver.web.restful.runtime.process;

import com.wisedu.amp.fserver.web.restful.OperationResponse;

/**
 * 启动流程实例响应体
 * 
 * @author ych
 *
 */
public class ProcessInstanceResponse extends OperationResponse {

	private String processInstanceId;// 流程实例编号

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Override
	public String toString() {
		return "ProcessInstanceResponse [processInstanceId="
				+ processInstanceId + "]";
	}

}
