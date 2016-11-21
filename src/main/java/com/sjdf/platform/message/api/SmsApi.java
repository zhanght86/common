package com.sjdf.platform.message.api;

import com.sjdf.platform.message.bean.SendApiConfig;

/**
 * 短信发送接口
 * User: ketqi
 * Date: 2015-07-03 10:34
 */
public interface SmsApi {
    /**
     * 发送短信
     *
     * @param mobile 电话号码
     * @param sms    短信内容
     * @return 发送结果, null:表示成功
     */
    String send(String mobile, String sms);

    /** 获取发该信息的接口配置 */
    SendApiConfig getConfig();
}
