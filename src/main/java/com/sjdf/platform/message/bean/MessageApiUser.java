package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.RandomUtils;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.cache.MessageApiImplCache;
import com.sjdf.platform.message.cache.SendApiConfigCache;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信息接口授权
 * User: ketqi
 * Date: 2015-07-03 10:48
 */
@Entity
@Table(name = "p_message_api_user")
public class MessageApiUser extends BaseBean {
    private static final long serialVersionUID = -7443631435666139326L;
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
     * 是否有效
     *
     * @see com.sjdf.platform.dictionary.bean.ValidMark
     */
    private long valid;
    /** 用户id */
    private String userId;
    /** 形象名称 */
    private String name;
    /** 用户连接密码 */
    private String connPwd;
    /**
     * 使用的短信接口
     *
     * @see com.sjdf.platform.message.bean.SendApiConfig
     */
    private String smsApi;
    /**
     * 使用的邮件接口
     *
     * @see com.sjdf.platform.message.bean.SendApiConfig
     */
    private String emailApi;
    /** 备注信息 */
    private String remark;
    /** 短信接口列表 */
    @Transient
    private transient List<Long> smsApiList;
    /** 邮件接口列表 */
    @Transient
    private transient List<Long> emailApiList;
    /** 短信发送计数器 */
    @Transient
    private transient AtomicInteger smsCounter = new AtomicInteger();
    /** 邮件发送计数器 */
    @Transient
    private transient AtomicInteger emailCounter = new AtomicInteger();

    public MessageApiUser initTransient() {
        smsApiList = PlatformUtils.parse2LongList(smsApi);
        emailApiList = PlatformUtils.parse2LongList(emailApi);
        return this;
    }

    public SmsApi getOneValidSmsApi() {
        if (smsApiList == null) {
            smsApiList = PlatformUtils.parse2LongList(smsApi);
        }

        if (smsApiList.isEmpty()) {
            return null;
        }

        int counter = smsApiList.size();
        SmsApi api = null;
        while (counter > 0) {
            api = getOneSmsApi();
            if (api != null) {
                break;
            }
            counter--;
        }
        return api;
    }

    private SmsApi getOneSmsApi() {
        if (smsApiList == null) {
            smsApiList = PlatformUtils.parse2LongList(smsApi);
        }

        if (smsApiList.isEmpty()) {
            return null;
        }

        long idx;
        int size = smsApiList.size();
        if (size == 1) {
            idx = smsApiList.get(0);
        } else {
            idx = smsApiList.get(smsCounter.incrementAndGet() % size);
        }
        if (idx == 0L) {
            return null;
        }

        SendApiConfig config = SendApiConfigCache.getInstance().get(idx);
        if (config == null || !config.isValidate()) {
            return null;
        }

        return MessageApiImplCache.getInstance().createSmsApi(config);
    }

    public EmailApi getOneValidEmailApi() {
        if (emailApiList == null) {
            emailApiList = PlatformUtils.parse2LongList(emailApi);
        }

        if (emailApiList.isEmpty()) {
            return null;
        }

        int counter = emailApiList.size();
        EmailApi api = null;
        while (counter > 0) {
            api = getOneEmailApi();
            if (api != null) {
                break;
            }
            counter--;
        }
        return api;
    }

    private EmailApi getOneEmailApi() {
        if (emailApiList == null) {
            emailApiList = PlatformUtils.parse2LongList(emailApi);
        }

        if (emailApiList.isEmpty()) {
            return null;
        }

        long idx;
        int size = emailApiList.size();
        if (size == 1) {
            idx = emailApiList.get(0);
        } else {
            idx = emailApiList.get(emailCounter.incrementAndGet() % size);
        }
        if (idx == 0L) {
            return null;
        }

        SendApiConfig config = SendApiConfigCache.getInstance().get(idx);
        if (config == null || !config.isValidate()) {
            return null;
        }

        return MessageApiImplCache.getInstance().createEmailApi(config);
    }

    @ModifyRecord(name = "使用的邮件接口")
    public String getEmailApiInfo() {
        List<Long> apiList = PlatformUtils.parse2LongList(emailApi);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        for (Long idx : apiList) {
            builder.append(SendApiConfigCache.getInstance().getName(idx)).append(CommonPlatformConstant.COMMA_SEPARATOR);
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    @ModifyRecord(name = "使用的短信接口")
    public String getSmsApiInfo() {
        List<Long> apiList = PlatformUtils.parse2LongList(smsApi);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_256);
        for (Long idx : apiList) {
            builder.append(SendApiConfigCache.getInstance().getName(idx)).append(CommonPlatformConstant.COMMA_SEPARATOR);
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public List<SendApiConfig> getEmailApiConfigList() {
        List<Long> apiList = PlatformUtils.parse2LongList(emailApi);
        List<SendApiConfig> list = new ArrayList<>(apiList.size());
        for (Long idx : apiList) {
            list.add(SendApiConfigCache.getInstance().get(idx));
        }
        return list;
    }

    public List<SendApiConfig> getSmsApiConfigList() {
        List<Long> apiList = PlatformUtils.parse2LongList(smsApi);
        List<SendApiConfig> list = new ArrayList<>(apiList.size());
        for (Long idx : apiList) {
            list.add(SendApiConfigCache.getInstance().get(idx));
        }
        return list;
    }

    @ModifyRecord(name = "所属公司")
    public String getCompanyInfo() {
        return ConfigManager.getInstance().getName(CompanyClass.class, company);
    }

    @ModifyRecord(name = "系统类型")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    @ModifyRecord(name = "是否有效")
    public String getValidInfo() {
        return ConfigManager.getInstance().getName(ValidMark.class, valid);
    }

    public boolean isValidate() {
        return valid == ValidMark.VALID;
    }

    public Message valid() {
        if (company == 0L || !ConfigManager.getInstance().existAttr(CompanyClass.class, company)) {
            return Message.createMessage("message.api.user.company.invalidate");
        }

        if (systemType == 0L || !ConfigManager.getInstance().existAttr(SystemType.class, systemType)) {
            return Message.createMessage("message.api.user.system.type.invalidate");
        }

        if (valid == 0L || !ConfigManager.getInstance().existAttr(ValidMark.class, valid)) {
            return Message.createMessage("message.api.user.valid.invalidate");
        }

        if (smsApiList != null && !smsApiList.isEmpty()) {
            for (ListIterator<Long> iterator = smsApiList.listIterator(); iterator.hasNext(); ) {
                long idx = iterator.next();
                if (idx <= 0L) {
                    iterator.remove();
                    continue;
                }

                if (SendApiConfigCache.getInstance().get(idx) == null) {
                    return Message.createMessage("message.api.user.sms.api.invalidate");
                }
            }
            smsApi = PlatformUtils.parse2String(smsApiList) + CommonPlatformConstant.COMMA_SEPARATOR;
        }

        if (emailApiList != null && !emailApiList.isEmpty()) {
            for (ListIterator<Long> iterator = emailApiList.listIterator(); iterator.hasNext(); ) {
                long idx = iterator.next();
                if (idx <= 0L) {
                    iterator.remove();
                    continue;
                }

                if (SendApiConfigCache.getInstance().get(idx) == null) {
                    return Message.createMessage("message.api.user.email.api.invalidate");
                }
            }

            emailApi = PlatformUtils.parse2String(emailApiList) + CommonPlatformConstant.COMMA_SEPARATOR;
        }

        if (!PlatformUtils.hasText(userId)) {
            return Message.createMessage("message.send.api.config.user.name.invalidate");
        }

        if (!PlatformUtils.hasText(connPwd)) {
            connPwd = RandomUtils.getRandomString(CommonPlatformConstant.LENGTH_16);
        }

        return Message.createEmptyMessage();
    }

    public String getNameInfo() {
        if (PlatformUtils.hasText(remark)) {
            return name + "(" + remark + ")";
        }
        return name;
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

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    @ModifyRecord(name = "用户id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ModifyRecord(name = "形象名称")
    public String getName() {
        if (!PlatformUtils.hasText(name)) {
            return userId;
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnPwd() {
        return connPwd;
    }

    public void setConnPwd(String connPwd) {
        this.connPwd = connPwd;
    }

    public String getSmsApi() {
        return smsApi;
    }

    public void setSmsApi(String smsApi) {
        this.smsApi = smsApi;
    }

    public String getEmailApi() {
        return emailApi;
    }

    public void setEmailApi(String emailApi) {
        this.emailApi = emailApi;
    }

    @ModifyRecord(name = "备注信息")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getSmsApiList() {
        return smsApiList;
    }

    public void setSmsApiList(List<Long> smsApiList) {
        this.smsApiList = smsApiList;
    }

    public List<Long> getEmailApiList() {
        return emailApiList;
    }

    public void setEmailApiList(List<Long> emailApiList) {
        this.emailApiList = emailApiList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        sb.append("MessageApiUser{");
        sb.append("company=").append(company);
        sb.append(", systemType=").append(systemType);
        sb.append(", valid=").append(valid);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", connPwd='").append(connPwd).append('\'');
        sb.append(", smsApi=").append(smsApi);
        sb.append(", emailApi=").append(emailApi);
        sb.append('}');
        return sb.toString();
    }
}
