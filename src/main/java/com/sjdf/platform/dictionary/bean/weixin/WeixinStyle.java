package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 微信自定义页面风格
 * @create 2014年4月14日12:28:21
 */
@Entity
@DiscriminatorValue("WEI_XIN_STYLE")
@BeanName(name = "微信自定义页面风格")
public class WeixinStyle extends Dictionary {

    private static final long serialVersionUID = -6127215704648737688L;

    /** 世纪东方微信平台默认风格 */
    @BeanAttrInfo(value = "1", cnName = "default")
    public static final long DEFAULT_STYLE = 1L;

    /** 世纪东方微信平台STYLE_1风格 */
    @BeanAttrInfo(value = "2", cnName = "style1")
    public static final long STYLE_1 = 2L;

    /** 世纪东方微信平台STYLE_2风格 */
    @BeanAttrInfo(value = "3", cnName = "style2")
    public static final long STYLE_2 = 3L;

}
