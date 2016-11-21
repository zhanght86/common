package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-06-11 16:46
 * 处理单元执行状态
 */
@Entity
@DiscriminatorValue("WORK_ORDER_EXECUTE_STATUS")
@BeanName(name = "处理单元执行状态")
public class WorkOrderExecuteStatus extends Dictionary {
    private static final long serialVersionUID = -6165438835816622678L;

    /** OK */
    @BeanAttrInfo(cnName = "OK")
    public static final long OK = 1;

    /** NG */
    @BeanAttrInfo(cnName = "NG")
    public static final long NG = 10;

    /** ALL */
    @BeanAttrInfo(cnName = "ALL")
    public static final long ALL = 20;

    /** 人工处理 */
    @BeanAttrInfo(cnName = "人工处理")
    public static final long MANUAL = 30;

    /** 程序内部异常 */
    @BeanAttrInfo(cnName = "程序内部异常")
    public static final long INTERNAL_EXCEPTION = 100;

    /** 自动回复并跳转 */
    @BeanAttrInfo(cnName = "自动回复并跳转")
    public static final long AUTOMATIC_JUMP = 101L;
}
