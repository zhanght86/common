package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.components.ContextBean;

import java.io.IOException;
import java.io.Writer;

/**
 * 2015-11-20
 * 验证码标签
 *
 * @author wangpeng
 */
public class VerificationCodeComponent extends ContextBean {

    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(this.getClass());
    private String type;
    private String address;
    private boolean isEmail;
    private boolean modifiable;
    private boolean showAddress;
    private String userName;
    private long resourceType;
    private String resource;
    private String resourceCallback;
    private String tipMessage;
    private static final String CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/css/tag/verCodeTag.css\"/>";
    private static final String SCRIPT1 = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/tag/verCodeTag.js\"></script>";
    private static final String SCRIPT2 = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/tag/validate.js\"></script>";

    public VerificationCodeComponent(ValueStack stack, String type, String address, boolean isEmail, boolean modifiable, boolean showAddress, long resourceType, String resource, String resourceCallback, String tipMessage) {
        super(stack);
        Object member = stack.findValue("member.userName");
        if (member != null) {
            userName = member.toString();
        }
        this.type = type;
        this.address = PlatformUtils.hasText(address) ? address.trim() : "";
        this.isEmail = isEmail;
        this.modifiable = modifiable;
        this.showAddress = showAddress;
        this.resourceType = resourceType;
        this.resource = resource;
        this.resourceCallback = resourceCallback;
        this.tipMessage = tipMessage;
    }

    @Override
    public boolean start(Writer writer) {
        try {
            writer.write(toHtml());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return super.start(writer);
    }

    private String toHtml() {
        return showAddress ? toShowAddressHtml() : toHideAddressHtml();
    }

    /**
     * @return 页面显示的html信息
     * @category 显示地址输入信息
     */
    private String toShowAddressHtml() {
        StringBuilder html = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        html.append(CSS);
        html.append(SCRIPT1);
        html.append(SCRIPT2);
        html.append("<p class='clearfix verLine'>");
        html.append("   <label class='fl'>").append(isEmail ? "邮箱账号" : "手机号码").append("：</label>");
        html.append("   <b class='f18 fl' style='width:200px;'>");
        html.append("       <input id='userName' type='hidden' value='").append(userName).append("' />");
        html.append("       <input id='sendType' type='hidden' value='").append(type).append("' />");
        html.append("       <input id='resourceType' type='hidden' value='").append(resourceType).append("' />");
        html.append("       <input id='resource' type='hidden' value='").append(resource == null ? "" : resource).append("' />");
        if (modifiable) {
            html.append("   <input id='sendAddress' value='").append(address).append("' class='h26-text c_bor fl verInputText'/>");
        } else {
            html.append("   <input id='sendAddress' value='").append(address).append("' class='h26-text c_bor fl verInputText' disabled='disabled' />");
        }
        html.append("   </b>");
        html.append("   <input type='button' value='获取验证码' class='getVerCodeButton h26-btn ml10 fl' resourceCallback='").append(resourceCallback == null ? "" : resourceCallback).append("'/>");
        html.append("   <strong class='ml10 none'>");
        html.append("       <em class='correctTips ml10 fl'></em>");
        html.append("   </strong>");
        html.append("</p>");
        html.append("<p class='clearfix verLine'>");
        html.append("   <label class='fl'>验证码：</label>");
        html.append("   <input id='verCode' name='verCode' class='h26-text c_bor fl w100' maxlength='6'/>");
        html.append("   <strong class='ml10' style='font-weight:normal; color:#e00;'>");
        html.append("       <em class='ml10 fl'>").append(tipMessage).append("</em>");
        html.append("   </strong>");
        html.append("</p>");
        return html.toString();
    }

    /**
     * @return 页面显示的html信息
     * @category 不显示地址输入信息
     */
    private String toHideAddressHtml() {
        StringBuilder html = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        html.append(CSS);
        html.append(SCRIPT1);
        html.append(SCRIPT2);
        html.append("<p class='clearfix verLine' style='margin-bottom:0;'>");
        html.append("   <label class='fl' style='width:155px; font-size:12px; line-height:20px;'>验证码：<span style='color:#ff6700;'>*</span></label>");
        html.append("   <b class='f18 fl'>");
        html.append("       <input id='userName' type='hidden' value='").append(userName).append("' />");
        html.append("       <input id='sendType' type='hidden' value='").append(type).append("' />");
        html.append("       <input id='resourceType' type='hidden' value='").append(resourceType).append("' />");
        html.append("       <input id='resource' type='hidden' value='").append(resource == null ? "" : resource).append("' />");
        html.append("       <input id='sendAddress' type='hidden' value='").append(address).append("' />");
        html.append("       <input id='verCode' name='verCode' class='h26-text c_bor fl verInputText' style='height:18px; border:1px solid #aaa; width:100px;'/>");
        html.append("   </b>");
        html.append("   <input type='button' value='获取验证码' class='getVerCodeButton h26-btn ml10 fl' style='height:22px; padding:0 6px; font-size:14px;' resourceCallback='").append(resourceCallback == null ? "" : resourceCallback).append("'/>");
        html.append("   <strong class='ml10 none'>");
        html.append("       <em class='correctTips ml10 fl'></em>");
        html.append("   </strong>");
        html.append("</p>");
        html.append("<p class='clearfix verLine' style='margin:0;'>");
        html.append("   <label class='fl'>");
        html.append("       <span style='color:red;margin-left:160px;font-size:12px;'>").append(tipMessage).append("</span>");
        html.append("   </label>");
        html.append("</p>");
        return html.toString();
    }
}
