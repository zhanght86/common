package com.sjdf.platform.dictionary.bean.eiss.domain;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 域名购买类型
 */
@Entity
@DiscriminatorValue("DOMAIN_BUY_TYPE")
@BeanName(name = "域名购买类型")
public class DomainBuyType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 2528014011710648042L;

    /** 国内域名 */
    @BeanAttrInfo(value = "1", cnName = "国内域名")
    public static final long CN = 1L;

    /** 国际域名 */
    @BeanAttrInfo(value = "2", cnName = "国际域名")
    public static final long EN = 2L;

    /** 通用网址 */
    @BeanAttrInfo(value = "3", cnName = "通用网址")
    public static final long GENERAL_WEB = 3L;

}
