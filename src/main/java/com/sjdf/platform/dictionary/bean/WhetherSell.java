package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-24
 *
 * @author 黄远昌
 * @category 是否购买
 */
@Entity
@DiscriminatorValue("WHETHER_SELL")
@BeanName(name = "是否销售")
public class WhetherSell extends Dictionary {
    private static final long serialVersionUID = 6977223466773546330L;
    /** 云主机是否销售 */
    @BeanAttrInfo(value = "1", cnName = "云主机是否销售")
    public static final long CHOST_IS_SELL = 1;
}
