package com.pallycon.admin.api.service.token.dao;

import com.pallycon.admin.api.entity.repository.r.ReadJwtRepository;
import com.pallycon.admin.api.service.token.model.TokenRequest;
import com.pallycon.admin.api.service.token.model.TokenResponse;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Brown on 2019-08-01.
 */
@Service("jwtTokenDao")
public class JwtTokenDaoImpl implements JwtTokenDao{

    @Autowired
    ReadJwtRepository readJwtRepository;

    @Autowired
    Environment env;

    @Override
    public TokenResponse findByUserId(TokenRequest tokenRequest, ErrorDto errorDto) throws ApiException{
        TokenResponse tokenResponse;
        try {
            tokenResponse = readJwtRepository.findByUserId(tokenRequest);
        }catch (Exception e){
            errorDto.setErrorCode("A9005");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("findByUserId", errorDto);
        }
        return tokenResponse;
    }
}
