package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-05
 *
 * @author ketqi
 * @category 客户对某产品或某咨询反馈回复等的评价等级
 */
@Entity
@DiscriminatorValue("APPRAISAL_LEVEL")
@BeanName(name = "客户评价等级")
public class AppraisalLevel extends Dictionary {
    private static final long serialVersionUID = -4354050594947908349L;
    /** 非常满意 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "非常满意")
    public static final long VERY_SATISFACTION = 1;

    /** 满意 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "满意")
    public static final long SATISFACTION = 2;

    /** 比较满意 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "比较满意")
    public static final long LESS_SATISFACTION = 3;

    /** 一般 */
    @BeanAttrInfo(value = "4", orderBy = 4, cnName = "一般")
    public static final long GENERAL = 4;

    /** 答非所问 */
    @BeanAttrInfo(value = "5", orderBy = 5, cnName = "答非所问")
    public static final long IRRELEVANT_ANSWER = 5;

    /** 技术有待提高 */
    @BeanAttrInfo(value = "6", orderBy = 6, cnName = "技术有待提高")
    public static final long TECHNOLOGY_IMPROVING = 6;

    /** 不满意 */
    @BeanAttrInfo(value = "7", orderBy = 7, cnName = "不满意")
    public static final long NO_SATISFACTION = 7;

    /** 投诉 */
    @BeanAttrInfo(value = "8", orderBy = 8, cnName = "投诉")
    public static final long COMPLAINT = 8;
}
