package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 云主机服务器监控配置
 * Create at 2013-08-01
 *
 * @author 陈绍刚
 */
@Entity
@DiscriminatorValue("CHOST_SERVER_MONITOR_CONFIG")
@BeanName(name = "云主机服务器监控配置")
public class ChostServerMonitorConfig extends Dictionary {
    private static final long serialVersionUID = 7564502402850863657L;

    /** 最大维护时间（天） */
    @BeanAttrInfo(value = "3", cnName = "最大维护时间（天）")
    public static final long MAX_MAINTENANCE_TIME = 7;

    /** 通知邮箱（多个用逗号隔开） */
    @BeanAttrInfo(value = "", cnName = "通知邮箱（多个用逗号隔开）")
    public static final long NOTICE_EMAIL = 8;

    /** 通知手机 （多个用逗号隔开） */
    @BeanAttrInfo(value = "", cnName = "通知手机（多个用逗号隔开）")
    public static final long NOTICE_MOBILE = 9;

    /** 监控数据保留时间（小时） */
    @BeanAttrInfo(value = "72", cnName = "监控数据保留时间（小时）")
    public static final long DATA_RETENTION_TIME = 10;

    /** 资源报警间隔（小时） */
    @BeanAttrInfo(value = "4", cnName = "资源报警间隔")
    public static final long ALERM_INTERVAL = 11;
}
