package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-05-25
 *
 * @author 黄远昌
 * @category 购买方式
 */
@Entity
@DiscriminatorValue("BUY_MODE")
@BeanName(name = "购买方式")
public class BuyMode extends Dictionary {
    private static final long serialVersionUID = 1456059539489288142L;
    /** 主站购买 */
    @BeanAttrInfo(value = "1", cnName = "主站购买")
    public static final long LOCAL = 1;
    /** 代理购买 */
    @BeanAttrInfo(value = "2", cnName = "代理购买")
    public static final long AGENT = 2;
}
