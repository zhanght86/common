package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014-12-05
 *
 * @author 黄远昌
 * @category 云主机服务器预留配额
 */
@Entity
@DiscriminatorValue("CHOST_SERVER_RESERVE_QUOTA")
@BeanName(name = "云主机服务器预留配额")
public class ChostServerReserveQuota extends Dictionary {

    private static final long serialVersionUID = 1421024215608655365L;

    /** 服务器内存默认预留值（单位：GB） */
    @BeanAttrInfo(value = "20", cnName = "服务器内存默认预留值（单位：GB）")
    public static final long SERVER_RESERVE_MEM = 1;

    /** 商务网站服务器内存预留值（单位：GB） */
    @BeanAttrInfo(value = "20", cnName = "商务网站服务器内存预留值（单位：GB）")
    public static final long SERVER_RESERVE_MEM_WEBSITE_USE = 11;

    /** 挂机宝服务器内存预留值（单位：GB） */
    @BeanAttrInfo(value = "5", cnName = "挂机宝服务器内存预留值（单位：GB）")
    public static final long SERVER_RESERVE_MEM_NOT_WEBSITE_USE = 12;

    /** 建站宝服务器内存预留值（单位：GB） */
    @BeanAttrInfo(value = "5", cnName = "建站宝服务器内存预留值（单位：GB）")
    public static final long SERVER_RESERVE_MEM_MINI_WEBSITE_USE = 13;

    /** 服务器数据盘默认预留值（单位：GB） */
    @BeanAttrInfo(value = "300", cnName = "服务器数据盘默认预留值（单位：GB）")
    public static final long SERVER_RESERVE_DISK = 2;

    /** 商务网站服务器数据盘预留值（单位：GB） */
    @BeanAttrInfo(value = "300", cnName = "商务网站服务器数据盘预留值（单位：GB）")
    public static final long SERVER_RESERVE_DISK_WEBSITE_USE = 21;

    /** 挂机宝服务器数据盘预留值（单位：GB） */
    @BeanAttrInfo(value = "25", cnName = "挂机宝服务器数据盘预留值（单位：GB）")
    public static final long SERVER_RESERVE_DISK_NOT_WEBSITE_USE = 22;

    /** 建站宝服务器数据盘预留值（单位：GB） */
    @BeanAttrInfo(value = "25", cnName = "建站宝服务器数据盘预留值（单位：GB）")
    public static final long SERVER_RESERVE_DISK_MINI_WEBSITE_USE = 23;

    /** 服务器默认负载极限（小于10为CPU总线程数的倍数，否则为负载极限值） */
    @BeanAttrInfo(value = "2", cnName = "服务器默认负载极限（小于10为CPU总线程数的倍数，否则为负载极限值）")
    public static final long SERVER_LOAD_LIMITS = 3;

    /** 商务网站服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值） */
    @BeanAttrInfo(value = "2", cnName = "商务网站服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值）")
    public static final long SERVER_LOAD_LIMITS_WEBSITE_USE = 31;

    /** 挂机宝服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值） */
    @BeanAttrInfo(value = "100", cnName = "挂机宝服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值）")
    public static final long SERVER_LOAD_LIMITS_NOT_WEBSITE_USE = 32;

    /** 建站宝服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值） */
    @BeanAttrInfo(value = "2", cnName = "建站宝服务器负载极限（小于10为CPU总线程数的倍数，否则为负载极限值）")
    public static final long SERVER_LOAD_LIMITS_MINI_WEBSITE_USE = 33;

    /** 开设一台云主机默认使用的内存值（单位：GB） */
    @BeanAttrInfo(value = "2", cnName = "开设一台云主机默认使用的内存值（单位：GB）")
    public static final long CHOST_DEFAULT_USE_MEM = 4;

    /** 开设一台商务网站云主机默认使用的内存值（单位：GB） */
    @BeanAttrInfo(value = "2", cnName = "开设一台商务网站云主机默认使用的内存值（单位：GB）")
    public static final long CHOST_DEFAULT_USE_MEM_WEBSITE_USE = 41;

    /** 开设一台挂机宝云主机默认使用的内存值（单位：GB） */
    @BeanAttrInfo(value = "1", cnName = "开设一台挂机宝云主机默认使用的内存值（单位：GB）")
    public static final long CHOST_DEFAULT_USE_MEM_NOT_WEBSITE_USE = 42;

    /** 开设一台建站宝云主机默认使用的内存值（单位：GB） */
    @BeanAttrInfo(value = "1", cnName = "开设一台建站宝云主机默认使用的内存值（单位：GB）")
    public static final long CHOST_DEFAULT_USE_MEM_MINI_WEBSITE_USE = 43;

    /** 开设一台云主机默认使用的磁盘空间（单位：GB） */
    @BeanAttrInfo(value = "50", cnName = "开设一台云主机默认使用的磁盘空间（单位：GB）")
    public static final long CHOST_DEFAULT_USE_DISK = 5;

    /** 开设一台商务网站云主机默认使用的磁盘空间（单位：GB） */
    @BeanAttrInfo(value = "50", cnName = "开设一台商务网站云主机默认使用的磁盘空间（单位：GB）")
    public static final long CHOST_DEFAULT_USE_DISK_WEBSITE_US = 51;

    /** 开设一台挂机宝云主机默认使用的磁盘空间（单位：GB） */
    @BeanAttrInfo(value = "5", cnName = "开设一台挂机宝云主机默认使用的磁盘空间（单位：GB）")
    public static final long CHOST_DEFAULT_USE_DISK_NOT_WEBSITE_USE = 52;

    /** 开设一台建站宝云主机默认使用的磁盘空间（单位：GB） */
    @BeanAttrInfo(value = "5", cnName = "开设一台建站宝云主机默认使用的磁盘空间（单位：GB）")
    public static final long CHOST_DEFAULT_USE_DISK_MINI_WEBSITE_USE = 53;
}
