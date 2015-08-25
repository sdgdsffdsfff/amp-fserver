package com.wisedu.amp.fserver.exception;

/**
 * 流程冲突异常
 * @author zengxianping
 *
 */
public class ProcessConflictException extends ProcessAccessException {
	private static final long serialVersionUID = 1L;

	public ProcessConflictException(String message) {
		super(message);
	}
}
