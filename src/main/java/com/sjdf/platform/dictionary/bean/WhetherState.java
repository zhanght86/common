package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 是否状态
 */
@Entity
@DiscriminatorValue("WHETHER_STATE")
@BeanName(name = "是否状态")
public class WhetherState extends Dictionary {
    private static final long serialVersionUID = -8230568652965100964L;
    /** 是 */
    @BeanAttrInfo(value = "1", cnName = "是")
    public static final long YES = 1;
    /** 否 */
    @BeanAttrInfo(value = "2", cnName = "否")
    public static final long NO = 2;
}
