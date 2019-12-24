package com.pallycon.admin.config.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Brown on 2019-04-16.
 */
public class ParamNameProcessor extends ServletModelAttributeMethodProcessor {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private static final Map<Class<?>, Map<String, String>> PARAM_MAPPINGS_CACHE = new ConcurrentHashMap<>(256);

    public ParamNameProcessor() {
        super(false);
    }

//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(RequestParam.class)
//                && !BeanUtils.isSimpleProperty(parameter.getParameterType())
//                && Arrays.stream(parameter.getParameterType().getDeclaredFields())
//                .anyMatch(field -> field.getAnnotation(ParamName.class) != null);
//    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest nativeWebRequest) {
        Object target = binder.getTarget();
        Map<String, String> paramMappings = this.getParamMappings(target.getClass());
        ParamNameDataBinder paramNameDataBinder = new ParamNameDataBinder(target, binder.getObjectName(), paramMappings);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(paramNameDataBinder);
        super.bindRequestParameters(paramNameDataBinder, nativeWebRequest);
    }

    /**
     * Get param mappings.
     * Cache param mappings in memory.
     *
     * @param targetClass
     * @return {@link Map < String ,  String >}
     */
    private Map<String, String> getParamMappings(Class<?> targetClass) {
        if (PARAM_MAPPINGS_CACHE.containsKey(targetClass)) {
            return PARAM_MAPPINGS_CACHE.get(targetClass);
        }

        // 부모 클래스 속성도 set 하기위해 추가
        Field[] superFields = targetClass.getSuperclass().getDeclaredFields();
        Field[] fields = targetClass.getDeclaredFields();
        Field[] paramFields;
        if( 0 == superFields.length){
            paramFields = fields;
        }else{
            paramFields = new Field[superFields.length+fields.length];
            System.arraycopy(superFields, 0, paramFields, 0, superFields.length);
            System.arraycopy(paramFields, 0, paramFields, superFields.length, fields.length);
        }

        Map<String, String> paramMappings = new HashMap<>(32);
        for (Field field : paramFields) {
            ParamName paramName = field.getAnnotation(ParamName.class);
            if (paramName != null && !paramName.value().isEmpty()) {
                paramMappings.put(paramName.value(), field.getName());
            }
        }
        PARAM_MAPPINGS_CACHE.put(targetClass, paramMappings);
        return paramMappings;
    }
}
