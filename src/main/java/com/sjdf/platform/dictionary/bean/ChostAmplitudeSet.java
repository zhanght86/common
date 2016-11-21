package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 云主机幅度参数设置
 * @ClassName ChostAmplitudeSet
 * @Created 2012 2012-12-3 上午9:59:49
 */
@Entity
@DiscriminatorValue("CHOST_AMPLITUDE_SET")
@BeanName(name = "云主机幅度参数设置")
public class ChostAmplitudeSet extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-12-3 上午10:00:42
     */
    private static final long serialVersionUID = 7828674325747873496L;

    /**
     * -90%
     */
    @BeanAttrInfo(value = "-0.9", cnName = "-90% ")
    public static final long PERCENT_MINUS_NINETY = 11;

    /**
     * -80%
     */
    @BeanAttrInfo(value = "-0.8", cnName = "-80% ")
    public static final long PERCENT_MINUS_EIGHTY = 12;

    /**
     * -70%
     */
    @BeanAttrInfo(value = "-0.7", cnName = "-70% ")
    public static final long PERCENT_MINUS_SEVENTY = 13;

    /**
     * -60%
     */
    @BeanAttrInfo(value = "-0.6", cnName = "-60% ")
    public static final long PERCENT_MINUS_SIXTY = 14;

    /**
     * -50%
     */
    @BeanAttrInfo(value = "-0.5", cnName = "-50% ")
    public static final long PERCENT_MINUS_FIFTY = 15;

    /**
     * -40%
     */
    @BeanAttrInfo(value = "-0.4", cnName = "-40% ")
    public static final long PERCENT_MINUS_FORTY = 16;

    /**
     * -30%
     */
    @BeanAttrInfo(value = "-0.3", cnName = "-30% ")
    public static final long PERCENT_MINUS_THIRTY = 17;

    /**
     * -20%
     */
    @BeanAttrInfo(value = "-0.2", cnName = "-20% ")
    public static final long PERCENT_MINUS_TWENTY = 18;

    /**
     * -10%
     */
    @BeanAttrInfo(value = "-0.1", cnName = "-10% ")
    public static final long PERCENT_MINUS_TEN = 19;

    /**
     * 0
     */
    @BeanAttrInfo(value = "0.0", cnName = "0 ")
    public static final long PERCENT_ZERO = 20;

    /**
     * 10%
     */
    @BeanAttrInfo(value = "0.1", cnName = "10% ")
    public static final long PERCENT_TEN = 21;

    /**
     * 20%
     */
    @BeanAttrInfo(value = "0.2", cnName = "20% ")
    public static final long PERCENT_TWENTY = 22;

    /**
     * 30%
     */
    @BeanAttrInfo(value = "0.3", cnName = "30% ")
    public static final long PERCENT_THIRTY = 23;

    /**
     * 40%
     */
    @BeanAttrInfo(value = "0.4", cnName = "40% ")
    public static final long PERCENT_FORTY = 24;

    /**
     * 50%
     */
    @BeanAttrInfo(value = "0.5", cnName = "50% ")
    public static final long PERCENT_FIFTY = 25;

    /**
     * 60%
     */
    @BeanAttrInfo(value = "0.6", cnName = "60% ")
    public static final long PERCENT_SIXTY = 26;

    /**
     * 70%
     */
    @BeanAttrInfo(value = "0.7", cnName = "70% ")
    public static final long PERCENT_SEVENTY = 27;

    /**
     * 80%
     */
    @BeanAttrInfo(value = "0.8", cnName = "80% ")
    public static final long PERCENT_EIGHTY = 28;

    /**
     * 90%
     */
    @BeanAttrInfo(value = "0.9", cnName = "90% ")
    public static final long PERCENT_NINETY = 29;

    /**
     * 100%
     */
    @BeanAttrInfo(value = "1.0", cnName = "100% ")
    public static final long PERCENT_ONE_HUNDRED = 30;

    /**
     * 200%
     */
    @BeanAttrInfo(value = "2.0", cnName = "200% ")
    public static final long PERCENT_TWO_HUNDRED = 31;

    /**
     * 300%
     */
    @BeanAttrInfo(value = "3.0", cnName = "300% ")
    public static final long PERCENT_THREE_HUNDRED = 32;

    /**
     * 400%
     */
    @BeanAttrInfo(value = "4.0", cnName = "400% ")
    public static final long PERCENT_FOUR_HUNDRED = 33;

    /**
     * 500%
     */
    @BeanAttrInfo(value = "5.0", cnName = "500% ")
    public static final long PERCENT_FIVE_HUNDRED = 34;

}
