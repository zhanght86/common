package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 系统类型
 * Create at 2012-04-28
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("SYSTEM_TYPE")
@BeanName(name = "系统类型")
public class SystemType extends Dictionary {
    private static final long serialVersionUID = -5517909321103065754L;
    //当前系统类型标识
    public static final String CURRENT_SYSTEM_TYPE = "current-system-type";

    /** 主站系统 */
    @BeanAttrInfo(orderBy = 1, cnName = "主站系统")
    public static final long EISS = 1;

    /** 财务系统 */
    @BeanAttrInfo(orderBy = 2, cnName = "财务系统")
    public static final long FINANCE = 2;

    /** 备案系统 */
    @BeanAttrInfo(orderBy = 3, cnName = "备案系统")
    public static final long RECORD = 3;

    /** 域名管理系统 */
    @BeanAttrInfo(orderBy = 4, cnName = "域名管理系统")
    public static final long DOMAIN = 4;

    /** Web工具系统 */
    @BeanAttrInfo(orderBy = 5, cnName = "Web工具系统")
    public static final long EISS_TOOLS = 5;

    /** 业务公共平台 */
    @BeanAttrInfo(orderBy = 6, cnName = "业务公共平台")
    public static final long EISS_COMMON = 6;

    /** 受控端 */
    @BeanAttrInfo(orderBy = 7, cnName = "受控端")
    public static final long EISS_CLIENT = 7;

    /** 微信公众平台 */
    @BeanAttrInfo(orderBy = 8, cnName = "微信公众平台")
    public static final long WEI_XIN = 8;

    /** 工单系统 */
    @BeanAttrInfo(orderBy = 9, cnName = "工单系统")
    public static final long WORK_ORDER = 9;

    /** 监控系统 */
    @BeanAttrInfo(orderBy = 11, cnName = "监控系统")
    public static final long MONITOR = 11;

    /** 世纪利信-公司管理系统 */
    @BeanAttrInfo(orderBy = 12, cnName = "世纪利信-公司管理系统")
    public static final long SJLX_CMS = 12;

    /** 贷款审批系统 */
    @BeanAttrInfo(orderBy = 13, cnName = "贷款审批系统")
    public static final long LOAN = 13;

    /** 其他 */
    @BeanAttrInfo(orderBy = 999, cnName = "其他")
    public static final long OTHER = 999;

    /**
     * 获取当前系统类型
     *
     * @return long
     */
    public static long getCurrentSystemType() {
        String str = System.getProperty(CURRENT_SYSTEM_TYPE);
        if (PlatformUtils.hasText(str)) {
            return Long.parseLong(str);
        }
        return 0L;
    }
}
