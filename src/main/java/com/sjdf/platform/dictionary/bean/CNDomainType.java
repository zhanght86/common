package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-06-05
 *
 * @author 王正伟
 * @category CN域名注册类型
 */

@Entity
@DiscriminatorValue("CN_DOMAIN_TYPE")
@BeanName(name = "CN注册类型")
public class CNDomainType extends Dictionary {
    private static final long serialVersionUID = 2394668568492936420L;
    /** 其他域名 */
    @BeanAttrInfo(value = "1", cnName = "其他域名类型", enName = "other type")
    public static final long OTHER_TYPE = 1;
    /** 个人姓名域名 */
    @BeanAttrInfo(value = "2", cnName = "个人姓名域名", enName = "personal name")
    public static final long CN_PERSONAL_NAME = 2;
    /** 个人公众域名 */
    @BeanAttrInfo(value = "3", cnName = "个人公众域名", enName = "personal public")
    public static final long CN_PERSONAL_PUBLIC = 3;
    /** 企业公众域名 */
    @BeanAttrInfo(value = "4", cnName = "企业公众域名", enName = "company")
    public static final long CN_COMPANY = 4;
}
