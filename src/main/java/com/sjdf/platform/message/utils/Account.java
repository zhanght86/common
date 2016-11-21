package com.sjdf.platform.message.utils;

import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.MessageApiUserDictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.constant.EmailNoticeType;

import java.util.List;

/**
 * 2015-10-16
 *
 * @author wangpeng
 * @category 授权账户用户名和密码
 */
public class Account {

    private String username;
    private String password;

    public Account() {
        this(EmailNoticeType.BUSINESS_NOTICE);
    }

    public Account(long mailType) {
        String value = ConfigManager.getInstance().getValue(MessageApiUserDictionary.class, mailType);
        List<String> list = Tools.getStringListByString(value);
        this.username = list.get(0);
        this.password = list.get(1);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
