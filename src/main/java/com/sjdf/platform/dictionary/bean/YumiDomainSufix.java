package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * 
 * 2016-02-18
 * @category 玉米网合作相关配置
 * @author wangpeng
 *
 */
@Entity
@DiscriminatorValue("YUMI_DOMAIN_SUFIX")
@BeanName(name = "一口价域名后缀")
public class YumiDomainSufix extends Dictionary{

    private static final long serialVersionUID = -7301722048714935376L;

    @BeanAttrInfo(value = "全部", cnName = "全部", enName = "all")
    public static final long ALL = 0;

    @BeanAttrInfo(value = ".com", cnName = ".com", enName = ".com")
    public static final long COM = 1;

    @BeanAttrInfo(value = ".cn", cnName = ".cn", enName = ".cn")
    public static final long CN = 2;

    @BeanAttrInfo(value = ".net", cnName = ".net", enName = ".net")
    public static final long NET = 2;
}
