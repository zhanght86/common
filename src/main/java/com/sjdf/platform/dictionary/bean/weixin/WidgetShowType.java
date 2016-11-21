package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 控件显示类型
 */
@Entity
@DiscriminatorValue("WIDGET_SHOW_TYPE")
@BeanName(name = "控件显示类型")
public class WidgetShowType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 3437295725148093962L;

    /** 标题图标 */
    @BeanAttrInfo(value = "1", cnName = "标题图标")
    public static final long TITLE_ICON = 1L;

    /** 标题图标图片 */
    @BeanAttrInfo(value = "2", cnName = "标题图标图片")
    public static final long TITLE_ICON_IAMGE = 2L;

    /** 标题描述图片 */
    @BeanAttrInfo(value = "3", cnName = "标题描述图片")
    public static final long TITLE_INTRO_IMAGE = 3L;

    /** 标题内容图片 */
    @BeanAttrInfo(value = "4", cnName = "标题内容图片")
    public static final long TITLE_CONTENT_IMAGE = 4L;

    /** 标题图片 */
    @BeanAttrInfo(value = "5", cnName = "标题图片")
    public static final long TITLE_IMAGE = 5L;

    /** 标题 */
    @BeanAttrInfo(value = "6", cnName = "标题")
    public static final long ONLY_TITLE = 6L;

    /** 图片 */
    @BeanAttrInfo(value = "7", cnName = "图片")
    public static final long ONLY_IMAGE = 7L;

    /** 标题描述 */
    @BeanAttrInfo(value = "8", cnName = "标题描述")
    public static final long TITLE_INTRO = 8L;

    /** 经纬度 */
    @BeanAttrInfo(value = "9", cnName = "经纬度")
    public static final long LONGITUDE_LATITUDE = 9L;
}
