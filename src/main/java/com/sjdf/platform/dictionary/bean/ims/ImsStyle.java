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
@DiscriminatorValue("IMS_STYLE")
@BeanName(name = "IMS款式")
public class ImsStyle extends Dictionary {
    private static final long serialVersionUID = -4666116051006930320L;

    @BeanAttrInfo(cnName = "款式1")
    public static final long STYLE1 = 1;
}
