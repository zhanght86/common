package com.sjdf.platform.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at 2012-04-06
 * 一个bean对象的标识 其中name用于标识这个bean的形象性名称
 *
 * @author 王正伟
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanName {
    /** 形象性名称 */
    String name() default "";
}
