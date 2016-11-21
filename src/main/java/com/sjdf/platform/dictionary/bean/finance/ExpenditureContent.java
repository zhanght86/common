package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 支出内容
 * User: ketqi
 * Date: 2014-12-04 10:34
 */
@Entity
@DiscriminatorValue("EXPENDITURE_CONTENT")
@BeanName(name = "财务-支出内容")
public class ExpenditureContent extends Dictionary {
    private static final long serialVersionUID = -1729618656478053663L;

    /** 支出内容1 */
    @BeanAttrInfo(value = "支出内容1", cnName = "支出内容1")
    public static final long CONTENT_1 = 1L;
    /** 支出内容2 */
    @BeanAttrInfo(value = "支出内容2", cnName = "支出内容2")
    public static final long CONTENT_2 = 2L;
    /** 支出内容3 */
    @BeanAttrInfo(value = "支出内容3", cnName = "支出内容3")
    public static final long CONTENT_3 = 3L;
}
