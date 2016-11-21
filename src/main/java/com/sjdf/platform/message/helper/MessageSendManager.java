package com.sjdf.platform.message.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.ResourceBundleHelper;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.AES;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.verify.RegexVerify;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信息发送管理辅助器
 * User: ketqi
 * Date: 2015-07-01 18:08
 */
public final class MessageSendManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageSendManager.class);

    private MessageSendManager() {
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final MessageSendManager INSTANCE = new MessageSendManager();
    }

    public static MessageSendManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 发送短信
     *
     * @param uid    用户id
     * @param pwd    连接密码
     * @param mobile 电话号码
     * @param sms    短信内容
     * @return 发送结果;null:表示发送成功
     */
    public String sms(String uid, String pwd, String mobile, String sms) {
        return sms(uid, pwd, mobile, sms, null);
    }

    /**
     * 发送短信
     *
     * @param uid      用户id
     * @param pwd      连接密码
     * @param mobile   电话号码
     * @param sms      短信内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     */
    public String sms(String uid, String pwd, String mobile, String sms, Date sendTime) {
        return sms(uid, pwd, mobile, sms, sendTime, null);
    }

    /**
     * 发送短信
     *
     * @param uid      用户id
     * @param pwd      连接密码
     * @param mobile   电话号码
     * @param sms      短信内容
     * @param sendTime 发送时间
     * @param sendUser 发送用户
     * @return 发送结果;null:表示发送成功
     */
    public String sms(String uid, String pwd, String mobile, String sms, Date sendTime, String sendUser) {
        //电话号码验证
        String mobileInvalidate = ResourceBundleHelper.getInstance().getText("message.send.message.sms.address.invalidate");
        if (!PlatformUtils.hasText(mobile)) {
            return mobileInvalidate;
        }
        if (mobile.length() > CommonPlatformConstant.LENGTH_11) {
            List<String> list = PlatformUtils.parse2StrList(mobile);
            for (String str : list) {
                if (!PlatformUtils.isPhone(str)) {
                    return mobileInvalidate;
                }
            }
        } else if (!PlatformUtils.isPhone(mobile)) {
            return mobileInvalidate;
        }

        //短信内容不能为空
        if (!PlatformUtils.hasText(sms)) {
            return ResourceBundleHelper.getInstance().getText("message.send.message.content.invalidate");
        }

        Map<String, String> postData = new HashMap<>();
        postData.put("uid", uid);
        postData.put("key", pwd);
        postData.put("sms.address", mobile);
        postData.put("sms.content", sms);
        postData.put("sms.sendUser", sendUser);
        if (sendTime != null) {
            postData.put("sms.sendTime", DateUtils.formatDateTime(sendTime));
        }
        return doSocket(MessageType.SMS, postData);
    }

    /**
     * 发送邮件
     *
     * @param uid     用户id
     * @param pwd     连接密码
     * @param to      邮件目标地址
     * @param title   标题
     * @param content 邮件内容
     * @return 发送结果;null:表示发送成功
     */
    public String email(String uid, String pwd, String to, String title, String content) {
        return email(uid, pwd, to, title, content, null);
    }

    /**
     * 发送邮件
     *
     * @param uid      用户id
     * @param pwd      连接密码
     * @param to       邮件目标地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     */
    public String email(String uid, String pwd, String to, String title, String content, Date sendTime) {
        return email(uid, pwd, to, null, null, title, content, sendTime);
    }

    /**
     * 发送邮件
     *
     * @param uid      用户id
     * @param pwd      连接密码
     * @param to       邮件目标地址
     * @param cc       抄送地址
     * @param bcc      暗送地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     */
    public String email(String uid, String pwd, String to, String cc, String bcc, String title, String content, Date sendTime) {
        return email(uid, pwd, to, cc, bcc, title, content, sendTime, null);
    }

    /**
     * 发送邮件
     *
     * @param uid      用户id
     * @param pwd      连接密码
     * @param to       邮件目标地址
     * @param cc       抄送地址
     * @param bcc      暗送地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @param sendUser 发送用户
     * @return 发送结果;null:表示发送成功
     */
    public String email(String uid, String pwd, String to, String cc, String bcc, String title, String content, Date sendTime, String sendUser) {
        //邮件地址验证
        String emailInvalidate = ResourceBundleHelper.getInstance().getText("message.send.message.email.address.invalidate");
        if (!PlatformUtils.hasText(to)) {
            return emailInvalidate;
        }
        List<String> list = PlatformUtils.parse2StrList(to);
        if (PlatformUtils.hasText(cc)) {
            list.addAll(PlatformUtils.parse2StrList(cc));
        }
        if (PlatformUtils.hasText(bcc)) {
            list.addAll(PlatformUtils.parse2StrList(to));
        }
        for (String str : list) {
            if (!RegexVerify.checkEmail(str)) {
                return emailInvalidate;
            }
        }

        //内容不能为空
        if (!PlatformUtils.hasText(content)) {
            return ResourceBundleHelper.getInstance().getText("message.send.message.content.invalidate");
        }

        Map<String, String> postData = new HashMap<>();
        postData.put("uid", uid);
        postData.put("key", pwd);
        postData.put("email.address", to);
        postData.put("email.title", title);
        postData.put("email.content", content);
        postData.put("email.sendUser", sendUser);
        if (PlatformUtils.hasText(cc)) {
            postData.put("email.ccAddress", cc);
        }
        if (PlatformUtils.hasText(bcc)) {
            postData.put("email.bccAddress", bcc);
        }
        if (sendTime != null) {
            postData.put("email.sendTime", DateUtils.formatDateTime(sendTime));
        }

        return doSocket(MessageType.EMAIL, postData);
    }

    /**
     * 创建信息列表访问地址
     *
     * @param messageType 信息类型
     * @param userName    用户名
     * @param pwd         密码(MD5后的密码)
     * @return url
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    public String createMessageListUrl(long messageType, String userName, String pwd) {
        String urlPrefix = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PLATFORM_API_PREFIX_URL);
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        long systemType = SystemType.EISS_COMMON;
        String password = AES.encrypt(pwd);

        StringBuilder vercode = new StringBuilder(CommonPlatformConstant.LENGTH_32);
        vercode.append(systemType);
        vercode.append(userName);
        vercode.append(password);
        vercode.append(connPwd);
        vercode.append(vertime);

        StringBuilder url = new StringBuilder(CommonPlatformConstant.LENGTH_256);
        url.append(urlPrefix).append("message!");
        if (messageType == MessageType.SMS) {
            url.append("smsList.action");
        } else if (messageType == MessageType.EMAIL) {
            url.append("emailList.action");
        } else if (MessageType.WECHAT == messageType) {
            url.append("wechatList.action");
        }

        url.append("?idx=").append(systemType);
        url.append("&uid=").append(userName);
        url.append("&key=").append(password);
        url.append("&vertime=").append(vertime);
        url.append("&vercode=").append(MD5.md5(vercode.toString()));

        return url.toString();
    }

    /**
     * 通过socket发送请求
     *
     * @param postData 请求数据
     * @return 发送结果;null:表示发送成功
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    private String doSocket(long messageType, Map<String, String> postData) {
        String urlPrefix = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PLATFORM_API_PREFIX_URL);
        StringBuilder url = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        url.append(urlPrefix).append("message!");
        if (messageType == MessageType.SMS) {
            url.append("sms.action");
        } else if (messageType == MessageType.EMAIL) {
            url.append("email.action");
        }

        HttpSocket socket = new HttpSocket(url.toString(), postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(url.toString() + postData.toString(), e);
            return "socket communication failure";
        }

        String result = socket.getResponseData();
        try {
            Map<String, String> map = AjaxSupport.parseJson(result);
            String bool = map.get("bool");
            if (bool != null && !"true".equals(bool)) {
                return map.get("message");
            }
        } catch (Exception e) {
            LOGGER.error(result + " parse json fail", e);
            return "parse json string fail";
        }

        return null;
    }
}
