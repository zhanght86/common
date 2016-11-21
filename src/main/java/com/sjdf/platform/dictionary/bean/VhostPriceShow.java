package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2013-06-26
 *
 * @author 陈绍刚
 * @category 虚拟主机价格显示配置
 */
@Entity
@DiscriminatorValue("VHOST_PRICE_SHOW")
@BeanName(name = "虚拟主机价格显示配置")
public class VhostPriceShow extends Dictionary {

    private static final long serialVersionUID = 1L;
    // PriceUnit + PriceType
    /** 1单位1年 */
    @BeanAttrInfo(value = "16", cnName = "1单位1年")
    public static final long UNIT1 = 1;
    /** 2单位1年 */
    @BeanAttrInfo(value = "26", cnName = "2单位1年")
    public static final long UNIT2 = 2;
    /** 3单位1年 */
    @BeanAttrInfo(value = "36", cnName = "3单位1年")
    public static final long UNIT3 = 3;
    /** 4单位1年 */
    @BeanAttrInfo(value = "46", cnName = "4单位1年")
    public static final long UNIT4 = 4;
    /** 5单位1年 */
    @BeanAttrInfo(value = "56", cnName = "5单位1年")
    public static final long UNIT5 = 5;
    /** 6单位1年 */
    @BeanAttrInfo(value = "66", cnName = "6单位1年")
    public static final long UNIT6 = 6;

}
