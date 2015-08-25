package com.wisedu.amp.fserver.web.restful;

/**
 * Generic class that represents an action to be performed on a resource. Should
 * be subclasses if additional action-parameters are required.
 * 
 * @author zengxianping
 *
 */
public class ActionRequest {
	/**
	 * 完成任务
	 */
	public static final String ACTION_COMPLETE = "complete";

	/**
	 * 退回任务
	 */
	public static final String ACTION_ROLLBACK = "rollback";
	private String action;

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}
}
