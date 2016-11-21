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
@DiscriminatorValue("IMS_ORDER_TYPE")
@BeanName(name = "IMS订单类型")
public class ImsOrderType extends Dictionary {
    private static final long serialVersionUID = -3590807914589494069L;
    /** 购买 */
    @BeanAttrInfo(cnName = "购买")
    public static final long BUY = 1;

    /** 试用 */
    @BeanAttrInfo(cnName = "试用")
    public static final long TRIAL = 2;

    /** 赠送 */
    @BeanAttrInfo(cnName = "赠送")
    public static final long GIFT = 3;
}
