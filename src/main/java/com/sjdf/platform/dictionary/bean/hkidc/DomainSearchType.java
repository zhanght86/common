package com.sjdf.platform.dictionary.bean.hkidc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category HKIDC域名类型
 * @Date: 2015-10-22 10:50
 */
@Entity
@DiscriminatorValue("DOMAIN_SEARCH_TYPE")
@BeanName(name = "HKIDC域名类型")
public class DomainSearchType extends Dictionary {
    private static final long serialVersionUID = 3178195083913155799L;

    /** COM域名 */
    @BeanAttrInfo(value = ".com", cnName = "COM域名")
    public static final long COM = 1L;

    /** NET域名 */
    @BeanAttrInfo(value = ".net", cnName = "NET域名")
    public static final long NET = 2L;

    /** CN域名 */
    @BeanAttrInfo(value = ".cn", cnName = "CN域名")
    public static final long CN = 3L;

    /** 【.中国】域名 */
    @BeanAttrInfo(value = ".xn--fiqs8s", cnName = "【.中国】域名")
    public static final long CHINA = 4L;

    /** 【.公司】域名 */
    @BeanAttrInfo(value = ".xn--55qx5d", cnName = "【.公司】域名")
    public static final long COMPANY = 5L;

    /** 【.网络】域名 */
    @BeanAttrInfo(value = ".xn--io0a7i", cnName = "【.网络】域名")
    public static final long NETWORK = 6L;
}
