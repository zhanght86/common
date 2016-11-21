package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 微信模板类型
 */
@Entity
@DiscriminatorValue("WEI_XIN_TEMPLATE_TYPE")
@BeanName(name = "微信模板类型")
public class WeiXinTemplateType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = -1859047857710231715L;

    /** 栏目首页模板风格 */
    @BeanAttrInfo(value = "1", cnName = "栏目首页模板风格")
    public static final long HOME = 1L;

    /** 频道模板风格 */
    @BeanAttrInfo(value = "2", cnName = "频道模板风格")
    public static final long CHANNEL = 2L;

    /** 图文列表模板风格 */
    @BeanAttrInfo(value = "3", cnName = "图文列表模板风格")
    public static final long GRAPHIC_LIST = 3L;

    /** 图文详细模板风格 */
    @BeanAttrInfo(value = "4", cnName = "图文详细模板风格")
    public static final long GRAPHIC_DETAIL = 4L;

}
