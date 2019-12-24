package com.pallycon.admin.api.service.token.dao;

import com.pallycon.admin.api.service.token.model.TokenRequest;
import com.pallycon.admin.api.service.token.model.TokenResponse;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;

/**
 * Created by Brown on 2019-08-01.
 */
public interface JwtTokenDao {
    TokenResponse findByUserId(TokenRequest tokenRequest, ErrorDto errorDto) throws ApiException;
}
