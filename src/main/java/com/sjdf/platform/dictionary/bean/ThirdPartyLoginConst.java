package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * create in 2016年7月27日
 * @category 第三方登录配置信息
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("THIRD_PARTY_LOGIN_CONST")
@BeanName(name = "第三方登录配置信息")
public class ThirdPartyLoginConst extends Dictionary{

    private static final long serialVersionUID = -233431048156661086L;

    private static final String PLACEHOLDER_WECHAT_APP_ID = "APPID";
    private static final String PLACEHOLDER_WECHAT_REDIRECT_URI = "REDIRECT_URI";
    private static final String PLACEHOLDER_WECHAT_SECRET = "SECRET";
    private static final String PLACEHOLDER_WECHAT_CODE = "CODE";
    private static final String PLACEHOLDER_ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String PLACEHOLDER_OPEN_ID = "OPENID";

    @BeanAttrInfo(value = "wx4859532a1a1d8638", cnName = "微信开放平台网站应用AppID")
    public static final long WECHAT_APP_ID = 1L;

    @BeanAttrInfo(value = "afb96d0add94004e22d9fbb0e77d175e", cnName = "微信开放平台网站应用AppSecret")
    public static final long WECHAT_APP_SECRET = 2L;

    @BeanAttrInfo(value = "http://www.51web.com/weCallback", cnName = "微信授权登录请求回调地址")
    public static final long WECHAT_REDIRECT_URI = 3L;

    @BeanAttrInfo(value = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login#wechat_redirect", cnName = "微信登录地址")
    public static final long WECHAT_URI = 4L;

    @BeanAttrInfo(value = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code", cnName = "微信通过code获取access_token地址")
    public static final long WECHAT_ACCESS_TOKEN_URI = 5L;

    @BeanAttrInfo(value = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID", cnName = "微信获取获取用户个人信息地址")
    public static final long WECHAT_USER_INFO_URI = 6L;

    public static String getWechatLoginUrl(String uri, String appId, String redirectUri) {
        return uri.replace(PLACEHOLDER_WECHAT_APP_ID, appId).replace(PLACEHOLDER_WECHAT_REDIRECT_URI, redirectUri);
    }

    public static String getWechatTokenUrl(String uri, String appId, String secret, String code) {
        return uri.replace(PLACEHOLDER_WECHAT_APP_ID, appId).replace(PLACEHOLDER_WECHAT_SECRET, secret).replace(PLACEHOLDER_WECHAT_CODE, code);
    }

    public static String getWechatUserInfoUrl(String uri,String token, String openId) {
        return uri.replace(PLACEHOLDER_ACCESS_TOKEN, token).replace(PLACEHOLDER_OPEN_ID, openId);
    }
}
