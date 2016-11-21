package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-05 13:10
 *
 * @category 资费配置
 */
@Entity
@DiscriminatorValue("TARIFF_CONFIG")
@BeanName(name = "资费配置")
public class TariffConfig extends Dictionary {
    private static final long serialVersionUID = -284296190995100274L;

    /** 快递费_圆通 */
    @BeanAttrInfo(value = "18", cnName = "快递费_圆通", refClass = ExpressType.class, refAttr = ExpressType.YTO)
    public static final long YTO = 1;

    /** 快递费_顺丰 */
    @BeanAttrInfo(value = "23", cnName = "快递费_顺丰", refClass = ExpressType.class, refAttr = ExpressType.SF)
    public static final long SF = 2;

    /** 快递费_EMS */
    @BeanAttrInfo(value = "20", cnName = "快递费_EMS", refClass = ExpressType.class, refAttr = ExpressType.EMS)
    public static final long EMS = 3;

    /** 快递费_上门自提 */
    @BeanAttrInfo(value = "0", cnName = "快递费_上门自提", refClass = ExpressType.class, refAttr = ExpressType.VISIT_HOLD)
    public static final long VISIT_HOLD = 4;

    /** 背景布成本费用 */
    @BeanAttrInfo(value = "2", cnName = "背景布成本费用")
    public static final long BACK_GROUND_TARIFF = 5;
}
