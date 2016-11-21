package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-03-23 09:36
 */
@Entity
@DiscriminatorValue("DOMAIN_CLASS")
@BeanName(name = "域名大分类")
public class DomainClass extends Dictionary {
    private static final long serialVersionUID = -4080369565315612842L;

    @BeanAttrInfo(cnName = "国内域名")
    public static final long LOCATION_DOMIAN = 1;

    @BeanAttrInfo(cnName = "国际域名")
    public static final long INTERNATIONAL_DOMAIN = 2;

    @BeanAttrInfo(cnName = "所有")
    public static final long ALL_DOMAIN = 99;
}
