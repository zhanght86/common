package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-03 15:33
 *
 * @category 快递状态
 */
@Entity
@DiscriminatorValue("EXPRESS_STATUS")
@BeanName(name = "快递状态")
public class ExpressStatus extends Dictionary {
    private static final long serialVersionUID = 5247032171686705606L;

    /** 待快递 */
    @BeanAttrInfo(orderBy = 2, cnName = "待快递")
    public static final long WAIT_EXPRESS = 1;

    /** 已快递 */
    @BeanAttrInfo(orderBy = 1, cnName = "已快递")
    public static final long EXPRESSED = 2;

    /** 账户余额不足 */
    @BeanAttrInfo(orderBy = 99, cnName = "账户余额不足")
    public static final long INSUFFICIENT_BALANCE = 99;
}
