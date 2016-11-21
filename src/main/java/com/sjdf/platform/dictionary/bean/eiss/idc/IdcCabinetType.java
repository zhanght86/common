package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

@Entity
@DiscriminatorValue("IDC_CABINET_TYPE")
@BeanName(name = "IDC机柜类型")
public class IdcCabinetType  extends Dictionary {

    private static final long serialVersionUID = -4411527419076176052L;

    @BeanAttrInfo(cnName="整柜")
    public static final long WHOLE = 1;

    @BeanAttrInfo(cnName="半柜")
    public static final long HALF = 2;
}
