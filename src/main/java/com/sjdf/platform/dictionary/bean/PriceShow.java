package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-01-16
 *
 * @author 王正伟
 * @category 价格显示设定
 */
@Entity
@DiscriminatorValue("PRICE_SHOW")
@BeanName(name = "价格显示设定")
public class PriceShow extends Dictionary {
    private static final long serialVersionUID = -3265855991823068841L;

    /** 显示价格1 */
    @BeanAttrInfo(value = "13", cnName = "显示价格1")
    public static final long PRICE_SHOW1 = 1;

    /** 显示价格2 */
    @BeanAttrInfo(value = "14", cnName = "显示价格2")
    public static final long PRICE_SHOW2 = 2;

    /** 显示价格3 */
    @BeanAttrInfo(value = "15", cnName = "显示价格3")
    public static final long PRICE_SHOW3 = 3;

    /** 显示价格4 */
    @BeanAttrInfo(value = "16", cnName = "显示价格4")
    public static final long PRICE_SHOW4 = 4;
}
