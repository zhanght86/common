package com.sjdf.platform.message.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.net.HttpSocket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2015-10-19
 *
 * @author wangpeng
 * @category 信息查询管理辅助器
 */
public class MessageQueryManager {

    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageQueryManager.class);

    private MessageQueryManager() {
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final MessageQueryManager INSTANCE = new MessageQueryManager();
    }

    public static MessageQueryManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param uid
     * @param pwd
     * @param mobile
     * @return
     * @author wangpeng
     * @category 根据电话号码查询短信
     */
    public SMSMessage findSmsByPhone(String uid, String pwd, String mobile) {
        Map<String, String> postData = new HashMap<>();
        postData.put("uid", uid);
        postData.put("key", pwd);
        postData.put("sms.address", mobile);
        postData.put("messageType", MessageType.SMS + "");
        postData.put("needPage", "true");
        postData.put("page.orderType", "desc");
        postData.put("page.orderBy", "sendTime");
        postData.put("page.pageSize", "1");
        try {
            JSONObject json = doSocket("messageList", MessageType.SMS, postData);
            boolean bool = json.getBoolean("bool");
            if (bool) {
                JSONArray array = json.getJSONArray("message");
                @SuppressWarnings("unchecked")
                List<SMSMessage> smsList = (List<SMSMessage>) JSONArray.toCollection(array, SMSMessage.class);
                if (smsList != null && !smsList.isEmpty()) {
                    return smsList.get(0);
                }
            } else {
                throw new Exception(json.getString("message"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param uid      授权用户名
     * @param pwd      授权用户密码
     * @param title    邮件标题
     * @param sendUser 发送用户
     * @return
     * @category 返回用户今天发送的推荐码邮件
     */
    public long countRecommendEmail(String uid, String pwd, String title, String sendUser) {
        Map<String, String> postData = new HashMap<>();
        postData.put("uid", uid);
        postData.put("key", pwd);
        postData.put("email.title", title);
        postData.put("email.sendUser", sendUser);
        try {
            JSONObject json = doSocket("countRecommendEmail", MessageType.EMAIL, postData);
            boolean bool = json.getBoolean("bool");
            if (bool) {
                return json.getLong("message");
            } else {
                throw new Exception(json.getString("message"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Long.MAX_VALUE;
    }

    /**
     * 通过socket发送请求
     *
     * @param postData    请求数据
     * @param messageType 消息类型
     * @throws Exception
     */
    private JSONObject doSocket(String method, long messageType, Map<String, String> postData) throws Exception {
        String urlPrefix = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PLATFORM_API_PREFIX_URL);
        StringBuilder url = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        url.append(urlPrefix).append("message!").append(method).append(".action");
        HttpSocket socket = new HttpSocket(url.toString(), postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(url.toString() + postData.toString(), e);
            throw new Exception("socket communication failure " + messageType);
        }

        String result = socket.getResponseData();
        try {
            return JSONObject.fromObject(result);
        } catch (Exception e) {
            LOGGER.error(result + " parse json fail", e);
            throw new Exception("parse json string fail");
        }
    }
}
