package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 控件类型
 */
@Entity
@DiscriminatorValue("WIDGET_TYPE")
@BeanName(name = "控件类型")
public class WidgetType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = -6558908450607309873L;

    /** 导航控件 */
    @BeanAttrInfo(value = "1", cnName = "导航控件")
    public static final long NAV = 1L;

    /** 频道控件 */
    @BeanAttrInfo(value = "2", cnName = "频道控件")
    public static final long CHANNEL = 2L;

    /** 快捷菜单控件 */
    @BeanAttrInfo(value = "3", cnName = "快捷菜单控件")
    public static final long MENU = 3L;

    /** 图片展示控件 */
    @BeanAttrInfo(value = "4", cnName = "图片展示控件")
    public static final long IMAGE = 4L;

    /** 底部展示控件 */
    @BeanAttrInfo(value = "5", cnName = "底部展示控件")
    public static final long FOOT = 5L;

}
