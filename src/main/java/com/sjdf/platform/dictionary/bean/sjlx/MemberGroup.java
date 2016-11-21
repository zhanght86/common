package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 会员级别
 * User: ketqi
 * Date: 2015-05-19 17:46
 */
@Entity
@DiscriminatorValue("SJLX_MEMBER_GROUP")
@BeanName(name = "sjlx-会员级别")
public class MemberGroup extends Dictionary {
    private static final long serialVersionUID = 8801681293363530992L;

    @BeanAttrInfo(cnName = "友好会员")
    public static final long FRIEND_AGENT = 1;


    @BeanAttrInfo(cnName = "VIP 客户")
    public static final long VIP_AGENT = 10;

    /**
     * 注意:
     * 直接客户的会员级别小于100
     * 代理客户的会员界别大于等于100
     */
    @BeanAttrInfo(cnName = "金牌代理")
    public static final long GOLD_AGENT = 100;

    @BeanAttrInfo(cnName = "核心代理")
    public static final long CORE_AGENT = 110;

    @BeanAttrInfo(cnName = "钻石代理")
    public static final long DIAMOND_AGENT = 120;
    @BeanAttrInfo(cnName = "伙伴代理")
    public static final long PARTNER_AGENT = 130;
}
