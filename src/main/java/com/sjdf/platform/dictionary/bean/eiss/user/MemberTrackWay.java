package com.sjdf.platform.dictionary.bean.eiss.user;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 会员跟踪途径
 * value标识有效次数
 * User: ketqi
 * Date: 2015-10-14 15:20
 */
@Entity
@DiscriminatorValue("MEMBER_TRACK_WAY")
@BeanName(name = "会员跟踪途径")
public class MemberTrackWay extends Dictionary {
    private static final long serialVersionUID = 6061048523611740140L;

    @BeanAttrInfo(value = "3", cnName = "电话")
    public static final long TRACK_MOBILE = 1;

    @BeanAttrInfo(value = "3", cnName = "短信")
    public static final long TRACK_SMS = 5;

    @BeanAttrInfo(value = "3", cnName = "QQ")
    public static final long TRACK_QQ = 10;

    @BeanAttrInfo(value = "3", cnName = "微信")
    public static final long TRACK_WEIXIN = 15;
}
