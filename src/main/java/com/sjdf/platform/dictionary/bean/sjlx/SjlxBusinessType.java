package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 业务类别
 * User: ketqi
 * Date: 2015-06-02 10:35
 */
@Entity
@DiscriminatorValue("SJLX_BUSINESS_TYPE")
@BeanName(name = "sjlx-业务类别")
public class SjlxBusinessType extends Dictionary {
    private static final long serialVersionUID = 6129668113561803463L;

    /** 工商 */
    @BeanAttrInfo(cnName = "工商")
    public static final long ICBC = 1;

    /** 银行 */
    @BeanAttrInfo(cnName = "银行")
    public static final long BANK = 2;

    /** 税务 */
    @BeanAttrInfo(cnName = "税务")
    public static final long TAX = 3;

    /** 记账 */
    @BeanAttrInfo(cnName = "记账")
    public static final long ACCOUNT = 4;

    @BeanAttrInfo(cnName = "核名申请提交")
    public static final long PROCESS1 = 10L;

    @BeanAttrInfo(cnName = "待取核名通知书")
    public static final long PROCESS2 = 15L;

    @BeanAttrInfo(cnName = "待正式注册")
    public static final long PROCESS3 = 20L;

    @BeanAttrInfo(cnName = "待取营业执照")
    public static final long PROCESS4 = 25L;

    @BeanAttrInfo(cnName = "待银行开户")
    public static final long PROCESS5 = 30L;

    @BeanAttrInfo(cnName = "待领取开户许可证")
    public static final long PROCESS6 = 35L;

    @BeanAttrInfo(cnName = "待签订银税协议")
    public static final long PROCESS7 = 40L;

    @BeanAttrInfo(cnName = "待首次国地税报道")
    public static final long PROCESS8 = 45L;

    @BeanAttrInfo(cnName = "待二次税务报道")
    public static final long PROCESS9 = 50L;

    @BeanAttrInfo(cnName = "待提交申请")
    public static final long PROCESS10 = 55L;

    @BeanAttrInfo(cnName = "待领取")
    public static final long PROCESS11 = 60L;

    @BeanAttrInfo(cnName = "待办理")
    public static final long PROCESS12 = 65L;

    @BeanAttrInfo(cnName = "待办理")
    public static final long INVOICE_PURCHASE = 70L;

    @BeanAttrInfo(cnName = "领取三章")
    public static final long PROCESS100 = 100L;
}
