package com.sjdf.platform.message.utils;

import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.message.constant.EmailNoticeType;
import com.sjdf.platform.message.helper.MessageQueryManager;


/**
 * 2015-10-19
 *
 * @author wangpeng
 * @category 信息查询工具类
 */
public class MessageQueryUtils {

    private MessageQueryUtils() {

    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final MessageQueryUtils INSTANCE = new MessageQueryUtils();
    }

    public static MessageQueryUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param phone
     * @return
     */
    public SMSMessage findSmsByPhone(String phone) {
        Account account = new Account();
        return MessageQueryManager.getInstance().findSmsByPhone(account.getUsername(), account.getPassword(), phone);
    }

    /**
     * 返回用户今天发送的推荐码邮件
     *
     * @param title
     * @param sendUser
     * @return
     */
    public long countRecommendEmail(String title, String sendUser) {
        Account account = new Account(EmailNoticeType.ADVERTISEMENT_NOTICE);
        return MessageQueryManager.getInstance().countRecommendEmail(account.getUsername(), account.getPassword(), title, sendUser);
    }
}
