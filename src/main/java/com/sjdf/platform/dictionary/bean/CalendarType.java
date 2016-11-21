package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 日历类型
 * @ClassName CalendarType
 * @Created 2012 2012-11-28 上午9:46:13
 */
@Entity
@DiscriminatorValue("CALENDAR_TYPE")
@BeanName(name = "日历类型")
public class CalendarType extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-11-28 上午9:46:57
     */
    private static final long serialVersionUID = 9094771413656648151L;

    @BeanAttrInfo(value = "1", cnName = "天次")
    public static final long DAY_TYPE = 1;

    @BeanAttrInfo(value = "2", cnName = "周次")
    public static final long WEEK_TYPE = 2;

}
