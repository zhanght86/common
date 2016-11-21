package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-06
 *
 * @category 优惠活动
 * @author 黄远昌
 */
@Entity
@DiscriminatorValue("PREFERENTIAL_ACTIVITY")
@BeanName(name = "优惠活动")
public class PreferentialActivity extends Dictionary {
    private static final long serialVersionUID = 5762370567023823014L;

    /** 云主机优惠活动 */
    @BeanAttrInfo(value = "云服务器优惠活动", cnName = "云服务器优惠活动 ")
    public static final long CHOST_ACTIVITY_CONTENT = ProductClass.CHOST;

    /** 域名优惠活动 */
    @BeanAttrInfo(value = "域名优惠活动", cnName = "域名优惠活动 ")
    public static final long DOMAIN_ACTIVITY_CONTENT = ProductClass.DOMAIN;

    /** 虚拟主机 */
    @BeanAttrInfo(value = "云虚拟主机优惠活动", cnName = "云虚拟主机优惠活动 ")
    public static final long VHOST_ACTIVITY_CONTENT = ProductClass.VHOST;

    /** 邮局优惠活动 */
    @BeanAttrInfo(value = "邮局优惠活动", cnName = "邮局优惠活动 ")
    public static final long MAIL_ACTIVITY_CONTENT = ProductClass.MAIL;

    /** 数据库优惠活动 */
    @BeanAttrInfo(value = "数据库优惠活动", cnName = "数据库优惠活动 ")
    public static final long DATABASE_ACTIVITY_CONTENT = ProductClass.DATABASE;
}
