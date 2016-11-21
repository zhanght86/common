package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-08-29
 *
 * @author Chen Mohan
 * @category 产品备案接入检查-机房
 */
@Entity
@DiscriminatorValue("PRODUCT_RECORD_ACCESS_IDC")
@BeanName(name = "产品备案接入检查-机房")
public class ProductRecordAccessIdc extends Dictionary {

    private static final long serialVersionUID = -7704040064108340484L;

    @BeanAttrInfo(cnName = "国内")
    public static final long CHINA = 1;

    @BeanAttrInfo(cnName = "非国内")
    public static final long OTHER = 2;
}
