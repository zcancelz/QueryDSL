package com.pallycon.admin.config.parameter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brown on 2019-04-16.
 */
@Configuration
@EnableWebMvc
public class ParamNameConfig implements WebMvcConfigurer {
    /**
     * Processor for annotation {@link ParamName}.
     *
     * @return ParamNameProcessor
     */
    @Bean
    protected ParamNameProcessor paramNameProcessor() {
        return new ParamNameProcessor();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof RequestMappingHandlerAdapter) {
                    RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
                    List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(adapter.getArgumentResolvers());
                    argumentResolvers.add(0, paramNameProcessor());
                    adapter.setArgumentResolvers(argumentResolvers);
                }
                return bean;
            }
        };
    }


}
