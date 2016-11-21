package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:17:42
 *
 * @author KETQI
 * @category 域名类型
 */
@Entity
@DiscriminatorValue("DOMIAN_REGISTRATION_TYPE")
@BeanName(name = "域名类型")
public class DomainRegistrationType extends Dictionary {
    private static final long serialVersionUID = 2794388618236699367L;

    /** 其他域名 */
    @BeanAttrInfo(value = "other", cnName = "其他域名类型")
    public static final long OTHER_TYPE = 1;


    /** 个人姓名英文国内域名 (.CN) */
    @BeanAttrInfo(value = "dompcn", cnName = "个人姓名英文国内域名 (.CN)")
    public static final long CN_PERSONAL_NAME = 2;

    /** 个人公众英文国内域名 (.CN) */
    @BeanAttrInfo(value = "domppcn", cnName = "个人公众英文国内域名 (.CN)")
    public static final long CN_PERSONAL_PUBLIC = 3;

    /** 企业公众英文国内域名 (.CN) */
    @BeanAttrInfo(value = "domnpcn", cnName = "企业公众英文国内域名 (.CN)")
    public static final long CN_COMPANY = 4;


    /** 英文国内域名(.GOV.CN) */
    @BeanAttrInfo(value = "domgovcn", cnName = "英文国内域名(.GOV.CN)")
    public static final long CN_GOVERNMENT = 5;


    /** 个人姓名英文国内域名 (.中国) */
    @BeanAttrInfo(value = "dompzh", cnName = "个人姓名英文国内域名 (.中国)")
    public static final long CN_PERSONAL_NAME_ZH = 6;

    /** 个人公众英文国内域名 (.中国) */
    @BeanAttrInfo(value = "domppzh", cnName = "个人公众英文国内域名 (.中国)")
    public static final long CN_PERSONAL_PUBLIC_ZH = 7;

    /** 企业公众英文国内域名 (.中国) */
    @BeanAttrInfo(value = "domnpzh", cnName = "企业公众英文国内域名 (.中国)")
    public static final long CN_COMPANY_ZH = 8;


    /** 个人姓名中文国内域名(.中国/.网络/.公司) */
    @BeanAttrInfo(value = "domzhpzh", cnName = "个人姓名中文国内域名(.中国/.网络/.公司)")
    public static final long ZH_PERSONAL_NAME_CN = 9;

    /** 个人公众中文国内域名(.中国/.网络/.公司) */
    @BeanAttrInfo(value = "domzhppzh", cnName = "个人公众中文国内域名(.中国/.网络/.公司)")
    public static final long ZH_PERSONAL_PUBLIC_CN = 10;

    /** 企业公众中文国内域名(.中国/.网络/.公司) */
    @BeanAttrInfo(value = "domzhnpzh", cnName = "企业公众中文国内域名(.中国/.网络/.公司)")
    public static final long ZH_COMPANY_CN = 11;


    /** 个人姓名中文国内域名(.CN) */
    @BeanAttrInfo(value = "domzhpcn", cnName = "个人姓名中文国内域名(.CN)")
    public static final long ZH_PERSONAL_NAME = 12;

    /** 个人公众中文国内域名(.CN) */
    @BeanAttrInfo(value = "domzhppcn", cnName = "个人公众中文国内域名(.CN)")
    public static final long ZH_PERSONAL_PUBLIC = 13;

    /** 企业公众中文国内域名(.CN) */
    @BeanAttrInfo(value = "domzhnpcn", cnName = "企业公众中文国内域名(.CN)")
    public static final long ZH_COMPANY = 14;
}
