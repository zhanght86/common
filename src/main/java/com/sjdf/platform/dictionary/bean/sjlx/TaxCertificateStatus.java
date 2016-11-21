package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年7月13日
 * @category 税务凭证状态
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("SJLX_CERTIFICATE_STATUS")
@BeanName(name = "sjlx-税务凭证状态")
public class TaxCertificateStatus extends Dictionary{

    private static final long serialVersionUID = -6520446769998267221L;

    @BeanAttrInfo(cnName="已结账")
    public static final long CHECKED_OUT = 1;

    @BeanAttrInfo(cnName="已结账，已打印")
    public static final long CHECKED_AND_PRINT = 2;

    @BeanAttrInfo(cnName="未结账，未打印")
    public static final long CHECKED_NOT_PRINT = 3;
}
