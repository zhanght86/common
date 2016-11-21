package com.sjdf.platform.message.api.impl;

import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.bean.SendApiConfig;

/**
 * 抽象的邮件接口实现
 * User: ketqi
 * Date: 2015-07-03 14:48
 */
public abstract class AbstractEmailApi implements EmailApi {
    /** 发送接口配置信息 */
    private SendApiConfig config;
    /** 主机地址 */
    private String host;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 源地址 */
    private String sourceAddress;
    /** 个人名称 */
    private String personalName;

    public AbstractEmailApi(SendApiConfig config) {
        this.config = config;
        this.host = config.getHostUrl();
        this.username = config.getUserName();
        this.password = config.getPwd();
        this.sourceAddress = config.getSourceAddress();
        this.personalName = config.getPersonalName();
    }

    @Override
    public SendApiConfig getConfig() {
        return this.config;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
}
