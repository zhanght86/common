package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 微信平台公共常量
 * @Create 2014-4-4
 */
@Entity
@DiscriminatorValue("WEI_XIN_CONST")
@BeanName(name = "微信平台公共常量")
public class WeiXinConst extends Dictionary {

    private static final long serialVersionUID = 7065256602170926146L;

    /** 微信平台默认管理域名 */
    @BeanAttrInfo(value = "weixin.51web.com", cnName = "微信平台默认管理域名")
    public static final long WEIXIN_DEFAULT_DOMAIN = 1L;

    /** 世纪东方LOGO图片在服务器上保存的地址 */
    @BeanAttrInfo(value = "http://weixin.51web.com/images/logo.png", cnName = "微信平台世纪东方LOGO地址")
    public static final long WEIXIN_SJDF_LOGO_ADDRESS = 2L;

    /** 微信公众平台所在机房 */
    @BeanAttrInfo(value = "20", cnName = "微信公众平台所在机房")
    public static final long WEIXIN_PLATFORM_IDC = 3L;

    /** 微信公众账号过期删除天数 */
    @BeanAttrInfo(value = "7", cnName = "微信公众账号过期删除天数")
    public static final long WEIXIN_ACCOUNT_EXPIRED_DAYS = 4L;

    /** 微信静态服务器物理地址 */
    @BeanAttrInfo(value = "/home/wwwroot/weixin.cdnhost.cn", cnName = "微信静态服务器物理地址")
    public static final long WEIXIN_STATIC_PHYSICAL_ADDRESS = 5L;

    /** 微信静态服务器WEB地址 */
    @BeanAttrInfo(value = "http://weixin.cdnhost.cn", cnName = "微信静态服务器WEB地址")
    public static final long WEIXIN_STATIC_WEB_ADDRESS = 6L;

    /** 微页面微首页 */
    @BeanAttrInfo(value = "微首页", cnName = "微首页")
    public static final long WEIXIN_MICRO_PAGE_HOME = 7L;

    /** 微页面新的页面 */
    @BeanAttrInfo(value = "新的页面", cnName = "新的页面")
    public static final long WEIXIN_MICRO_PAGE_NEW = 8L;

    /** 微信公众账号测试回复 */
    @BeanAttrInfo(value = "恭喜测试连接成功！", cnName = "测试")
    public static final long WEIXIN_TEST_REPLAY = 9L;

    /** 微活动还未开始提示 */
    @BeanAttrInfo(value = "该活动还未开始，敬请等待！", cnName = "活动还未开始提示")
    public static final long WEIXIN_ACTIVITY_NOT_START_TIP = 10;

    @BeanAttrInfo(value = "对不起，该活动可能已经被删除了。", cnName = "访问删除活动提示")
    public static final long WEIXIN_ACTIVITY_DELETED_TIP = 11;

    @BeanAttrInfo(value = "对不起，您来晚了，奖品已经被领完了，请关注我们后续的活动。", cnName = "奖品发放完毕提示")
    public static final long WEIXIN_ACTIVITY_AWARD_NOT_MORE = 12;

    /** 是否验证公众账号的有效性 */
    @BeanAttrInfo(value = "1", cnName = "是否验证公众账号的有效性（1：是，2：否）")
    public static final long WEIXIN_ACCOUNT_CHECK = 13L;

    /** 上传图片大小限制（KB） */
    @BeanAttrInfo(value = "2048", cnName = "上传图片大小限制（KB）")
    public static final long WEIXIN_PICTURE_SIZE = 14L;

    /** 微信静态页面物理地址 */
    @BeanAttrInfo(value = "/home/apache-tomcat-7.0.21-8057-30313-weixin_platform/webapps/website", cnName = "微信静态页面物理地址")
    public static final long WEIXIN_HTML_PHYSICAL_ADDRESS = 15L;

    /** 微信后台默认域名 */
    @BeanAttrInfo(value = "weicp.51web.com", cnName = "微信后台默认域名")
    public static final long WEIXIN_ADMIN_DEFAULT_DOMAIN = 16L;

    /** 微信微官网图文消息封面默认图片相对地址 */
    @BeanAttrInfo(value = "/activity-coupon-start.jpg", cnName = "微信微官网图文消息封面默认图片相对地址")
    public static final long WEIXIN_MICRO_PAGE_IMAGE = 17L;

    /** 微信access_token有效期 */
    @BeanAttrInfo(value = "period_validity", cnName = "access_token有效期")
    public static final long WEIXIN_ACCESS_TOKEN_PERIOD_VALIDITY = 7200;

    /** 微信平台新用户注册链接地址 */
    @BeanAttrInfo(value = "http://www.51web.com/register.html", cnName = "微信平台新用户注册链接地址")
    public static final long WEIXIN_NEW_USER_REGISTER_LINK = 18L;

    /** 微信平台默认提示域名别名 */
    @BeanAttrInfo(value = "weixin.51web.com", cnName = "微信平台默认提示域名别名")
    public static final long WEIXIN_TIP_DOMAIN = 19L;

    /** 是否压缩图片 */
    @BeanAttrInfo(value = "1", cnName = "是否压缩图片")
    public static final long WHETHER_COMPRESS_IMAGE = 20L;

    /** 压缩图片下限大小值 */
    @BeanAttrInfo(value = "200", cnName = "压缩图片下限大小值")
    public static final long COMPRESS_IMAGE_LIMIT = 21L;

    /** 压缩图片质量(is greater than{@code 0.0D} or is less than{@code 1.0D}.) */
    @BeanAttrInfo(value = "1.0", cnName = "压缩图片质量(is greater than{@code 0.0} or is less than{@code 1.0}.)")
    public static final long COMPRESS_IMAGE_QUALITY = 22L;

    @BeanAttrInfo(value = "http://weixin.51web.com/api/", cnName = "微信平台对外接口地址")
    public static final long WEIXIN_API_URL = 23;
}
