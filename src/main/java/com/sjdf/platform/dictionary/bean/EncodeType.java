package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-04-05
 *
 * @author 王正伟
 * @category 字符编码类型
 */
@Entity
@DiscriminatorValue("ENCODE_TYPE")
@BeanName(name = "字符编码类型")
public class EncodeType extends Dictionary {
    private static final long serialVersionUID = 8642032665430901990L;
    /** 默认字符编码类型 */
    @BeanAttrInfo(value = "DEFAULT", cnName = "默认", refClass = EncodeType.class, refAttr = EncodeType.UTF8)
    public static final long DEFAULT = 1;

    @BeanAttrInfo(value = "UTF-8", cnName = "UTF8")
    public static final long UTF8 = 2;

    @BeanAttrInfo(value = "GBK", cnName = "GBK")
    public static final long GBK = 3;
}
