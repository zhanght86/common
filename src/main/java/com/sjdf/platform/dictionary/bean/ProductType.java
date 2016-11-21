package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 产品类型
 */
@Entity
@DiscriminatorValue("PRODUCT_TYPE")
@BeanName(name = "产品类型")
public class ProductType extends Dictionary {
    private static final long serialVersionUID = -6168652097156496407L;
    /** 购买 */
    @BeanAttrInfo(cnName = "购买")
    public static final long BUY = 1;
    /** 试用 */
    @BeanAttrInfo(cnName = "试用")
    public static final long TRIAL = 2;
    /** 赠送 */
    @BeanAttrInfo(cnName = "赠送")
    public static final long GIFT = 3;

    /** 转移注册商（入） */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, cnName = "转移注册商（入）")
    public static final long TRANSFER_IN = 4;

    /** 转移注册商（出） */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, cnName = "转移注册商（出）")
    public static final long TRANSFER_OUT = 5;

    /** 仅使用我司DNS */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, cnName = "仅使用DNS")
    public static final long USE_DEFAULT_DNS = 6;

    /** 赎回 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, cnName = "赎回")
    public static final long RANSOM = 7;
}
