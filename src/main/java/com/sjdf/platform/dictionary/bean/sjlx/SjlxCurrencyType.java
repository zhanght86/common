package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SJLX_CURRENCY_TYPE")
@BeanName(name = "sjlx-币种类型")
public class SjlxCurrencyType extends Dictionary {
    private static final long serialVersionUID = 2032829489670096963L;

    /** 美元 */
    @BeanAttrInfo(cnName = "美元")
    public static final long US_DOLLAR = 1;

    /** 港币 */
    @BeanAttrInfo(cnName = "港币")
    public static final long HK_DOLLAR = 2;

    /** 日元 */
    @BeanAttrInfo(cnName = "日元")
    public static final long JP_DOLLAR = 3;
    /** 人民币 */
    @BeanAttrInfo(cnName = "人民币")
    public static final long CH_DOLLAR = 4;
}
