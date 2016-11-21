package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-06-28
 *
 * @author 陈绍刚
 * @category 缓存类型
 */
@Entity
@DiscriminatorValue("CACHE_TYPE")
@BeanName(name = "缓存类型")
public class CacheType extends Dictionary {

    private static final long serialVersionUID = -5034287843577233576L;

    /** 机房缓存 */
    @BeanAttrInfo(value = "PubIdcCache", cnName = "机房缓存")
    public static final long PUB_IDC_CACHE = 1;

    /** 服务器缓存 */
    @BeanAttrInfo(value = "PubServerCache", cnName = "服务器缓存")
    public static final long PUB_SERVER_CACHE = 2;

    /** 价格缓存 */
    @BeanAttrInfo(value = "PriceCache", cnName = "价格缓存")
    public static final long PRICE_CACHE = 3;

    /** 会员相关缓存 */
    @BeanAttrInfo(value = "MemberCache", cnName = "会员相关缓存")
    public static final long MEMBER_CACHE = 4;

    /** 首页展示缓存 */
    @BeanAttrInfo(value = "HomeCache", cnName = "首页展示缓存")
    public static final long HOME_CACHE = 5;

    /** 轮播广告缓存 */
    @BeanAttrInfo(value = "AdverCache", cnName = "轮播广告缓存")
    public static final long ADVER_CACHE = 6;

    /** 产品分类缓存 */
    @BeanAttrInfo(value = "PubProductClsCache", cnName = "产品分类缓存")
    public static final long PUB_PRODUCT_CLS_CACHE = 7;

    /** 云主机缓存 */
    @BeanAttrInfo(value = "ChostCache", cnName = "云主机缓存")
    public static final long CHOST_CACHE = 8;

    /** 虚拟主机缓存 */
    @BeanAttrInfo(value = "VhostCache", cnName = "虚拟主机缓存")
    public static final long VHOST_CACHE = 9;

    /** 域名缓存 */
    @BeanAttrInfo(value = "DomainCache", cnName = "域名缓存")
    public static final long DOMAIN_CACHE = 10;

    /** 自定义导航缓存 */
    @BeanAttrInfo(value = "NavigationCache", cnName = "自定义导航缓存")
    public static final long NAVIGATION_CACHE = 11;
}
