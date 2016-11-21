package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 发票类型
 * User: ketqi
 * Date: 2016-08-31 11:14
 */
@Entity
@DiscriminatorValue("IDC_INVOICE_TYPE")
@BeanName(name = "财务发票类型")
public class InvoiceType extends Dictionary {
    private static final long serialVersionUID = 7646312341051513672L;

    /** 专票 */
    @BeanAttrInfo(cnName = "专票")
    public static final long SPECIAL_INVOICE = 1;

    /** 普票 */
    @BeanAttrInfo(cnName = "普票")
    public static final long ORDINARY_INVOICE = 5;
}
