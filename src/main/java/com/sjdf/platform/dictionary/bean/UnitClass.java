package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-11-26
 *
 * @author 王鸣忠
 * @category 年限分类（1年、2年、3年、5年等）
 */
@Entity
@DiscriminatorValue("UNIT_CLASS")
@BeanName(name = "年限分类")
public class UnitClass extends Dictionary {

    private static final long serialVersionUID = -7491830513067214998L;
    /** 1年 */
    @BeanAttrInfo(value = "1", cnName = "1年")
    public static final long YEAR_1 = 1;

    /** 2年 */
    @BeanAttrInfo(value = "2", cnName = "2年")
    public static final long YEAR_2 = 2;

    /** 3年 */
    @BeanAttrInfo(value = "3", cnName = "3年")
    public static final long YEAR_3 = 3;

    /** 5年 */
    @BeanAttrInfo(value = "5", cnName = "5年")
    public static final long YEAR_5 = 5;
}
