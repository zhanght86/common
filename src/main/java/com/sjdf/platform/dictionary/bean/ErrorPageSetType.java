package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 虚拟主机控制面板错误页面设置类型
 */
@Entity
@DiscriminatorValue("ERROR_PAGE_SET_TYPE")
@BeanName(name = "虚拟主机控制面板错误页面设置类型")
public class ErrorPageSetType extends Dictionary {
    /**
     * serial
     */
    private static final long serialVersionUID = -6995393938400461136L;

    /** 自定义错误 */
    @BeanAttrInfo(value = "1", cnName = "自定义错误")
    public static final long CUSTOM_ERROR = 1L;

    /** 详细错误 */
    @BeanAttrInfo(value = "2", cnName = "详细错误")
    public static final long DETAIL_ERROR = 2L;

}
