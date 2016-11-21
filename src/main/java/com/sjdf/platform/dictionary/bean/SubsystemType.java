package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 子系统类型
 * Create at 2012-04-28
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("SUB_SYSTEM_TYPE")
@BeanName(name = "子系统类型")
public class SubsystemType extends Dictionary {
    private static final long serialVersionUID = -5517909321103065754L;

    /** 自动任务 */
    @BeanAttrInfo(value = "auto task", cnName = "自动任务 ")
    public static final long AUTO_TASK = 1;

    /** 维护工具 */
    @BeanAttrInfo(value = "maenance tools", cnName = "维护工具")
    public static final long MAENANCE_TOOLS = 2;

    /** 控制面板 */
    @BeanAttrInfo(value = "control panel", cnName = "控制面板")
    public static final long CONTROL_PANEL = 3;

    /** 管理中心 */
    @BeanAttrInfo(value = "manager center", cnName = "管理中心 ")
    public static final long MANAGER_CENTER = 4;

    /** 业务后台 */
    @BeanAttrInfo(value = "business background", cnName = "业务后台")
    public static final long BUSINESS_BACKGROUND = 5;

    /** API */
    @BeanAttrInfo(value = "api", cnName = "API")
    public static final long API = 6;

    /** 产品设置 */
    @BeanAttrInfo(value = "product set", cnName = "产品设置")
    public static final long PRODUCT_SET = 7;

    /** 其他 */
    @BeanAttrInfo(value = "other", cnName = "其他")
    public static final long OTHER = 8;

    /** 财务 */
    @BeanAttrInfo(cnName = "财务")
    public static final long FINANCIAL = 9;

    @BeanAttrInfo(cnName = "产品管理")
    public static final long PRODUCT_MANAGE = 10;
}
