package com.sjdf.platform.dictionary.bean.eiss.pub;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * cdn缓存类型
 * User: ketqi
 * Date: 2015-02-05 10:06
 */
@Entity
@DiscriminatorValue("CDN_TYPE")
@BeanName(name = "CDN缓存类型")
public class CDNType extends Dictionary {
    @BeanAttrInfo(cnName = "主站专用")
    public static final long MASTER_SPECIAL_PURPOSE = 1;

    @BeanAttrInfo(cnName = "客户旁路缓存")
    public static final long CUSTOMER_BRANCH_CACHE = 2;

    @BeanAttrInfo(cnName = "客户抗攻击缓存")
    public static final long CUSTOMER_ATTACK_CACHE = 3;
}
