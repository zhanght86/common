package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-03 15:24
 *
 * @category 快递派送时间
 */
@Entity
@DiscriminatorValue("DELIVERY_TIME")
@BeanName(name = "快递派送时间")
public class DeliveryDate extends Dictionary {
    private static final long serialVersionUID = -4442514282585196469L;

    /** 只在工作日派送 */
    @BeanAttrInfo(cnName = "只在工作日派送")
    public static final long WORK_DAY = 1;

    /** 只在双休日、节假日派送 */
    @BeanAttrInfo(cnName = "只在双休日、节假日派送")
    public static final long WEEKEND_OR_HOLIDAY = 2;

    /** 所有时间均可派送 */
    @BeanAttrInfo(cnName = "所有时间均可派送")
    public static final long ANYTIME = 3;
}
