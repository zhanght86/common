package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-16 10:59
 */
@Entity
@DiscriminatorValue("IDC_ORDER_INTERNAL_STATUS")
@BeanName(name = "IDC订单内部状态")
public class IdcOrderInternalStatus extends Dictionary {
    private static final long serialVersionUID = -9025377749468289634L;

    /** 客户经理未审核 */
    @BeanAttrInfo(cnName = "客户经理未审核")
    public static final long CLIENT_MANAGER_UN_VERIFY = 1;

    /** 部门经理未审核（客户经理已审核） */
    @BeanAttrInfo(cnName = "部门经理未审核")
    public static final long DEPARTMENT_MANAGER_UN_VERIFY = 5;

    /** 业务中心未审核（部门经理已审核） */
    @BeanAttrInfo(cnName = "业务中心未审核")
    public static final long BUSINESS_CENTER_UN_VERIFY = 10;

    /** 待支付（业务扣款失败，需要客户支付） */
    @BeanAttrInfo(cnName = "待支付")
    public static final long WAIT_PAY = 15;

    /** 客服部未确认（业务中心已审核） */
    @BeanAttrInfo(cnName = "客服部未确认")
    public static final long CUSTOMER_SERVICE_UN_CONFIRM = 20;

    /** 客服部已确认（等待硬件采购/托管采购状态完成，仅服务器租用/设备租用有硬件采购） */
    @BeanAttrInfo(cnName = "客服部已确认")
    public static final long CUSTOMER_SERVICE_CONFIRMED = 25;

    /** 技术部未确认（客服部已审核） */
    @BeanAttrInfo(cnName = "技术部未确认")
    public static final long TECHNICAL_DEPARTMENT_UN_CONFIRM = 30;

    /** 技术部已确认 */
    @BeanAttrInfo(cnName = "技术部已确认")
    public static final long TECHNICAL_DEPARTMENT_CONFIRMED = 35;

    /** 技术部未复核（技术部已完成） */
    @BeanAttrInfo(cnName = "技术部未复核")
    public static final long TECHNICAL_DEPARTMENT_UN_REVIEW = 40;

    /** 已完成（技术部已复核、【硬件代购】产品客户部已审核） */
    @BeanAttrInfo(cnName = "已完成")
    public static final long COMPLETED = 45;

    /** 审核不通过：客户可修改产品配置信息。 */
    @BeanAttrInfo(cnName = "审核不通过")
    public static final long UN_PASS = 50;

    /** 已废弃（具有废弃订单权限的管理员废弃订单） */
    @BeanAttrInfo(cnName = "已废弃")
    public static final long DISCARD = 99;
}
