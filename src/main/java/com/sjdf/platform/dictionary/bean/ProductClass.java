package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-05
 *
 * @author 王正伟
 * @category 产品分类;为了适应财务系统,请不要修改value的值(虚拟主机,邮局,数据库,VPS,域名,IDC,云主机,其他)
 */
@Entity
@DiscriminatorValue("PRODUCT_CLASS")
@BeanName(name = "产品分类")
public class ProductClass extends Dictionary {
    private static final long serialVersionUID = 1993611356284383720L;
    /** 虚拟主机 */
    @BeanAttrInfo(value = "Vhost", orderBy = 1, cnName = "虚拟主机")
    public static final long VHOST = 1;

    /** 邮局 */
    @BeanAttrInfo(value = "Mail", orderBy = 2, cnName = "邮局")
    public static final long MAIL = 2;

    /** 数据库 */
    @BeanAttrInfo(value = "DataBase", orderBy = 3, cnName = "数据库")
    public static final long DATABASE = 3;

    /** VPS */
    @BeanAttrInfo(value = "VPS", orderBy = 4, cnName = "VPS")
    public static final long VPS = 4;

    /** 域名 */
    @BeanAttrInfo(value = "Domain", orderBy = 5, cnName = "域名")
    public static final long DOMAIN = 5;

    /** IDC */
    @BeanAttrInfo(value = "IDC", orderBy = 6, cnName = "IDC")
    public static final long IDC = 6;

    /** 云主机 */
    @BeanAttrInfo(value = "Chost", orderBy = 7, cnName = "云主机 ")
    public static final long CHOST = 7;

    /** 微信 */
    @BeanAttrInfo(value = "Weixin", orderBy = 8, cnName = "微信 ")
    public static final long WEIXIN = 8;

    @BeanAttrInfo(value = "SSL", orderBy = 9, cnName = "SSL证书 ")
    public static final long SSL = 9;

    /** 其他 */
    @BeanAttrInfo(value = "Other", orderBy = 99, cnName = "其他")
    public static final long OTHER = 99;
}
