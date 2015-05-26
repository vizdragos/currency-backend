package com.currency.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerErrorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerErrorHandler.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseMessage internalServerError(Exception e) {

		LOG.error("Failed to satisfy request: ", e);

		return ResponseMessage.builder()
				.withCode(ErrorMessageCode.INTERNAL_SERVER_ERROR.getCode())
				.withMessage(ErrorMessageCode.INTERNAL_SERVER_ERROR.getMessage())
				.build();
	}

}
