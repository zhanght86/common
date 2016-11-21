package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 消息类型
 * User: ketqi
 * Date: 2015-07-01 11:20
 */
@Entity
@DiscriminatorValue("MESSAGE_TYPE")
@BeanName(name = "消息类型")
public class MessageType extends Dictionary {
    private static final long serialVersionUID = 334483378596716629L;

    /** 短信 */
    @BeanAttrInfo(cnName = "短信")
    public static final long SMS = 1L;

    /** 邮件 */
    @BeanAttrInfo(cnName = "邮件")
    public static final long EMAIL = 5L;

    /** 微信 */
    @BeanAttrInfo(value = "10", cnName = "微信", enName = "wechat")
    public static final long WECHAT = 10;
}
