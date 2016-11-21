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
@DiscriminatorValue("IMS_SIZE")
@BeanName(name = "IMS大小")
public class ImsSize extends Dictionary {
    private static final long serialVersionUID = -4158859137213381286L;

    @BeanAttrInfo(cnName = "70")
    public static final long SIZE1 = 1;

    @BeanAttrInfo(cnName = "75")
    public static final long SIZE2 = 2;

    @BeanAttrInfo(cnName = "80")
    public static final long SIZE3 = 3;

    @BeanAttrInfo(cnName = "85")
    public static final long SIZE4 = 4;
}
