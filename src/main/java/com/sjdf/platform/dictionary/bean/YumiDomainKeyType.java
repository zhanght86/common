package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 
 * 2016-02-22
 * @category 一口价域名搜索关键字类型
 * @author wangpeng
 *
 */
@Entity
@DiscriminatorValue("YUMI_DOMAIN_KEY_TYPE")
@BeanName(name = "一口价域名搜索关键字类型")
public class YumiDomainKeyType extends Dictionary{

    private static final long serialVersionUID = 4530198298235834465L;

    @BeanAttrInfo(value = "1", enName = "contain", cnName = "包含")
    public static final long CONTAIN = 1;

    @BeanAttrInfo(value = "2", enName = "equal", cnName = "精确")
    public static final long EQUAL = 2;

    @BeanAttrInfo(value = "3", enName = "prefix", cnName = "开头")
    public static final long PRIFIX = 3;

    @BeanAttrInfo(value = "4", enName = "sufix", cnName = "结尾")
    public static final long SUFIX = 4;
}
