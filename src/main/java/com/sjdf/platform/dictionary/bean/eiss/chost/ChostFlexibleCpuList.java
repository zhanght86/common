package com.sjdf.platform.dictionary.bean.eiss.chost;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 云主机弹性购买Cpu设置
 *
 * @author sjdf
 */
@Entity
@DiscriminatorValue("CHOST_FLEXIBLE_CPU_List")
@BeanName(name = "云主机弹性购买Cpu设置")
public class ChostFlexibleCpuList extends Dictionary {
    private static final long serialVersionUID = -4019377655059990529L;

    /** 1核对应的内存值 */
    @BeanAttrInfo(value = "[1, 2, 4, 8]", orderBy = 1, cnName = "1核")
    public static final long ONE = 1;

    /** 2核对应的内存值 */
    @BeanAttrInfo(value = "[2, 4, 8, 12, 16]", orderBy = 1, cnName = "2核")
    public static final long TWO = 2;

    /** 4核对应的内存值 */
    @BeanAttrInfo(value = "[4, 8, 12, 16, 24]", orderBy = 1, cnName = "4核")
    public static final long FOUR = 4;

    /** 8核对应的内存值 */
    @BeanAttrInfo(value = "[8, 12, 16, 24, 32]", orderBy = 1, cnName = "8核")
    public static final long EIGHT = 8;
}
