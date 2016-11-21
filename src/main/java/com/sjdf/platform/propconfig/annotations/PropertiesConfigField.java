package com.sjdf.platform.propconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2015-08-26
 * 属性配置字段注解
 *
 * @author wangpeng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropertiesConfigField {

    /** 字段的中文名称 */
    String value();
}
