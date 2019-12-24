package com.pallycon.admin.api.service.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pallycon.admin.api.service.token.dao.JwtTokenDao;
import com.pallycon.admin.api.service.token.model.TokenRequest;
import com.pallycon.admin.api.service.token.model.TokenResponse;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import com.pallycon.admin.cmmn.util.Base64Encoder;
import com.pallycon.admin.cmmn.util.LogUtil;
import com.pallycon.admin.cmmn.util.SHAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Brown on 2019-08-01.
 */
@Service("jwtToken")
public class JwtTokenImpl implements JwtToken {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected Environment env;
    @Autowired
    private JwtTokenDao jwtTokenDao;

    @Override
    public void checkJwtToken(String jwt, String apiCode, LogUtil logUtil) throws ApiException{
        ErrorDto errorDto = new ErrorDto();
        checkValidate(jwt, apiCode, errorDto, logUtil);
    }

    /**
     * token format : header.payload.signature
     * payload format
     * payload.sub : QuesDSLSample
     * payload.aud : brown
     * payload.iss : Brown
     * payload.iat : yyyyMMddHHmmss
     * payload.userId : userId
     * @param token
     * @param apiCode
     * @param errorDto
     * @param logUtil
     * @return
     * @throws ApiException
     */
    private void checkValidate(String token, String apiCode, ErrorDto errorDto, LogUtil logUtil) throws ApiException{
        String body, header, payload, signature;
        TokenRequest tokenRequest;
        TokenResponse tokenResponse;
        ObjectMapper mapper = new ObjectMapper();
        try{
            logUtil.put("apiCode", apiCode);
            logUtil.put("token", token);

            body = token.split(JWT_PREFIX)[1];
            String[] jwtArr = body.split("\\.");
            header = jwtArr[0];
            payload = jwtArr[1];
            signature = jwtArr[2];

            tokenRequest = mapper.readValue(Base64Encoder.decode(payload), TokenRequest.class);
            tokenRequest.setApiCode(apiCode);
            logUtil.put("tokenRequest", tokenRequest);

        }catch (Exception e){
            e.printStackTrace();
            errorDto.setErrorCode("A9001");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("checkValidate", errorDto);

        }
        tokenResponse = jwtTokenDao.findByUserId(tokenRequest, errorDto);
        logUtil.put("tokenResponse", tokenResponse);

        if(null == tokenResponse.getToken()){
            errorDto.setErrorCode("A9006");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("checkValidate", errorDto);
        }

        checkPayload(tokenRequest, errorDto);
        checkSignature(header, payload, signature, tokenResponse.getToken(), errorDto);
    }

    private void checkSignature(String header, String payload, String signature, String token, ErrorDto errorDto) throws ApiException{
        StringBuffer sb = new StringBuffer();
        sb.append(header);
        sb.append(".");
        sb.append(payload);
        sb.append(token);
        String sig = SHAUtil.encrypt(sb.toString());
        if(!signature.equals(sig)){
            errorDto.setErrorCode("A9004");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("checkPayload", errorDto);
        }

    }

    private void checkPayload(TokenRequest tokenRequest, ErrorDto errorDto) throws ApiException{
        if( !tokenRequest.getAdmin()
                || !JWT_SUB.equals(tokenRequest.getSub())
                || !JWT_ISS.equals(tokenRequest.getIss())
                || !JWT_AUD.equals(tokenRequest.getAud()) ){
            errorDto.setErrorCode("A9002");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("checkPayload", errorDto);
        }

        Calendar calendar= Calendar.getInstance();
        calendar.add( Calendar.HOUR, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        TimeZone z = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(z);
        Long currentTime = Long.parseLong(dateFormat.format(calendar.getTime()));

        if(currentTime > tokenRequest.getIat()){
            errorDto.setErrorCode("A9003");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("checkPayload", errorDto);
        }
    }
}
