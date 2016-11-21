package com.sjdf.platform.dictionary.annotations;

import com.sjdf.platform.dictionary.bean.Dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Dictionary对象中标示一个属性的值
 * Created at 2012-05-30
 *
 * @author 王正伟
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanAttrInfo {
    /** 对应Dictionary对象中value */
    String value() default "";

    /** 排序 */
    long orderBy() default 0;

    /** 对应Dictionary对象中cnName */
    String cnName() default "";

    /** 对应Dictionary对象中enName;如果为空,默认使用attr值 */
    String enName() default "";

    /** 引用的Class */
    Class<? extends Dictionary> refClass() default Dictionary.class;

    /** 引用的Class所对应的属性 */
    long refAttr() default 0;

    /** 所属系统类型 */
    long systemType() default 0;
}
