package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-10-11
 *
 * @author 黄远昌
 * @category 自定义产品参数下限
 */
@Entity
@DiscriminatorValue("CHOST_PARAM_LOWERLIMIT")
@BeanName(name = "自定义产品参数下限")
public class ChostParamLowerLimit extends Dictionary {
    private static final long serialVersionUID = 5120640111125585986L;
    /** CPU个数（单位：个） */
    @BeanAttrInfo(value = "1", cnName = "CPU个数")
    public static final long CHOST_CPU_NUM = 1;
    /** 内存大小（单位：MB） */
    @BeanAttrInfo(value = "512", cnName = "内存大小")
    public static final long CHOST_MEM_SIZE = 2;
    /** 硬盘大小（单位：GB） */
    @BeanAttrInfo(value = "1", cnName = "硬盘大小")
    public static final long CHOST_DISK_SIZE = 3;
    /** 域名个数（单位：个） */
    @BeanAttrInfo(value = "0", cnName = "域名个数")
    public static final long CHOST_DOMAIN_NUM = 4;
    /** IP数  个数（单位：个） */
    @BeanAttrInfo(value = "0", cnName = "IP数 个数")
    public static final long CHOST_IP_NUM = 5;
}
