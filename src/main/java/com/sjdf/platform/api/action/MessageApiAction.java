package com.sjdf.platform.api.action;

import com.sjdf.platform.api.vo.AuthenticateVo;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.MessageApiUser;
import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.message.service.MessageService;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * 信息接口
 * User: ketqi
 * Date: 2015-07-01 17:51
 */
public class MessageApiAction extends BaseAction {
    private static final long serialVersionUID = 272313951184931416L;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MessageService messageService;
    private SMSMessage sms;
    private EmailMessage email;
    /** 用户id和密码 */
    private String uid, key;
    private long messageType;
    private boolean needPage;

    /**
     * api/common/message!sms.action
     * 短信接口
     *
     * @return result
     */
    public String sms() {
        MessageApiUser user = MessageApiUserCache.getInstance().exist(uid, key);
        if (user == null) {
            AjaxSupport.sendFailText(getText("login.user.wrongUsernameAndPassword"));
            return NONE;
        }
        if (!user.isValidate()) {
            AjaxSupport.sendFailText(getText("login.user.forbid"));
            return NONE;
        }
        if (sms == null) {
            AjaxSupport.sendFailText(getText("message.send.message.invalidate"));
            return NONE;
        }
        sms.setUserId(uid);
        sms.setCompany(user.getCompany());
        sms.setSystemType(user.getSystemType());

        Message message = messageService.saveOrUpdateSms(sms, null, uid);
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    /**
     * api/common/message!email.action
     * 邮件接口
     *
     * @return result
     */
    public String email() {
        MessageApiUser user = MessageApiUserCache.getInstance().exist(uid, key);
        if (user == null) {
            AjaxSupport.sendFailText(getText("login.user.wrongUsernameAndPassword"));
            return NONE;
        }
        if (!user.isValidate()) {
            AjaxSupport.sendFailText(getText("login.user.forbid"));
            return NONE;
        }
        if (email == null) {
            AjaxSupport.sendFailText(getText("message.send.message.invalidate"));
            return NONE;
        }
        email.setUserId(uid);
        email.setCompany(user.getCompany());
        email.setSystemType(user.getSystemType());

        Message message = messageService.saveOrUpdateEmail(email, uid);
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    /**
     * <pre>
     * api/common/message!smsList.action
     * vercode = MD5.md5(idx + uid + key + connPwd + vertime)
     * 参数如下:
     * idx:systemType
     * uid
     * key(AES.encrypt(MD5.md5(key)))
     * vertime
     * vercode
     * </pre>
     * 短信列表接口
     *
     * @return result
     */
    public String smsList() {
        Message message = permissionService.auth(idx, uid, key, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(AuthenticateVo.cteateFailVo(getText(message)).parse());
        } else {
            User user = (User) message.getReturnData();
            UserHelper.createCurrentLoginUser(user);
        }
        return "smsList";
    }

    /**
     * <pre>
     * api/common/message!emailList.action
     * vercode = MD5.md5(idx + uid + key + connPwd + vertime)
     * 参数如下:
     * idx:systemType
     * uid
     * key(AES.encrypt(MD5.md5(key)))
     * vertime
     * vercode
     * </pre>
     * 邮件列表接口
     *
     * @return result
     */
    public String emailList() {
        Message message = permissionService.auth(idx, uid, key, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(AuthenticateVo.cteateFailVo(getText(message)).parse());
        } else {
            User user = (User) message.getReturnData();
            UserHelper.createCurrentLoginUser(user);
        }
        return "emailList";
    }

    /**
     * 消息查询接口
     * {"message":"message","bool":true}
     */
    public String messageList() {
        JSONObject json = new JSONObject();
        MessageApiUser user = MessageApiUserCache.getInstance().exist(uid, key);
        if (user == null) {
            AjaxSupport.sendFailText(getText("login.user.wrongUsernameAndPassword"));
            return NONE;
        }
        if (!user.isValidate()) {
            AjaxSupport.sendFailText(getText("login.user.forbid"));
            return NONE;
        }
        if ((messageType != MessageType.SMS && messageType != MessageType.EMAIL)
                || (messageType == MessageType.SMS && sms == null)
                || (messageType == MessageType.EMAIL && email == null)) {
            AjaxSupport.sendFailText(getText("message.send.message.invalidate"));
            return NONE;
        }
        if (messageType == MessageType.SMS) {
            sms.setSendTime(null);
            sms.setCreateTime(null);
            sms.setUpdateTime(null);
            List<SMSMessage> smsList = messageService.smsMessageList(sms, needPage ? page : null);
            if (smsList == null) {
                smsList = Collections.emptyList();
            }
            json.put("bool", Boolean.TRUE);
            json.put("message", JSONArray.fromObject(smsList));
            printWrite(json.toString());
            return NONE;
        } else {
            email.setSendTime(null);
            email.setCreateTime(null);
            email.setUpdateTime(null);
            List<EmailMessage> emailList = messageService.emailMessageList(email, needPage ? page : null);
            if (emailList == null) {
                emailList = Collections.emptyList();
            }
            json.put("bool", Boolean.TRUE);
            json.put("message", JSONArray.fromObject(emailList));
            printWrite(json.toString());
        }
        return NONE;
    }

    /** 微信消息查询接口 */
    public String wechatList() {
        Message message = permissionService.auth(idx, uid, key, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(AuthenticateVo.cteateFailVo(getText(message)).parse());
        } else {
            User user = (User) message.getReturnData();
            UserHelper.createCurrentLoginUser(user);
        }
        return "wechatList";
    }

    /** 统计当前发送的推荐码邮件 */
    public String countRecommendEmail() {
        MessageApiUser user = MessageApiUserCache.getInstance().exist(uid, key);
        if (user == null) {
            AjaxSupport.sendFailText(getText("login.user.wrongUsernameAndPassword"));
            return NONE;
        }
        if (!user.isValidate()) {
            AjaxSupport.sendFailText(getText("login.user.forbid"));
            return NONE;
        }
        if (email == null || !PlatformUtils.hasText(email.getTitle()) || !PlatformUtils.hasText(email.getSendUser())) {
            AjaxSupport.sendFailText(getText("message.send.message.invalidate"));
            return NONE;
        }
        long count = messageService.countRecommendEmail(email.getTitle(), email.getSendUser());
        AjaxSupport.sendSuccessText(String.valueOf(count));
        return NONE;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getMessageType() {
        return messageType;
    }

    public void setMessageType(long messageType) {
        this.messageType = messageType;
    }

    public boolean isNeedPage() {
        return needPage;
    }

    public void setNeedPage(boolean needPage) {
        this.needPage = needPage;
    }
}
