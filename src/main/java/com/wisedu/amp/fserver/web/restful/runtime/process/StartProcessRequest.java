package com.wisedu.amp.fserver.web.restful.runtime.process;

import java.util.Map;

/**
 * 流程启动请求体
 * 
 * @author ych
 *
 */
public class StartProcessRequest {

	/**
	 * 流程定义编号
	 */
	private String processKey;

	/**
	 * 业务关联键
	 */
	private String bizKey;

	/**
	 * 启动人
	 */
	private String initiator;

	/**
	 * 流程变量(持久化)
	 */
	private Map<String, Object> variables;

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	@Override
	public String toString() {
		return "StartProcessRequest [processKey=" + processKey + ", bizKey="
				+ bizKey + ", initiator=" + initiator + ", variables="
				+ variables + "]";
	}

}
