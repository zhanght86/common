package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-07-05
 *
 * @author Chen Mohan
 * @category 相同状态
 */
@Entity
@DiscriminatorValue("SAME_STATUS")
@BeanName(name = "相同状态")
public class SameStatus extends Dictionary {
    private static final long serialVersionUID = -8230568652965100964L;

    /** 相同 */
    @BeanAttrInfo(value = "1", cnName = "相同")
    public static final long SAME = 1;

    /** 不相同 */
    @BeanAttrInfo(value = "2", cnName = "不相同")
    public static final long DIFFERENT = 2;
}
