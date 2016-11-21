package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-05-25
 *
 * @author 黄远昌
 * @category 带宽上限类别
 */
@Entity
@DiscriminatorValue("BANDWIDTH_TYPE")
@BeanName(name = "带宽上限类别")
public class BandwidthType extends Dictionary {
    private static final long serialVersionUID = 3415863797658576511L;
    /** 正常带宽 */
    @BeanAttrInfo(value = "1", cnName = "正常带宽")
    public static final long MAX_BANDWIDTH = 1;
    /** 限制带宽 */
    @BeanAttrInfo(value = "2", cnName = "限制带宽")
    public static final long MAX_PUNISHMENT = 2;
}
