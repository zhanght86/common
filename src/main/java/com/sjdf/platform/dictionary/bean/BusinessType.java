package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-03 09:27
 *
 * @category 业务种类
 */
@Entity
@DiscriminatorValue("BUSINESS_TYPE")
@BeanName(name = "业务种类")
public class BusinessType extends Dictionary {
    private static final long serialVersionUID = 1456059539489288143L;
    /** 汇款入账 */
    @BeanAttrInfo(cnName = "汇款入账")
    public static final long REMIT_MONEY = 1;
    /** 业务扣款 */
    @BeanAttrInfo(cnName = "业务扣款")
    public static final long BUSINESS_DEDUCTION = 2;
    /** 退款入户 */
    @BeanAttrInfo(cnName = "退款入户")
    public static final long REIMBURSE_IN = 3;
    /** 退款出账 */
    @BeanAttrInfo(cnName = "退款出账")
    public static final long REIMBURSE_OUT = 4;
    /** 优惠返款 */
    @BeanAttrInfo(cnName = "优惠返款")
    public static final long PREFERENTIAL_REBATE = 5;
    /** 资金借用 */
    @BeanAttrInfo(cnName = "资金借用")
    public static final long BORROWED_FUND = 6;
    /** 借款归还 */
    @BeanAttrInfo(cnName = "借款归还")
    public static final long RETURN_BORROWED = 7;
    /** 取消返款 */
    @BeanAttrInfo(cnName = "取消返款")
    public static final long CANCEL_REBATE = 8;
}
