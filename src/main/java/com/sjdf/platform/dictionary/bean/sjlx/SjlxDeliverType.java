package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-02-15 11:42
 */
@Entity
@DiscriminatorValue("SJLX_DELIVER_TYPE")
@BeanName(name = "sjlx-交付类型")
public class SjlxDeliverType extends Dictionary {
    private static final long serialVersionUID = 6664935454266192597L;

    @BeanAttrInfo(cnName = "工商交接", value = "特别注意的说明")
    public static final long IC = 1;

    @BeanAttrInfo(cnName = "银行交接", value = "特别注意的说明")
    public static final long BANK = 2;

    @BeanAttrInfo(cnName = "税务交接", value = "特别注意的说明")
    public static final long TAX = 3;

    @BeanAttrInfo(cnName = "其他交接")
    public static final long OTHER = 99;
}
