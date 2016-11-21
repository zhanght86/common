package com.sjdf.platform.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: ketqi
 * Date: 2014-05-21 13:22
 * 条件限制标记:sql中的查询约束符号
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestrictionMark {

    /** 是否禁用该查询属性 */
    boolean disabled() default false;

    /**
     * 限制符号
     *
     * @see com.sjdf.platform.common.annotations.Operator
     */
    Operator operator() default Operator.EQ;
}
