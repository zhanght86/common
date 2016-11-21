package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-24 09:49
 */
@Entity
@DiscriminatorValue("IMS_FINANCE_TYPE")
@BeanName(name = "IMS财务类型")
public class ImsFinanceType extends Dictionary {
    private static final long serialVersionUID = 6273418234299989546L;

    /** 入账 */
    @BeanAttrInfo(cnName = "入账")
    public static final long IN = 1;

    /** 出账 */
    @BeanAttrInfo(cnName = "出账")
    public static final long OUT = 2;
}
