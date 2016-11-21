package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-14
 *
 * @author 王正伟
 * @category 限制状态
 */
@Entity
@DiscriminatorValue("LIMIT_STATE")
@BeanName(name = "限制状态")
public class LimitState extends Dictionary {
    private static final long serialVersionUID = -5509655426681852752L;
    /** 允许 */
    @BeanAttrInfo(value = "1", cnName = "允许")
    public static final long ALLOW = 1;
    /** 禁止 */
    @BeanAttrInfo(value = "2", cnName = "禁止")
    public static final long FORBID = 2;
}
