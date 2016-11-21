package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-06-03 14:14
 * 工单自动检测执行条目
 */
@Entity
@DiscriminatorValue("WORK_ORDER_EXECUTE_ITEM")
@BeanName(name = "工单自动检测执行条目")
public class WorkOrderExecuteItem extends Dictionary {
    private static final long serialVersionUID = -6702062973108165614L;

    /** 虚拟主机-无法访问-解析IP与产品IP一致性 */
    @BeanAttrInfo(cnName = "解析IP与产品IP一致性", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_IP_PARSE = 1;

    /** 虚拟主机-无法访问-产品运行状态 */
    @BeanAttrInfo(cnName = "产品运行状态", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_PRODUCT_STATUS = 2;

    /** 虚拟主机-无法访问-域名黑名单状态 */
    @BeanAttrInfo(cnName = "域名黑名单状态", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_DOMAIN_BLACKLIST_STATUS = 3;

    /** 虚拟主机-无法访问-产品绑定状态 */
    @BeanAttrInfo(cnName = "产品绑定状态", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_PRODUCT_BIND_STATUS = 4;

    /** 虚拟主机-无法访问-域名状态 */
    @BeanAttrInfo(cnName = "域名状态", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_DOMAIN_STATUS = 5;

    /** 虚拟主机-无法访问-白名单状态 */
    @BeanAttrInfo(cnName = "白名单状态", refClass = WorkOrderAutomaticReplyMark.class, refAttr = WorkOrderAutomaticReplyMark.VHOST_INACCESSIBLE)
    public static final long VHOST_WHITELIST_STATUS = 6;

}
