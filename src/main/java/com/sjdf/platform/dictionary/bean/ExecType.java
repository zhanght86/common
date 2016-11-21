package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-05
 *
 * @author 黄远昌
 * @category 执行类型
 */
@Entity
@DiscriminatorValue("EXEC_TYPE")
@BeanName(name = "执行类型")
public class ExecType extends Dictionary {
    private static final long serialVersionUID = -7726332346514411125L;
    /** 本地执行 */
    @BeanAttrInfo(value = "1", cnName = "本地执行 ")
    public static final long LOCAL = 1;
    /** 远程执行 */
    @BeanAttrInfo(value = "2", cnName = "远程执行")
    public static final long REMOTE = 2;
}
