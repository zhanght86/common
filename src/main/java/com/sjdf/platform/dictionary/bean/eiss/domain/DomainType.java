package com.sjdf.platform.dictionary.bean.eiss.domain;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 域名分类
 */
@Entity
@DiscriminatorValue("DOMAIN_TYPE")
@BeanName(name = "域名分类")
public class DomainType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 2528014011710648042L;

    /** 国内域名 */
    @BeanAttrInfo(value = "国内域名", cnName = "国内域名")
    public static final long TYPE = 1L;


}
