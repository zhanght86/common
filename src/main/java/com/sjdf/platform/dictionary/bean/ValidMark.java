package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-11
 *
 * @author 王正伟
 * @category 有效标记
 */
@Entity
@DiscriminatorValue("VALID_MARK")
@BeanName(name = "有效状态")
public class ValidMark extends Dictionary {
    private static final long serialVersionUID = -4751763725470767797L;
    /** 有效 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "有效 ")
    public static final long VALID = 1;
    /** 无效 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "无效")
    public static final long INVALID = 2;
    /** 已经删除 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "已经删除")
    public static final long HAS_DELETED = 3;
}
