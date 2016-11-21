package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-18 11:22
 */
@Entity
@DiscriminatorValue("IMS_BRAND")
@BeanName(name = "IMS品牌")
public class ImsBrand extends Dictionary {
    private static final long serialVersionUID = -212693245269626309L;

    @BeanAttrInfo(cnName = "品牌1")
    public static final long BRAND1 = 1;
}
