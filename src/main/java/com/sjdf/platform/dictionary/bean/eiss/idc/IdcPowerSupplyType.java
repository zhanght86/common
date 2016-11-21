package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-21 13:38
 */
@Entity
@DiscriminatorValue("IDC_POWER_SUPPLY_TYPE")
@BeanName(name = "电源类型")
public class IdcPowerSupplyType extends Dictionary{
    private static final long serialVersionUID = -1362006306268831430L;

    /** 单电源 */
    @BeanAttrInfo(cnName = "单电源")
    public static final long ALONE = 1;

    /** 双电源 */
    @BeanAttrInfo(cnName = "双电源")
    public static final long DOUBLE = 2;
}
