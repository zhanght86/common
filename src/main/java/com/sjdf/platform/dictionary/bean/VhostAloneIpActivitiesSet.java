package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2014-05-26
 *
 * @author hunk
 * @category 虚拟主机独立IP购买优惠活动设置
 */
@Entity
@DiscriminatorValue("VHOST_ALONE_IP_ACTIVITIES_SET")
@BeanName(name = "虚拟主机独立IP购买优惠活动设置")
public class VhostAloneIpActivitiesSet extends Dictionary {

    private static final long serialVersionUID = -8358504902344013905L;

    /** 活动产品ID */
    @BeanAttrInfo(value = "host20100409071", cnName = "活动产品ID")
    public static final long ACTIVITIES_PRODUCT_ID = 1;

    /** 活动机房ID */
    @BeanAttrInfo(value = "37", cnName = "活动机房ID")
    public static final long ACTIVITIES_IDC_ID = 2;

    /** 活动用户级别（针对所有级别请设置为空，设置级别前后必须用逗号分隔） */
    @BeanAttrInfo(value = ",1,4,16,", cnName = "活动用户级别（针对所有级别请设置为空，设置级别前后必须用逗号分隔）")
    public static final long ACTIVITIES_MEMBER_LEVEL = 3;

    /** 活动状态（是否开启：1-是，2-否） */
    @BeanAttrInfo(value = "1", cnName = "活动状态（是否开启：1-是，2-否）")
    public static final long ACTIVITIES_STATUS = 4;
}
