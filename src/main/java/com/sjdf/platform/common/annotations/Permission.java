package com.sjdf.platform.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解, 用于初始化权限信息
 * User: ketqi
 * Date: 2013-04-15 15:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    /** 权限主键 */
    String code();

    /** 是否验证 */
    boolean valid() default true;

    /** 依附的权限主键 */
    String supportedCode() default "";

    /** 权限名称 */
    String name();

    /** 请求路径 */
    String url() default "";

    /** 是否是菜单 */
    boolean menu() default false;

    /** 顺序 */
    int orderBy() default 0;
}
