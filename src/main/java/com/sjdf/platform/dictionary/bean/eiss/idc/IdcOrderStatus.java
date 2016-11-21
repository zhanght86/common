package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-16 10:58
 */
@Entity
@DiscriminatorValue("IDC_ORDER_STATUS")
@BeanName(name = "IDC订单状态")
public class IdcOrderStatus extends Dictionary {
    private static final long serialVersionUID = -3179858568844958338L;

    @BeanAttrInfo(cnName = "不通过")
    public static final long UN_PASS = 1;

    @BeanAttrInfo(cnName = "待支付")
    public static final long WAIT_PAY = 5;

    @BeanAttrInfo(cnName = "审核中")
    public static final long VERIFYING = 10;

    @BeanAttrInfo(cnName = "处理中")
    public static final long HANDLING = 15;

    @BeanAttrInfo(cnName = "已完成")
    public static final long COMPLETED = 20;

    @BeanAttrInfo(cnName = "已废弃")
    public static final long DISCARD = 99;
}
