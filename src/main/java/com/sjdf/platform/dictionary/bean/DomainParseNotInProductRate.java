package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-11-15
 *
 * @author 邱忠福
 * @category 域名解析中与产品不一致所占的警告比例
 */
@Entity
@DiscriminatorValue("DOMAIN_PARSE_NOT_IN_PRODUCT_RATE")
@BeanName(name = "域名解析中与产品不一致所占的警告比例")
public class DomainParseNotInProductRate extends Dictionary {

    private static final long serialVersionUID = -3453342223750859401L;

    /** 警告比例 */
    @BeanAttrInfo(value = "0.2", cnName = "警告比例")
    public static final long WANNING_RATE = 1;
}
