package com.pallycon.admin.config;

import com.pallycon.admin.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Brown on 2019-08-01.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JwtInterceptor jwtInterceptor;

    private static final String[] EXCLUDE_PATHS = {
            "/api/access", "/api/ping"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
