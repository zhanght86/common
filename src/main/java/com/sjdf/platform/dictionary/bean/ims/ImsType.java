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
@DiscriminatorValue("IMS_TYPE")
@BeanName(name = "IMS产品类型")
public class ImsType extends Dictionary {
    private static final long serialVersionUID = -4670728635655280277L;

    @BeanAttrInfo(cnName = "类型11")
    public static final long TYPE1 = 1;
}
