package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:15:46
 * 轮播广告类型
 *
 * @author laberwu
 */
@Entity
@DiscriminatorValue("CAROUSE_ADVERTISE_TYPE")
@BeanName(name = "轮播广告类型")
public class CarouseAdvertiseType extends Dictionary {
    private static final long serialVersionUID = 4890803046888755482L;

    /** 主站首页 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "主站首页", systemType = SystemType.EISS)
    public static final long EISS_INDEX = 1;

    /** 主站云虚拟主机 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "主站云虚拟主机", systemType = SystemType.EISS)
    public static final long EISS_VHOST = 2;

    /** 主站首页域名 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "主站首页域名", systemType = SystemType.EISS)
    public static final long EISS_INDEX_DOMAIN = 3;

    /** 主站云服务器 */
    @BeanAttrInfo(value = "4", orderBy = 4, cnName = "主站云服务器", systemType = SystemType.EISS)
    public static final long EISS_CHOST = 4;

    /** 主站网站建设 */
    @BeanAttrInfo(value = "5", orderBy = 5, cnName = "主站网站建设", systemType = SystemType.EISS)
    public static final long EISS_WEB_BUILD = 5;

    /** 主站企业办公 */
    @BeanAttrInfo(value = "6", orderBy = 6, cnName = "主站企业办公", systemType = SystemType.EISS)
    public static final long EISS_BUSINESS_OFFICE = 6;

    /** 主站代理加盟 */
    @BeanAttrInfo(value = "7", orderBy = 7, cnName = "主站代理加盟", systemType = SystemType.EISS)
    public static final long EISS_AGENT = 7;

    /** 主站数据库 */
    @BeanAttrInfo(value = "8", orderBy = 8, cnName = "主站数据库", systemType = SystemType.EISS)
    public static final long EISS_DATA_BASE = 8;

    /** 主站租用托管 */
    @BeanAttrInfo(value = "8", orderBy = 9, cnName = "主站租用托管", systemType = SystemType.EISS)
    public static final long EISS_RENTAL_HOSTING = 9;

    /** 弹出广告 */
    @BeanAttrInfo(value = "10", orderBy = 10, cnName = "弹出广告", systemType = SystemType.EISS)
    public static final long EISS_ALERT_ADVERTISE = 10;

    /** 主站SSL证书 */
    @BeanAttrInfo(value = "11", orderBy = 11, cnName = "主站SSL证书", systemType = SystemType.EISS)
    public static final long EISS_SSL = 11;

    /** 主站管理中心 */
    @BeanAttrInfo(value = "12", orderBy = 12, cnName = "主站管理中心", systemType = SystemType.EISS)
    public static final long EISS_MANAGER_CENTER = 12;
}
