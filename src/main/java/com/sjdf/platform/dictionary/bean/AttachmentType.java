package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-23
 *
 * @author 王正伟
 * @category 附件类型
 */
@Entity
@DiscriminatorValue("ATTACHMENT_TYPE")
@BeanName(name = "附件类型")
public class AttachmentType extends Dictionary {
    private static final long serialVersionUID = 8067708213561064991L;

    @BeanAttrInfo(orderBy = 1, cnName = "邮件内容")
    public static final long EMAIL_CONTENT = 1;

    @BeanAttrInfo(orderBy = 5, cnName = "个人或者域名所有人身份证正反两面")
    public static final long DOMAIN_OWNER_ID_CARD_DOUBLE_SIDE = 5;

    @BeanAttrInfo(orderBy = 10, cnName = "域名联系人身份证正反两面")
    public static final long DOMAIN_CONTACT_ID_CARD_DOUBLE_SIDE = 10;

    @BeanAttrInfo(orderBy = 15, cnName = "年检有效的营业执照副本或组织机构代码证")
    public static final long BUSINESS_LICENSE_OR_ORGANIZATION_CODE = 15;

    @BeanAttrInfo(orderBy = 20, cnName = "侵权证明")
    public static final long INFRINGEMENT_PROVE = 20;

    @BeanAttrInfo(orderBy = 25, cnName = "法律文件")
    public static final long LEGAL_DOCUMENT = 25;

    @BeanAttrInfo(orderBy = 30, systemType = SystemType.EISS, cnName = "域名所有者证件")
    public static final long DOMAIN_OWNER_CARD = 30;

    @BeanAttrInfo(orderBy = 31, systemType = SystemType.EISS, cnName = "单位证件")
    public static final long DOMAIN_ENTERPRISE_CARD = 31;

    @BeanAttrInfo(orderBy = 32, systemType = SystemType.EISS, cnName = "注册申请表")
    public static final long DOMAIN_REGISTER_APPLY_CARD = 32;

    @BeanAttrInfo(orderBy = 33, systemType = SystemType.EISS, cnName = "转出申请表")
    public static final long DOMAIN_TRANSFER_OUT_APPLY_TABLE = 33;

    @BeanAttrInfo(orderBy = 34, systemType = SystemType.EISS, cnName = "代理间业务转移申请表")
    public static final long DOMAIN_AGENT_TRANSFER_APPLY_TABLE = 34;

    @BeanAttrInfo(orderBy = 35, systemType = SystemType.EISS, cnName = "过户申请表")
    public static final long DOMAIN_TRANSFER_APPLY_TABLE = 35;

    @BeanAttrInfo(orderBy = 36, systemType = SystemType.EISS, cnName = "旧所有人证件")
    public static final long DOMAIN_OLD_OWNER_CARD = 36;

    @BeanAttrInfo(orderBy = 37, systemType = SystemType.EISS, cnName = "新所有人证件")
    public static final long DOMAIN_NEW_OWNER_CARD = 37;

    @BeanAttrInfo(orderBy = 38, systemType = SystemType.EISS, cnName = "免责声明")
    public static final long DOMAIN_DISCLAIMER_DECLARATION = 38;

    @BeanAttrInfo(orderBy = 39, systemType = SystemType.EISS, cnName = "旧联系人证件")
    public static final long DOMAIN_OLD_CONTACT_CARD = 39;

    @BeanAttrInfo(orderBy = 40, systemType = SystemType.EISS, cnName = "新联系人证件")
    public static final long DOMAIN_NEW_CONTACT_CARD = 40;

    @BeanAttrInfo(orderBy = 41, systemType = SystemType.EISS, cnName = "授权委托书")
    public static final long DOMAIN_COMMISSION = 41;

    @BeanAttrInfo(orderBy = 42, systemType = SystemType.EISS, cnName = "域名信息修改申请表")
    public static final long DOMAIN_INFO_MODIFY_TABLE = 42;

    @BeanAttrInfo(orderBy = 43, systemType = SystemType.EISS, cnName = "域名联系人证件")
    public static final long DOMAIN_CONTACT_CARD = 43;

    @BeanAttrInfo(orderBy = 44, systemType = SystemType.EISS, cnName = "所在帮助中心位置")
    public static final long DOMAIN_HELP_LOCATION = 44;

    @BeanAttrInfo(orderBy = 45, systemType = SystemType.EISS, cnName = "新网声明书")
    public static final long XINNET_STATEMENT = 45;

    @BeanAttrInfo(orderBy = 46, systemType = SystemType.DOMAIN, cnName = "备案承诺书")
    public static final long DOMAIN_RECORD_PROMISETION = 46;

    @BeanAttrInfo(orderBy = 47, systemType = SystemType.DOMAIN, cnName = "商标证书")
    public static final long DOMAIN_RECORD_CERT = 47;

    @BeanAttrInfo(orderBy = 48, systemType = SystemType.DOMAIN, cnName = "商标持有人组织证明材料")
    public static final long DOMAIN_RECORD_ORG = 48;

    @BeanAttrInfo(orderBy = 49, systemType = SystemType.DOMAIN, cnName = "gov域名申请表")
    public static final long DOMAIN_RECORD_GOV = 49;

    @BeanAttrInfo(orderBy = 9999, cnName = "其他类型")
    public static final long OTHER_TYPE = 9999;
}
