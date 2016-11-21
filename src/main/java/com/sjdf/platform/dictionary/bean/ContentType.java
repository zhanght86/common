package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author andy
 * @category kefu content type
 * @ClassName ContentType
 * @Created 2013-10-25
 */
@Entity
@DiscriminatorValue("CONTENT_TYPE")
@BeanName(name = "客服信息")
public class ContentType extends Dictionary {
    private static final long serialVersionUID = -2749892746665664901L;

    /** 客服信息类型一 */
    @BeanAttrInfo(value = "1", cnName = "您好，世纪东方欢迎您的光临，请问有什么可以帮到您？")
    public static final long KEFU_ONE = 1;
}
