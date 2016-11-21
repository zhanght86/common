package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.VerificationConfig;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2015-11-20
 * 验证码tag
 *
 * @author wangpeng
 */
public class VerificationCodeTag extends ContextBeanTag {

    private static final long serialVersionUID = -6316791490143293024L;

    /**
     * 获取验证码的方式
     *
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    private long sendType = MessageType.SMS;

    /**
     * 电话号码/邮箱账号
     */
    private String sendAddress;

    /**
     * 是否显示地址信息
     */
    private boolean showAddress = true;

    /**
     * 地址信息是否能修改
     */
    private boolean modifiable = false;

    /**
     * 该验证码的类型
     *
     * @see com.sjdf.platform.dictionary.bean.VerificationType
     */
    private long resourceType;

    /**
     * 资源标示，如：用户名，域名
     */
    private String resource;

    /** 当resource为空时,资源标识值根据计算得到 */
    private String resourceCallback;
    /**
     * 提示信息
     */
    private String tipMessage;

    private static final String SMS = "sms";
    private static final String EMAIL = "email";

    @Override
    public VerificationCodeComponent getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        String type = EMAIL;
        boolean isEmail = true;
        if (MessageType.SMS == sendType) {
            type = SMS;
            isEmail = false;
        }
        if (!PlatformUtils.hasText(tipMessage)) {
            tipMessage = ConfigManager.getInstance().getValue(VerificationConfig.class, VerificationConfig.TIP_MESSAGE);
        }
        return new VerificationCodeComponent(stack, type, sendAddress, isEmail, modifiable, showAddress, resourceType, resource, resourceCallback, tipMessage);
    }

    public long getSendType() {
        return sendType;
    }

    public void setSendType(long sendType) {
        this.sendType = sendType;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public boolean isShowAddress() {
        return showAddress;
    }

    public void setShowAddress(boolean showAddress) {
        this.showAddress = showAddress;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public String getTipMessage() {
        return tipMessage;
    }

    public void setTipMessage(String tipMessage) {
        this.tipMessage = tipMessage;
    }

    public long getResourceType() {
        return resourceType;
    }

    public void setResourceType(long resourceType) {
        this.resourceType = resourceType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceCallback() {
        return resourceCallback;
    }

    public void setResourceCallback(String resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
