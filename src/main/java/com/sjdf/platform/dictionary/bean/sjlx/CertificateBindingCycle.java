package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年7月13日
 * @category 税务凭证装订周期
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("SJLX_CERTIFICATE_BINDING_CYCLE")
@BeanName(name = "sjlx-税务凭证装订周期")
public class CertificateBindingCycle extends Dictionary {

    private static final long serialVersionUID = 8176495503662619181L;

    @BeanAttrInfo(cnName="月度")
    public static final long MONTH = 1;

    @BeanAttrInfo(cnName="季度")
    public static final long QUARTER = 2;

    @BeanAttrInfo(cnName="半年度")
    public static final long HALF_YEAR = 3;
}
