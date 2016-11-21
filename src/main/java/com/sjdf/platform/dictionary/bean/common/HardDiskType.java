package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-02 15:29
 */
@Entity
@DiscriminatorValue("HARD_DISK_TYPE")
@BeanName(name = "硬盘类型")
public class HardDiskType extends Dictionary {
    private static final long serialVersionUID = -6089906759029629684L;
    /** SATA */
    @BeanAttrInfo(cnName = "SATA")
    public static final long SATA = 1L;

    /** SAS */
    @BeanAttrInfo(cnName = "SAS")
    public static final long SAS = 10L;
}
