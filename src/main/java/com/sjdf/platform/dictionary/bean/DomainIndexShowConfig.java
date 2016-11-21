package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-04-11
 *
 * @author 域名首页显示配置
 * @category 陈绍刚
 */
@Entity
@DiscriminatorValue("DOMAIN_INDEX_SHOW_CONFIG")
@BeanName(name = "域名首页显示配置")
public class DomainIndexShowConfig extends Dictionary {

    private static final long serialVersionUID = 810789454207104747L;
    /** 中文域名显示个数 */
    @BeanAttrInfo(value = "7", cnName = "中文域名显示个")
    public static final long CN_SHOW_NUM = 1;
    /** 英文域名显示个数 */
    @BeanAttrInfo(value = "12", cnName = "英文域名显示个数")
    public static final long EN_SHOW_NUM = 2;

    /** 域名检测默认后缀数 */
    @BeanAttrInfo(value = "5", cnName = "域名检测默认后缀数")
    public static final long CHECK_SUFFIX_NUM = 3;

    /** 批量查询最大个数 */
    @BeanAttrInfo(value = "10", cnName = "批量查询最大个数")
    public static final long BATCH_CHECK_MAX_NUM = 4;
}
