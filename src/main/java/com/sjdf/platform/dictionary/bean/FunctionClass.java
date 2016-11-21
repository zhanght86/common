package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-28
 *
 * @author 王正伟
 * @category 功能大类
 */
@Entity
@DiscriminatorValue("FUNCTION_CLASS")
@BeanName(name = "功能大类")
public class FunctionClass extends Dictionary {
    private static final long serialVersionUID = -7656584641172861674L;
    /** 虚拟主机 */
    @BeanAttrInfo(value = "1", cnName = "虚拟主机")
    public static final long VHOST = 1;

    /** 邮局 */
    @BeanAttrInfo(value = "2", cnName = "邮局")
    public static final long MAIL = 2;

    /** 数据库 */
    @BeanAttrInfo(value = "3", cnName = "数据库")
    public static final long DATABASE = 3;

    /** VPS */
    @BeanAttrInfo(value = "4", cnName = "VPS")
    public static final long VPS = 4;

    /** 域名 */
    @BeanAttrInfo(value = "5", cnName = "域名")
    public static final long DOMAIN = 5;

    /** IDC */
    @BeanAttrInfo(value = "6", cnName = "IDC")
    public static final long IDC = 6;

    /** 云主机 */
    @BeanAttrInfo(value = "7", cnName = "云主机")
    public static final long CLOUD_HOST = 7;

    /** 共通功能 */
    @BeanAttrInfo(value = "8", cnName = "共通功能")
    public static final long COMMON_FOUNTION = 8;

    /** 备案 */
    @BeanAttrInfo(value = "9", cnName = "备案")
    public static final long RECORD_SYSTEM = 9;

    /** 微信 */
    @BeanAttrInfo(value = "10", cnName = "微信")
    public static final long WEIXIN = 10;

    /** 工单 */
    @BeanAttrInfo(cnName = "工单")
    public static final long WORK_ORDER = 11;

    /** 财务 */
    @BeanAttrInfo(cnName = "财务")
    public static final long FINANCE = 12;

    /** 监控 */
    @BeanAttrInfo(cnName = "监控")
    public static final long MONITOR = 13;

    /** 会员 */
    @BeanAttrInfo(cnName = "会员")
    public static final long MEMBER = 14;

    /** IP */
    @BeanAttrInfo(cnName = "IP地址")
    public static final long IP = 15;

    /** 业务公共平台 */
    @BeanAttrInfo(cnName = "业务公共平台")
    public static final long COMMON_PLATFORM = 16;

    /** 贷款审核系统 */
    @BeanAttrInfo(cnName = "贷款审核系统")
    public static final long LOAN_PLATFORM = 17;
}
