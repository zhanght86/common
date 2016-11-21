package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category ID类别常量
 */
@Entity
@DiscriminatorValue("CONST_ID_TYPE")
@BeanName(name = "ID类别常量")
public class ConstIdType extends Dictionary {
    private static final long serialVersionUID = -4542326913332698872L;

    /** 虚拟主机购买单号 */
    @BeanAttrInfo(value = "1", cnName = "虚拟主机购买单号")
    public static final long VHOST_ORDER_NUM = 1;
    /** 数据库购买单号 */
    @BeanAttrInfo(value = "2", cnName = "数据库购买单号")
    public static final long DATABASE_ORDER_NUM = 2;
    /** 邮局购买单号 */
    @BeanAttrInfo(value = "3", cnName = "邮局购买单号")
    public static final long MAIN_ORDER_NUM = 3;
    /** VPS购买单号 */
    @BeanAttrInfo(value = "4", cnName = "VPS购买单号")
    public static final long VPS_ORDER_NUM = 4;
    /** 虚拟主机产品ID */
    @BeanAttrInfo(value = "5", cnName = "虚拟主机产品ID")
    public static final long VHOST_PRODUCT_ID = 5;
    /** 数据库产品ID */
    @BeanAttrInfo(value = "6", cnName = "数据库产品ID")
    public static final long DATABASE_PRODUCT_ID = 6;
    /** 邮局产品ID */
    @BeanAttrInfo(value = "7", cnName = "邮局产品ID")
    public static final long MAIL_PRODUCT_ID = 7;
    /** VPS产品ID */
    @BeanAttrInfo(value = "8", cnName = "VPS产品ID")
    public static final long VPS_PRODUCT_ID = 8;
    /** 云主机产品ID */
    @BeanAttrInfo(value = "9", cnName = "云主机产品ID")
    public static final long CHOST_PRODUCT_ID = 9;
    /** 云主机购买单号 */
    @BeanAttrInfo(value = "10", cnName = "云主机购买单号")
    public static final long CHOST_ORDER_NUM = 10;
    /** 微信公众账号产品ID */
    @BeanAttrInfo(value = "11", cnName = "微信公众账号产品ID")
    public static final long WEIXIN_PRODUCT_ID = 11;
}
