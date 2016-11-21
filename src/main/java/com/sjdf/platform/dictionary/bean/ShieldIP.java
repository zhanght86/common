package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-01
 *
 * @author 黄远昌
 * @category 屏蔽IP
 */
@Entity
@DiscriminatorValue("SHIELD_IP")
@BeanName(name = "屏蔽IP")
public class ShieldIP extends Dictionary {
    private static final long serialVersionUID = -3730182188420527772L;
    /** 公司IP */
    @BeanAttrInfo(value = "182.148.112.78", cnName = "公司IP")
    public static final long COMPANY_IP = 1;
    /** VPN IP */
    @BeanAttrInfo(value = "125.65.112.58", cnName = "VPN IP")
    public static final long VPN_IP = 2;
}
