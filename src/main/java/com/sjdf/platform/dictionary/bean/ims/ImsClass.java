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
@DiscriminatorValue("IMS_CLASS")
@BeanName(name = "IMS大分类")
public class ImsClass extends Dictionary {
    private static final long serialVersionUID = -1766950863938132029L;

    @BeanAttrInfo(cnName = "大分类1")
    public static final long CLASS1 = 1;
}
