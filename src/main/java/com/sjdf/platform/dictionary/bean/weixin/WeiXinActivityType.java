package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微活动类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_ACTIVITY_TYPE")
@BeanName(name = "微活动类型")
public class WeiXinActivityType extends Dictionary {

    private static final long serialVersionUID = -8059917352936644547L;

    @BeanAttrInfo(value = "1", cnName = "优惠券")
    public static final long COUPON = 1L;

    @BeanAttrInfo(value = "2", cnName = "刮刮卡")
    public static final long SCRATCH_CARD = 2L;

    @BeanAttrInfo(value = "3", cnName = "大转盘")
    public static final long BIG_WHEEL = 3L;
}
