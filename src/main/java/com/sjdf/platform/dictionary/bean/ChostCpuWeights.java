package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-31
 *
 * @author 黄远昌
 * @category 云主机cpu配额权值
 */
@Entity
@DiscriminatorValue("CHOST_CPU_WEIGHTS")
@BeanName(name = "云主机cpu配额权值")
public class ChostCpuWeights extends Dictionary {

    private static final long serialVersionUID = 3013134276474573180L;
    /** 1核cpu配额权值 */
    @BeanAttrInfo(value = "2", cnName = "1核cpu配额权值")
    public static final long CPU1_WEIGHTS = 1;
    /** 2核cpu配额权值 */
    @BeanAttrInfo(value = "2", cnName = "2核cpu配额权值")
    public static final long CPU2_WEIGHTS = 2;
    /** 4核cpu配额权值 */
    @BeanAttrInfo(value = "3", cnName = "4核cpu配额权值")
    public static final long CPU4_WEIGHTS = 4;
    /** 5核cpu配额权值 */
    @BeanAttrInfo(value = "3", cnName = "5核cpu配额权值")
    public static final long CPU5_WEIGHTS = 5;
    /** 6核cpu配额权值 */
    @BeanAttrInfo(value = "4", cnName = "6核cpu配额权值")
    public static final long CPU6_WEIGHTS = 6;
    /** 7核cpu配额权值 */
    @BeanAttrInfo(value = "4", cnName = "7核cpu配额权值")
    public static final long CPU7_WEIGHTS = 7;
    /** 8核cpu配额权值 */
    @BeanAttrInfo(value = "4", cnName = "8核cpu配额权值")
    public static final long CPU8_WEIGHTS = 8;
}
