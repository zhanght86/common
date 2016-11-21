package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-18 11:22
 */
@Entity
@DiscriminatorValue("IMS_ORDER_STATUS")
@BeanName(name = "IMS订单状态")
public class ImsOrderStatus extends Dictionary {
    private static final long serialVersionUID = -6679846110951125409L;

    @BeanAttrInfo(cnName = "已下单")
    public static final long PLACED_ORDER = 1;

    @BeanAttrInfo(cnName = "已发货")
    public static final long SHIPPED_ORDER = 5;

    @BeanAttrInfo(cnName = "已完成")
    public static final long COMPLETED_ORDER = 10;

    @BeanAttrInfo(cnName = "已废弃")
    public static final long ABANDONED_ORDER = 20;
}
