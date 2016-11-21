package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-05-27 15:44
 * 工单回复类型(客户回复，自动回复,检测后自动回复,超管回复)
 */
@Entity
@DiscriminatorValue("WORK_ORDER_REPLY_TYPE")
@BeanName(name = "工单回复类型")
public class WorkOrderReplyType extends Dictionary {
    private static final long serialVersionUID = -8204320788242147970L;

    /** 客户提问 */
    @BeanAttrInfo(cnName = "客户提问")
    public static final long USER_QUESTION = 1L;

    /** 客户回复 */
    @BeanAttrInfo(cnName = "客户回复")
    public static final long ASK = 5L;

    /** 自动回复 */
    @BeanAttrInfo(cnName = "自动回复")
    public static final long AUTOMATIC = 10L;

    /** 突发事件的智能回复 */
    @BeanAttrInfo(cnName = "智能回复")
    public static final long EMERAICE = 11L;

    /** 检测后自动回复 */
    @BeanAttrInfo(cnName = "检测后自动回复")
    public static final long CHECK_AUTOMATIC = 15L;

    /** 超管回复 */
    @BeanAttrInfo(cnName = "超管回复")
    public static final long MANAGER_REPLY = 20L;

    /** 自动回复并跳转 */
    @BeanAttrInfo(cnName = "自动回复并跳转")
    public static final long AUTOMATIC_JUMP = 25L;


    /** 工单跟踪 */
    @BeanAttrInfo(cnName = "工单跟踪")
    public static final long WORKORDER_TRACK = 30L;
}
