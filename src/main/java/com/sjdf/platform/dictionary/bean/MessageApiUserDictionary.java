package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.message.constant.EmailNoticeType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-10-16
 *
 * @author wangpeng
 * @category 不同的邮件类型设置不同的授权用户
 */
@Entity
@DiscriminatorValue("MESSAGE_API_USER")
@BeanName(name = "授权用户账户")
public class MessageApiUserDictionary extends Dictionary {

    private static final long serialVersionUID = 4477959671232865264L;

    /**
     * @category 发送业务通知使用的授权用户
     */
    @BeanAttrInfo(value = "username;password", cnName = "业务通道授权用户")
    public static final long BUSINESS_MESSAGE_USER = EmailNoticeType.BUSINESS_NOTICE;

    /**
     * @category 发送广告使用的授权用户
     */
    @BeanAttrInfo(value = "username;password", cnName = "广告通道授权用户")
    public static final long AD_MESSAGE_USER = EmailNoticeType.ADVERTISEMENT_NOTICE;

    /**
     * @category 发送验证码使用的授权用户
     */
    @BeanAttrInfo(value = "username;password", cnName = "验证码通道授权用户")
    public static final long VC_MESSAGE_USER = EmailNoticeType.VER_CODE_NOTICE;
}
