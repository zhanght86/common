package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 发票状态
 * User: ketqi
 * Date: 2015-01-19 17:47
 */
@Entity
@DiscriminatorValue("INVOICE_STATUS")
@BeanName(name = "财务发票状态")
public class InvoiceStatus extends Dictionary {
    private static final long serialVersionUID = 7227764961700457292L;

    /** 未开票 */
    @BeanAttrInfo(orderBy = 1, cnName = "未开票")
    public static final long UN_DELIVER = 1;

    /** 已投递 */
    @BeanAttrInfo(orderBy = 5, cnName = "已投递")
    public static final long DELIVERED = 2;

    /** 已作废 */
    @BeanAttrInfo(orderBy = 10, cnName = "已作废")
    public static final long BEEN_INVALID = 3;

    /** 已开票 */
    @BeanAttrInfo(orderBy = 3, cnName = "已开票")
    public static final long DO_DELIVER = 9;
}
