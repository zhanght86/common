package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category IP分配_使用方式
 */
@Entity
@DiscriminatorValue("IP_ALLOT_USE_TYPE")
@BeanName(name = "IP分配_使用方式")
public class IpAllotUseType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1912979870036755318L;

    /** 静态 */
    @BeanAttrInfo(value = "1", cnName = "静态")
    public static final long STATIC_TYPE = 1L;

    /** 动态 */
    @BeanAttrInfo(value = "2", cnName = "动态")
    public static final long DYNAMIC_TYPE = 2L;

    /** 未知 */
    @BeanAttrInfo(value = "3", cnName = "未知")
    public static final long UNKNOWN = 3L;
}
