package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-10 16:27
 */
@Entity
@DiscriminatorValue("IDC_CONTRACT_TYPE")
@BeanName(name = "IDC合同类型")
public class IdcContractType extends Dictionary {
    private static final long serialVersionUID = 6432060094558361503L;

    @BeanAttrInfo(cnName="在线合同")
    public static final long ONLINE = 1;

    @BeanAttrInfo(cnName="线下合同")
    public static final long OFFLINE = 10;
}
