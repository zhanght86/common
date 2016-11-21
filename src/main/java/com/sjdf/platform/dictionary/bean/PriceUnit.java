package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 价格单位(一单位, 二单位, 三单位, 四单位, 五单位, 六单位, 七单位, 八单位, 九单位, 十单位)
 */
@Entity
@DiscriminatorValue("PRICE_UNIT")
@BeanName(name = "价格单位")
public class PriceUnit extends Dictionary {
    private static final long serialVersionUID = 6401560418688501625L;
    /** 一单位 */
    @BeanAttrInfo(value = "1", cnName = "一单位")
    public static final long UNIT_1 = 1;
    /** 二单位 */
    @BeanAttrInfo(value = "2", cnName = "二单位")
    public static final long UNIT_2 = 2;
    /** 三单位 */
    @BeanAttrInfo(value = "3", cnName = "三单位")
    public static final long UNIT_3 = 3;
    /** 四单位 */
    @BeanAttrInfo(value = "4", cnName = "四单位")
    public static final long UNIT_4 = 4;
    /** 五单位 */
    @BeanAttrInfo(value = "5", cnName = "五单位")
    public static final long UNIT_5 = 5;
    /** 六单位 */
    @BeanAttrInfo(value = "6", cnName = "六单位")
    public static final long UNIT_6 = 6;
    /** 七单位 */
    @BeanAttrInfo(value = "7", cnName = "七单位")
    public static final long UNIT_7 = 7;
    /** 八单位 */
    @BeanAttrInfo(value = "8", cnName = "八单位")
    public static final long UNIT_8 = 8;
    /** 九单位 */
    @BeanAttrInfo(value = "9", cnName = "九单位")
    public static final long UNIT_9 = 9;
    /** 十单位 */
    @BeanAttrInfo(value = "10", cnName = "十单位")
    public static final long UNIT_10 = 10;
}
