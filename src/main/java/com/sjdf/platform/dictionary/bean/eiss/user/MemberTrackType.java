package com.sjdf.platform.dictionary.bean.eiss.user;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 会员跟踪大分类
 * User: ketqi
 * Date: 2015-10-14 15:01
 */
@Entity
@DiscriminatorValue("MEMBER_TRACK_TYPE")
@BeanName(name = "会员跟踪大分类")
public class MemberTrackType extends Dictionary {
    private static final long serialVersionUID = 1993114557314180074L;

    @BeanAttrInfo(cnName = "电话不通")
    public static final long MOBILE_NOT_CONNECT = 1;

    @BeanAttrInfo(cnName = "有意向")
    public static final long HAS_INTENT = 2;
}
