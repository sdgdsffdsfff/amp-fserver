package com.wisedu.amp.fserver.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一的异常处理
 * 
 * @author zengxianping
 *
 */
@ControllerAdvice
@ResponseStatus(HttpStatus.OK)
public class ExceptionHandlerAdvice {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(ProcessConflictException.class)
	@ResponseBody
	public ErrorInfo handleConflict(ProcessConflictException e) {
		return new ErrorInfo("409", "conflict", e.getMessage());
	}

	@ExceptionHandler(ProcessTaskAlreadyClaimedException.class)
	@ResponseBody
	public ErrorInfo handleTaskAlreadyClaimed(
			ProcessTaskAlreadyClaimedException e) {
		return new ErrorInfo("409", "Task was already claimed", e.getMessage());
	}

	@ExceptionHandler(AccessTokenAlreadyGeneratedException.class)
	@ResponseBody
	public ErrorInfo accessTokenAlreadyGenerated(
			AccessTokenAlreadyGeneratedException e) {
		return new ErrorInfo("409", "accessToken was already generated",
				e.getMessage());
	}

	@ExceptionHandler(ProcessObjectNotFoundException.class)
	@ResponseBody
	public ErrorInfo handleNotFound(ProcessObjectNotFoundException e) {
		return new ErrorInfo("404", "Not found", e.getMessage());
	}

	@ExceptionHandler(ProcessForbiddenException.class)
	@ResponseBody
	public ErrorInfo handleForbidden(ProcessForbiddenException e) {
		return new ErrorInfo("403", "Forbidden", e.getMessage());
	}

	@ExceptionHandler(ProcessIllegalArgumentException.class)
	@ResponseBody
	public ErrorInfo handleIllegal(ProcessIllegalArgumentException e) {
		return new ErrorInfo("400", "Bad request", e.getMessage());
	}

	@ExceptionHandler(ProcessOperationNotSupportedException.class)
	@ResponseBody
	public ErrorInfo handleNotSupportedOperation(
			ProcessOperationNotSupportedException e) {
		return new ErrorInfo("400", "Bad request", e.getMessage());
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseBody
	public ErrorInfo handleBadMessageConversion(HttpMessageConversionException e) {
		return new ErrorInfo("400", "Bad request", e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorInfo handleOtherException(Exception e) {
		LOGGER.error("Unhandled exception", e);
		return new ErrorInfo("500", "Internal server error", e.getMessage());
	}

}
