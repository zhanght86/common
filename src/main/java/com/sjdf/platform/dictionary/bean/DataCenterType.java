package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-23
 *
 * @author 王正伟
 * @category 信息库资料类型
 */
@Entity
@DiscriminatorValue("DATA_CENTER_TYPE")
@BeanName(name = "信息库资料类型")
public class DataCenterType extends Dictionary {
    private static final long serialVersionUID = -6136101978442545124L;

    /** 系统邮件 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "系统")
    public static final long SYSTEM_ATTACHMENT = 1;

    /** 人工邮件 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "人工")
    public static final long MANUAL_ATTACHMENT = 2;

    /** 客户资料 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "资料")
    public static final long CUSTOMER_ATTACHMENT = 3;

    /** 国内域名审核资料 */
    @BeanAttrInfo(value = "4", orderBy = 4, cnName = "审核资料")
    public static final long VERIFY_ATTACHMENT = 4;
}
