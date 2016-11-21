package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 时间戳标示常量——用于配置一些参数
 * @ClassName TimeStampConstants
 * @Created 2012 2012-8-11 下午3:07:40
 */
@Entity
@DiscriminatorValue("TIME_STAMP_CONSTANTS")
@BeanName(name = "时间戳标示常量")
public class TimeStampConstants extends Dictionary {
    private static final long serialVersionUID = -5254958549590452539L;

    /**
     * ==============================云主机相关配置================
     */
    /** 删除过期云主机时间间隔（长；单位：天数） */
    @BeanAttrInfo(value = "7", cnName = "删除过期云主机时间间隔（长）")
    public static final long CHOST_DELETE_EXPIRED_LONG = 701;

    /** 删除过期云主机时间间隔（短；单位：天数） */
    @BeanAttrInfo(value = "3", cnName = "删除过期云主机时间间隔（短）")
    public static final long CHOST_DELETE_EXPIRED_SHORTG = 702;

    /** 删除迁移云主机时间间隔 （单位：天数） */
    @BeanAttrInfo(value = "3", cnName = "删除迁移云主机时间间隔")
    public static final long CHOST_DELETE_EMIGRATE = 703;

    /** 云主机快照系统回滚超时间隔 （单位：分钟） */
    @BeanAttrInfo(value = "60", cnName = "云主机快照系统回滚超时间隔")
    public static final long CHOST_SNAPSHOT_ROLLBACK_SYS = 704;

    /** 云主机快照数据回滚超时间隔 （单位：分钟） */
    @BeanAttrInfo(value = "60", cnName = "云主机快照数据回滚超时间隔")
    public static final long CHOST_SNAPSHOT_ROLLBACK_DATA = 705;

    /** 云主机带宽流量周期 （单位：秒） */
    @BeanAttrInfo(value = "60", cnName = "云主机带宽流量周期 ")
    public static final long CHOST_BANDWIDTH_PERIOD = 706;

    /** 云主机带宽重置周期 （单位：小时） */
    @BeanAttrInfo(value = "0.5", cnName = "云主机带宽重置周期 ")
    public static final long CHOST_BANDWIDTH_RESET_PERIOD = 707;

    /** 云主机带宽超限百分比（单位：%） */
    @BeanAttrInfo(value = "95", cnName = "云主机带宽超限百分比 ")
    public static final long CHOST_BANDWIDTH_OVERRUN = 708;

    /** 云主机检测超时时间（单位：毫秒） */
    @BeanAttrInfo(value = "30000", cnName = "云主机检测超时时间 ")
    public static final long CHOST_DETECT_TIMEOUT = 709;

    /** 云主机资源监控带宽检测周期（单位：秒） */
    @BeanAttrInfo(value = "60", cnName = "云主机资源监控带宽检测周期 ")
    public static final long CHOST_MONITOR_BANDWIDTH = 710;

    /** 云主机资源监控CPU检测周期（单位：分钟） */
    @BeanAttrInfo(value = "10", cnName = "云主机资源监控CPU检测周期 ")
    public static final long CHOST_MONITOR_CPU = 711;

    /** 云主机资源监控磁盘检测周期（单位：天） */
    @BeanAttrInfo(value = "1", cnName = "云主机资源监控磁盘检测周期 ")
    public static final long CHOST_MONITOR_DISK = 712;

    /** 云主机IP设置超时时间（单位：毫秒） */
    @BeanAttrInfo(value = "60000", cnName = "云主机IP设置超时时间 ")
    public static final long CHOST_SET_IP_TIMEOUT = 713;

    /** 云主机绑定限制个数（单位：个） */
    @BeanAttrInfo(value = "20", cnName = "云主机绑定限制个数")
    public static final long CHOST_BIND_LIMIT_NUM = 714;

    /**
     * ===============HKIDC相关==============================
     */
    /** HKIDC域名转移有效期（单位：天） */
    @BeanAttrInfo(value = "10", cnName = "HKIDC域名转移有效期")
    public static final long DOMAIN_TRANSFER_VALID_PERIOD = 501;

    /** 域名相关连接生效时间长度,单位:天 */
    @BeanAttrInfo(value = "7", cnName = "域名相关连接生效时间长度")
    public static final long HKIDC_DOMAIN_LINK_WORK_TIME = 502;

    /**
     * ========================其他==========================
     */

    /** 客户信息管理手机验证码过期时间间隔（单位：秒） */
    @BeanAttrInfo(value = "1000", cnName = "客户信息管理手机验证码过期时间间隔 ")
    public static final long CUSTOMER_PHONE_VALIDATE_CODE_OVERDUE = 1;

    /** 不发送短信时间（在xx小时后）（单位：小时数） */
    @BeanAttrInfo(value = "22", cnName = "不发送短信时间（在xx小时后）")
    public static final long NOT_SMS_AFTER_HOUR = 2;

    /** 不发送短信时间（在xx小时前）（单位：小时数） */
    @BeanAttrInfo(value = "9", cnName = "不发送短信时间（在xx小时后）")
    public static final long NOT_SMS_BEFORE_HOUR = 3;

    /** 购买云主机后不限制CPU的时间（单位：分钟） */
    @BeanAttrInfo(value = "10", cnName = "购买云主机后不限制CPU的时间")
    public static final long TIME_FOR_NOT_LIMITING_CPU_AFTER_BUYING_CHOST = 4;

    /** 产品到期前通知代理的天数（短信） */
    @BeanAttrInfo(value = "2", cnName = "产品到期前通知代理的天数（短信）")
    public static final long TIME_FOR_EXPIRATION_NOTICE = 5;

    /** socket超时的时间 */
    @BeanAttrInfo(value = "60000", cnName = "socket超时的时间")
    public static final long TIME_FOR_SOCKET_TIME_OUT = 6;

    /**
     * ==========================产品备案接入检查相关配置============
     */
    /** 取消接入时限（单位：天） */
    @BeanAttrInfo(value = "10", cnName = "取消接入时限")
    public static final long OUR_REC_NOT_PRO_IP = 11;

    /** 我司接入，备案IP和域名解析IP不一致连续X天停止产品（单位：天） */
    @BeanAttrInfo(value = "29", cnName = "我司接入，备案IP和域名解析IP不一致连续X天停止产品")
    public static final long OUR_REC_DIFF_IP = 12;

    /** 虚拟主机过期x天视为域名已经删除 （单位：天） */
    @BeanAttrInfo(value = "20", cnName = "虚拟主机过期x天视为域名已经删除")
    public static final long OUR_REC_NOT_CHINA = 13;

    /** 接入非我司，我司绑定产品连续x天停止产品 */
    @BeanAttrInfo(value = "60", cnName = "接入非我司，我司绑定产品连续x天停止产品")
    public static final long NOT_REC_OUR_PRO = 14;

    /**
     * ==========================主站域名相关配置===============================================
     */

    /** 域名解析生效时间 */
    @BeanAttrInfo(value = "10", cnName = "域名解析生效时间")
    public static final long DOMAIN_REC_WORK_TIME = 15;
    /** 外部供应商域名解析生效时间 */
    @BeanAttrInfo(value = "2-24", cnName = "外部供应商域名解析生效时间")
    public static final long OUTER_DOMAIN_REC_WORK_TIME = 16;
    /** hkidc过户多少天后才能转出 */
    @BeanAttrInfo(value = "180", cnName = "hkidc过户多少天后才能转出")
    public static final long DOMAIN_TANSFER_OUT_RESTRAINT = 17;

    /**
     * ==========================主站相关配置===============================================
     */
    /** 主站轮播广告滚动周期(毫秒) */
    @BeanAttrInfo(value = "5000", orderBy = 201, cnName = "主站轮播广告滚动周期(毫秒)")
    public static final long EISS_CAROUSE_ADVERTISE_HOME = 201;
    /** 主站首页域名广告滚动周期(毫秒) */
    @BeanAttrInfo(value = "3000", orderBy = 202, cnName = "主站首页域名轮播广告滚动周期(毫秒)")
    public static final long EISS_CAROUSE_ADVERTISE_HOME_DOMAIN = 202;
    /** 主站首页客户展示滚动周期(毫秒) */
    @BeanAttrInfo(value = "1000", orderBy = 203, cnName = "主站首页客户展示滚动周期(毫秒)")
    public static final long EISS_CAROUSE_ADVERTISE_HOME_CUSTOMER = 203;

    /** 主站首页截至到当前时间为止X个月内发票客户(月) */
    @BeanAttrInfo(value = "6", orderBy = 203, cnName = "主站首页截至到当前时间为止X个月内发票客户(月)")
    public static final long EISS_HOME_INVOICE_CUSTOMER_DATE_MONTH = 204;
    /** 主站轮播广告推迟时间(毫秒) */
    @BeanAttrInfo(value = "2500", orderBy = 205, cnName = "主站轮播广告推迟时间(毫秒)")
    public static final long EISS_CAROUSE_ADVERTISE_DELAY_TIME = 205;
}
