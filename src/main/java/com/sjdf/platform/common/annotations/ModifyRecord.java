package com.sjdf.platform.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 变更记录(仅仅适用于没有携带任何参数的方法上)
 * User: ketqi
 * Date: 2015-10-14 17:43
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyRecord {
    /** 名称 */
    String name() default "";
}
