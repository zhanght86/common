package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-06
 *
 * @category 付款类型
 * @author 黄远昌
 */
@Entity
@DiscriminatorValue("PAYMENT_TYPE")
@BeanName(name = "付款类型")
public class PaymentType extends Dictionary {
    private static final long serialVersionUID = 7611922747877503904L;
    /** 不限制 */
    @BeanAttrInfo(value = "0", cnName = "不限制 ")
    public static final long NOTLIMIT = 0;
    /** 月付 */
    @BeanAttrInfo(value = "3", cnName = "月付 ")
    public static final long MONTH = 3;
    /** 季付 */
    @BeanAttrInfo(value = "4", cnName = "季付 ")
    public static final long QUARTER = 4;
    /** 半年付 */
    @BeanAttrInfo(value = "5", cnName = "半年付 ")
    public static final long HALF_YEAR = 5;
    /** 年付 */
    @BeanAttrInfo(value = "6", cnName = "年付 ")
    public static final long YEAR = 6;
}
