package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年3月5日 下午1:27:52
 *
 * @author KETQI
 * @category 快递派送时间类型
 */
@Entity
@DiscriminatorValue("INVOICE_DELIVERY_TIME")
@BeanName(name = "快递派送时间类型")
public class InvoiceDeliveryTime extends Dictionary {
    private static final long serialVersionUID = -792012860155349831L;

    /** 1:只在工作日派送 */
    @BeanAttrInfo(cnName = "只在工作日派送")
    public static final long WORKDAY = 1;

    /** 2:只在双休日、节假日派送 */
    @BeanAttrInfo(cnName = "只在双休日、节假日派送")
    public static final long HOLIDAY = 2;

    /** 3:所有时间均可派送 */
    @BeanAttrInfo(cnName = "所有时间均可派送")
    public static final long ANYTIME = 3;
}
