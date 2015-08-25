package com.wisedu.amp.fserver.exception;

/**
 * Runtime exception that is the superclass of all process exceptions.
 * 
 * 
 * @author zengxianping
 *
 */
public class ProcessAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProcessAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessAccessException(String message) {
		super(message);
	}
}
