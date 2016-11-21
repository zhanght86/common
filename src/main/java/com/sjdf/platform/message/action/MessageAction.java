package com.sjdf.platform.message.action;

import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.conf.ProfileMapHelper;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.WeChatApiManager;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.bean.*;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.message.service.MybatisService;
import com.sjdf.platform.message.task.SendManager;
import com.sjdf.platform.message.vo.WechatMaterialVo;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 消息Action
 * User: ketqi
 * Date: 2015-07-01 17:41
 */
public class MessageAction extends MessageBaseAction {
    private static final long serialVersionUID = -5099232208180768525L;
    @Autowired
    private MybatisService mybatisService;
    private SMSMessage sms;
    private List<SMSMessage> smsList;
    private EmailMessage email;
    private List<EmailMessage> emailList;
    private List<MessageApiUser> userList;
    private List<InfoTemplate> templateList;
    private long status;
    private long[] ids;
    private WechatMessage wechat;
    private List<WechatMaterialVo> materialList;
    private List<WechatMessage> wechatList;
    private File xls;

    /**
     * admin/common/message!sms.action
     * 短信列表
     *
     * @return result
     */
    @Permission(code = "06011341", name = "短信管理", url = "admin/common/message!sms.action", menu = true)
    public String sms() {
        if (sms == null) {
            sms = new SMSMessage();
            sms.setCreateTime(DateUtils.getBeginOfDate());
            sms.setUpdateTime(DateUtils.getEndOfDate());
            sms.setStatus(SendStatus.SEND_FAIL);
        }
        smsList = messageService.smsMessageList(sms, page);
        return "smsList";
    }

    /**
     * admin/common/message!smsHistory.action
     * 历史短信列表
     *
     * @return result
     */
    @Permission(code = "06011342", name = "历史短信查询")
    public String smsHistory() {
        smsList = mybatisService.querySmsList(sms, page);
        return "smsHistory";
    }

    /**
     * admin/common/message!editSms.action
     * 短信编辑
     *
     * @return result
     */
    @Permission(code = "06011343", name = "短信编辑")
    public String editSms() {
        createApiUrl(MessageType.SMS);
        userList = MessageApiUserCache.getInstance().getList(MessageType.SMS);
        templateList = messageService.listTemplateTree(0L, MessageType.SMS);

        if (idx >= 0) {
            sms = messageService.get(SMSMessage.class, idx);
        }
        return "sms";
    }

    /**
     * admin/common/message!editSms.action
     * 短信编辑
     *
     * @return result
     */
    @Permission(code = "06011344", name = "历史短信详情")
    public String infoSms() {
        if (idx >= 0) {
            sms = mybatisService.getSms(idx);
        }
        return "infoSms";
    }

    /**
     * admin/common/message!saveOrUpdateSms.action
     * 添加或更新短信
     *
     * @return result
     */
    @Permission(code = "06011345", name = "短信添加或更新")
    public String saveOrUpdateSms() {
        Message message = messageService.saveOrUpdateSms(sms, xls, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/message!email.action
     * 短信列表
     *
     * @return result
     */
    @Permission(code = "06011361", name = "邮件管理", url = "admin/common/message!email.action", menu = true)
    public String email() {
        if (email == null) {
            email = new EmailMessage();
            email.setCreateTime(DateUtils.getBeginOfDate());
            email.setUpdateTime(DateUtils.getEndOfDate());
            email.setStatus(SendStatus.SEND_FAIL);
        }
        emailList = messageService.emailMessageList(email, page);
        return "emailList";
    }

    /**
     * admin/common/message!emailHistory.action
     * 历史邮件列表
     *
     * @return result
     */
    @Permission(code = "06011362", name = "历史邮件查询")
    public String emailHistory() {
        emailList = mybatisService.queryEmailList(email, page);
        return "emailHistory";
    }

    /**
     * admin/common/message!editEmail.action
     * 邮件编辑
     *
     * @return result
     */
    @Permission(code = "06011363", name = "邮件编辑")
    public String editEmail() {
        createApiUrl(MessageType.EMAIL);
        userList = MessageApiUserCache.getInstance().getList(MessageType.EMAIL);
        templateList = messageService.listTemplateTree(0L, MessageType.EMAIL);

        if (idx >= 0) {
            email = messageService.get(EmailMessage.class, idx);
        }
        return "email";
    }

    /**
     * admin/common/message!editEmail.action
     * 邮件编辑
     *
     * @return result
     */
    @Permission(code = "06011364", name = "历史邮件详情")
    public String infoEmail() {
        if (idx >= 0) {
            email = mybatisService.getEmail(idx);
        }
        return "infoEmail";
    }

    /**
     * admin/common/message!saveOrUpdateEmail.action
     * 添加或更新邮件
     *
     * @return result
     */
    @Permission(code = "06011365", name = "邮件添加或更新")
    public String saveOrUpdateEmail() {
        Message message = messageService.saveOrUpdateEmail(email, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(getText("command.completed.successfully"));
        }
        return NONE;
    }

    /**
     * admin/common/message!updateStatus.action
     * 更新发送状态
     *
     * @return result
     */
    @Permission(code = "06011366", name = "更新信息状态")
    public String updateStatus() {
        Message message = messageService.updateStatus(idx, ids, status, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(getText("command.completed.successfully"));
        }
        return NONE;
    }

    /**
     * admin/common/message!editWechat.action
     * 编辑微信
     *
     * @return result
     */
    @Permission(code = "06011367", name = "微信编辑")
    public String editWechat() {
        createApiUrl(MessageType.WECHAT);
        if (idx >= 0) {
            wechat = messageService.get(WechatMessage.class, idx);
        }
        materialList = WeChatApiManager.getInstance().batchGetNewsMaterial();
        return "wechat";
    }

    /**
     * admin/common/message!saveOrUpdateWechat.action
     * 微信添加或更新
     *
     * @return result
     */
    @Permission(code = "06011368", name = "微信添加或更新")
    public String saveOrUpdateWechat() {
        Message message = messageService.saveOrUpdateWechat(wechat, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(getText("command.completed.successfully"));
        }
        return NONE;
    }

    /**
     * admin/common/message!wechat.action
     * 微信列表
     *
     * @return result
     */
    @Permission(code = "06011369", name = "微信管理", url = "admin/common/message!wechat.action", menu = true)
    public String wechat() {
        if (wechat == null) {
            wechat = new WechatMessage();
            wechat.setCreateTime(DateUtils.getBeginOfDate());
            wechat.setUpdateTime(DateUtils.getEndOfDate());
            wechat.setStatus(SendStatus.SEND_FAIL);
        }
        wechatList = messageService.wechatMessageList(wechat, page);
        return "wechatList";
    }

    /**
     * admin/common/message!monitor.action
     * 发送队列监控
     *
     * @return result
     */
    public String monitor() {
        AjaxSupport.sendSuccessText("", SendManager.getInstance().monitorList());
        return NONE;
    }

    private void createApiUrl(long messageType) {
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        vertime = DateUtils.formatDateTimestamp(new Date());
        vercode = MD5.md5(connPwd + vertime);
        tipMessageUrl = ProfileProvider.getValue(ProfileMapHelper.MESSAGE_EXTERNAL_API_URL) + "?msgType=" + messageType + "&vertime=" + vertime + "&vercode=" + vercode;
    }

    public SMSMessage getSms() {
        return sms;
    }

    public void setSms(SMSMessage sms) {
        this.sms = sms;
    }

    public EmailMessage getEmail() {
        return email;
    }

    public void setEmail(EmailMessage email) {
        this.email = email;
    }

    public List<SMSMessage> getSmsList() {
        return smsList;
    }

    public List<EmailMessage> getEmailList() {
        return emailList;
    }

    public List<MessageApiUser> getUserList() {
        return userList;
    }

    public List<InfoTemplate> getTemplateList() {
        return templateList;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public WechatMessage getWechat() {
        return wechat;
    }

    public void setWechat(WechatMessage wechat) {
        this.wechat = wechat;
    }

    public List<WechatMaterialVo> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<WechatMaterialVo> materialList) {
        this.materialList = materialList;
    }

    public List<WechatMessage> getWechatList() {
        return wechatList;
    }

    public void setWechatList(List<WechatMessage> wechatList) {
        this.wechatList = wechatList;
    }

    public File getXls() {
        return xls;
    }

    public void setXls(File xls) {
        this.xls = xls;
    }
}
