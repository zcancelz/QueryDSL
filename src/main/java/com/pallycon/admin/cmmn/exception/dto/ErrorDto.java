package com.pallycon.admin.cmmn.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto {
	@JsonProperty("error_code")
	String errorCode;
	@JsonProperty("message")
	String message;
	@JsonIgnore
	String body;

	public ErrorDto(){
		super();
	}

	public ErrorDto(String errorCode, String message){
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
