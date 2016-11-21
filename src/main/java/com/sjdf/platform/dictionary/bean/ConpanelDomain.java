package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-09-12
 *
 * @author 陈绍刚
 * @category 控制面板域名
 */
@Entity
@DiscriminatorValue("CONPANEL_DOMAIN")
@BeanName(name = "控制面板域名")
public class ConpanelDomain extends Dictionary {

    private static final long serialVersionUID = 3645859850493506079L;
    /** @category 控制面板云主机域名 */
    @BeanAttrInfo(value = "chost.client.cdnhost.cn", cnName = "控制面板云主机域名")
    public static final long CONPANEL_CHOST_DOMAIN = 1;

    /** @category 控制面板虚拟主机域名 */
    @BeanAttrInfo(value = "vhost.client.cdnhost.cn", cnName = "控制面板虚拟主机域名")
    public static final long CONPANEL_VHOST_DOMAIN = 2;

    /** @category 控制面板数据库域名 */
    @BeanAttrInfo(value = "database.client.cdnhost.cn", cnName = "控制面板数据库域名")
    public static final long CONPANEL_DATABASE_DOMAIN = 3;

    /** @category 控制面板邮局域名 */
    @BeanAttrInfo(value = "mail.client.cdnhost.cn", cnName = "控制面板邮局域名")
    public static final long CONPANEL_MAIL_DOMAIN = 4;

    /** @category 控制面板域名域名 */
    @BeanAttrInfo(value = "domain.client.cdnhost.cn", cnName = "控制面板域名域名")
    public static final long CONPANEL_DOMAIN_DOMAIN = 5;

    /** @category 控制面板vps域名 */
    @BeanAttrInfo(value = "vps.client.cdnhost.cn", cnName = "控制面板vps域名")
    public static final long CONPANEL_VPS_DOMAIN = 6;

    /** @category 控制面板idc域名 */
    @BeanAttrInfo(value = "idc.client.cdnhost.cn", cnName = "控制面板idc域名")
    public static final long CONPANEL_IDC_DOMAIN = 7;

    /** @category 代理控制面板云主机域名 */
    @BeanAttrInfo(value = "210.209.69.133", cnName = "代理控制面板云主机域名")
    public static final long AGENT_CONPANEL_CHOST_DOMAIN = 8;

    /** @category 代理控制面板获取验证码URL */
    @BeanAttrInfo(value = "http://210.209.69.133/chost/loginAction!getVerifyCode.action", cnName = "代理控制面板获取验证码URL")
    public static final long AGENT_CONPANEL_GET_VERIFY_CODE_URL = 9;

    /** @category 云工坊控制面板域名 */
    @BeanAttrInfo(value = "test.51web.com", cnName = "云工坊控制面板域名")
    public static final long YGF_CONPANEL_DOMAIN = 10L;
}
