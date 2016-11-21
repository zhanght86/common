package com.sjdf.platform.dictionary.bean.eiss.user;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-07-01 16:15
 */
@Entity
@DiscriminatorValue("MEMBER_REJECT_REASON")
@BeanName(name = "会员驳回原因")
public class MemberRejectReason extends Dictionary {
    @BeanAttrInfo(cnName = "驳回原因1", value = "驳回原因1")
    public static final long REJECT_REASON_1 = 1;
}
