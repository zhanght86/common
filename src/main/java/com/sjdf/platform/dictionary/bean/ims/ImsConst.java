package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-21 09:12
 */
@Entity
@DiscriminatorValue("IMS_CONST")
@BeanName(name = "IMS常量")
public class ImsConst extends Dictionary {
    private static final long serialVersionUID = -8997929556662302469L;

    @BeanAttrInfo(value = "1", cnName = "试用天数")
    public static final long TRIAL_DAYS = 1;

    @BeanAttrInfo(value = "1", cnName = "购买年限")
    public static final long BUY_YEARS = 2;

    @BeanAttrInfo(value = "1", cnName = "续费年限")
    public static final long RENEW_YEARS = 3;

    @BeanAttrInfo(value = "300", cnName = "购买费用")
    public static final long BUY_PRICE = 4;

    @BeanAttrInfo(value = "300", cnName = "续费费用")
    public static final long RENEW_PRICE = 5;
}
