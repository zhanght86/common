package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月28日 下午4:03:06
 *
 * @author KETQI
 * @category cnnic产品类型(value禁止修改)
 */
@Entity
@DiscriminatorValue("CNNIC_PRODUCT_TYPE")
@BeanName(name = "cnnic产品类型")
public class CnnicProductType extends Dictionary {
    private static final long serialVersionUID = 7191620538197618959L;

    /** 英文域名产品类型 */
    @BeanAttrInfo(value = "cn", cnName = "英文域名")
    public static final long CN = 1;

    /** 中文域名 产品类型 */
    @BeanAttrInfo(value = "cdn", cnName = "中文域名")
    public static final long CDN = 2;
}
