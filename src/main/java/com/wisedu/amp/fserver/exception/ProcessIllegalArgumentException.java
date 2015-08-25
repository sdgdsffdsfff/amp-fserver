package com.wisedu.amp.fserver.exception;

/**
 * 流程非法参数（参数不匹配异常）
 * @author zengxianping
 *
 */
public class ProcessIllegalArgumentException extends ProcessException {

	private static final long serialVersionUID = 1L;

	public ProcessIllegalArgumentException(String message) {
		super(message);
	}

}
