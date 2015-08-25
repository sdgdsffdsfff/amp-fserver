package com.wisedu.amp.fserver.exception;

/**
 * 流程媒体类型不支持异常
 * @author zengxianping
 *
 */
public class ProcessContentNotSupportedException extends ProcessAccessException {
	private static final long serialVersionUID = 1L;

	public ProcessContentNotSupportedException(String message) {
		super(message);
	}
}