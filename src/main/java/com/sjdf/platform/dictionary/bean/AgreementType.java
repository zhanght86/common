package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-6-6
 *
 * @author 丁yan
 * @category 授权协议类型
 */
@Entity
@DiscriminatorValue("AGREEMENT_TYPE")
@BeanName(name = "授权协议类型")
public class AgreementType extends Dictionary {

    private static final long serialVersionUID = -2523674273809432686L;

    /** 商务中国域名修改邮箱授权协议 */
    @BeanAttrInfo(value = "", cnName = "商务中国域名修改邮箱授权协议")
    public static final long BIZCN_DOMAIN_EDIT_EMAIL_AGREEMENT = 1;
}
