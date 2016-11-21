package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鹏
 * @category 产品停放配置库
 * @date 2014-10-09
 */

@Entity
@DiscriminatorValue("PRODUCT_PARK")
@BeanName(name = "产品停放配置库")
public class ProductParkDictionary extends Dictionary {

    private static final long serialVersionUID = -8731296092578062844L;

    /**
     * @category 域名过期后间隔多久后开始自动停放，单位：天
     */
    @BeanAttrInfo(value = "1", enName = "domain expire to park interval", cnName = "域名过期到停放间隔")
    public static final long DOMAIN_EXPIRE_TO_PARK_INTERVAL = 1;

    /**
     * @category 虚拟主机过期后间隔多久后开始自动停放，单位：天
     */
    @BeanAttrInfo(value = "1", enName = "vhost expire to park interval", cnName = "虚拟主机过期到停放间隔")
    public static final long VHOST_EXPIRE_TO_PARK_INTERVAL = 2;

    /**
     * @category 域名停放地址
     */
    @BeanAttrInfo(value = "127.0.0.1", enName = "domain park address", cnName = "域名停放地址")
    public static final long DOMAIN_PARK_ADDRESS = 10;

    /**
     * @category 虚拟主机重定向地址
     */
    @BeanAttrInfo(value = "http://www.51web.com", enName = "vhost redirect address", cnName = "虚拟主机重定向地址(以http://开头)")
    public static final long VHOST_REDIRECT_ADDRESS = 11;

    /**
     * @category 域名出售停放地址
     */
    @BeanAttrInfo(value = "216.218.207.146", enName = "domain sale address", cnName = "域名出售停放地址")
    public static final long DOMAIN_SALE_ADDRESS = 12;
}
