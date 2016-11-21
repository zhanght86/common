package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-05-27 15:54
 * 工单系统自动回复标识
 */
@Entity
@DiscriminatorValue("WORK_ORDER_AUTOMATIC_REPLY_MARK")
@BeanName(name = "工单系统自动回复标识")
public class WorkOrderAutomaticReplyMark extends Dictionary {
    private static final long serialVersionUID = -13174426297918981L;

    /** 虚拟主机-无法访问 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "无法访问")
    public static final long VHOST_INACCESSIBLE = 1L;

    /** 虚拟主机-域名绑定 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "域名绑定")
    public static final long VHOST_DOMAIN_BIND = 2L;

    /** 虚拟主机-ftp使用异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "ftp使用异常")
    public static final long VHOST_FTP_USE_EXCEPTION = 3L;

    /** 虚拟主机-数据库连接异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "数据库连接异常")
    public static final long VHOST_DATABASE_CONN_EXCEPTION = 4L;

    /** 虚拟主机-数据备份与恢复 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "数据备份与恢复")
    public static final long VHOST_DATA_BACKUP_RECOVER = 5L;

    /** 虚拟主机-申请开通站点 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.VHOST, cnName = "申请开通站点")
    public static final long VHOST_APPLY_OPEN_SITE = 6L;

    /** 数据库使用异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DATABASE, cnName = "数据库使用异常")
    public static final long DATABASE_USE_EXCEPTION = 100L;

    /** 邮局停止后申请开通 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.MAIL, cnName = "邮局停止后申请开通")
    public static final long MAIL_APPLY_RUN = 200L;

    /** 云主机无法远程 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.CHOST, cnName = "无法远程")
    public static final long CHOST_NO_REMOTE = 300L;

    /** 云主机网站访问异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.CHOST, cnName = "网站访问异常")
    public static final long CHOST_SITE_ACCESS_EXCEPTION = 301L;

    /** 云主机机器不通 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.CHOST, cnName = "机器不通")
    public static final long CHOST_MACHINE_NOWHERE = 302L;

    /** IDC网站访问异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.IDC, cnName = "IDC网站访问异常")
    public static final long IDC_SITE_ACCESS_EXCEPTION = 400L;

    /** IDC网络异常 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.IDC, cnName = "IDC网络异常")
    public static final long IDC_NETWORK_EXCEPTION = 401L;

    /** IDC机器不通 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.IDC, cnName = "IDC机器不通")
    public static final long IDC_MACHINE_NOWHERE = 402L;

    /** IDC增加备案域名 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.IDC, cnName = "IDC增加备案域名")
    public static final long IDC_ADD_RECORD_DOMAIN = 403L;

    /** 域名证书 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名证书")
    public static final long DOMAIN_CERTIFICATE = 500L;

    /** 域名过户-如何提交 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名过户-如何提交")
    public static final long DOMAIN_TRANSFER = 501L;

    /** 域名转入-如何提交 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.BUSINESS_CONSULTATION, cnName = "域名转入-如何提交")
    public static final long DOMAIN_TRANSFER_IN = 502L;

    /** 域名转出-如何办理 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名转出-如何办理")
    public static final long DOMAIN_TRANSFER_OUT = 503L;

    /** 域名转出-需要的资料 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名转出-需要的资料")
    public static final long DOMAIN_TRANSFER_OUT_ATTACHMENT = 504L;

    /** 域名转出-需要的条件 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名转出-需要的条件")
    public static final long DOMAIN_TRANSFER_OUT_CONDITION = 505L;

    /** 域名修改DNS */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名修改DNS")
    public static final long DOMAIN_UPDATE_DNS = 506L;

    /** 域名解析-如何解析 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名解析-如何解析")
    public static final long DOMAIN_PARSE = 507L;

    /** 域名解析-解析不生效 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名解析-解析不生效")
    public static final long DOMAIN_PARSE_NO_EFFECTIVE = 508L;

    /** 域名解析-域名被锁 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名解析-域名被锁")
    public static final long DOMAIN_LOCKED = 509L;

    /** 通用网址-续费 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "通用网址-续费")
    public static final long WEBSITE_RENEW = 510L;

    /** 域名续费-转入世纪东方续费 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名续费-转入世纪东方续费")
    public static final long DOMAIN_TRANSFER_IN_SJDF = 511L;

    /** 备案咨询-备案审核进度查询 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.RECORD, cnName = "备案咨询-备案审核进度查询")
    public static final long RECORD_VERIFY_SCHEDULE_QUERY = 600L;

    /** 备案常见问题-备案系统用户名及密码 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.RECORD, cnName = "备案常见问题-备案系统用户名及密码")
    public static final long RECORD_USERNAME_PWD = 601L;

    /** 域名-新注册域名已有备案号 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.DOMAIN, cnName = "域名-新注册域名已有备案号")
    public static final long RECORD_NEW_DOMAIN_HAS_RECORD_NUM = 602L;

    /** 备案常见问题-取消接入 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.RECORD, cnName = "备案常见问题-取消接入")
    public static final long RECORD_CANCEL_IN = 603L;

    /** 财务&会员	申请入款 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.FINANCE_MEMBER, cnName = "财务&会员-申请入款")
    public static final long FINANCE_MEMBER_APPLY_DEPOSIT = 700;

    /** 财务&会员	发票-如何申请发票 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.FINANCE_MEMBER, cnName = "如何申请发票")
    public static final long FINANCE_APPLY_INVOICE = 701;

    /** 财务&会员	发票-取消发票申请 */
    @BeanAttrInfo(refClass = WorkOrderBusinessType.class, refAttr = WorkOrderBusinessType.FINANCE_MEMBER, cnName = "取消发票申请")
    public static final long FINANCE_CANCEL_INVOICE = 702;
}
