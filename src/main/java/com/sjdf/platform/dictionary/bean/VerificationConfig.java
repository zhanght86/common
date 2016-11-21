package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-06-05
 *
 * @author 陈绍刚
 * @category 验证码相关配置
 */
@Entity
@DiscriminatorValue("VERIFICATION_CONFIG")
@BeanName(name = "验证码相关配置")
public class VerificationConfig extends Dictionary {

    private static final long serialVersionUID = -6906369503167085354L;

    /** 验证码失效时间 */
    @BeanAttrInfo(value = "30", cnName = "验证码失效时间（分钟）")
    public static final long EXPIRES_IN = 1;

    /** 短信发送最大次数 */
    @BeanAttrInfo(value = "5", cnName = "短信发送最大次数")
    public static final long SMS_SEND_MAX_TIMES = 2;

    /** 邮件发送最大次数 */
    @BeanAttrInfo(value = "5", cnName = "邮件发送最大次数")
    public static final long EMAIL_SEND_MAX_TIMES = 3;

    /** 错误最大次数 */
    @BeanAttrInfo(value = "10", cnName = "错误最大次数")
    public static final long ERROR_MAX_TIMES = 4;

    @BeanAttrInfo(value = "验证码当天有效，如5分钟后仍无法获取请联系我们", cnName = "验证码提示")
    public static final long TIP_MESSAGE = 6;

    /**
     * @category 以下为网建的限制
     */
    @BeanAttrInfo(value = "1", cnName = "同号码1分钟发送频率限制")
    public static final long WJ_LIMIT_ON_ONE_MINUTE = 11;

    @BeanAttrInfo(value = "9", cnName = "同号码30分钟发送频率限制")
    public static final long WJ_LIMIT_ON_THIRTY_MINUTES = 12;

    @BeanAttrInfo(value = "30", cnName = "同号码1天发送频率限制")
    public static final long WJ_LIMIT_ON_ONE_DAY = 13;

    /**
     * @category 超过网建限制对应提示信息
     */
    @BeanAttrInfo(value = "1分钟最多只能获取一次验证码", cnName = "1分钟超限提示")
    public static final long ONE_MINUTE_TIP = 100;

    @BeanAttrInfo(value = "30分钟最多只能获取9次验证码", cnName = "30分钟超限提示")
    public static final long THIRTY_MINUTES_TIP = 101;

    @BeanAttrInfo(value = "1天最多只能获取30次验证码", cnName = "1天超限提示")
    public static final long ONE_DAY_TIP = 102;
}