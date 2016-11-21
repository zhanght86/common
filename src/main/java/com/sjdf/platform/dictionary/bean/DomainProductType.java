package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-01
 *
 * @author 王正伟
 * @category 域名产品类型
 */
@Entity
@DiscriminatorValue("DOMAIN_PRODUCT_TYPE")
@BeanName(name = "域名产品类型")
public class DomainProductType extends Dictionary {
    private static final long serialVersionUID = 1075750902608438163L;

    /** 购买 */
    @BeanAttrInfo(value = "1", cnName = "购买", enName = "buy")
    public static final long BUY = 1;

    /** 试用 */
    @BeanAttrInfo(value = "2", cnName = "试用", enName = "try use")
    public static final long TRY_USE = 2;

    /** 赠送 */
    @BeanAttrInfo(value = "3", cnName = "赠送", enName = "present")
    public static final long PRESENT = 3;

    /** 转移注册商（入） */
    @BeanAttrInfo(value = "4", cnName = "转移注册商（入）", enName = "transfer in")
    public static final long TRANSFER_IN = 4;

    /** 转移注册商（出） */
    @BeanAttrInfo(value = "5", cnName = "转移注册商（出）", enName = "transfer out")
    public static final long TRANSFER_OUT = 5;

    /** 仅使用我司DNS */
    @BeanAttrInfo(value = "6", cnName = "仅使用DNS", enName = "default dns")
    public static final long DEFAULT_DNS = 6;

    /** 赎回期 */
    @BeanAttrInfo(value = "7", cnName = "赎回", enName = "ransom")
    public static final long RANSOM = 7;
}
