package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-3-2
 *
 * @author 丁yan
 * @category 域名接口类型
 */
@Entity
@DiscriminatorValue("DOMAIN_API_TYPE")
@BeanName(name = "域名接口类型")
public class DomainApiType extends Dictionary {
    private static final long serialVersionUID = 6789757361882303828L;

    /** 域名检测 */
    @BeanAttrInfo(value = "1", cnName = "域名检测")
    public static final long DOMAIN_CHECK = 1;

    /** 域名注册 */
    @BeanAttrInfo(value = "2", cnName = "域名注册")
    public static final long DOMAIN_REGISTER = 2;

    /** 域名续费 */
    @BeanAttrInfo(value = "3", cnName = "域名续费")
    public static final long DOMAIN_RENEW = 3;

    /** 域名删除 */
    @BeanAttrInfo(value = "4", cnName = "域名删除")
    public static final long DOMAIN_DELETE = 4;

    /** 域名转移 */
    @BeanAttrInfo(value = "5", cnName = "域名转移")
    public static final long DOMAIN_TRANSFER = 5;

    /** 域名赎回 */
    @BeanAttrInfo(value = "6", cnName = "域名赎回")
    public static final long DOMAIN_RANSOM = 6;

    /** 域名锁定 */
    @BeanAttrInfo(value = "7", cnName = "域名锁定")
    public static final long DOMAIN_LOCKING = 7;

    /** 域名解锁 */
    @BeanAttrInfo(value = "8", cnName = "域名解锁")
    public static final long DOMAIN_UNLOCK = 8;

    /** 添加主机 */
    @BeanAttrInfo(value = "9", cnName = "添加主机")
    public static final long HOST_ADD = 9;

    /** 修改主机 */
    @BeanAttrInfo(value = "10", cnName = "修改主机")
    public static final long HOST_MODIFY = 10;

    /** 删除主机 */
    @BeanAttrInfo(value = "11", cnName = "删除主机")
    public static final long HOST_DELETE = 11;

    /** 添加主机的IP */
    @BeanAttrInfo(value = "12", cnName = "添加主机的IP")
    public static final long HOST_IP_ADD = 12;

    /** 修改主机的IP */
    @BeanAttrInfo(value = "13", cnName = "修改主机的IP")
    public static final long HOST_IP_MODIFY = 13;

    /** 删除主机的IP */
    @BeanAttrInfo(value = "14", cnName = "删除主机的IP")
    public static final long HOST_IP_DELETE = 14;

    /** 查询主机的信息 */
    @BeanAttrInfo(value = "15", cnName = "查询主机的信息")
    public static final long HOST_INFO_QUERY = 15;

    /** 添加域名的DNS */
    @BeanAttrInfo(value = "16", cnName = "添加域名的DNS")
    public static final long DNS_ADD = 16;

    /** 修改域名的DNS */
    @BeanAttrInfo(value = "17", cnName = "修改域名的DNS")
    public static final long DNS_MODIFY = 17;

    /** 删除域名的DNS */
    @BeanAttrInfo(value = "18", cnName = "删除域名的DNS")
    public static final long DNS_DELETE = 18;

    /** 取消域名转出 */
    @BeanAttrInfo(value = "19", cnName = "取消域名转出")
    public static final long DOMAIN_TRANSFER_OUT_CANCEL = 19;

    /** 获取域名转移密码 */
    @BeanAttrInfo(value = "20", cnName = "获取域名转移密码")
    public static final long DOMAIN_TRANSFER_PASSWORD_QUERY = 20;

    /** 获取域名管理密码 */
    @BeanAttrInfo(value = "21", cnName = "获取域名管理密码")
    public static final long DOMAIN_MANAGE_PASSWORD_QUERY = 21;

    /** 修改域名管理密码 */
    @BeanAttrInfo(value = "22", cnName = "修改域名管理密码")
    public static final long DOMAIN_MANAGE_PASSWORD_MODIFY = 22;

    /** 获取指定域名信息 */
    @BeanAttrInfo(value = "23", cnName = "获取指定域名信息")
    public static final long DOMAIN_INFO_QUERY = 23;

    /** 修改联系人域名信息 */
    @BeanAttrInfo(value = "24", cnName = "修改联系人域名信息")
    public static final long DOMAIN_CONTACT_MODIFY = 24;

    /** 域名过户 */
    @BeanAttrInfo(value = "25", cnName = "域名过户")
    public static final long DOMAIN_TRANSFER_OWNER = 25;

    /** 域名whois信息保护 */
    @BeanAttrInfo(value = "26", cnName = "域名whois信息保护")
    public static final long DOMAIN_UPDATE_PRIVACY = 26;
}
