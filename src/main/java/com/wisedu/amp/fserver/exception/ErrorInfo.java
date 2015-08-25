package com.wisedu.amp.fserver.exception;

/**
 * 错误信息类
 * <p>
 * { "code" : 1234, "message" : "Something bad happened", "description" :
 * "More details about the error here" }
 * </p>
 * 
 * @author zengxianping
 *
 */
public class ErrorInfo {
	private String code;
	private String message;
	private String description;

	public ErrorInfo() {
	}

	public ErrorInfo(String code) {
		this.code = code;
	}

	public ErrorInfo(String code, String message) {
		this(code);
		this.message = message;
	}

	public ErrorInfo(String code, String message, String description) {
		this(code, message);
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ErrorInfo [code=" + code + ", message=" + message
				+ ", description=" + description + "]";
	}

}