package com.wisedu.amp.fserver.exception;

/**
 * 流程禁止访问异常
 * @author zengxianping
 *
 */
public class ProcessForbiddenException extends ProcessAccessException {
	private static final long serialVersionUID = 1L;

	public ProcessForbiddenException(String message) {
		super(message);
	}
}
