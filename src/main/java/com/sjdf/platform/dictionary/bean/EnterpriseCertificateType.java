package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-08
 *
 * @author 王正伟
 * @category 单位证件类型
 */
@Entity
@DiscriminatorValue("ENTERPRISE_CERTIFICATE_TYPE")
@BeanName(name = "单位证件类型")
public class EnterpriseCertificateType extends Dictionary {
    private static final long serialVersionUID = -6790539198800539909L;

    /** 组织机构代码证书 */
    @BeanAttrInfo(value = "10018", cnName = "组织机构代码证书", enName = "organization code certificate")
    public static final long ORG_CODE_CERTIFICATE = 10018;
    /** 工商营业执照 */
    @BeanAttrInfo(value = "10019", cnName = "工商营业执照 ", enName = "business license")
    public static final long BUSINESS_LICENSE = 10019;
    /** 事业法人证书 */
    @BeanAttrInfo(value = "10020", cnName = "事业法人证书", enName = "legal certificate")
    public static final long LEGAL_CERTIFICATE = 10020;
    /** 社会法人证书 */
    @BeanAttrInfo(value = "10021", cnName = "社会法人证书", enName = "social certificate")
    public static final long SOCIAL_CERTIFICATE = 10021;
    /** 军队代号 */
    @BeanAttrInfo(value = "10022", cnName = "军队代号", enName = "military code")
    public static final long MILITARY_CODE = 10022;

}
