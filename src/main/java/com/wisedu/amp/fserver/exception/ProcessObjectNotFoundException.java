package com.wisedu.amp.fserver.exception;

/**
 * 流程对象找不到异常
 * @author zengxianping
 *
 */
public class ProcessObjectNotFoundException extends ProcessAccessException {
	private static final long serialVersionUID = 1L;

	public ProcessObjectNotFoundException(String message) {
		super(message);
	}

}
