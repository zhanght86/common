package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-09-05 14:15
 */
@Entity
@DiscriminatorValue("IDC_IP_TYPE")
@BeanName(name = "IDC IP类型")
public class IdcIpType extends Dictionary {
    private static final long serialVersionUID = 8139530271449837686L;

    @BeanAttrInfo(cnName = "国内")
    public static final long INTERNAL_COUNTRY = 1L;

    @BeanAttrInfo(cnName = "国外")
    public static final long EXTERNAL_COUNTRY = 5L;
}
