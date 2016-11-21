package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年5月4日 下午5:58:37
 *
 * @author KETQI
 *         工单系统参数配置
 */
@Entity
@DiscriminatorValue("WORK_ORDER_SCOPE_CONFIG")
@BeanName(name = "工单接受配置")
public class WorkOrderScopeConfig extends Dictionary {
    /** 工单范围,【不接收工单：0】、【接收工单：1】或者【所有工单：2】 */
    @BeanAttrInfo(value = "关闭接入", cnName = "关闭接入")
    public static final long WORK_ORDER_ACCEPT = 0;
    /** 工单范围,【不接收工单：0】、【接收工单：1】或者【所有工单：2】 */
    @BeanAttrInfo(value = "接受部门派单", cnName = "接受部门派单")
    public static final long WORK_ORDER_ACCEPT1 = 1;
    /** 工单范围,【不接收工单：0】、【接收工单：1】或者【所有工单：2】 */
    @BeanAttrInfo(value = "接受所有工单", cnName = "接受所有工单")
    public static final long WORK_ORDER_ACCEPT2 = 2;
}
