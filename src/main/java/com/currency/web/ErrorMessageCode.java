package com.currency.web;

public enum ErrorMessageCode {

	// 100 - 110 Generic errors
	INTERNAL_SERVER_ERROR("E100", "internal_server_error"),

	// 150 - 200 Validation errors
	SOME_VALIDATION_ERROR("V150", "some_validation_error");

	// etc

	private String code;
	private String message;

	private ErrorMessageCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
