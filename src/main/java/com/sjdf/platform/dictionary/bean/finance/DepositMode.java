package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年1月17日 下午3:05:47
 * 入款方式标志
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("DEPOSIT_MODE")
@BeanName(name = "财务-入款类型")
public class DepositMode extends Dictionary {
    private static final long serialVersionUID = 860243464618461011L;

    @BeanAttrInfo(cnName = "客户入款")
    public static final long CONSUMER_INCOME = 1;

    @BeanAttrInfo(cnName = "超管入款")
    public static final long ADMIN_INCOME = 2;

    @BeanAttrInfo(cnName = "系统入款")
    public static final long SYSTEM_INCOME = 3;

    @BeanAttrInfo(cnName = "超管支出")
    public static final long ADMIN_DISBURSE = 10;
}
