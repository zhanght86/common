package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-28
 *
 * @author 王正伟
 * @category 日志记录类型
 */
@Entity
@DiscriminatorValue("LOG_TYPE")
@BeanName(name = "日志记录类型")
public class LogType extends Dictionary {
    private static final long serialVersionUID = -6167705507167581959L;

    /** 操作日志; */
    @BeanAttrInfo(value = "info", cnName = "操作日志")
    public static final long INFO = 1;

    /** 错误(异常)日志 */
    @BeanAttrInfo(value = "error", cnName = "异常日志")
    public static final long ERROR = 2;
}
