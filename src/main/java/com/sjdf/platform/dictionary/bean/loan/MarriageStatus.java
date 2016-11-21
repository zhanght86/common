package com.sjdf.platform.dictionary.bean.loan;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 婚姻状状况
 * User: ketqi
 * Date: 2016-01-21 16:02
 */
@Entity
@DiscriminatorValue("LOAN_MARRIAGE_STATUS")
@BeanName(name = "婚姻状状况")
public class MarriageStatus extends Dictionary {
    @BeanAttrInfo(cnName = "已婚")
    public static final long MARRIED = 1;

    @BeanAttrInfo(cnName = "单身")
    public static final long SINGLE = 5;

    @BeanAttrInfo(cnName = "离异")
    public static final long DIVORCED = 10;
}
