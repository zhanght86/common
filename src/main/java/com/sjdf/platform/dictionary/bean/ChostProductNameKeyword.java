package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-11-07
 *
 * @author 黄远昌
 * @category 云主机产品名称关键字
 */
@Entity
@DiscriminatorValue("CHOST_PRODUCT_NAME_KEYWORD")
@BeanName(name = "云主机产品名称关键字")
public class ChostProductNameKeyword extends Dictionary {
    private static final long serialVersionUID = -3673156680643528133L;
    /** 行云 */
    @BeanAttrInfo(value = "行", cnName = "行云")
    public static final long CHOST_KEYWORD1 = 1;
    /** 鸿云 */
    @BeanAttrInfo(value = "鸿", cnName = "鸿云")
    public static final long CHOST_KEYWORD2 = 2;
    /** 登云 */
    @BeanAttrInfo(value = "登", cnName = "登云")
    public static final long CHOST_KEYWORD3 = 3;
    /** 腾飞云 */
    @BeanAttrInfo(value = "腾飞", cnName = "腾飞云")
    public static final long CHOST_KEYWORD4 = 4;
}
