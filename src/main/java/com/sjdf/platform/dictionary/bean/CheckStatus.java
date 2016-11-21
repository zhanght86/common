package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-06-13
 *
 * @author 黄远昌
 * @category 核实状态
 */
@Entity
@DiscriminatorValue("CHECK_STATUS")
@BeanName(name = "核实状态")
public class CheckStatus extends Dictionary {

    private static final long serialVersionUID = 7287708124303537325L;

    /** 已核实 */
    @BeanAttrInfo(value = "1", cnName = "已核实 ")
    public static final long CHECKED = 1;

    /** 未核实 */
    @BeanAttrInfo(value = "2", cnName = "未核实 ")
    public static final long NOTCHECK = 2;
}
