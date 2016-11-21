package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-18
 *
 * @author 陈绍刚
 * @category 升级部件限制
 */
@Entity
@DiscriminatorValue("UPGRADE_PART_LIMIT")
@BeanName(name = "升级部件限制")
public class UpgradePartLimit extends Dictionary {
    private static final long serialVersionUID = 2941826606229590435L;

    /** 云主机CPU最大值 */
    @BeanAttrInfo(value = "8", cnName = "云主机CPU最大值")
    public static final long CHOST_CPU = 1;
    /** 云主机内存最大值 */
    @BeanAttrInfo(value = "8192", cnName = "云主机内存最大值")
    public static final long CHOST_MEM = 2;
    /** 云主机硬盘最大值 */
    @BeanAttrInfo(value = "999", cnName = "云主机硬盘最大值")
    public static final long CHOST_DISK = 3;
    /** 云主机白名单最大值 */
    @BeanAttrInfo(value = "150", cnName = "云主机白名单最大值")
    public static final long CHOST_BINDINGS = 4;
    /** 云主机IP数最大值 */
    @BeanAttrInfo(value = "10", cnName = "云主机IP数最大值")
    public static final long CHOST_IP = 5;
}
