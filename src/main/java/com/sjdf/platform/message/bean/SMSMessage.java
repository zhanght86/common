package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.message.api.SmsApi;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信消息
 * User: ketqi
 * Date: 2015-07-01 11:03
 */
@Entity
@Table(name = "p_message_sms")
public class SMSMessage extends BaseMessage implements Cloneable {
    private static final long serialVersionUID = 1858966323893231905L;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        sb.append("SMSMessage{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * 发送短信
     *
     * @param api 发送接口
     */
    public void send(SmsApi api) {
        setSendApiName(api.getConfig().getName());

        List<String> addressList = getFailAddressList();
        Map<String, String> failMap = new HashMap<>();
        for (String address : addressList) {
            String result = api.send(address, getContent());
            if (PlatformUtils.hasText(result)) {
                failMap.put(address, result);
            }
        }
        if (!failMap.isEmpty()) {
            addFailCounter();
            setStatus(SendStatus.SEND_FAIL);
            setFailAddressList(failMap.keySet());
            setResultMap(failMap);
        } else {
            setStatus(SendStatus.SEND_SUCCESS);
            setRemark("");
        }
    }

    @Override
    public SMSMessage clone() {
        SMSMessage message = null;
        try {
            message = (SMSMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage(), e);
        }
        return message;
    }
}
