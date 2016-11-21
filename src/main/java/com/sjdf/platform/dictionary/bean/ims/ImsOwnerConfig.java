package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-21 09:12
 */
@Entity
@DiscriminatorValue("IMS_OWNER_CONFIG")
@BeanName(name = "IMS所有者配置")
public class ImsOwnerConfig extends Dictionary {
    private static final long serialVersionUID = -3571008031891417936L;

    @BeanAttrInfo(value = "1", enName = "18030517936", cnName = "是否用配置库管理（1：是；2：不是）")
    public static final long CONFIG = 1;
}
