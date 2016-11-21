package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 硬件类型
 * User: ketqi
 * Date: 2016-03-30 14:14
 */
@Entity
@DiscriminatorValue("IDC_HARDWARE_TYPE")
@BeanName(name = "IDC硬件类型")
public class IdcHardwareType extends Dictionary {
    private static final long serialVersionUID = 6305124421056274244L;

    @BeanAttrInfo(cnName = "服务器")
    public static final long SERVER = 1;

    @BeanAttrInfo(cnName = "其他设备")
    public static final long OTHER = 99;
}
