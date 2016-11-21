package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 需要显示信息收集页面的产品类型
 * @ClassName ProductClass4WhiteListInfo
 * @Created 2012 2012-8-6 下午4:05:39
 */
@Entity
@DiscriminatorValue("PRODUCT_CLASS_4WHITE_LIST_INFO")
@BeanName(name = "显示信息收集页面的产品类型")
public class ProductClass4WhiteListInfo extends Dictionary {
    private static final long serialVersionUID = 1993611356284383720L;
    /** 虚拟主机 */
    @BeanAttrInfo(value = "2", cnName = "虚拟主机")
    public static final long VHOST = 1;

    /** 邮局 */
    @BeanAttrInfo(value = "2", cnName = "邮局")
    public static final long MAIL = 2;

    /** 数据库 */
    @BeanAttrInfo(value = "2", cnName = "数据库")
    public static final long DATABASE = 3;

    /** vps */
    @BeanAttrInfo(value = "2", cnName = "vps")
    public static final long VPS = 4;

    /** 域名 */
    @BeanAttrInfo(value = "2", cnName = "域名")
    public static final long DOMAIN = 5;

    /** IDC */
    @BeanAttrInfo(value = "1", cnName = "IDC")
    public static final long IDC = 6;

    /** 云主机 */
    @BeanAttrInfo(value = "2", cnName = "云主机 ")
    public static final long CHOST = 7;
}
