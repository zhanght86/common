package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 机柜使用类型
 * @date 2016年3月8日下午3:47:19
 * @author wangpeng *
 */
@Entity
@DiscriminatorValue("IDC_CABINET_USED_TYPE")
@BeanName(name = "IDC机柜使用类型")
public class IdcCabinetUsedType extends Dictionary{

    private static final long serialVersionUID = -9178495032759117175L;

    @BeanAttrInfo(value="1", cnName="机柜", enName="cabinet")
    public static final long CABINET = 1;

    @BeanAttrInfo(value="2", cnName="散户", enName="Retail")
    public static final long RETAIL = 2;
}
