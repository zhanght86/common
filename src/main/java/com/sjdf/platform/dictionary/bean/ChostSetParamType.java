package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-18
 *
 * @author 黄远昌
 * @category 云主机设置类型
 */
@Entity
@DiscriminatorValue("CHOST_SET_PARAM_TYPE")
@BeanName(name = "云主机设置类型")
public class ChostSetParamType extends Dictionary {

    private static final long serialVersionUID = -6772554107258463611L;

    /** 带宽设置 */
    @BeanAttrInfo(value = "1", cnName = "带宽设置")
    public static final long CHOST_BANDWIDTH = 1;

    /** CPU配额设置 */
    @BeanAttrInfo(value = "2", cnName = "CPU配额设置")
    public static final long CHOST_CPU_QUOTA = 2;

    /** 状态设置 */
    @BeanAttrInfo(value = "3", cnName = "状态设置")
    public static final long CHOST_STATUS_SET = 3;
}
