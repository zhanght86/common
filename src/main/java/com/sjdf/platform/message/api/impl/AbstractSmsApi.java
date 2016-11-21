package com.sjdf.platform.message.api.impl;

import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.bean.SendApiConfig;

/**
 * 抽象的短信接口实现
 * User: ketqi
 * Date: 2015-07-03 14:42
 */
public abstract class AbstractSmsApi implements SmsApi {
    /** 发送接口配置信息 */
    private SendApiConfig config;
    /** 访问地址 */
    private String url;
    /** 上家接口id */
    private String userId;
    /** 上家接口密码 */
    private String pwd;

    /** 必须实现该构造函数 */
    public AbstractSmsApi(SendApiConfig config) {
        this.config = config;
        this.url = config.getHostUrl();
        this.userId = config.getUserName();
        this.pwd = config.getPwd();
    }

    @Override
    public SendApiConfig getConfig() {
        return this.config;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
