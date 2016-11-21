package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 页面内容管理-页面标识
 *
 * @author sjdf
 */
@Entity
@DiscriminatorValue("PAGE_CONTENT_MARKUP")
@BeanName(name = "页面内容管理-页面标识")
public class PageContentMarkup extends Dictionary {
    private static final long serialVersionUID = 2662494812053822072L;
    /**
     * 主站公共页面顶部链接
     */
    @BeanAttrInfo(value = "1", cnName = "主站公共页面顶部链接", systemType = SystemType.EISS)
    public static final long EISS_COMMON_TOP_LINK = 1;
    /**
     * 主站公共页面底部链接
     */
    @BeanAttrInfo(value = "2", cnName = "主站公共页面底部链接", systemType = SystemType.EISS)
    public static final long EISS_COMMON_FOOT_LINK = 2;
    /**
     * 主站公共页面底部说明
     */
    @BeanAttrInfo(value = "3", cnName = "主站公共页面底部信息", systemType = SystemType.EISS)
    public static final long EISS_COMMON_FOOT_INFO = 3;
    /**
     * 主站公共页面导航条链接
     */
    @BeanAttrInfo(value = "4", cnName = "主站公共页面导航条链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK = 4;
    /**
     * 主站顶部快速链接
     */
    @BeanAttrInfo(value = "5", cnName = "主站顶部快速链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_TOP_QUICK_LINK = 5;
    /**
     * 主站左侧产品优势
     */
    @BeanAttrInfo(value = "6", cnName = "主站左侧产品优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_LEFT_PRODUCT_ADVANTAGE = 6;
    /**
     * 主站右侧产品优势
     */
    @BeanAttrInfo(value = "7", cnName = "主站右侧产品优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_RIGHT_PRODUCT_ADVANTAGE = 7;
    /**
     * 主站云主机广告
     */
    @BeanAttrInfo(value = "8", cnName = "主站云主机广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_AD = 8;
    /**
     * 主站客服电话
     */
    @BeanAttrInfo(value = "9", cnName = "主站客服电话", systemType = SystemType.EISS)
    public static final long EISS_NEW_SERVICE_TEL = 9;
    /**
     * =============================主站公共页面导航条链接=========================
     */
    /**
     * 主站公共页面导航条链接域名其他服务
     */
    @BeanAttrInfo(value = "10", cnName = "主站公共页面导航条链接域名其他服务", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_DOMAIN_OTHER_SERVICE = 10;
    /**
     * 主站公共页面导航条链接域名购买指南
     */
    @BeanAttrInfo(value = "11", cnName = "主站公共页面导航条链接域名购买指南", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_DOMAIN_BUY_GUIDE = 11;
    /**
     * 主站公共页面导航条链接域名
     */
    @BeanAttrInfo(value = "12", cnName = "主站公共页面导航条链接域名", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_DOMAIN = 12;
    /**
     * 主站公共页面导航条链接首页
     */
    @BeanAttrInfo(value = "13", cnName = "主站公共页面导航条链接首页", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_HOME = 13;
    /**
     * 主站公共页面导航条链接云服务器
     */
    @BeanAttrInfo(value = "14", cnName = "主站公共页面导航条链接云服务器", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_CHOST = 14;
    /**
     * 主站公共页面导航条链接云服务器
     */
    @BeanAttrInfo(value = "15", cnName = "主站公共页面导航条链接云虚拟主机", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_VHOST = 15;
    /**
     * 主站公共页面导航条链接网站建设
     */
    @BeanAttrInfo(value = "16", cnName = "主站公共页面导航条链接网站建设", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_WEB_BUILD = 16;
    /**
     * 主站公共页面导航条链接企业办公
     */
    @BeanAttrInfo(value = "17", cnName = "主站公共页面导航条链接企业办公", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_WEB_BUSINESS_OFFICE = 17;
    /**
     * ==========================主站首页========================================
     */
    /**
     * 主站首页右边
     */
    @BeanAttrInfo(value = "18", cnName = "主站首页右边", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT = 18;
    /**
     * 主站首页右边新闻公告
     */
    @BeanAttrInfo(value = "19", cnName = "主站首页右边新闻公告", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_NEWS = 19;
    /**
     * 主站首页右边客户展示
     */
    @BeanAttrInfo(value = "20", cnName = "主站首页右边客户展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_CUSTOMER_SHOW = 20;
    /**
     * 主站首页右边服务网络
     */
    @BeanAttrInfo(value = "21", cnName = "主站首页右边服务网络", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_SERVICE_NET = 21;
    /**
     * 主站首页右边资质证明
     */
    @BeanAttrInfo(value = "22", cnName = "主站首页右边资质证明", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_CERTIFICATION = 22;
    /**
     * 主站首页右边快捷查询
     */
    @BeanAttrInfo(value = "23", cnName = "主站首页右边快捷查询", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_QUICK_CHECK = 23;
    /**
     * 主站首页产品展示云计算
     */
    @BeanAttrInfo(value = "24", cnName = "主站首页产品展示云计算", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING = 24;
    /**
     * 主站首页产品展示域名服务
     */
    @BeanAttrInfo(value = "25", cnName = "主站首页产品展示域名服务", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE = 25;
    /**
     * 主站首页产品展示企业办公
     */
    @BeanAttrInfo(value = "26", cnName = "主站首页产品展示企业办公", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_BUSINESS_OFFICE = 26;
    /**
     * 主站首页产品展示网站建设
     */
    @BeanAttrInfo(value = "27", cnName = "主站首页产品展示网站建设", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD = 27;
    /**
     * 主站首页产品展示云计算-云服务器
     */
    @BeanAttrInfo(value = "28", cnName = "主站首页产品展示云计算-云服务器", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_CHOST = 28;
    /**
     * 主站首页产品展示云计算-云虚拟主机
     */
    @BeanAttrInfo(value = "29", cnName = "主站首页产品展示云计算-云虚拟主机", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_VHOST = 29;
    /**
     * 主站首页产品展示云计算-租用托管
     */
    @BeanAttrInfo(value = "30", cnName = "主站首页产品展示云计算-租用托管", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_IDC = 30;
    /**
     * 主站首页产品展示域名服务-英文域名
     */
    @BeanAttrInfo(value = "31", cnName = "主站首页产品展示域名服务-英文域名", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_EN = 31;
    /**
     * 主站首页产品展示域名服务-中文域名
     */
    @BeanAttrInfo(value = "32", cnName = "主站首页产品展示域名服务-中文域名", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_CN = 32;
    /**
     * 主站首页产品展示域名服务-新顶级域
     */
    @BeanAttrInfo(value = "33", cnName = "主站首页产品展示域名服务-新顶级域", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_NEW_TOP = 33;
    /**
     * 主站首页产品展示域名服务-帮助中心
     */
    @BeanAttrInfo(value = "34", cnName = "主站首页产品展示域名服务-帮助中心", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_HELP = 34;
    /**
     * 主站首页产品展示企业办公-软件定制
     */
    @BeanAttrInfo(value = "37", cnName = "主站首页产品展示企业办公-软件定制", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_BUSINESS_OFFICE_SOFT_CUSTOMIZATION = 37;
    /**
     * 主站首页产品展示网站建设-个性设计
     */
    @BeanAttrInfo(value = "38", cnName = "主站首页产品展示网站建设-个性设计", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD_INDIVIDUAL_DESIGN = 38;
    /**
     * 主站首页产品展示网站建设-个性设计
     */
    @BeanAttrInfo(value = "39", cnName = "主站首页产品展示网站建设-精典案例", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD_CLASSIC_CASES = 39;
    /**
     * 主站首页产品展示
     */
    @BeanAttrInfo(value = "40", cnName = "主站首页产品展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT = 40;
    /**
     * 主站首页产品展示云计算产品展示
     */
    @BeanAttrInfo(value = "41", cnName = "主站首页产品展示云计算产品展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_PRODUCT = 41;
    /**
     * 主站首页产品展示云计算导购
     */
    @BeanAttrInfo(value = "42", cnName = "主站首页产品展示云计算导购", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_GUIDE = 42;
    /**
     * 主站首页产品展示域名服务产品展示
     */
    @BeanAttrInfo(value = "43", cnName = "主站首页产品展示域名服务产品展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_PRODUCT = 43;
    /**
     * 主站首页产品展示域名服务域名注册攻略
     */
    @BeanAttrInfo(value = "44", cnName = "主站首页产品展示域名服务域名注册攻略", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_DOMAIN_SERVICE_GUIDE = 44;
    /**
     * 主站首页产品展示企业办公-产品展示
     */
    @BeanAttrInfo(value = "45", cnName = "主站首页产品展示企业办公-产品展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_BUSINESS_OFFICE_PRODUCT = 45;
    /**
     * 主站首页产品展示网站建设-产品展示
     */
    @BeanAttrInfo(value = "46", cnName = "主站首页产品展示网站建设-产品展示", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD_PRODUCT = 46;
    /**
     * 主站首页产品展示网站建设-新手建站指南
     */
    @BeanAttrInfo(value = "47", cnName = "主站首页产品展示网站建设-新手建站指南", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD_GUIDE = 47;
    /**
     * 主站首页产品展示企业办公-企业商务应用指南
     */
    @BeanAttrInfo(value = "48", cnName = "主站首页产品展示企业办公-企业商务应用指南", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_BUSINESS_OFFICE_GUIDE = 48;
    /**
     * =========================主站云虚拟主机首页====================================
     */
    /**
     * 主站云虚拟主机首页-左边部分
     */
    @BeanAttrInfo(value = "49", cnName = "主站云虚拟主机首页-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_LEFT = 49;
    /**
     * 主站云虚拟主机首页-左边部分-云虚拟主机
     */
    @BeanAttrInfo(value = "50", cnName = "主站云虚拟主机首页-左边部分-云虚拟主机", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_LEFT_VHOST = 50;
    /**
     * 主站云虚拟主机首页-左边部分-世纪东方优势
     */
    @BeanAttrInfo(value = "51", cnName = "主站云虚拟主机首页-左边部分-世纪东方优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_LEFT_SUPERIORITY = 51;
    /**
     * 主站云虚拟主机首页-左边部分-广告
     */
    @BeanAttrInfo(value = "52", cnName = "主站云虚拟主机首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_LEFT_ADVERT = 52;
    /**
     * 主站云虚拟主机首页-左边部分-行业新闻
     */
    @BeanAttrInfo(value = "53", cnName = "主站云虚拟主机首页-左边部分-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_LEFT_NEWS = 53;
    /**
     * 主站云虚拟主机首页-主体部分-功能说明
     */
    @BeanAttrInfo(value = "54", cnName = "主站云虚拟主机首页-左边部分-功能说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_VHOST_HOME_MAIN_SHOW = 54;
    /**
     * =========================主站云服务器首页====================================
     */
    /**
     * 主站云服务器首页-左边部分
     */
    @BeanAttrInfo(value = "55", cnName = "主站云服务器首页-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_LEFT = 55;
    /**
     * 主站云服务器首页-左边部分-云服务器
     */
    @BeanAttrInfo(value = "56", cnName = "主站云服务器首页-左边部分-云服务器", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_LEFT_CHOST = 56;
    /**
     * 主站云服务器首页-左边部分-世纪东方优势
     */
    @BeanAttrInfo(value = "57", cnName = "主站云服务器首页-左边部分-世纪东方优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_LEFT_SUPERIORITY = 57;
    /**
     * 主站云服务器首页-左边部分-广告
     */
    @BeanAttrInfo(value = "58", cnName = "主站云服务器首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_LEFT_ADVERT = 58;
    /**
     * 主站云服务器首页-左边部分-行业新闻
     */
    @BeanAttrInfo(value = "59", cnName = "主站云服务器首页-左边部分-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_LEFT_NEWS = 59;
    /**
     * 主站云服务器首页-主体部分-功能说明
     */
    @BeanAttrInfo(value = "60", cnName = "主站云服务器首页-主体部分-功能说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_CHOST_HOME_MAIN_SHOW = 60;
    /**
     * =========================主站企业办公首页====================================
     */
    /**
     * 主站企业办公首页-左边部分
     */
    @BeanAttrInfo(value = "61", cnName = "主站企业办公首页-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_LEFT = 61;
    /**
     * 主站企业办公首页-左边部分-企业办公
     */
    @BeanAttrInfo(value = "62", cnName = "主站企业办公首页-左边部分-企业办公", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_LEFT_BUSINESS_OFFICE = 62;
    /**
     * 主站企业办公首页-左边部分-世纪东方优势
     */
    @BeanAttrInfo(value = "63", cnName = "主站企业办公首页-左边部分-世纪东方优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_LEFT_SUPERIORITY = 63;
    /**
     * 主站企业办公首页-左边部分-广告
     */
    @BeanAttrInfo(value = "64", cnName = "主站企业办公首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_LEFT_ADVERT = 64;
    /**
     * 主站企业办公首页-左边部分-行业新闻
     */
    @BeanAttrInfo(value = "65", cnName = "主站企业办公首页-左边部分-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_LEFT_NEWS = 65;
    /**
     * 主站企业办公首页-主体部分-功能说明
     */
    @BeanAttrInfo(value = "66", cnName = "主站企业办公首页-主体部分-功能说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUSINESS_OFFICE_HOME_MAIN_SHOW = 66;
    /**
     * =========================主站域名首页====================================
     */
    /**
     * 主站域名首页-左边部分
     */
    @BeanAttrInfo(value = "67", cnName = "主站域名首页-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_LEFT = 67;
    /**
     * 主站域名首页-左边部分-域名注册服务
     */
    @BeanAttrInfo(value = "68", cnName = "主站域名首页-左边部分-域名注册服务", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_LEFT_DOMAIN = 68;
    /**
     * 主站域名首页-左边部分-域名注册价格
     */
    @BeanAttrInfo(value = "69", cnName = "主站域名首页-左边部分-域名注册价格", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_LEFT_PRICE = 69;
    /**
     * 主站域名首页-左边部分-域名相关帮助
     */
    @BeanAttrInfo(value = "70", cnName = "主站域名首页-左边部分-域名相关帮助", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_LEFT_HELP = 70;
    /**
     * 主站域名首页-主体部分-背景图片-域名查询
     */
    @BeanAttrInfo(value = "71", cnName = "主站域名首页-主体部分-背景图片-域名查询", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_IMAGE_DOMAIN = 71;
    /**
     * 主站域名首页-主体部分-背景图片-批量查询
     */
    @BeanAttrInfo(value = "72", cnName = "主站域名首页-主体部分-背景图片-批量查询", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_IMAGE_BATCH = 72;
    /**
     * 主站域名首页-主体部分-域名说明
     */
    @BeanAttrInfo(value = "73", cnName = "主站域名首页-主体部分-域名说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW = 73;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势
     */
    @BeanAttrInfo(value = "74", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY = 74;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势-图片说明
     */
    @BeanAttrInfo(value = "75", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势-图片说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY_IMAGE = 75;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图
     */
    @BeanAttrInfo(value = "76", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY_CONTRAST = 76;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图-标题
     */
    @BeanAttrInfo(value = "77", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图-标题", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY_CONTRAST_TH = 77;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图-内容
     */
    @BeanAttrInfo(value = "78", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势-优势对比图-内容", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY_CONTRAST_TD = 78;
    /**
     * 主站域名首页-主体部分-域名说明-世纪东方域名优势-权威认证
     */
    @BeanAttrInfo(value = "79", cnName = "主站域名首页-主体部分-域名说明-世纪东方域名优势-权威认证", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_SUPERIORITY_AUTHORITY = 79;
    /**
     * 主站域名首页-主体部分-域名说明-域名价格总览
     */
    @BeanAttrInfo(value = "80", cnName = "主站域名首页-主体部分-域名说明-域名价格总览", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_PRICE = 80;
    /**
     * 主站域名首页-主体部分-域名说明-荣誉资质
     */
    @BeanAttrInfo(value = "81", cnName = "主站域名首页-主体部分-域名说明-荣誉资质", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_HONOR = 81;
    /**
     * 主站域名首页-主体部分-域名说明-域名指南
     */
    @BeanAttrInfo(value = "82", cnName = "主站域名首页-主体部分-域名说明-域名指南", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_SHOW_GUIDE = 82;
    /**
     * =========================主站付款方式页面====================================
     */
    /**
     * 主站付款方式页面-左边部分
     */
    @BeanAttrInfo(value = "83", cnName = "主站付款方式页面-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_LEFT = 83;
    /**
     * 主站付款方式页面-左边部分-付款方式
     */
    @BeanAttrInfo(value = "84", cnName = "主站付款方式页面-左边部分-付款方式", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_LEFT_PAY = 84;
    /**
     * 主站付款方式页面-左边部分-广告
     */
    @BeanAttrInfo(value = "85", cnName = "主站付款方式页面-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_LEFT_ADVERT = 85;
    /**
     * 主站付款方式页面-主体部分-网上支付
     */
    @BeanAttrInfo(value = "86", cnName = "主站付款方式页面-主体部分-网上支付", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY = 86;
    /**
     * 主站付款方式页面-主体部分-网上支付-顶部
     */
    @BeanAttrInfo(value = "87", cnName = "主站付款方式页面-主体部分-网上支付-顶部", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_TOP = 87;
    /**
     * 主站付款方式页面-主体部分-网上支付-快捷支付
     */
    @BeanAttrInfo(value = "88", cnName = "主站付款方式页面-主体部分-网上支付-快捷支付", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_QUICK = 88;
    /**
     * 主站付款方式页面-主体部分-网上支付-公司账号（对公账号三天到账）
     */
    @BeanAttrInfo(value = "89", cnName = "主站付款方式页面-主体部分-网上支付-公司账号（对公账号三天到账）", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_COMPANY = 89;
    /**
     * 主站付款方式页面-主体部分-网上支付-公司账号（立即到账）
     */
    @BeanAttrInfo(value = "90", cnName = "主站付款方式页面-主体部分-网上支付-公司账号（立即到账）", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_COMPANY_NOW = 90;
    /**
     * 主站付款方式页面-主体部分-网上支付-邮局电汇
     */
    @BeanAttrInfo(value = "91", cnName = "主站付款方式页面-主体部分-网上支付-邮局电汇", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_MAIL = 91;
    /**
     * 主站付款方式页面-主体部分-网上支付-底部说明
     */
    @BeanAttrInfo(value = "92", cnName = "主站付款方式页面-主体部分-网上支付-底部说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_BOTTOM = 92;
    /**
     * 主站付款方式页面-主体部分-银行电汇
     */
    @BeanAttrInfo(value = "93", cnName = "主站付款方式页面-主体部分-银行电汇", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT = 93;
    /**
     * 主站付款方式页面-主体部分-银行电汇-顶部
     */
    @BeanAttrInfo(value = "94", cnName = "主站付款方式页面-主体部分-银行电汇-顶部", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT_TOP = 94;
    /**
     * 主站付款方式页面-主体部分-银行电汇-样例
     */
    @BeanAttrInfo(value = "95", cnName = "主站付款方式页面-主体部分-银行电汇-样例", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT_EXAMPLE = 95;
    /**
     * 主站付款方式页面-主体部分-到我司付款
     */
    @BeanAttrInfo(value = "96", cnName = "主站付款方式页面-主体部分-到我司付款", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_COMPANY = 96;
    /**
     * 主站付款方式页面-主体部分-到我司付款-顶部
     */
    @BeanAttrInfo(value = "97", cnName = "主站付款方式页面-主体部分-到我司付款-顶部", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_COMPANY_TOP = 97;
    /**
     * 主站付款方式页面-主体部分-到我司付款-样例
     */
    @BeanAttrInfo(value = "98", cnName = "主站付款方式页面-主体部分-到我司付款-样例", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_COMPANY_EXAMPLE = 98;
    /**
     * 主站付款方式页面-主体部分-银行电汇-头部说明
     */
    @BeanAttrInfo(value = "99", cnName = "主站付款方式页面-主体部分-银行电汇-说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT_SHOW = 99;
    /**
     * 主站付款方式页面-主体部分-网上支付-头部导航
     */
    @BeanAttrInfo(value = "100", cnName = "主站付款方式页面-主体部分-网上支付-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_HEAD = 100;
    /**
     * 主站付款方式页面-主体部分-银行电汇-头部导航
     */
    @BeanAttrInfo(value = "101", cnName = "主站付款方式页面-主体部分-银行电汇-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT_HEAD = 101;
    /**
     * 主站付款方式页面-主体部分-到我司付款-头部导航
     */
    @BeanAttrInfo(value = "102", cnName = "主站付款方式页面-主体部分-到我司付款-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_COMPANY_HEAD = 102;
    /**
     * 主站付款方式页面-主体部分-网上支付-内容
     */
    @BeanAttrInfo(value = "103", cnName = "主站付款方式页面-主体部分-网上支付-内容", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_PAY_CONTENT = 103;
    /**
     * 主站付款方式页面-主体部分-银行电汇-内容
     */
    @BeanAttrInfo(value = "104", cnName = "主站付款方式页面-主体部分-银行电汇-内容", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_REMIT_CONTENT = 104;
    /**
     * 主站付款方式页面-主体部分-到我司付款-内容
     */
    @BeanAttrInfo(value = "105", cnName = "主站付款方式页面-主体部分-到我司付款-内容", systemType = SystemType.EISS)
    public static final long EISS_NEW_PAY_HOME_MAIN_COMPANY_CONTENT = 105;
    /**
     * 主站首页右边快捷查询-域名查询
     */
    @BeanAttrInfo(value = "106", cnName = "主站首页右边快捷查询-域名查询", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_QUICK_CHECK_DOMAIN = 106;
    /**
     * 主站首页右边快捷查询-域名查状态
     */
    @BeanAttrInfo(value = "107", cnName = "主站首页右边快捷查询-域名查状态", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_QUICK_CHECK_DOMAIN_STATUS = 107;
    /**
     * 主站首页右边快捷查询-域名查状态
     */
    @BeanAttrInfo(value = "108", cnName = "主站首页右边快捷查询-主机状态查询", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_QUICK_CHECK_VHOST = 108;
    /**
     * 主站首页右边新闻公告-最新公告
     */
    @BeanAttrInfo(value = "109", cnName = "主站首页右边新闻公告-最新公告", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_NEWS_BULLETIN = 109;
    /**
     * 主站首页右边新闻公告-行业新闻
     */
    @BeanAttrInfo(value = "110", cnName = "主站首页右边新闻公告-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_NEWS_NEWS = 110;
    /**
     * 主站首页右边新闻公告-优惠活动
     */
    @BeanAttrInfo(value = "111", cnName = "主站首页右边新闻公告-优惠活动", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_RIGHT_NEWS_SPECIAL_OFFERS = 111;

    /**
     * 主站公共页面导航条微信平台
     */
    @BeanAttrInfo(value = "112", cnName = "主站公共页面导航条链接微信平台", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_WEIXIN = 112;

    /**
     * =========================主站关于我们页面====================================
     */

    /**
     * 主站关于我们
     */
    @BeanAttrInfo(value = "120", cnName = "主站关于我们", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT = 120;

    /**
     * 主站关于我们-公司介绍
     */
    @BeanAttrInfo(value = "121", cnName = "主站关于我们-公司介绍", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_INTRODUCE = 121;

    /**
     * 主站关于我们-企业文化
     */
    @BeanAttrInfo(value = "122", cnName = "主站关于我们-企业文化", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CULTURE = 122;

    /**
     * 主站关于我们-公司资质
     */
    @BeanAttrInfo(value = "123", cnName = "主站关于我们-公司资质", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_HONOR = 123;

    /**
     * 主站关于我们-最新公告
     */
    @BeanAttrInfo(value = "124", cnName = "主站关于我们-最新公告", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_PLACARD = 124;

    /**
     * 主站关于我们-新闻中心
     */
    @BeanAttrInfo(value = "125", cnName = "主站关于我们-新闻中心", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_NEWS = 125;

    /**
     * 主站关于我们-合作伙伴
     */
    @BeanAttrInfo(value = "126", cnName = "主站关于我们-合作伙伴", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_PARTNER = 126;

    /**
     * 主站关于我们-客户案例
     */
    @BeanAttrInfo(value = "127", cnName = "主站关于我们-客户案例", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CASE = 127;

    /**
     * 主站关于我们-机房介绍
     */
    @BeanAttrInfo(value = "128", cnName = "主站关于我们-机房介绍", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_IDC = 128;

    /**
     * 主站关于我们-友情链接
     */
    @BeanAttrInfo(value = "129", cnName = "主站关于我们-友情链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_LINKS = 129;

    /**
     * 主站关于我们-加入我们
     */
    @BeanAttrInfo(value = "130", cnName = "主站关于我们-加入我们", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_JOINUS = 130;

    /**
     * 主站关于我们-联系我们
     */
    @BeanAttrInfo(value = "131", cnName = "主站关于我们-联系我们", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CONTACTUS = 131;

    /**
     * 主站关于我们-左边广告
     */
    @BeanAttrInfo(value = "132", cnName = "主站关于我们-左边广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_LEFT_AD = 132;

    /**
     * 主站会员注册-右侧广告
     */
    @BeanAttrInfo(value = "133", cnName = "主站会员注册-右侧广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_SIGN_UP_AD = 133;

    /**
     * 云服务器使用注意
     */
    @BeanAttrInfo(value = "134", cnName = "云服务器使用注意", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_CHOST_USE_TIP = 134;

    /**
     * 企业办公使用注意
     */
    @BeanAttrInfo(value = "135", cnName = "企业办公使用注意", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_MAIL_USE_TIP = 135;

    /**
     * 虚拟主机使用注意
     */
    @BeanAttrInfo(value = "136", cnName = "虚拟主机使用注意", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_VHOST_USE_TIP = 136;

    /**
     * 数据库使用注意
     */
    @BeanAttrInfo(value = "137", cnName = "数据库使用注意", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_DATABASE_USE_TIP = 137;

    /**
     * 企业办公注意事项
     */
    @BeanAttrInfo(value = "138", cnName = "企业办公注意事项", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_MAIL_TIP = 138;

    /**
     * 虚拟主机注意事项
     */
    @BeanAttrInfo(value = "139", cnName = "虚拟主机注意事项", systemType = SystemType.EISS)
    public static final long EISS_NEW_BUY_VHOST_TIP = 139;

    /**
     * 主站关于我们-网站地图
     */
    @BeanAttrInfo(value = "140", cnName = "主站关于我们-网站地图", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_SITE_MAP = 140;

    /** 主站代理加盟页面
     */
    /**
     * 主站代理加盟页面-左边部分
     */
    @BeanAttrInfo(value = "200", cnName = "主站代理加盟页面-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_LEFT = 200;
    /**
     * 主站代理加盟页面-左边部分-代理加盟
     */
    @BeanAttrInfo(value = "202", cnName = "主站代理加盟页面-左边部分-代理加盟", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_LEFT_AGENT = 202;
    /**
     * 主站代理加盟页面-左边部分-广告
     */
    @BeanAttrInfo(value = "203", cnName = "主站代理加盟页面-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_LEFT_ADVERT = 203;
    /**
     * 主站代理加盟页面-主体部分-代理加盟
     */
    @BeanAttrInfo(value = "204", cnName = "主站代理加盟页面-主体部分-代理加盟", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT = 204;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-头部导航
     */
    @BeanAttrInfo(value = "205", cnName = "主站代理加盟页面-主体部分-代理加盟-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_HEAD = 205;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-内容部分
     */
    @BeanAttrInfo(value = "206", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT = 206;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-轮播广告
     */
    @BeanAttrInfo(value = "207", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-轮播广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_ADVERT = 207;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-加盟优势
     */
    @BeanAttrInfo(value = "208", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_SUPERIORITY = 208;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_品牌优势
     */
    @BeanAttrInfo(value = "209", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_品牌优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_SUPERIORITY_BRAND = 209;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_利润优势
     */
    @BeanAttrInfo(value = "210", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_利润优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_SUPERIORITY_PROFIT = 210;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_产品优势
     */
    @BeanAttrInfo(value = "211", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_产品优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_SUPERIORITY_PRODUCT = 211;
    /**
     * 主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_服务优势
     */
    @BeanAttrInfo(value = "212", cnName = "主站代理加盟页面-主体部分-代理加盟-内容部分-加盟优势_服务优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_AGENT_CONTENT_SUPERIORITY_SERVICE = 212;
    /**
     * 主站代理加盟页面-主体部分-代理流程
     */
    @BeanAttrInfo(value = "213", cnName = "主站代理加盟页面-主体部分-代理流程", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_POCEDURE = 213;
    /**
     * 主站代理加盟页面-主体部分-代理流程-头部导航
     */
    @BeanAttrInfo(value = "214", cnName = "主站代理加盟页面-主体部分-代理流程-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_POCEDURE_HEAD = 214;
    /**
     * 主站代理加盟页面-主体部分-代理流程-内容部分
     */
    @BeanAttrInfo(value = "215", cnName = "主站代理加盟页面-主体部分-代理流程-内容部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_POCEDURE_CONTNET = 215;
    /**
     * 主站代理加盟页面-主体部分-代理流程-内容部分-轮播广告
     */
    @BeanAttrInfo(value = "216", cnName = "主站代理加盟页面-主体部分-代理流程-内容部分-轮播广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_POCEDURE_CONTNET_ADVERT = 216;
    /**
     * 主站代理加盟页面-主体部分-代理流程-内容部分-代理流程
     */
    @BeanAttrInfo(value = "217", cnName = "主站代理加盟页面-主体部分-代理流程-内容部分-代理流程", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_POCEDURE_CONTNET_POCEDURE = 217;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别
     */
    @BeanAttrInfo(value = "218", cnName = "主站代理加盟页面-主体部分-代理资格和级别", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL = 218;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别-头部导航
     */
    @BeanAttrInfo(value = "219", cnName = "主站代理加盟页面-主体部分-代理资格和级别-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL_HEAD = 219;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别-内容部分
     */
    @BeanAttrInfo(value = "220", cnName = "主站代理加盟页面-主体部分-代理资格和级别-内容部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL_CONTENT = 220;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别-内容部分-轮播广告
     */
    @BeanAttrInfo(value = "221", cnName = "主站代理加盟页面-主体部分-代理资格和级别-内容部分-轮播广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL_CONTENT_ADVERT = 221;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别-内容部分-代理资格和级别
     */
    @BeanAttrInfo(value = "222", cnName = "主站代理加盟页面-主体部分-代理资格和级别-内容部分-代理资格和级别", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL_CONTENT_LEVEL = 222;
    /**
     * 主站代理加盟页面-主体部分-代理资格和级别-内容部分-图片
     */
    @BeanAttrInfo(value = "223", cnName = "主站代理加盟页面-主体部分-代理资格和级别-内容部分-图片", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_LEVEL_CONTENT_IMG = 223;
    /**
     * 主站代理加盟页面-主体部分-代理申请
     */
    @BeanAttrInfo(value = "224", cnName = "主站代理加盟页面-主体部分-代理申请", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_APPLY = 224;
    /**
     * 主站代理加盟页面-主体部分-代理申请-头部导航
     */
    @BeanAttrInfo(value = "225", cnName = "主站代理加盟页面-主体部分-代理申请-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_APPLY_HEAD = 225;
    /**
     * 主站代理加盟页面-主体部分-代理申请-内容部分
     */
    @BeanAttrInfo(value = "226", cnName = "主站代理加盟页面-主体部分-代理申请-内容部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_APPLY_CONTNET = 226;
    /**
     * 主站代理加盟页面-主体部分-代理申请-内容部分-轮播广告
     */
    @BeanAttrInfo(value = "227", cnName = "主站代理加盟页面-主体部分-代理申请-内容部分-轮播广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_APPLY_CONTNET_ADVERT = 227;
    /**
     * 主站代理加盟页面-主体部分-代理申请-内容部分-代理申请
     */
    @BeanAttrInfo(value = "228", cnName = "主站代理加盟页面-主体部分-代理申请-内容部分-代理申请", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_APPLY_CONTNET_APPLY = 228;
    /**
     * 主站代理加盟页面-主体部分-代理FAQ
     */
    @BeanAttrInfo(value = "229", cnName = "主站代理加盟页面-主体部分-代理FAQ", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_FAQ = 229;
    /**
     * 主站代理加盟页面-主体部分-代理FAQ-头部导航
     */
    @BeanAttrInfo(value = "230", cnName = "主站代理加盟页面-主体部分-代理FAQ-头部导航", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_FAQ_HEAD = 230;
    /**
     * 主站代理加盟页面-主体部分-代理FAQ-内容部分
     */
    @BeanAttrInfo(value = "231", cnName = "主站代理加盟页面-主体部分-代理FAQ-内容部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_FAQ_CONTENT = 231;
    /**
     * 主站代理加盟页面-主体部分-代理FAQ-内容部分-轮播广告
     */
    @BeanAttrInfo(value = "232", cnName = "主站代理加盟页面-主体部分-代理FAQ-内容部分-轮播广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_FAQ_CONTENT_ADVERT = 232;
    /**
     * 主站代理加盟页面-主体部分-代理FAQ-内容部分-FAQ
     */
    @BeanAttrInfo(value = "233", cnName = "主站代理加盟页面-主体部分-代理FAQ-内容部分-FAQ", systemType = SystemType.EISS)
    public static final long EISS_NEW_AGENT_HOME_MAIN_FAQ_CONTENT_FAQ = 233;

    /**
     * 主站首页产品展示云计算-租用托管
     */
    /**
     * 主站首页产品展示云计算-租用托管-产品配置
     */
    @BeanAttrInfo(value = "234", cnName = "主站首页产品展示云计算-租用托管-产品配置", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_IDC_PRODUCT = 234;
    /**
     * 主站首页产品展示云计算-租用托管-产品配置
     */
    @BeanAttrInfo(value = "235", cnName = "主站首页产品展示云计算-租用托管-链接地址", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_CLOUD_COMPUTING_IDC_LINK = 235;
    /**
     * 主站关于我们-友情链接
     */
    /**
     * 主站关于我们-友情链接-图片链接
     */
    @BeanAttrInfo(value = "236", cnName = "主站关于我们-友情链接-图片链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_LINKS_IMAGE = 236;
    /**
     * 主站关于我们-友情链接-文字链接
     */
    @BeanAttrInfo(value = "237", cnName = "主站关于我们-友情链接-文字链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_LINKS_TEXT = 237;
    /**
     * 主站关于我们-友情链接-链接代码
     */
    @BeanAttrInfo(value = "238", cnName = "主站关于我们-友情链接-链接代码", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_LINKS_CODE = 238;
    /**
     * 主站关于我们-联系我们
     */
    /**
     * 主站关于我们-联系我们-内容
     */
    @BeanAttrInfo(value = "239", cnName = "主站关于我们-联系我们-内容", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CONTACTUS_CONTENT = 239;
    /**
     * 主站关于我们-联系我们-地图
     */
    @BeanAttrInfo(value = "240", cnName = "主站关于我们-联系我们-地图", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CONTACTUS_MAP = 240;
    /**
     * 主站公共页面顶部LOGO
     */
    @BeanAttrInfo(value = "241", cnName = "主站公共页面顶部LOGO", systemType = SystemType.EISS)
    public static final long EISS_COMMON_TOP_LOGO = 241;
    /**
     * 主站首页产品展示网站建设-快速链接
     */
    @BeanAttrInfo(value = "242", cnName = "主站首页产品展示网站建设-快速链接", systemType = SystemType.EISS)
    public static final long EISS_NEW_HOME_PRODUCT_WEB_BUILD_LINK = 242;
    /**
     * =========================主站数据库首页====================================
     */
    /**
     * 主站数据库首页-左边部分
     */
    @BeanAttrInfo(value = "243", cnName = "主站数据库首页-左边部分", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_LEFT = 243;
    /**
     * 主站数据库首页-左边部分-产品
     */
    @BeanAttrInfo(value = "244", cnName = "主站数据库首页-左边部分-产品", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_LEFT_PRODUCT = 244;
    /**
     * 主站数据库首页-左边部分-世纪东方优势
     */
    @BeanAttrInfo(value = "245", cnName = "主站数据库首页-左边部分-世纪东方优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_LEFT_SUPERIORITY = 245;
    /**
     * 主站数据库首页-左边部分-广告
     */
    @BeanAttrInfo(value = "246", cnName = "主站数据库首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_LEFT_ADVERT = 246;
    /**
     * 主站数据库首页-左边部分-行业新闻
     */
    @BeanAttrInfo(value = "247", cnName = "主站数据库首页-左边部分-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_LEFT_NEWS = 247;
    /**
     * 主站云虚拟主机首页-主体部分-功能说明
     */
    @BeanAttrInfo(value = "248", cnName = "主站云虚拟主机首页-左边部分-功能说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_DATA_BASE_HOME_MAIN_SHOW = 248;
    /**
     * =========================主站网站建设首页====================================
     */
    /**
     * 主站网站建设首页-左边部分-广告
     */
    @BeanAttrInfo(value = "249", cnName = "主站网站建设首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_WEB_BUILD_HOME_LEFT_ADVERT = 249;

    /**
     * 主站关于我们-联系我们-公司信息
     */
    @BeanAttrInfo(value = "250", cnName = "主站关于我们-联系我们-公司信息", systemType = SystemType.EISS)
    public static final long EISS_NEW_ABOUT_CONTACTUS_COMPANY = 250;

    /**
     * =========================主站租用托管首页====================================
     *
     */

    /**
     * 主站租用托管首页-左边部分子列表
     */
    @BeanAttrInfo(value = "251", cnName = "主站租用托管首页-左边部分子列表", systemType = SystemType.EISS)
    public static final long EISS_NEW_RENTAL_HOME_LEFT = 251;

    /**
     * 主站租用托管首页-左边部分-广告
     */
    @BeanAttrInfo(value = "252", cnName = "主站租用托管首页-左边部分-广告", systemType = SystemType.EISS)
    public static final long EISS_NEW_RENTAL_HOSTING_HOME_LEFT_ADVERT = 252;

    /**
     * 主站租用托管首页-主体部分-功能说明
     */
    @BeanAttrInfo(value = "253", cnName = "主站租用托管首页-主体部分-功能说明", systemType = SystemType.EISS)
    public static final long EISS_NEW_RENTAL_HOSTING_HOME_MAIN_SHOW = 253;

    /**
     * 主站租用托管首页-左边部分-优势
     */
    @BeanAttrInfo(value = "254", cnName = "主站租用托管首页-左边部分-优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_RENTAL_HOSTING_HOME_LEFT_SUPERIORITY = 254;

    /**
     * 主站域名首页-主体部分-域名说明-域名简介
     */
    @BeanAttrInfo(value = "255", cnName = "主站域名首页-主体部分-域名说明-域名简介", systemType = SystemType.EISS)
    public static final long EISS_NEW_DOMAIN_HOME_MAIN_DOMAIN_INTRODUCTION = 255;

    /**
     * =========================SSL证书====================================
     */
    /**
     * 主站SSL证书首页-左边部分子列表
     */
    @BeanAttrInfo(value = "256", cnName = "主站SSL证书首页-左边部分子列表", systemType = SystemType.EISS)
    public static final long EISS_NEW_SSL_HOME_LEFT = 256;

    /**
     * 主站SSL证书首页-左边部分-优势
     */
    @BeanAttrInfo(value = "257", cnName = "主站SSL证书首页-左边部分-优势", systemType = SystemType.EISS)
    public static final long EISS_NEW_SSL_HOME_LEFT_SUPERIORITY = 257;

    /**
     * 主站SSL证书首页-左边部分-行业新闻
     */
    @BeanAttrInfo(value = "258", cnName = "主站SSL证书首页-左边部分-行业新闻", systemType = SystemType.EISS)
    public static final long EISS_NEW_SSL_HOME_LEFT_NEWS = 258;

    /**
     * 主站公共页面导航条链接SSL证书
     */
    @BeanAttrInfo(value = "259", cnName = "主站公共页面导航条链接SSL证书", systemType = SystemType.EISS)
    public static final long EISS_NEW_NAV_LINK_SSL = 259;
}
