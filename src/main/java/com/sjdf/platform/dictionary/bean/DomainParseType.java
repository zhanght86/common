package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:17:29
 *
 * @author KETQI
 * @category 域名解析类别
 */
@Entity
@DiscriminatorValue("DOMAIN_PARSE_TYPE")
@BeanName(name = "域名解析类别")
public class DomainParseType extends Dictionary {
    private static final long serialVersionUID = 2882157632658724040L;

    /** @category MX */
    @BeanAttrInfo(value = "MX", orderBy = 1, cnName = "MX")
    public static final long MX_TYPE = 1;

    /** @category CNAME */
    @BeanAttrInfo(value = "CNAME", orderBy = 2, cnName = "CNAME")
    public static final long CNAME_TYPE = 2;

    /** @category A */
    @BeanAttrInfo(value = "A", orderBy = 3, cnName = "A")
    public static final long A_TYPE = 3;

    /** @category 转发 */
    @BeanAttrInfo(value = "转发", orderBy = 4, cnName = "转发")
    public static final long FORWARD_TYPE = 4;

    /** @category TXT */
    @BeanAttrInfo(value = "TXT", orderBy = 5, cnName = "TXT")
    public static final long TXT_TYPE = 5;
}
