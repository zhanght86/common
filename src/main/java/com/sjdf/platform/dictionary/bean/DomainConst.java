package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2016-07-13
 *
 * @author Hunk
 * @category 域名常量
 */
@Entity
@DiscriminatorValue("DOMAIN_CONST")
@BeanName(name = "域名常量")
public class DomainConst extends Dictionary {
    private static final long serialVersionUID = 8456934823979791981L;
    /** 需要实名认证的域名后缀 */
    @BeanAttrInfo(value = ".COM,.NET", cnName = "需要实名认证的域名后缀（如果为空，所有域名都需要进行实名认证）")
    public static final long NEED_AUTH_DOMAIN_SUFFIX = 1;
}
