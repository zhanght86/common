package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-12-14
 *
 * @author 黄远昌
 * @category HKIDC消息类型
 */
@Entity
@DiscriminatorValue("HKIDC_MSG_TYPE")
@BeanName(name = "HKIDC消息类型")
public class HkidcMsgType extends Dictionary {
    private static final long serialVersionUID = 4488559301036827053L;
    /** 域名转移消息 */
    @BeanAttrInfo(value = "1", cnName = "域名转移消息")
    public static final long TRANSFER_MSG = 1;
    /** 域名赎回通知 */
    @BeanAttrInfo(value = "2", cnName = "域名赎回通知")
    public static final long RGP_MSG = 2;
    /** 余额不足通知 */
    @BeanAttrInfo(value = "3", cnName = "余额不足通知")
    public static final long LOW_BALANCE_MSG = 3;
    /** 其他消息 */
    @BeanAttrInfo(value = "0", cnName = "其他消息")
    public static final long OTHER_MSG = 0;
}
