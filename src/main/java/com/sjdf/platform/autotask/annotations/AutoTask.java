package com.sjdf.platform.autotask.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在(? extends BaseAutoTask)对象中指定自动任务的类型
 * Created at 2012-07-20
 *
 * @author 王正伟
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoTask {
    /**
     * 自动任务类型
     *
     * @return 自动任务类型
     * @see com.sjdf.platform.dictionary.bean.AutoTaskType
     */
    long type() default 0;
}
