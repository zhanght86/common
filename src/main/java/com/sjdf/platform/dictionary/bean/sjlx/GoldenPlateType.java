package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

@Entity
@DiscriminatorValue("SJLX_GOLDEN_PLATE_TYPE")
@BeanName(name = "sjlx-金税盘种类")
public class GoldenPlateType  extends Dictionary{

    private static final long serialVersionUID = 804758213375659838L;

    @BeanAttrInfo(cnName = "航天金穗")
    public static final long HTJH = 1;

    @BeanAttrInfo(cnName = "百旺金赋")
    public static final long BWJF = 2;

    @BeanAttrInfo(cnName = "成都金税")
    public static final long CDJS = 3;
}
