package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-08-29
 *
 * @author ketqi
 * @category 是否需要备案
 */
@Entity
@DiscriminatorValue("NEED_TO_RECORD")
@BeanName(name = "是否需要备案")
public class NeedToRecord extends Dictionary {
    private static final long serialVersionUID = 2765671044276157325L;

    @BeanAttrInfo(cnName = "需要", orderBy = 1)
    public static final long YES = 1;// 需要备案

    @BeanAttrInfo(cnName = "不需要", orderBy = 2)
    public static final long NO = 2;// 不需要备案
}
