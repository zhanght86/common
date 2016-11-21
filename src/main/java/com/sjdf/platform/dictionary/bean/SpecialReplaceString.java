package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:20:20
 *
 * @author KETQI
 * @category 备案特殊省份(字符串)
 */
@Entity
@DiscriminatorValue("SPECIAL_REPLACE_STRING")
@BeanName(name = "特殊替换字符")
public class SpecialReplaceString extends Dictionary {
    private static final long serialVersionUID = 2882157632658724038L;

    /** 特殊替换字符：{X} */
    @BeanAttrInfo(value = "{X}", orderBy = 1, cnName = "特殊替换字符：{X}")
    public static final long SPECIAL_REPLACE_STR_X = 1;
}
