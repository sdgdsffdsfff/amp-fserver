package com.wisedu.amp.fserver.exception;

/**
 * 流程任务已被执行异常
 * @author zengxianping
 *
 */
public class ProcessTaskAlreadyClaimedException extends ProcessAccessException {

	private static final long serialVersionUID = 1L;

	public ProcessTaskAlreadyClaimedException(String message) {
		super(message);
	}

}
