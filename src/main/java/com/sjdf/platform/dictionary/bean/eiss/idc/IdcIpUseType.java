package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-09 17:14
 */
@Entity
@DiscriminatorValue("IDC_IP_USE_TYPE")
@BeanName(name = "IDC IP使用类型")
public class IdcIpUseType extends Dictionary {
    private static final long serialVersionUID = 2892988036032777025L;

    @BeanAttrInfo(cnName = "自用")
    public static final long SELF_USED = 1;

    @BeanAttrInfo(cnName = "租用")
    public static final long LEASEHOLD = 5;

    @BeanAttrInfo(cnName = "托管")
    public static final long TRUSTEESHIP = 10;

    @BeanAttrInfo(cnName = "机柜")
    public static final long MACHINE_CABINETS = 15;
}
