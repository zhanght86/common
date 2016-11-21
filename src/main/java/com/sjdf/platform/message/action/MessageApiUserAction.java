package com.sjdf.platform.message.action;

import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.message.bean.MessageApiUser;
import com.sjdf.platform.message.bean.SendApiConfig;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.message.cache.SendApiConfigCache;
import com.sjdf.platform.rbac.helper.UserHelper;

import java.util.List;

/**
 * 信息接口授权Action
 * User: ketqi
 * Date: 2015-07-08 16:16
 */
public class MessageApiUserAction extends MessageBaseAction {
    private static final long serialVersionUID = 4467230079308235719L;
    private MessageApiUser user;
    private List<MessageApiUser> userList;

    /**
     * /admin/common/apiAuth!list.action
     * 授权列表
     *
     * @return result
     */
    @Permission(code = "06011321", name = "信息接口授权管理")
    public String list() {
        userList = messageService.userList(user, page);
        return "list";
    }

    /**
     * admin/common/apiAuth!edit.action
     * 信息接口授权编辑
     *
     * @return result
     */
    @Permission(code = "06011322", name = "信息接口授权编辑")
    public String edit() {
        if (idx > 0) {
            user = MessageApiUserCache.getInstance().get(idx);
        }
        return "edit";
    }

    /**
     * admin/common/apiAuth!post.action
     * 信息接口授权添加或者更新
     *
     * @return result
     */
    @Permission(code = "06011323", name = "信息接口授权添加或更新")
    public String post() {
        Message message = messageService.saveOrUpdateUser(user, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    public List<SendApiConfig> getSmsConfigList() {
        return SendApiConfigCache.getInstance().getConfigList(MessageType.SMS);
    }

    public List<SendApiConfig> getEmailConfigList() {
        return SendApiConfigCache.getInstance().getConfigList(MessageType.EMAIL);
    }

    public MessageApiUser getUser() {
        return user;
    }

    public void setUser(MessageApiUser user) {
        this.user = user;
    }

    public List<MessageApiUser> getUserList() {
        return userList;
    }
}
