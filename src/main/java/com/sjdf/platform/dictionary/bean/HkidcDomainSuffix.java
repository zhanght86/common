package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月30日 上午11:51:56
 * hkidc所支持的域名后缀(注意:value:表示顶级商,orderBy:表示是否需要联系人信息1不需要,2需要)
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("HKIDC_DOMAIN_SUFFIX")
@BeanName(name = "hkidc所支持的域名后缀")
public class HkidcDomainSuffix extends Dictionary {
    private static final long serialVersionUID = -8514122619929841586L;

    public static final String VERISIGN = "verisign";
    public static final String CNNIC = "cnnic";

    /** .com域名 */
    @BeanAttrInfo(orderBy = 1, value = VERISIGN, cnName = ".com", enName = ".com")
    public static final long COM = 1;

    /** .net域名 */
    @BeanAttrInfo(orderBy = 1, value = VERISIGN, cnName = ".net", enName = ".net")
    public static final long NET = 2;

    /** .cn域名 */
    @BeanAttrInfo(orderBy = 2, value = CNNIC, cnName = ".cn", enName = ".cn")
    public static final long CN = 3;

    /** .中国域名 */
    @BeanAttrInfo(orderBy = 2, value = CNNIC, cnName = ".中国", enName = ".xn--fiqs8s")
    public static final long CHINA = 4;

    /**
     * 判断是否是cnnic域名
     *
     * @return boolean
     */
    public boolean isCnnicDomain() {
        return CNNIC.equals(getValue());
    }

    /**
     * 判断是否是verisign域名
     *
     * @return boolean
     */
    public boolean isVerisignDomain() {
        return VERISIGN.equals(getValue());
    }

    /**
     * 判断是否需要联系人信息
     *
     * @return boolean
     */
    public boolean isNeedContact() {
        return CommonPlatformConstant.LENGTH_2 == getOrderBy();
    }
}
