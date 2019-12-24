package com.pallycon.admin.cmmn.exception;

import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import com.pallycon.admin.cmmn.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiException extends Exception {

	private static final long serialVersionUID = 951651037317174970L;

	public static Logger logger = LoggerFactory.getLogger(ApiException.class);

	private ErrorDto errorDto;
	private String function;

	public ApiException(String function, ErrorDto errorDto ){
		this.errorDto = errorDto;
		this.function = function;

		logger.error("{\"function\":\"" + function + "\""
					+ ", \"ERRORDTO\": " + StringUtil.objectToJson(errorDto) + "}");
		makeErrorBody(errorDto);
	}

	public ApiException(String function, ErrorDto errorDto, String uniqueKey){
		this.errorDto = errorDto;
		this.function = function;

		logger.error("{\"uniqueKey\":\"" + uniqueKey + "\""
					+ ", \"function\":\"" + function + "\""
					+ ", \"ERRORDTO\": " + StringUtil.objectToJson(errorDto) + "}");
		makeErrorBody(errorDto);
	}
	
	
	@Override
	public String getMessage(){
		return errorDto.getBody();
	}

	public String getErrorCode(){
		return this.errorDto.getErrorCode();
	}

	public String getErrorMessage(){
		return this.errorDto.getMessage();
	}

	public ErrorDto getErrorDto() {
		return errorDto;
	}

	public void setErrorDto(ErrorDto errorDto) {
		this.errorDto = errorDto;
	}

	public void makeErrorBody(ErrorDto errorDto){
		errorDto.setBody(StringUtil.objectToJson(errorDto));
	}
	
}
