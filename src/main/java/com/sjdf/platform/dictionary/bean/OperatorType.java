package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-07-23 10:22
 * 操作类型
 */
@Entity
@DiscriminatorValue("OPERATOR_TYPE")
@BeanName(name = "操作类型")
public class OperatorType extends Dictionary {
    private static final long serialVersionUID = 4115042188883423826L;

    /** 自动 */
    @BeanAttrInfo(cnName = "自动")
    public static final long AUTOMATIC = 1;

    /** 手动 */
    @BeanAttrInfo(cnName = "手动")
    public static final long MANUAL = 2;
}
