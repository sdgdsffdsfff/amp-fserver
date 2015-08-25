package com.wisedu.amp.fserver.exception;

/**
 * 访问令牌已经生成异常
 * 
 * @author tives
 *
 */
public class AccessTokenAlreadyGeneratedException extends ProcessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessTokenAlreadyGeneratedException(String message) {
		super(message);
	}

}
