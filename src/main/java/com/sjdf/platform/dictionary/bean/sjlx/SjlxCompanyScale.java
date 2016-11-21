package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SJLX_COMPANY_SCALE")
@BeanName(name = "sjlx-公司规模")
public class SjlxCompanyScale extends Dictionary {
    private static final long serialVersionUID = 3496647349270907109L;

    /** 一般纳税人 */
    @BeanAttrInfo(cnName = "一般纳税人")
    public static final long GENERAL = 1;
    /** 小规模纳税人 */
    @BeanAttrInfo(cnName = "小规模纳税人")
    public static final long SMALL = 2;
}
