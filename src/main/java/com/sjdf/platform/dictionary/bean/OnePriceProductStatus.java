package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * 
 * 2016-02-25
 * @category 一口价域名状态
 * @author wangpeng
 *
 */
@Entity
@DiscriminatorValue("ONE_PRICE_PRODUCT_STATUS")
@BeanName(name = "一口价域名状态")
public class OnePriceProductStatus extends Dictionary{

    private static final long serialVersionUID = 5311413657104196938L;

    @BeanAttrInfo(value = "1", cnName = "处理中")
    public static final long BUY_SUCESS = 1;

    @BeanAttrInfo(value = "2", cnName = "转移码已经获取")
    public static final long PROCESSING = 2;

    @BeanAttrInfo(value = "3", cnName = "交易成功")
    public static final long TRANSACTION_SUCCESS = 3;
}