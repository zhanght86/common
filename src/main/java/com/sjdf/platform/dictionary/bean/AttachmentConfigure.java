package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * Create at 2013-4-17 下午2:52:02
 *
 * @author frank
 * @category 附件文件路径配置类
 */
@Entity
@DiscriminatorValue("ATTACHMENT_FILE_PATH_CONFIGURE")
@BeanName(name = "附件文件路径配置")
public class AttachmentConfigure extends Dictionary {

    private static final long serialVersionUID = 2882157632658724037L;

    /** 附件库附件保存路径 */
    @BeanAttrInfo(value = "/data/home/AttachmentFile", orderBy = 1, cnName = "附件库附件保存路径")
    public static final long COMMON_ATTACHMENT_SAVE_PATH = 1;

    /** 备案系统临时附件保存路径 */
    @BeanAttrInfo(value = "/data/home/record", orderBy = 2, cnName = "备案系统临时附件保存路径")
    public static final long RECORD_ATTACHMENT_TEMP_SAVE_PATH = 2;

    /** 域名系统临时附件保存路径 */
    @BeanAttrInfo(value = "/data/home/ImageTemp/Domain", orderBy = 3, cnName = "域名系统临时附件保存路径")
    public static final long DOMAIN_ATTACHMENT_TEMP_SAVE_PATH = 3;

    /** 附件图片显示的临时路径名称 */
    @BeanAttrInfo(value = "attachmentTempPath", orderBy = 4, cnName = "附件图片显示的临时路径名称")
    public static final long ATTACHMENT_TEMP_PATH = 4;

    /** 附件缩小图片后的存放路径 */
    @BeanAttrInfo(value = "resizeImage", orderBy = 5, cnName = "附件缩小图片后的存放路径")
    public static final long COMMON_RESIZE_IMAGE = 5;

    /** 附件缩小宽度 */
    @BeanAttrInfo(value = "308", orderBy = 6, cnName = "附件缩小宽度")
    public static final long RESIZE_WIDTH = 6;

    /** 附件缩小高度 */
    @BeanAttrInfo(value = "247", orderBy = 7, cnName = "附件缩小高度")
    public static final long RESIZE_HEIGHT = 7;

    /** 附件获取方式 */
    @BeanAttrInfo(value = "2", orderBy = 8, cnName = "附件获取方式,1表示从附件库获取，2表示从本地获取")
    public static final long GET_ATTACHMENT_WAY = 8;

    /** 主站系统临时附件保存路径 */
    @BeanAttrInfo(value = "/home/ImageTemp/Eiss", orderBy = 9, cnName = "主站系统临时附件保存路径")
    public static final long EISS_ATTACHMENT_TEMP_SAVE_PATH = 9;

    /** 主站系统附件支持的后缀 */
    @BeanAttrInfo(value = ".jpg,.JPG,.rar", orderBy = 10, cnName = "主站系统附件支持的后缀")
    public static final long EISS_ATTACHMENT_SUFFIX = 10;

    /**
     * ----------------页面内容管理系统附件相关配置
     */
    /** 页面内容管理系统临时存放路径 */
    @BeanAttrInfo(value = "/home/temp/pageContentManage", orderBy = 101, cnName = "页面内容管理系统临时存放路径")
    public static final long PAGE_CONTENT_TEMP_SAVE_PATH = 101;
    /** 页面内容管理系统存放路径 */
    @BeanAttrInfo(value = "/home/wwwroot/new_static_51web/common/images/upload/pageContentManage", orderBy = 102, cnName = "页面内容管理系统存放路径")
    public static final long PAGE_CONTENT_SAVE_PATH = 102;

    /** 主站轮播广告管理存放路径 */
    @BeanAttrInfo(value = "/home/wwwroot/new_static_51web/eiss_new/images/upload/carouselAdvertise", orderBy = 202, cnName = "主站轮播广告管理存放路径")
    public static final long EISS_CAROUSE_ADVERTISE_SAVE_PATH = 202;

    /** 主站首页产品展示管理存放路径 */
    @BeanAttrInfo(value = "/home/wwwroot/new_static_51web/eiss_new/images/upload/homeProductShow", orderBy = 202, cnName = "主站首页产品展示管理存放路径")
    public static final long EISS_HOME_PRODUCT_SHOW_SAVE_PATH = 203;

    /** 主站首页新闻图片管理存放路径 */
    @BeanAttrInfo(value = "/home/wwwroot/new_static_51web/eiss_new/images/upload/newsImageShow", orderBy = 202, cnName = "主站新闻图片管理存放路径")
    public static final long EISS_HOME_NEW_IMG_SHOW_SAVE_PATH = 204;

    /** 微信模板示例图片存放相对路径 */
    @BeanAttrInfo(value = "/weixin/images/upload/template", orderBy = 205, cnName = "微信模板示例图片存放相对路径")
    public static final long WEI_XIN_TEMPLATE_IMG_SAVE_PATH = 205;

    /** 微信公共账号图片存放相对路径 */
    @BeanAttrInfo(value = "/weixin/images/upload/account", orderBy = 206, cnName = "微信公共账号图片存放相对路径")
    public static final long WEI_XIN_ACCOUNT_IMG_SAVE_PATH = 206;

}
