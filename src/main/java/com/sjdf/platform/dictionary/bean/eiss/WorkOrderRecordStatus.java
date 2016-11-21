package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.WhetherState;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-09-02 14:30
 */
@Entity
@DiscriminatorValue("WORK_ORDER_RECORD_STATUS")
@BeanName(name = "工单备案系统状态")
public class WorkOrderRecordStatus extends Dictionary {
    private static final long serialVersionUID = 4234230975903321102L;

    /** 是 */
    @BeanAttrInfo(cnName = "是")
    public static final long YES = WhetherState.YES;

    /** 否 */
    @BeanAttrInfo(cnName = "否")
    public static final long NO = WhetherState.NO;

    /** 待接入商审核 */
    @BeanAttrInfo(cnName = "待接入商审核")
    public static final long WAIT_PROVIDER_VERIFY = 5;

    /** 待管局审核 */
    @BeanAttrInfo(cnName = "待管局审核")
    public static final long WAIT_PARENT_VERIFY = 10;

    /** 我司退回 */
    @BeanAttrInfo(cnName = "我司退回")
    public static final long OWNER_RETURN = 15;

    /** 管局退回 */
    @BeanAttrInfo(cnName = "管局退回")
    public static final long PARENT_RETURN = 20;
}
