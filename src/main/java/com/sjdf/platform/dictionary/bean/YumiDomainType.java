package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * 
 * 2016-02-22
 * @category 一口价域名类型
 * @author wangpeng
 *
 */
@Entity
@DiscriminatorValue("YUMI_DOMAIN_TYPE")
@BeanName(name = "一口价域名类型")
public class YumiDomainType extends Dictionary{

    private static final long serialVersionUID = 2249760026573043501L;

    @BeanAttrInfo(value = "0", enName = "all", cnName = "所有类型")
    public static final long ALL = 0;

    @BeanAttrInfo(value = "1", enName = "number", cnName = "纯数字")
    public static final long NUMBER = 1;

    @BeanAttrInfo(value = "2", enName = "letter", cnName = "纯字母")
    public static final long LETTER = 2;
}
