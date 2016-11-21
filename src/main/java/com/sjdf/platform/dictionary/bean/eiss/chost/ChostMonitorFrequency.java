package com.sjdf.platform.dictionary.bean.eiss.chost;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Administrator
 * @category 云主机监控频率
 */
@Entity
@DiscriminatorValue("CHOST_MONITOR_FREQUENCY")
@BeanName(name = "云主机监控频率(秒)")
public class ChostMonitorFrequency extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = -7179896949593075315L;

    @BeanAttrInfo(value = "10", orderBy = 1, cnName = "10秒")
    public static final long FREQUENCY_ONE = 1L;

    @BeanAttrInfo(value = "30", orderBy = 2, cnName = "30秒")
    public static final long FREQUENCY_TWO = 2L;

    @BeanAttrInfo(value = "60", orderBy = 3, cnName = "1分钟")
    public static final long FREQUENCY_THREE = 3L;

    @BeanAttrInfo(value = "300", orderBy = 4, cnName = "5分钟")
    public static final long FREQUENCY_FOUR = 4L;

    @BeanAttrInfo(value = "600", orderBy = 5, cnName = "10分钟")
    public static final long FREQUENCY_FIVE = 5L;

}
