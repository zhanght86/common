package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 
 * IDC带宽计费方式
 * @date 2016年6月7日上午9:49:46
 * @author wangpeng
 * 
 */
@Entity
@DiscriminatorValue("IDC_BANDWIDTH_BILLING_MOTHOD")
@BeanName(name = "IDC带宽计费方式")
public class IdcBandWidthBillingMothod extends Dictionary{

    private static final long serialVersionUID = 4144807171438756651L;

    @BeanAttrInfo(cnName = "限速峰值")
    public static final long RATE_LIMITING_PEAK = 1;

    @BeanAttrInfo(cnName = "最高峰值")
    public static final long PEAK_VALUE = 2;

    @BeanAttrInfo(cnName = "第4峰值")
    public static final long FOURTH_PEAK = 3;

    @BeanAttrInfo(cnName = "95th峰值")
    public static final long NINETY_FIVE_TH_PEAK = 4;

    @BeanAttrInfo(cnName = "平均峰值")
    public static final long AVERAGE_PEAK_VALUE = 5;
}
