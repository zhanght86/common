package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-30
 *
 * @author 王正伟
 * @category 数据模板分类
 */
@Entity
@DiscriminatorValue("TEMPLATE_TYPE")
@BeanName(name = "模板分类")
public class TemplateType extends Dictionary {
    private static final long serialVersionUID = 8063555461876246208L;

    /** 域名注册模板 */
    @BeanAttrInfo(value = "101", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.EISS, cnName = "域名注册模板")
    public static final long DOMAIN_REGISTER_TEMPLATE = 101;

    /** 发票信息模板 */
    @BeanAttrInfo(value = "102", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.EISS, cnName = "发票信息模板")
    public static final long INVOICE_TEMPLATE = 102;

    /** 备案系统会员模板 */
    @BeanAttrInfo(value = "103", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.RECORD, cnName = "备案系统会员模板")
    public static final long RECORD_USER_TEMPLATE = 103;

    /** 备案系统备案信息模板 */
    @BeanAttrInfo(value = "104", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.RECORD, cnName = "备案系统备案信息模板")
    public static final long RECORD_INFO_SUBJECT_TEMPLATE = 104;

    /** 客户信息管理模板 */
    @BeanAttrInfo(value = "105", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.EISS, cnName = "客户信息管理模板")
    public static final long CUSTOMER_INFO_MANAGE_TEMPLATE = 105;

    /** 主站会员信息模板 */
    @BeanAttrInfo(value = "106", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.EISS, cnName = "主站会员信息模板")
    public static final long MEMBER_INFO_TEMPLATE = 106;

    /** 代理自定义产品模板 */
    @BeanAttrInfo(value = "107", refClass = TemplateClass.class, refAttr = TemplateClass.PRODUCT_TEMPLATE_CLASS, systemType = SystemType.EISS, cnName = "代理自定义产品模板")
    public static final long DIY_PRODUCT_TEMPLATE = 107;

    /** 世纪利信内部订单模板 */
    @BeanAttrInfo(value = "108", refClass = TemplateClass.class, refAttr = TemplateClass.INFO_TEMPLATE_CLASS, systemType = SystemType.SJLX_CMS, cnName = "世纪利信内部订单模板")
    public static final long CMS_INTERNAL_ORDER_TEMPLATE = 108;
}
