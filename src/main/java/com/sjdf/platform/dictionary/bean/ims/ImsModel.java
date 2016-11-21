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
@DiscriminatorValue("IMS_MODEL")
@BeanName(name = "IMS型号")
public class ImsModel extends Dictionary {
    private static final long serialVersionUID = -8125402058006837407L;

    @BeanAttrInfo(cnName = "A")
    public static final long A = 1;

    @BeanAttrInfo(cnName = "B")
    public static final long B = 2;

    @BeanAttrInfo(cnName = "C")
    public static final long C = 3;
}
