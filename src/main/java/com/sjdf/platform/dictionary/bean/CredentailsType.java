package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2012-10-12 下午3:33:35
 * @category 证件类型
 */
@Entity
@DiscriminatorValue("CREDENTAILS_TYPE")
@BeanName(name = "证件类型")
public class CredentailsType extends Dictionary {

    private static final long serialVersionUID = 2882157632658724013L;

    /** 工商营业执照号码,禁止修改value值,hkidc使用 */
    @BeanAttrInfo(value = "YYZZ", orderBy = 1, cnName = "工商营业执照号码")
    public static final long BUSINESS_LICENSE = 1;

    /** 身份证,禁止修改value值,hkidc使用 */
    @BeanAttrInfo(value = "SFZ", orderBy = 2, cnName = "身份证")
    public static final long ID_CARD = 2;

    /** 组织机构代码证书,禁止修改value值,hkidc使用 */
    @BeanAttrInfo(value = "ORG", orderBy = 3, cnName = "组织机构代码证书")
    public static final long ORGANIZATION_CERTIFICATE = 3;

    /** 事业法人证书 */
    @BeanAttrInfo(orderBy = 4, cnName = "事业法人证书")
    public static final long CAREER_LEGAL_CERTIFICATE = 4;

    /** 军队证 */
    @BeanAttrInfo(orderBy = 5, cnName = "军队证")
    public static final long ARMY_CODE = 5;

    /** 社团法人证书 */
    @BeanAttrInfo(orderBy = 6, cnName = "社团法人证书")
    public static final long SOCIETY_LEGAL_CERTIFICATE = 6;

    /** 护照 ,禁止修改value值,hkidc使用 */
    @BeanAttrInfo(value = "HZ", orderBy = 7, cnName = "护照")
    public static final long PASSPORT = 7;

    /** 军官证 ,禁止修改value值,hkidc使用 */
    @BeanAttrInfo(value = "JGZ", orderBy = 8, cnName = "军官证")
    public static final long OFFICER_CERTIFICATE = 8;

    /** 台胞证 */
    @BeanAttrInfo(orderBy = 9, cnName = "台胞证")
    public static final long TAIWAN_COMPATRIOTS_CERTIFICATE = 11;

}
