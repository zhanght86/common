package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-03 10:51
 *
 * @category 产品动作;英文名称不允许修改,财务历史数据问题
 */
@Entity
@DiscriminatorValue("PRODUCT_ACTION")
@BeanName(name = "产品动作")
public class ProductAction extends Dictionary {
    private static final long serialVersionUID = -6749259379932549023L;

    /** 购买 */
    @BeanAttrInfo(cnName = "购买", enName = "Buy")
    public static final long BUY = 1;

    /** 续费 */
    @BeanAttrInfo(cnName = "续费", enName = "Renew")
    public static final long RENEW = 2;

    /** 升级 */
    @BeanAttrInfo(cnName = "升级", enName = "Upgrade")
    public static final long UPGRADE = 3;

    /** 转入 */
    @BeanAttrInfo(cnName = "转入", enName = "In")
    public static final long IN = 4;

    /** 转出 */
    @BeanAttrInfo(cnName = "转出", enName = "Out")
    public static final long OUT = 5;

    /** 过户 */
    @BeanAttrInfo(cnName = "过户", enName = "Owner")
    public static final long OWNER = 6;

    /** 增加空间大小 */
    @BeanAttrInfo(cnName = "增加空间大小", enName = "AddDiskQuota")
    public static final long ADD_DISK_QUOTA = 7;

    /** 增加流量 */
    @BeanAttrInfo(cnName = "增加流量", enName = "AddFlow")
    public static final long ADD_FLOW = 8;

    /** 增加连接数 */
    @BeanAttrInfo(cnName = "增加连接数", enName = "AddConnections")
    public static final long ADD_CONNECTIONS = 9;

    /** 增加带宽 */
    @BeanAttrInfo(cnName = "增加带宽", enName = "AddBandwidth")
    public static final long ADD_BANDWIDTH = 10;

    /** 增加域名绑定数 */
    @BeanAttrInfo(cnName = "增加域名绑定数", enName = "AddBindings")
    public static final long ADD_BINDINGS = 11;

    /** 增加邮箱用户数 */
    @BeanAttrInfo(cnName = "增加邮箱用户数", enName = "AddMailUser")
    public static final long ADD_MAIL_USER = 12;

    /** 增加FTP帐户 */
    @BeanAttrInfo(cnName = "增加FTP帐户", enName = "AddFTPUser")
    public static final long ADD_FTP_USER = 13;

    /** 增加IP */
    @BeanAttrInfo(cnName = "增加IP", enName = "AddIP")
    public static final long ADD_IP = 14;

    /** 增加内存 */
    @BeanAttrInfo(cnName = "增加内存", enName = "AddMemory")
    public static final long ADD_MEMORY = 14;

    /** 增加硬盘 */
    @BeanAttrInfo(cnName = "增加硬盘", enName = "AddHardDisk")
    public static final long ADD_HARD_DISK = 14;

    /** 备案域名增量 */
    @BeanAttrInfo(cnName = "备案域名增量", enName = "AddIncreaseRecordDomain")
    public static final long ADD_INCREASE_RECORD_DOMAIN = 14;
}
