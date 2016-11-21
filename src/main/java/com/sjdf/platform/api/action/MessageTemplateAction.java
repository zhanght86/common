package com.sjdf.platform.api.action;

import com.sjdf.platform.api.vo.AuthenticateVo;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateHelper;
import com.sjdf.platform.messageTemplate.service.MessageTemplateService;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 邮件和短信模板
 * User: ketqi
 * Date: 2013-05-21 11:58
 */
public class MessageTemplateAction extends BaseAction {
    private static final long serialVersionUID = -5819856925151842076L;
    @Autowired
    private MessageTemplateService templateService;
    @Autowired
    private PermissionService permissionService;
    private MessageTemplate template;
    /** 用户id和密码 */
    private String uid, key;

    /**
     * api/common/MessageTemplateAction!get.action
     * 邮件或短信获取接口
     */
    public String get() {
        List<MessageTemplate> templateList = templateService.list(template, null);
        printWrite(MessageTemplateHelper.parse(templateList));
        return NONE;
    }

    /**
     * <pre>
     * /api/common/MessageTemplateAction!list.action
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
    public String list() {
        Message message = permissionService.auth(idx, uid, key, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(AuthenticateVo.cteateFailVo(getText(message)).parse());
        } else {
            User user = (User) message.getReturnData();
            UserHelper.createCurrentLoginUser(user);
        }
        return "list";
    }

    public MessageTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MessageTemplate template) {
        this.template = template;
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
}
