package com.sjdf.platform.message.api.impl;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.bean.SendApiConfig;
import com.sjdf.platform.net.HttpSocket;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 网建短信接口实现
 * User: ketqi
 * Date: 2015-07-03 10:37
 */
@BeanName(name = "网建短信接口")
public class WebchineseSmsApiImpl extends AbstractSmsApi implements SmsApi {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(WebchineseSmsApiImpl.class);
    private static final Map<String, String> RESULT_MAP;

    static {
        RESULT_MAP = resultMap();
    }

    public WebchineseSmsApiImpl(SendApiConfig config) {
        super(config);
    }

    /**
     * 发送短信
     *
     * @param mobile 电话号码
     * @param sms    短信内容
     * @return 发送结果, null:表示成功
     */
    @Override
    public String send(String mobile, String sms) {
        Map<String, String> postData = new HashMap<>();
        postData.put("Uid", getUserId());
        postData.put("Key", getPwd());
        postData.put("smsMob", mobile);
        postData.put("smsText", sms);

        HttpSocket socket = new HttpSocket(getUrl(), postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(mobile + sms, e);
        }
        String result = socket.getResponseData();
        LOGGER.debug(mobile + sms + "-->" + result);

        int resultCode;
        try {
            resultCode = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            LOGGER.error(mobile + sms, result);
            return result;
        }
        if (resultCode > 0) {
            return null;
        } else {
            String temp = RESULT_MAP.get(result);
            return temp == null ? result : temp;
        }
    }

    private static Map<String, String> resultMap() {
        Map<String, String> map = new HashMap<>();
        try (InputStreamReader inputStreamReader = new InputStreamReader(WebchineseSmsApiImpl.class.getResourceAsStream("WebchineseSmsApiImpl.config"), HttpSocket.UTF8)) {
            Properties properties = new Properties();
            properties.load(inputStreamReader);
            for (String key : properties.stringPropertyNames()) {
                String v = properties.getProperty(key);
                if (!PlatformUtils.hasText(key) || !PlatformUtils.hasText(v)) {
                    continue;
                }
                map.put(key.trim(), v.trim());
            }
        } catch (Exception e) {
            LOGGER.error("load WebchineseSmsApiImpl.config fail", e);
        }
        return map;
    }
}
