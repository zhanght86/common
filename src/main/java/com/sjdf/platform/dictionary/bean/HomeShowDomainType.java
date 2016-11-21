package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2014-01-06
 *
 * @author 首页域名显示类型
 * @category 陈绍刚
 */
@Entity
@DiscriminatorValue("HOME_SHOW_DOMAIN_TYPE")
@BeanName(name = "首页域名显示类型")
public class HomeShowDomainType extends Dictionary {

    private static final long serialVersionUID = -6072517384047762198L;

    /** 首页显示英文域名后缀 */
    @BeanAttrInfo(value = ".com:1;.cn:33;.net:43;.org:3;.cc:4;.biz:6;.mobi:8;.me:2;.tv:5", cnName = "英文域名")
    public static final long HOME_SHOW_DOMAIN_EN = 1;

    /** 首页显示中文域名后缀 */
    @BeanAttrInfo(value = ".com:13;.cn:39;.net:44;.中国:51;.公司:52;.网络:53;.cc:14;.tv:16;.biz:15", cnName = "中文域名")
    public static final long HOME_SHOW_DOMAIN_CN = 2;
}
