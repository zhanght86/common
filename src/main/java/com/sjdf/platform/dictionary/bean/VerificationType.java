package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-05-18
 *
 * @author 丁yan
 * @category 验证码所对应的类型
 */
@Entity
@DiscriminatorValue("VERIFICATION_TYPE")
@BeanName(name = "验证码所对应的类型")
public class VerificationType extends Dictionary {
    private static final long serialVersionUID = -1343282661367690818L;

    /** 域名转出 */
    @BeanAttrInfo(value = "1", cnName = "域名转出")
    public static final long TRANSFERS_OUT = 1;

    /** 修改所有者邮箱 */
    @BeanAttrInfo(value = "2", cnName = "修改所有者邮箱")
    public static final long EDIT_OWNER_EMAIL = 2;

    /** 会员找回密码 */
    @BeanAttrInfo(value = "3", cnName = "会员找回密码")
    public static final long MEMBER_FORGET_PASSWORD = 3;

    /** 会员绑定或修改电子邮箱/手机号码 */
    @BeanAttrInfo(value = "4", cnName = "会员绑定或修改电子邮箱/手机号码")
    public static final long MEMBER_SAFETYINFO_BINDING = 4;

    /** 会员初始密码 */
    @BeanAttrInfo(value = "5", cnName = "会员初始密码")
    public static final long MEMBER_INIT_PASSWORD = 5;

    /** 会员查看公司 */
    @BeanAttrInfo(value = "6", cnName = "会员查看公司")
    public static final long MEMBER_LOOK_COMPANY = 6;

    /** 会员修改公司 */
    @BeanAttrInfo(value = "7", cnName = "会员修改公司")
    public static final long MEMBER_MODIFY_COMPANY = 7;

    /** 会员查看公司附件 */
    @BeanAttrInfo(value = "8", cnName = "会员查看公司附件")
    public static final long MEMBER_LOOK_COMPANY_ATTACH = 8;

    /** 会员下载公司附件 */
    @BeanAttrInfo(value = "9", cnName = "会员下载公司附件")
    public static final long MEMBER_DOWN_COMPANY_ATTACH = 9;

    /** 会员删除公司附件 */
    @BeanAttrInfo(value = "10", cnName = "会员删除公司附件")
    public static final long MEMBER_DELETE_COMPANY_ATTACH = 10;

    /** 域名过户 */
    @BeanAttrInfo(value = "11", cnName = "域名过户")
    public static final long DOMIAN_TRANSFER_OWNER = 11;

    /** 会员绑定或修改电子邮箱/手机号码 */
    @BeanAttrInfo(value = "12", cnName = "会员绑定或修改电子邮箱/手机号码2")
    public static final long MEMBER_SAFETYINFO_BINDING_2 = 12;

    /** 会员注册验证码 */
    @BeanAttrInfo(value = "13", cnName = "会员注册验证码")
    public static final long USER_REGISTER_SEND_SMS = 13;

    /** 会员手机号码验证 */
    @BeanAttrInfo(value = "14", cnName = "会员手机号码验证")
    public static final long USER_MEMBER_MOBILE_SMS = 14;
}
