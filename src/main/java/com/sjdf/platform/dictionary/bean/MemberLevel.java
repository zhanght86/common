package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 会员级别
 * 2012-06-04
 *
 * @author 黄远昌
 */
@Entity
@DiscriminatorValue("MEMBER_LEVEL")
@BeanName(name = "会员级别")
public class MemberLevel extends Dictionary {
    private static final long serialVersionUID = -4596183189875853898L;

    @BeanAttrInfo(value = "4,6,7", cnName = "代理")
    public static final long AGENT = 1000;

    @BeanAttrInfo(value = "1,16,18", cnName = "非代理")
    public static final long UN_AGENT = 1001;

    /** 会员级别 */
    @BeanAttrInfo(value = "16", cnName = "高级会员")
    public static final long MEMBER_LEVEL = 16;

    /** 主站未登录时默认会员名称 */
    @BeanAttrInfo(value = "sjdfowner", cnName = "主站未登录时默认会员名称")
    public static final long DEFAULT_USER_NAME = 1;

    /** 主站未登录时默认会员编号 */
    @BeanAttrInfo(value = "17173609", cnName = "主站未登录时默认会员编号")
    public static final long DEFAULT_USER_ID = 2;
}
