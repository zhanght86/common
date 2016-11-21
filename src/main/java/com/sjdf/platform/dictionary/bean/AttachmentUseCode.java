package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-28
 *
 * @author 刘敏
 * @category 附件用途代码
 */
@Entity
@DiscriminatorValue("ATTACHMENT_USE_CODE")
@BeanName(name = "附件用途代码")
public class AttachmentUseCode extends Dictionary {

    private static final long serialVersionUID = 2882157675429724167L;

    /** 港澳居民来往内地通行证 */
    @BeanAttrInfo(orderBy = 10010, cnName = "港澳居民来往内地通行证")
    public static final long ATTACHMENT_USE_CODE_GA_PASSPORT = 10010;

    /** 台胞证 */
    @BeanAttrInfo(orderBy = 10012, cnName = "台胞证")
    public static final long ATTACHMENT_USE_CODE_TAIWAN_COMPATRIOTS_CERTIFICATE = 10012;

    /** 护照 */
    @BeanAttrInfo(orderBy = 10011, cnName = "护照")
    public static final long ATTACHMENT_USE_CODE_PASSPORT = 10011;

    /** 军官证 */
    @BeanAttrInfo(orderBy = 10013, cnName = "军官证")
    public static final long ATTACHMENT_USE_CODE_OFFICER_CERTIFICATE = 10013;

    /** 身份证 */
    @BeanAttrInfo(orderBy = 10014, cnName = "身份证")
    public static final long ATTACHMENT_USE_CODE_ID_CARD = 10014;

    /** 网站负责人相片 */
    @BeanAttrInfo(orderBy = 10015, cnName = "网站负责人相片")
    public static final long ATTACHMENT_USE_CODE_WEB_RESP_PEOPLE_PHOTO = 10015;

    /** 网站备案信息真实性核验单 */
    @BeanAttrInfo(orderBy = 10016, cnName = "网站备案信息真实性核验单")
    public static final long ATTACHMENT_USE_CODE_WEB_VERIFI_SINGLE = 10016;

    /** 信息安全责任书 */
    @BeanAttrInfo(orderBy = 10017, cnName = "信息安全责任书")
    public static final long ATTACHMENT_USE_CODE_INFO_LETTERS_CERTIFICATE = 10017;

    /** 工商营业执照 */
    @BeanAttrInfo(orderBy = 10019, cnName = "工商营业执照")
    public static final long ATTACHMENT_USE_CODE_BUSINESS_LICENSE = 10019;

    /** 事业法人证书 */
    @BeanAttrInfo(orderBy = 10020, cnName = "事业法人证书")
    public static final long ATTACHMENT_USE_CODE_CAREER_LEGAL_CERTIFICATE = 10020;

    /** 社会法人证书 */
    @BeanAttrInfo(orderBy = 10021, cnName = "社会法人证书")
    public static final long ATTACHMENT_USE_CODE_SOCIETY_LEGAL_CERTIFICATE = 10021;

    /** 军队证 */
    @BeanAttrInfo(orderBy = 10022, cnName = "军队证")
    public static final long ATTACHMENT_USE_CODE_ARMY_CERTIFICATE = 10022;

    /** 域名证书 */
    @BeanAttrInfo(orderBy = 10023, cnName = "域名证书")
    public static final long ATTACHMENT_USE_CODE_DOMAIN_CERTIFICATE = 10023;

    /** 组织机构代码证书 */
    @BeanAttrInfo(orderBy = 10018, cnName = "组织机构代码证书")
    public static final long ATTACHMENT_USE_CODE_ORGANIZATION_CERTIFICATE = 10018;

    /** 网站负责人授权书 */
    @BeanAttrInfo(orderBy = 10027, cnName = "网站负责人授权书")
    public static final long ATTACHMENT_USE_CODE_WEB_RESP_WARRANT_CERTIFICATE = 10027;

    /** 域名注册申请表 */
    @BeanAttrInfo(orderBy = 10028, cnName = "域名注册申请表")
    public static final long ATTACHMENT_USE_CODE_REGISTRATION_APPLICATION_FORM = 10028;

    /** 通信地址 */
    @BeanAttrInfo(orderBy = 99999, cnName = "通信地址")
    public static final long ATTACHMENT_USE_CODE_POSTAL_ADDRESS_CERTIFICATE = 99999;

    /** 前置审批 */
    @BeanAttrInfo(orderBy = 88888, cnName = "前置审批")
    public static final long ATTACHMENT_USE_CODE_PRE_APPROVAL_CERTIFICATE = 88888;

    /** 网安回执单 */
    @BeanAttrInfo(orderBy = 77777, cnName = "网安回执单")
    public static final long ATTACHMENT_USE_CODE_POLICERECEIPT = 77777L;

    /** 统一社会信用代码证书 */
    @BeanAttrInfo(orderBy = 10024, cnName = "统一社会信用代码证书")
    public static final long ATTACHMENT_UNIFIED_SOCIAL_CREDIT_CERTIFICATE = 10024L;

    /** 会员附件 */
    @BeanAttrInfo(orderBy = 66666, cnName = "会员附件")
    public static final long ATTACHMENT_USE_CODE_MEMBER = 66666L;

    /** 主体负责人授权书 */
    @BeanAttrInfo(orderBy = 10026, cnName = "主体负责人授权书")
    public static final long ATTACHMENT_USE_CODE_ZT_RESP_WARRANT_CERTIFICATE = 10026L;

    /** 民办非企业单位登记证书 */
    @BeanAttrInfo(orderBy = 10029, cnName = "民办非企业单位登记证书")
    public static final long ATTACHMENT_USE_CODE_MBFQY_DWDJ_CERTIFICATE = 10029L;

    /** 律师事务所执业许可证 */
    @BeanAttrInfo(orderBy = 10030, cnName = "律师事务所执业许可证")
    public static final long ATTACHMENT_USE_CODE_LSSWS_ZYXK_CERTIFICATE = 10030L;

    /** 基金会法人登记证书 */
    @BeanAttrInfo(orderBy = 10031, cnName = "基金会法人登记证书")
    public static final long ATTACHMENT_USE_CODE_JJH_CAREER_LEGAL_CERTIFICATE = 10031L;
}
