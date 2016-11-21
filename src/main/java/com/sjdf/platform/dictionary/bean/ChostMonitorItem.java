package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-07-24
 *
 * @author 陈绍刚
 * @category 云主机监控项目
 */
@Entity
@DiscriminatorValue("CHOST_MONITOR_ITEM")
@BeanName(name = "云主机监控项目")
public class ChostMonitorItem extends Dictionary {

    private static final long serialVersionUID = 3050493508619645961L;

    /** CPU */
    @BeanAttrInfo(value = "1", cnName = "CPU")
    public static final long CPU = 1;

    /** 带宽 */
    @BeanAttrInfo(value = "2", cnName = "带宽")
    public static final long BANDWIDTH = 2;

    /** 磁盘IO */
    @BeanAttrInfo(value = "3", cnName = "磁盘IO")
    public static final long DISK_IO = 3;

    /** 内存 */
    @BeanAttrInfo(value = "4", cnName = "内存")
    public static final long MEM = 4;

}
