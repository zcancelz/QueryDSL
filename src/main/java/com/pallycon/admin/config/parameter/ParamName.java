package com.pallycon.admin.config.parameter;

import java.lang.annotation.*;

/**
 * Created by Brown on 2019-04-16.
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamName {
    String value();
}
