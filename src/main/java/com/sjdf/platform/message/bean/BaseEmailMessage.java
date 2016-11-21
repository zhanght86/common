package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.message.api.EmailApi;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件消息
 * User: ketqi
 * Date: 2015-07-16 11:14
 */
@MappedSuperclass
public class BaseEmailMessage extends BaseMessage {
    private static final long serialVersionUID = 1609530046948111035L;
    /** 邮件标题 */
    @Column(name = "m_title")
    private String title;
    /** 抄送地址,多个用分号分隔 */
    private String ccAddress;
    /** 暗抄地址,多个用分号分隔 */
    private String bccAddress;
    /**
     * 邮件组别（默认为0：表示直接和代理用户，1：表示代理下的客户）
     *
     * @see com.sjdf.platform.dictionary.bean.GroupLevel
     */
    @Column(name = "m_group")
    private long mailGroup;

    /**
     * 类型,标识是邮件类型（0：普通邮件；1：广告邮件；2：验证码相关邮件）
     *
     * @see com.sjdf.platform.message.constant.EmailNoticeType
     */
    @Column(name = "m_type")
    private long mailType;

    /**
     * 邮件发送
     *
     * @param api 邮件发送接口
     */
    public void send(EmailApi api) {
        setSendApiName(api.getConfig().getName());

        List<String> addressList = getFailAddressList();
        Map<String, String> failMap = new HashMap<>();
        for (String address : addressList) {
            String result = api.send(address, getCcAddress(), getBccAddress(), getTitle(), getContent());
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

    public List<String> ccList() {
        return PlatformUtils.parse2StrList(ccAddress);
    }

    public List<String> bccList() {
        return PlatformUtils.parse2StrList(bccAddress);
    }

    public String getTitleInfo() {
        if (PlatformUtils.hasText(title) && title.length() > CommonPlatformConstant.LENGTH_24) {
            return title.substring(0, CommonPlatformConstant.LENGTH_24) + "....";
        }
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    public long getMailGroup() {
        return mailGroup;
    }

    public void setMailGroup(long mailGroup) {
        this.mailGroup = mailGroup;
    }

    public long getMailType() {
        return mailType;
    }

    public void setMailType(long mailType) {
        this.mailType = mailType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_4096);
        sb.append(super.toString());
        sb.append(", title='").append(title).append('\'');
        sb.append(", ccAddress='").append(ccAddress).append('\'');
        sb.append(", bccAddress='").append(bccAddress).append('\'');
        sb.append(", mailGroup=").append(mailGroup);
        sb.append(", mailType=").append(mailType);
        return sb.toString();
    }
}
