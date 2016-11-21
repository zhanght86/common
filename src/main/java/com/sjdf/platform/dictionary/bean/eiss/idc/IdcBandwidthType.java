package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-09 17:04
 */
@Entity
@DiscriminatorValue("IDC_BANDWIDTH_TYPE")
@BeanName(name = "IDC带宽类型")
public class IdcBandwidthType extends Dictionary {
    private static final long serialVersionUID = 3637035307344918621L;

    @BeanAttrInfo(cnName = "限速峰值")
    public static final long SPEED_LIMIT = 1;

    @BeanAttrInfo(cnName = "最高峰值")
    public static final long TOP_SPEED = 5;

    @BeanAttrInfo(cnName = "第4峰值")
    public static final long FOURTH_SPEED = 10;

    @BeanAttrInfo(cnName = "95th峰值")
    public static final long PEAK_95 = 15;

    @BeanAttrInfo(cnName = "平均峰值")
    public static final long AVERAGE = 20;
}
