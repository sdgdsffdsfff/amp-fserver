package com.wisedu.amp.fserver.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 错误信息类
 * 
 * @author zengxianping
 *
 */
public class ErrorInfo {

	private String message;
	private String exception;

	public ErrorInfo(String message, Exception ex) {
		this.message = message;
		if (ex != null) {
			this.exception = ex.getLocalizedMessage();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@JsonInclude(Include.NON_NULL)
	public String getException() {
		return exception;
	}

	@Override
	public String toString() {
		return "ErrorInfo [message=" + message + ", exception=" + exception
				+ "]";
	}

}