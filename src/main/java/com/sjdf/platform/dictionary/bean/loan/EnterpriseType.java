package com.sjdf.platform.dictionary.bean.loan;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 企业类型
 * User: ketqi
 * Date: 2016-01-21 15:43
 */
@Entity
@DiscriminatorValue("LOAN_ENTERPRISE_TYPE")
@BeanName(name = "企业类型")
public class EnterpriseType extends Dictionary {
    @BeanAttrInfo(cnName = "生产")
    public static final long PRODUCE = 1;

    @BeanAttrInfo(cnName = "销售")
    public static final long SELL = 5;

    @BeanAttrInfo(cnName = "代理")
    public static final long PROXY = 10;

    @BeanAttrInfo(cnName = "内贸")
    public static final long DOMESTIC = 15;

    @BeanAttrInfo(cnName = "出口")
    public static final long EXPORT = 20;
}
