package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-12-06
 *
 * @author 陈绍刚
 * @category 抢购状态
 */
@Entity
@DiscriminatorValue("PANIC_BUY_STATUS")
@BeanName(name = "抢购状态")
public class PanicBuyStatus extends Dictionary {
    private static final long serialVersionUID = -9099759832948333568L;

    /** 抢购倒计时 */
    @BeanAttrInfo(value = "1", cnName = "抢购倒计时")
    public static final long BEFORE_START = 1;

    /** 抢购进行中 */
    @BeanAttrInfo(value = "2", cnName = "抢购进行中")
    public static final long HAS_START = 2;

    /** 产品已售罄 */
    @BeanAttrInfo(value = "3", cnName = "产品已售罄")
    public static final long NO_PRODUCT = 3;

    /** 抢购已结束 */
    @BeanAttrInfo(value = "4", cnName = "抢购已结束")
    public static final long HAS_END = 4;
}
