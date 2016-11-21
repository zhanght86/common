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
@DiscriminatorValue("IMS_COLOR")
@BeanName(name = "IMS颜色")
public class ImsColor extends Dictionary {
    private static final long serialVersionUID = -893168701492080070L;

    @BeanAttrInfo(cnName = "红色")
    public static final long RED = 1;
}
