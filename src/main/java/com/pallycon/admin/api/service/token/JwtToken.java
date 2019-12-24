package com.pallycon.admin.api.service.token;

import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.util.LogUtil;

/**
 * Created by Brown on 2019-08-01.
 */
public interface JwtToken {
    String JWT_PREFIX="Bearer ";
    String JWT_AUD="brown";
    String JWT_SUB="QuesDSLSample";
    String JWT_ISS="Brown";
    String JWT_HEADER="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.";

    void checkJwtToken(String jwt, String apiCode, LogUtil logUtil) throws ApiException;
}
