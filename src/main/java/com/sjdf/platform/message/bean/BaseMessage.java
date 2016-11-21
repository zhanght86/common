package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.net.HttpSocket;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息共同属性
 * User: ketqi
 * Date: 2015-07-01 10:27
 */
@MappedSuperclass
public class BaseMessage extends BaseBean implements Cloneable {
    private static final long serialVersionUID = -4137015888894336128L;

    /**
     * 所属用户
     */
    private String sendUser;

    /**
     * 所属公司
     *
     * @see com.sjdf.platform.dictionary.bean.CompanyClass
     */
    private long company;
    /**
     * 系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long systemType;
    /**
     * 所属用户
     *
     * @see com.sjdf.platform.message.bean.MessageApiUser
     */
    private String userId;
    /** 目标地址,可多个,使用分号分隔 */
    @Index(name = "i_address")
    @Column(columnDefinition = "longtext")
    private String address;
    /** 发送失败的目标地址 */
    @Column(columnDefinition = "longtext")
    private String failAddress;
    /** 消息内容 */
    @Column(columnDefinition = "longtext")
    private String content;
    /**
     * 状态
     *
     * @see com.sjdf.platform.dictionary.bean.common.SendStatus
     */
    @Index(name = "i_status")
    @Column(name = "send_status")
    private long status;
    /** 定时发送时间;默认当前时间 */
    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime = new Date();
    /** 发送接口名称 */
    private String sendApiName;
    /** 备注或者错误信息 */
    @Column(columnDefinition = "longtext")
    private String remark;
    /** 失败发送次数 */
    @Transient
    private transient int failCounter;
    /** 定时发送时间列表 */
    @Transient
    private transient List<Date> sendTimeList;

    public boolean isDelayMessage() {
        //延迟30秒的都是延迟消息
        return sendTime != null && sendTime.getTime() > System.currentTimeMillis() + CommonPlatformConstant.LENGTH_30 * CommonPlatformConstant.SECOND_OF_MILLISECOND;
    }

    public String getStatusInfo() {
        return ConfigManager.getInstance().getName(SendStatus.class, status);
    }

    public String getSendTimeInfo() {
        return DateUtils.formatDateTime(sendTime);
    }

    public List<String> getAddressList() {
        return PlatformUtils.parse2StrList(address);
    }

    public List<String> getFailAddressList() {
        return PlatformUtils.parse2StrList(failAddress);
    }

    public void setFailAddressList(Collection<String> failAddressList) {
        this.failAddress = PlatformUtils.parse2String(failAddressList);
    }

    public String getCompanyInfo() {
        return ConfigManager.getInstance().getName(CompanyClass.class, company);
    }

    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    public String getNameInfo() {
        return MessageApiUserCache.getInstance().getNameInfo(userId);
    }

    public String getAddressInfo() {
        if (PlatformUtils.hasText(address) && address.length() > CommonPlatformConstant.LENGTH_24) {
            return address.substring(0, CommonPlatformConstant.LENGTH_24) + "....";
        }
        return address;
    }

    public String getContentInfo() {
        if (PlatformUtils.hasText(content) && content.length() > CommonPlatformConstant.LENGTH_24) {
            return content.substring(0, CommonPlatformConstant.LENGTH_24) + "....";
        }
        return content;
    }

    public String getRemarkInfo() {
        if (PlatformUtils.hasText(remark) && remark.length() > CommonPlatformConstant.LENGTH_24) {
            return remark.substring(0, CommonPlatformConstant.LENGTH_24) + "....";
        }
        return remark;
    }

    public void setResultMap(Map<String, String> map) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append(entry.getKey()).append(":").append(entry.getValue()).append(HttpSocket.CRLF);
        }
        remark = builder.toString();
    }

    public void addFailCounter() {
        this.failCounter += 1;
    }

    public Message valid() {
        Message userinvalidate = Message.createMessage("message.send.api.config.user.name.invalidate");
        if (!PlatformUtils.hasText(userId)) {
            return userinvalidate;
        } else {
            MessageApiUser user = MessageApiUserCache.getInstance().getByUserId(userId);
            if (user == null || !user.isValidate()) {
                return userinvalidate;
            }

            company = user.getCompany();
            systemType = user.getSystemType();
        }

        if (!PlatformUtils.hasText(address)) {
            if (this instanceof SMSMessage) {
                return Message.createMessage("message.send.message.sms.address.invalidate");
            } else {
                return Message.createMessage("message.send.message.email.address.invalidate");
            }
        }

        if (!ConfigManager.getInstance().existAttr(SendStatus.class, status)) {
            status = SendStatus.WAIT_SEND;
        }

        failAddress = address;
        return Message.createEmptyMessage();
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFailCounter() {
        return failCounter;
    }

    public void setFailCounter(int failCounter) {
        this.failCounter = failCounter;
    }

    public String getFailAddress() {
        return failAddress;
    }

    public void setFailAddress(String failAddress) {
        this.failAddress = failAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendApiName() {
        return sendApiName;
    }

    public void setSendApiName(String sendApiName) {
        this.sendApiName = sendApiName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Date> getSendTimeList() {
        return sendTimeList;
    }

    public void setSendTimeList(List<Date> sendTimeList) {
        this.sendTimeList = sendTimeList;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_4096);
        sb.append("id=").append(getId());
        sb.append(", createUser='").append(getCreateUser()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateUser='").append(getUpdateUser()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime()).append('\'');
        sb.append(", sendUser='").append(sendUser);
        sb.append(", company=").append(company);
        sb.append(", systemType=").append(systemType);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", failAddress='").append(failAddress).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", status=").append(status);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sendApiName='").append(sendApiName).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        return sb.toString();
    }
}
