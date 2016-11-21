package com.sjdf.platform.propconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2015-08-26
 * 属性配置类注解
 *
 * @author wangpeng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropertiesConfigType {

    /** 页面展示名称 */
    String name();

    /** 数据库保存名称 */
    String value();
}
