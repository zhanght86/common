package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category cpu类别
 * @ClassName CpuType
 * @Created 2012 2012-12-21 上午11:01:50
 */
@Entity
@DiscriminatorValue("CPU_TYPE")
@BeanName(name = "CPU类别")
public class CpuType extends Dictionary {
    private static final long serialVersionUID = 3415863797658576511L;
    /** 限制CPU */
    @BeanAttrInfo(value = "1", cnName = "限制CPU")
    public static final long CPU_PUNISHMENT = 1;

    /** 正常CPU */
    @BeanAttrInfo(value = "2", cnName = "正常CPU")
    public static final long CPU_NORMAL = 2;

}
