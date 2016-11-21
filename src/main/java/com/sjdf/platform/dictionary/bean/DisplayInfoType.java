package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @category 【展示信息】类别
 * @date 2012-8-24 上午10:34:42
 */
@Entity
@DiscriminatorValue("DISPALY_INFO_TYPE")
@BeanName(name = "展示信息类别")
public class DisplayInfoType extends Dictionary {

    private static final long serialVersionUID = 2882157632658724001L;

    /** 备案客户信息提示 */
    @BeanAttrInfo(orderBy = 1, cnName = "备案客户信息提示")
    public static final long AUDIT_OPINION = 1;

    /** 备案标准审核要求 */
    @BeanAttrInfo(orderBy = 2, cnName = "备案标准审核要求")
    public static final long GENERAL_AUDIT_REQUIREMENTS = 2;

    /** 备案特殊审核要求 */
    @BeanAttrInfo(orderBy = 3, cnName = "备案特殊审核要求")
    public static final long SPECIAL_AUDIT_REQUIREMENTS = 3;

    /** 备案照相地址 */
    @BeanAttrInfo(orderBy = 4, cnName = "备案照相地址")
    public static final long PHOTOGRAPH_ADDRESS = 4;

    /** 上传附件要求 */
    @BeanAttrInfo(orderBy = 5, cnName = "上传附件要求")
    public static final long REQUIREMENTS_OF_UPLOAD_ATTACHMENT = 5;

    /** 审核意见 */
    @BeanAttrInfo(orderBy = 6, cnName = "审核意见")
    public static final long ATTACHMENT_AUDIT_OPINION = 6;
}
