package com.sjdf.platform.messageTemplate.bean;

import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateManager;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 邮件或短信模板
 * Create at 2012-08-01
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_message_template")
public class MessageTemplate extends BaseBean {
    private static final long serialVersionUID = -8221905250676871312L;
    /**
     * 系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long systemType;
    /**
     * 模板类型
     *
     * @see com.sjdf.platform.dictionary.bean.MessageTemplateType
     */
    private long templateType;
    /** 邮件标题 */
    private String title;

    /** 邮件内容 */
    @Lob
    @Column(columnDefinition = "longtext")
    private String email;

    /** 短信 */
    @Lob
    @Column(columnDefinition = "longtext")
    private String sms;

    /**
     * 邮件是否有效
     *
     * @see com.sjdf.platform.dictionary.bean.ValidMark
     */
    private long emailValid;

    /**
     * 短信是否有效
     *
     * @see com.sjdf.platform.dictionary.bean.ValidMark
     */
    private long smsValid;

    @Transient
    private transient String mailAddress;//预览邮件地址

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public long getTemplateType() {
        return templateType;
    }

    public void setTemplateType(long templateType) {
        this.templateType = templateType;
    }

    @ModifyRecord(name = "邮件标题")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ModifyRecord(name = "邮件内容")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ModifyRecord(name = "短信")
    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public long getEmailValid() {
        return emailValid;
    }

    public void setEmailValid(long emailValid) {
        this.emailValid = emailValid;
    }

    public long getSmsValid() {
        return smsValid;
    }

    public void setSmsValid(long smsValid) {
        this.smsValid = smsValid;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @ModifyRecord(name = "系统类型")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    @ModifyRecord(name = "模板类型")
    public String getTemplateTypeInfo() {
        return ConfigManager.getInstance().getName(MessageTemplateType.class, templateType);
    }

    @ModifyRecord(name = "邮件是否有效")
    public String getEmailValidInfo() {
        return ConfigManager.getInstance().getName(ValidMark.class, emailValid);
    }

    @ModifyRecord(name = "短信是否有效")
    public String getSmsValidInfo() {
        return ConfigManager.getInstance().getName(ValidMark.class, smsValid);
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<template>");
        xml.append("<id><![CDATA[").append(getId()).append("]]></id>");
        xml.append("<systemType><![CDATA[").append(systemType).append("]]></systemType>");
        xml.append("<templateType><![CDATA[").append(templateType).append("]]></templateType>");
        xml.append("<title><![CDATA[").append(title).append("]]></title>");
        xml.append("<email><![CDATA[").append(email).append("]]></email>");
        xml.append("<sms><![CDATA[").append(sms).append("]]></sms>");
        xml.append("<emailValid><![CDATA[").append(emailValid).append("]]></emailValid>");
        xml.append("<smsValid><![CDATA[").append(smsValid).append("]]></smsValid>");
        xml.append("</template>");
        return xml.toString();
    }

    /** 邮件模板是否有效 */
    public boolean isEmailValid() {
        return ValidMark.VALID == emailValid;
    }

    /** 短信模板是否有效 */
    public boolean isSmsValid() {
        return ValidMark.VALID == smsValid;
    }

    /** 该模板记录是否有效 */
    public boolean isValid() {
        return isEmailValid() && isSmsValid();
    }

    /**
     * 邮件模板渲染, 如果邮件内容无效或者为空则返回null, 否则返回替换后的内容
     *
     * @param params 待填充的数据
     * @return 邮件内容
     */
    public String renderEmail(Map<String, String> params) {
        //验证
        if (ValidMark.VALID != emailValid || !StringUtils.hasText(email)) {
            return null;
        }

        Map<String, String> dataMap = params;
        if (dataMap == null || dataMap.isEmpty()) {
            dataMap = new HashMap<>();
        }

        //添加邮件尾部
        List<Dictionary> list = MessageTemplateManager.getInstance().getEmailMessageTemplateVariable(templateType);
        if (!list.isEmpty()) {
            long footer;
            Set<Long> varList = MessageTemplateManager.getSystemEmailFooterList();
            for (Dictionary d : list) {
                if (d != null && varList.contains(d.getAttr())) {
                    footer = d.getAttr();
                    dataMap.put(ConfigManager.getInstance().getValue(MessageTemplateVariable.class, footer), ConfigManager.getInstance().getValue(EmailFooter.class, footer));
                }
            }
        }

        String temp = email;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            temp = temp.replace(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
        }

        return temp;
    }

    /**
     * 短信模板渲染, 如果短信内容无效或者为空则返回null, 否则返回替换后的内容
     *
     * @param dataMap 待填充的数据
     * @return 短信内容
     */
    public String renderSMS(Map<String, String> dataMap) {
        if (ValidMark.VALID != smsValid || !StringUtils.hasText(sms)) {
            return null;
        }

        if (dataMap == null || dataMap.isEmpty()) {
            return sms;
        }

        String temp = sms;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            temp = temp.replace(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
        }

        return temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MessageTemplate [systemType=");
        builder.append(systemType);
        builder.append(", templateType=");
        builder.append(templateType);
        builder.append(", title=");
        builder.append(title);
        builder.append(", email=");
        builder.append(email);
        builder.append(", sms=");
        builder.append(sms);
        builder.append(", emailValid=");
        builder.append(emailValid);
        builder.append(", smsValid=");
        builder.append(smsValid);
        builder.append("]");
        return builder.toString();
    }
}
