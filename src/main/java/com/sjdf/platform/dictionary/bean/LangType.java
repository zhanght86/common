package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-05
 *
 * @author 王正伟
 * @category 语言类型
 */

@Entity
@DiscriminatorValue("LANG_TYPE")
@BeanName(name = "语言类型")
public class LangType extends Dictionary {
    private static final long serialVersionUID = -6913208264989783013L;
    /** 默认 */
    @BeanAttrInfo(value = "1", cnName = "默认", refClass = LangType.class, refAttr = LangType.CN)
    public static final long DEFAULT = 1;

    /** 英文 */
    @BeanAttrInfo(value = "2", cnName = "英文")
    public static final long EN = 2;

    /** 中文 */
    @BeanAttrInfo(value = "3", cnName = "中文")
    public static final long CN = 3;

    /** 其他 */
    @BeanAttrInfo(value = "99", cnName = "其他")
    public static final long OTHER = 99;
}
