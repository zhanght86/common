package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-04 14:43
 */
@Entity
@DiscriminatorValue("IDC_REGION")
@BeanName(name = "机房地域")
public class IdcRegion extends Dictionary {
    private static final long serialVersionUID = 2566151929987971308L;

    @BeanAttrInfo(cnName = "上海")
    public static final long SHANG_HAI = 1;

    @BeanAttrInfo(cnName = "浙江")
    public static final long ZHE_JIANG = 5;

    @BeanAttrInfo(cnName = "北京")
    public static final long BEI_JING = 10;

    @BeanAttrInfo(cnName = "美国")
    public static final long MEI_GUO = 15;

    @BeanAttrInfo(cnName = "香港")
    public static final long XIANG_GANG = 20;

    @BeanAttrInfo(cnName = "其他")
    public static final long OTHER = 99;
}
