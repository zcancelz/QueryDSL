package com.pallycon.admin.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pallycon.admin.api.service.model.ConsoleJwtRequest;
import com.pallycon.admin.api.service.token.JwtToken;
import com.pallycon.admin.api.template.ResponseRestTemplate;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import com.pallycon.admin.cmmn.util.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by Brown on 2019-06-28.
 */
public abstract class AbstractService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected Environment env;

    protected enum Method{
        POST("POST"),
        GET("GET"),
        PUT("PUT"),
        DELETE("DELETE");

        private String type;

        Method(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    protected enum apiResult{
        SUCCESS("Y"),
        FAIL("N");
        private String value;

        apiResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    protected void checkApiCode(String expectedApiCode, String actureApiCode, ErrorDto errorDto) throws ApiException{
        if(!expectedApiCode.equals(actureApiCode)){
            errorDto.setErrorCode("A1000");
            errorDto.setMessage(env.getProperty("A1000"));
            throw new ApiException("checkParameter", errorDto);
        }
    }
    protected void cretaeResponseFormat(ResponseRestTemplate responseRestTemplate, ErrorDto errorDto){
        responseRestTemplate.errorCode = errorDto.getErrorCode();
        responseRestTemplate.errorMessage = errorDto.getMessage();
    }
    protected void cretaeResponseFormat(ResponseRestTemplate responseRestTemplate, Object obj, ErrorDto errorDto){
        responseRestTemplate.errorCode = errorDto.getErrorCode();
        responseRestTemplate.errorMessage = errorDto.getMessage();
        responseRestTemplate.dataInfo = obj;
    }

    protected ConsoleJwtRequest parseJwt(String jwt, ErrorDto errorDto) throws ApiException{
        String body = jwt.split(JwtToken.JWT_PREFIX)[1];
        String[] jwtArr = body.split("\\.");
        String payload = jwtArr[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(Base64Encoder.decode(payload), ConsoleJwtRequest.class);
        }catch (Exception e){
            e.printStackTrace();
            errorDto.setErrorCode("A9013");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("parseJwt", errorDto);
        }
    }
}
