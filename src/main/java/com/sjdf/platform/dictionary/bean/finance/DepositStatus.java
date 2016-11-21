package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 入款状态
 * User: ketqi
 * Date: 2014-11-20 15:03
 */
@Entity
@DiscriminatorValue("DEPOSIT_STATUS")
@BeanName(name = "财务-入款状态")
public class DepositStatus extends Dictionary {
    private static final long serialVersionUID = -658352222420089159L;

    /** 否 */
    @BeanAttrInfo(cnName = "否")
    public static final long NO = 1;

    /** 是 */
    @BeanAttrInfo(cnName = "是")
    public static final long YES = 5;

    /** 驳回 */
    @BeanAttrInfo(cnName = "驳回")
    public static final long REJECT = 10;
}
