package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-08-21
 *
 * @author 王正伟
 * @category 邮件标题
 */
@Entity
@DiscriminatorValue("EMAIL_TITLE")
@BeanName(name = "邮件标题")
public class EmailTitle extends Dictionary {
    private static final long serialVersionUID = 4845516711562715878L;

    @BeanAttrInfo(systemType = SystemType.DOMAIN, orderBy = 1, cnName = "域名过户", value = "域名过户")
    public static final long HKIDC_DOMAIN_TRANSFER = 1;
}
