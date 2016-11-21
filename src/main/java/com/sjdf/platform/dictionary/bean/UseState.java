package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 使用状态
 */
@Entity
@DiscriminatorValue("USE_STATE")
@BeanName(name = "使用状态")
public class UseState extends Dictionary {

    private static final long serialVersionUID = 3860066769750844362L;

    /** 未使用 */
    @BeanAttrInfo(value = "1", cnName = "未使用")
    public static final long UNUSED = 1;
    /** 已使用 */
    @BeanAttrInfo(value = "2", cnName = "已使用")
    public static final long USED = 2;
}
