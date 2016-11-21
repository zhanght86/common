package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-08
 *
 * @author 王正伟
 * @category 个人证件类型
 */
@Entity
@DiscriminatorValue("PERSONAL_CERTIFICATE_TYPE")
@BeanName(name = "个人证件类型")
public class PersonalCertificateType extends Dictionary {
    private static final long serialVersionUID = 8771147257719345645L;
    /** 身份证 */
    @BeanAttrInfo(value = "10014", cnName = "身份证 ", enName = "id card")
    public static final long ID_CARD = 10014;
    /** 护照 */
    @BeanAttrInfo(value = "10011", cnName = "护照", enName = "passport")
    public static final long PASSPORT = 10011;
    /** 台胞证 */
    @BeanAttrInfo(value = "10012", cnName = "台胞证", enName = "mpts")
    public static final long MTPS = 10012;
    /** 军官证 */
    @BeanAttrInfo(value = "10013", cnName = "军官证", enName = "military officer")
    public static final long MILITARY_OFFICER = 10013;
}
