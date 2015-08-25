package com.wisedu.amp.fserver.web.restful;

/**
 * 操作响应实体
 * 
 * @author zengxianping
 *
 */
public class OperationResponse {
	/**
	 * 操作码，用户事务补偿
	 */
	private String operationId;

	/**
	 * 流程当前状态
	 */
	private String processLocation;

	public OperationResponse() {
	}

	public OperationResponse(String operationId) {
		this.operationId = operationId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getProcessLocation() {
		return processLocation;
	}

	public void setProcessLocation(String processLocation) {
		this.processLocation = processLocation;
	}

	@Override
	public String toString() {
		return "OperationResponse [operationId=" + operationId
				+ ", processLocation=" + processLocation + "]";
	}

}
