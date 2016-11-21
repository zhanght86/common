package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-28
 *
 * @author 王正伟
 * @category 日志记录时, 操作函数，用于通过类名+方法名得到功能大类、功能小类、操作的组合
 */
@Entity
@DiscriminatorValue("OPERATOR_FUNCTION")
@BeanName(name = "操作函数")
public class OperatorFunction extends Dictionary {
    private static final long serialVersionUID = -2659822814203199658L;
    /** 测试 */
    public static final String TEST = "com.sjdf.platform.log.Test.main";

}
