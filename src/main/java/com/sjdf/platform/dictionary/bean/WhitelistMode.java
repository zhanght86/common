package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-2-22
 *
 * @author 邱忠福
 * @category
 */
@Entity
@DiscriminatorValue("WHITELIST_MODE")
@BeanName(name = "白名单模式")
public class WhitelistMode extends Dictionary {

    private static final long serialVersionUID = 161019255616405272L;

    /** 单域名白名单 */
    @BeanAttrInfo(value = "0", cnName = "单域名白名单")
    public static final long WHITELIST_SINGLE_DOMAIN = 1;

    /** 泛解析白名单 */
    @BeanAttrInfo(value = "1", cnName = "泛解析白名单")
    public static final long WHITELIST_SPREAD_RECORD = 2;

    /** 免白名单 */
    @BeanAttrInfo(value = "2", cnName = "免白名单")
    public static final long WHITELIST_NO_NEED = 3;
}
