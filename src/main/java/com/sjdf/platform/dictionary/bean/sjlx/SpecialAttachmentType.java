package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年8月31日
 * @category 世纪利信特殊附件
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("SPECIAL_ATTACHMENT_TYPE")
@BeanName(name = "sjlx-特殊附件")
public class SpecialAttachmentType extends Dictionary {

    private static final long serialVersionUID = 8281241497631782213L;

    @BeanAttrInfo(value = "1" , cnName = "股东身份证类型ID")
    public static final long SHAREHOLDER_ID_CARD = 1;

    @BeanAttrInfo(value = "2" , cnName = "开户许可证类型ID")
    public static final long COMPANY_OPEN_PERMIT = 2;

    @BeanAttrInfo(value = "3" , cnName = "一般纳税人登记表ID")
    public static final long TAXPAYER_REGISTRATION = 3;

    @BeanAttrInfo(value = "4" , cnName = "营业执照正本ID")
    public static final long COMPANY_BUSINESS_LICENSE_ORIGINAL = 4;

    @BeanAttrInfo(value = "4" , cnName = "营业执照副本ID")
    public static final long COMPANY_BUSINESS_LICENSE_COPY = 5;

    @BeanAttrInfo(value = "4" , cnName = "组织机构代码证正本ID")
    public static final long COMPANY_ORGANIZATION_CODE_CERTIFICATE_ORIGINAL = 6;

    @BeanAttrInfo(value = "4" , cnName = "组织机构代码证副本ID")
    public static final long COMPANY_ORGANIZATION_CODE_CERTIFICATE_COPY = 7;

    @BeanAttrInfo(value = "4" , cnName = "税务登记证正本ID")
    public static final long COMPANY_TAX_REGISTRATION_CERTIFICATE_ORIGINAL = 8;

    @BeanAttrInfo(value = "4" , cnName = "税务登记证副本ID")
    public static final long COMPANY_TAX_REGISTRATION_CERTIFICATE_COPY = 9;

    @BeanAttrInfo(value = "4" , cnName = "机构信用代码证ID")
    public static final long COMPANY_AGENCY_CREDIT_CODE = 10;
}
