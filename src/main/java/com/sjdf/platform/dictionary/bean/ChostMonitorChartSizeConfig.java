package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 陈绍刚
 * @category 云主机监控图形大小配置
 */
@Entity
@DiscriminatorValue("CHOST_MONITOR_CHART_SIZE_CONFIG")
@BeanName(name = "云主机监控图形大小配置")
public class ChostMonitorChartSizeConfig extends Dictionary {

    private static final long serialVersionUID = -4396079228152777643L;

    @BeanAttrInfo(value = "346,200", cnName = "小图")
    public static final long S = 1;
    @BeanAttrInfo(value = "690,335", cnName = "中图")
    public static final long M = 2;
    @BeanAttrInfo(value = "1400,670", cnName = "大图")
    public static final long L = 3;
}
