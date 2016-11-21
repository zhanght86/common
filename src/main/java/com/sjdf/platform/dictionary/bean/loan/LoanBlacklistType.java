package com.sjdf.platform.dictionary.bean.loan;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2015-12-28 15:49
 */
@Entity
@DiscriminatorValue("LOAN_BLACKLIST_TYPE")
@BeanName(name = "黑名单类型")
public class LoanBlacklistType extends Dictionary {
    private static final long serialVersionUID = 7265958628567789737L;

    @BeanAttrInfo(cnName = "手机号码")
    public static final long MOBILE = 1;

    @BeanAttrInfo(cnName = "身份证")
    public static final long IDCARD_NUM = 2;
}
