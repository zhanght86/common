package com.sjdf.platform.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用AjaxSupport时,
 *
 * @author ketqi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Json {
    String name() default "";

    boolean excluded() default false;

    String format() default "";
}
