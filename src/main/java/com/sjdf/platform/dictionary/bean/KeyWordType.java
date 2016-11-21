package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @category 关键字类型
 */
@Entity
@DiscriminatorValue("KEY_WORD_TYPE")
@BeanName(name = "关键字类型")
public class KeyWordType extends Dictionary {

    private static final long serialVersionUID = 2882163375429724168L;

    /** 名称 */
    @BeanAttrInfo(orderBy = 1, cnName = "名称")
    public static final long NAME = 1;

    /** 证件号码 */
    @BeanAttrInfo(orderBy = 2, cnName = "证件号码")
    public static final long CERTIFICATE_NUM = 2;

    /** 证件住所 */
    @BeanAttrInfo(orderBy = 3, cnName = "证件住所")
    public static final long CERTIFICATE_ADDR = 3;

    /** 主办单位名称 */
    @BeanAttrInfo(orderBy = 4, cnName = "主办单位名称")
    public static final long FIRM_NAME = 4;

    /** 域名 */
    @BeanAttrInfo(orderBy = 5, cnName = "域名")
    public static final long DOMAIN_NAME = 5;

    /** 法定代表人姓名 */
    @BeanAttrInfo(orderBy = 6, cnName = "法定代表人姓名")
    public static final long LEGAL_NAME = 6;

    /** 网站名称 */
    @BeanAttrInfo(orderBy = 9, cnName = "网站名称")
    public static final long WEB_NAME = 9;
}
