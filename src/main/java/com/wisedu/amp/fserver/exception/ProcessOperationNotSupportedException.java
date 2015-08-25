package com.wisedu.amp.fserver.exception;

/**
 * 流程操作不支持异常
 * @author zengxianping
 *
 */
public class ProcessOperationNotSupportedException extends ProcessAccessException {
	private static final long serialVersionUID = 1L;

	public ProcessOperationNotSupportedException(String message) {
		super(message);
	}
}
