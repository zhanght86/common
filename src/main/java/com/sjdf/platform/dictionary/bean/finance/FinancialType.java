package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 消费类型, 业务种类
 * Create at 2014年1月16日 下午2:09:56
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("FINANCIAL_TYPE")
@BeanName(name = "财务-业务种类")
public class FinancialType extends Dictionary {
    private static final long serialVersionUID = 975311104099439587L;

    @BeanAttrInfo(cnName = "汇款入账")
    public static final long REMITTANCE = 1;

    @BeanAttrInfo(cnName = "业务扣款")
    public static final long BUSINESS_DEBIT = 5;

    @BeanAttrInfo(cnName = "退款入户")
    public static final long REIMBURSE = 10;

    @BeanAttrInfo(cnName = "退款出账")
    public static final long RETURN_ACCOUNT = 15;

    @BeanAttrInfo(cnName = "资金借用")
    public static final long BORROW = 20;

    @BeanAttrInfo(cnName = "借款归还")
    public static final long RETURN_BORROW = 25;

    @BeanAttrInfo(cnName = "转E宝")
    public static final long TRANSFER_E = 30;

    @BeanAttrInfo(cnName = "赠送")
    public static final long GIVE = 35;

    @BeanAttrInfo(cnName = "转预付款")
    public static final long TRANSFER_MONEY = 40;

    @BeanAttrInfo(cnName = "E宝失效")
    public static final long INVALIDATE_UNIVERSAL_FUNDS = 45;
}
