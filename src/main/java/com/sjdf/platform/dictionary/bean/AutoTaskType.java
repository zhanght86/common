package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 自动任务类型
 * Create at 2012-07-20
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("AUTO_TASK_TYPE")
@BeanName(name = "自动任务类型")
public class AutoTaskType extends Dictionary {
    private static final long serialVersionUID = 7074646815385492045L;

    /** 数据备份 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 1, cnName = "数据备份", enName = "data backup")
    public static final long EISS_DATA_BACKUP = 1;

    /** 备案检查解除与恢复 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 2, cnName = "备案检查解除与恢复", enName = "record remove restore")
    public static final long EISS_RECORD_REMOVE_RESTORE = 2;

    /** CPU与网络流量使用统计 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 4, cnName = "CPU与网络流量使用统计", enName = "cpu flow statistics")
    public static final long EISS_CPU_FLOW_STATISTICS = 4;

    /** 超限产品处理 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 5, cnName = "超限产品处理", enName = "out boundary handle")
    public static final long EISS_OUT_BOUNDARY_HANDLE = 5;

    /** 网站模版同步 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 6, cnName = "网站模版同步", enName = "sync web template")
    public static final long EISS_SYNC_WEB_TEMPLATE = 6;

    /** eiss日志备份 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 7, cnName = "eiss日志备份", enName = "eiss log backup")
    public static final long EISS_LOG_BACKUP = 7;

    /** 网站和备案信息缓存更新 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 8, cnName = "网站和备案信息缓存更新", enName = "record site cache")
    public static final long EISS_RECORD_SITE_CACHE = 8;

    /** VPS库存统计 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 9, cnName = "VPS库存统计", enName = "vps inventory count")
    public static final long EISS_VPS_INVENTORY_COUNT = 9;

    /** 网站访问检查 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 10, cnName = "网站访问检查", enName = "website access check")
    public static final long EISS_WEBSITE_ACCESS_CHECK = 10;

    /** 白名单防火墙 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 11, cnName = "白名单防火墙", enName = "white list firewall")
    public static final long EISS_WHITE_LIST_FIREWALL = 11;

    /** 云主机快照回滚确认 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 12, cnName = "云主机快照回滚确认", enName = "chost snapshot rollback")
    public static final long EISS_CHOST_SNAPSHOT_ROLLBACK = 12;

    /** 产品备案接入检查 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 13, cnName = "产品备案接入检查", enName = "product record access")
    public static final long EISS_PRODUCT_RECORD_ACCESS = 13;

    /** 白名单清理 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 14, cnName = "白名单清理 ", enName = "white list firewall clear")
    public static final long EISS_WHITE_LIST_FIREWALL_CLEAR = 14;

    /** 产品到期续费通知 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 15, cnName = "产品到期续费通知 ", enName = "product expire renew inform")
    public static final long EISS_PRODUCT_EXPIRE_RENEW_INFORM = 15;

    /** 白名单信息收集 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 16, cnName = "白名单信息收集 ", enName = "white list info collection")
    public static final long EISS_WHITE_LIST_INFO_COLLECTION = 16;

    /** 云主机计划设置 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 17, cnName = "云主机计划设置 ", enName = "chost plan set")
    public static final long EISS_CHOST_PLAN_SET = 17;

    /** 域名订单记录处理 */
    @BeanAttrInfo(systemType = SystemType.EISS, orderBy = 24, cnName = "域名订单记录处理 ", enName = "domian order record handle")
    public static final long EISS_DOMIAN_ORDER_RECORD_HANDLE = 24;
    /** 域名dns记录whois比对自动任务 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 25, cnName = "域名dns记录whois比对自动任务 ", enName = "domian DNS record compare")
    public static final long EISS_DOMIAN_DNS_RECORD_COMPARE = 25;

    /*
     * ------------------------------------------hkidc--------------------------------------
     */
    /** hkidc邮件自动接收 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 100, cnName = "hkidc邮件自动接收")
    public static final long HKIDC_EMAIL_RECEIVE = 100;

    /** hkidc域名转移自动处理 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 101, cnName = "hkidc域名转移自动处理")
    public static final long HKIDC_DOMAIN_TRANSFER = 101;

    /** hkidc消息自动处理 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 102, cnName = "hkidc消息自动处理")
    public static final long HKIDC_POLL_MESSAGE = 102;

    /** hkidc注册数据托管 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 103, cnName = "hkidc注册数据托管")
    public static final long HKIDC_REGISTRAR_DATA_ESCROW = 103;

    /** hkidc域名过期处理 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 104, cnName = "hkidc域名过期处理")
    public static final long HKIDC_DOMAIN_EXPIRED = 104;

    /** hkidc域名过户处理 */
    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 105, cnName = "hkidc域名过户处理")
    public static final long HKIDC_DOMAIN_TRANSFER_OWNER = 105;

    /*
     * -----------------------------------------client---------------------------------------
     */
    /** 带宽和CPU限制 */
    @BeanAttrInfo(systemType = SystemType.EISS_CLIENT, orderBy = 200, cnName = "带宽和CPU限制 ", enName = "bandwidth cpu limit")
    public static final long CLIENT_BANDWIDTH_CPU_LIMIT = 200;

    /*
     * -----------------------------------------weixin----------------------------------------
     */
    /** 公众账号过期处理 */
    @BeanAttrInfo(systemType = SystemType.WEI_XIN, orderBy = 300, cnName = "公众账号过期处理 ", enName = "account expired")
    public static final long WEIXIN_ACCOUNT_EXPIRED = 300;
    /** 公众账号每月请求数清零处理 */
    @BeanAttrInfo(systemType = SystemType.WEI_XIN, orderBy = 301, cnName = "公众账号每月请求数清空处理 ", enName = "account requests clear")
    public static final long WEIXIN_ACCOUNT_REQUESTS_CLEAR = 301;
    /** 登录Session清空处理 */
    @BeanAttrInfo(systemType = SystemType.WEI_XIN, orderBy = 302, cnName = "登录Session清空处理 ", enName = "session clear")
    public static final long WEIXIN_SESSION_CLEAR = 302;
    /** 当天用户参与活动的次数清零处理 */
    @BeanAttrInfo(systemType = SystemType.WEI_XIN, orderBy = 303, cnName = "当天用户参与活动的次数清零处理 ", enName = "account campaign times clear")
    public static final long WEIXIN_ACTIVITY_CAMPAIGN_TIMES_CLEAR = 303;
    /** 微信活动自动开始处理 */
    @BeanAttrInfo(systemType = SystemType.WEI_XIN, orderBy = 304, cnName = "微信活动自动开始处理 ", enName = "weixin activity auto start")
    public static final long WEIXIN_ACTIVITY_AUTO_START = 304;

    /*
     * -----------------------------------------eiss-common----------------------------------------
     */
    /** 清理消息自动任务 */
    @BeanAttrInfo(systemType = SystemType.EISS_COMMON, cnName = "清理消息自动任务")
    public static final long EISS_COMMON_CLEAR_MESSAGE_AUTO_SEND = 401;
}
