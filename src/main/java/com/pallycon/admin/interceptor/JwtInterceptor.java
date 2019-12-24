package com.pallycon.admin.interceptor;

import com.pallycon.admin.api.service.token.JwtToken;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import com.pallycon.admin.cmmn.util.LogUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by Brown on 2019-08-01.
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "jwtToken")
    JwtToken jwtToken;
    @Autowired
    protected Environment env;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogUtil logUtil = new LogUtil();

        try{
            String jwt = Optional.ofNullable( request.getHeader("Authorization") )
                    .orElseThrow( () -> new ApiException(
                            "checkAuth", new ErrorDto("A9000", env.getProperty("A9000") ) )
                    );
            String apiCode = Optional.ofNullable(request.getParameter("api_code")).orElse("");
            jwtToken.checkJwtToken(jwt, apiCode, logUtil);
        }catch (ApiException e){
            logger.info(logUtil.toLogString());
            response.getWriter().write(e.getMessage());
            response.setStatus(HttpStatus.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

}
