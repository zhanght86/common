package com.sjdf.platform.common.constant;

/**
 * 
 * 2015-12-30
 * @category 微信相关常量
 * @author wangpeng
 *
 */
public class WeChatConst {

    /**
     * @category 表示该二维码是用于检查用户是否关注我司微信公众账号
     */
    public static final String QRCODE_USES_PREFIX_CHECK = "check#";

    /**
     * @category 表示该二维码是用于用户扫描自动登录
     */
    public static final String QRCODE_USES_PREFIX_LOGIN = "login#";

    /**
     * @category 获取微信二维码的链接模板地址，使用时需要用真实的ticket替换TICKET
     */
    public static final String QRCODE_URL_TEMPLATE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

    /**
     * @category 临时二维码有效时间为1小时（单位：ms）
     */
    public static final long EXPIRE_SECONDS = 1 * 60 * 60 * 1000;
}
