package com.wisedu.amp.fserver.exception;

/**
 * Runtime exception that is the superclass of all process exceptions.
 * 
 * 
 * @author zengxianping
 *
 */
public class ProcessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessException(String message) {
		super(message);
	}
}
