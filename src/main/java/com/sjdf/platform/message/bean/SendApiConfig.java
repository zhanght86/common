package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.cache.MessageApiImplCache;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 发送接口配置
 * User: ketqi
 * Date: 2015-07-01 11:17
 */
@Entity
@Table(name = "p_message_api_config")
public class SendApiConfig extends BaseBean {
    private static final long serialVersionUID = 1531266924695247323L;
    /** 接口配置名称 */
    private String name;
    /**
     * 消息类型
     *
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    private long messageType;
    /**
     * 是否有效
     *
     * @see com.sjdf.platform.dictionary.bean.ValidMark
     */
    private long valid;
    /**
     * 发送接口实例
     *
     * @see com.sjdf.platform.message.api.EmailApi
     * @see com.sjdf.platform.message.api.SmsApi
     * @see com.sjdf.platform.message.cache.MessageApiImplCache
     */
    private String sendApiImpl;
    /** 主机地址 */
    private String hostUrl;
    /** 源地址 */
    private String sourceAddress;
    /** 个人名称 */
    private String personalName;
    /** 用户名 */
    private String userName;
    /** 密码 */
    private String pwd;

    public SmsApi smsApi() {
        if (!isValidate()) {
            return null;
        }
        return MessageApiImplCache.getInstance().createSmsApi(this);
    }

    public EmailApi emailApi() {
        if (!isValidate()) {
            return null;
        }
        return MessageApiImplCache.getInstance().createEmailApi(this);
    }

    public boolean isValidate() {
        return valid == ValidMark.VALID;
    }

    @ModifyRecord(name = "消息类型")
    public String getMessageTypeInfo() {
        return ConfigManager.getInstance().getName(MessageType.class, messageType);
    }

    @ModifyRecord(name = "是否有效")
    public String getValidInfo() {
        return ConfigManager.getInstance().getName(ValidMark.class, valid);
    }

    @ModifyRecord(name = "发送接口实例")
    public String getSendApiImplInfo() {
        return MessageApiImplCache.getInstance().name(messageType, sendApiImpl);
    }

    public Message valid() {
        if (!PlatformUtils.hasText(name)) {
            return Message.createMessage("message.send.api.config.name.invalidate");
        }

        if (messageType <= 0L || !ConfigManager.getInstance().existAttr(MessageType.class, messageType)) {
            return Message.createMessage("message.send.api.config.message.type.invalidate");
        }

        if (!PlatformUtils.hasText(sendApiImpl) || !PlatformUtils.hasText(MessageApiImplCache.getInstance().name(messageType, sendApiImpl))) {
            return Message.createMessage("message.send.api.config.send.api.impl.invalidate");
        }

        if (valid == 0L || !ConfigManager.getInstance().existAttr(ValidMark.class, valid)) {
            return Message.createMessage("message.api.user.valid.invalidate");
        }

        if (!PlatformUtils.hasText(hostUrl)) {
            return Message.createMessage("message.send.api.config.host.url.invalidate");
        }

        if (!PlatformUtils.hasText(userName)) {
            return Message.createMessage("message.send.api.config.user.name.invalidate");
        }

        if (!PlatformUtils.hasText(pwd)) {
            return Message.createMessage("message.send.api.config.pwd.invalidate");
        }

        if (messageType == MessageType.EMAIL && !PlatformUtils.hasText(sourceAddress)) {
            return Message.createMessage("message.send.api.config.source.address.invalidate");
        }
        return Message.createEmptyMessage();
    }

    @ModifyRecord(name = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMessageType() {
        return messageType;
    }

    public void setMessageType(long messageType) {
        this.messageType = messageType;
    }

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    public String getSendApiImpl() {
        return sendApiImpl;
    }

    public void setSendApiImpl(String sendApiImpl) {
        this.sendApiImpl = sendApiImpl;
    }

    @ModifyRecord(name = "主机地址")
    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @ModifyRecord(name = "源地址")
    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    @ModifyRecord(name = "个人名称")
    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    @ModifyRecord(name = "用户名")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        sb.append("SendApiConfig{");
        sb.append("name='").append(name).append('\'');
        sb.append(", messageType=").append(messageType);
        sb.append(", valid=").append(valid);
        sb.append(", sendApiImpl='").append(sendApiImpl).append('\'');
        sb.append(", hostUrl='").append(hostUrl).append('\'');
        sb.append(", sourceAddress='").append(sourceAddress).append('\'');
        sb.append(", personalName='").append(personalName).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", pwd='").append(pwd).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
