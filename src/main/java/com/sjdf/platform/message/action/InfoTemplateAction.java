package com.sjdf.platform.message.action;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.message.bean.InfoTemplate;
import com.sjdf.platform.rbac.helper.UserHelper;

/**
 * 消息模板管理Action
 * User: ketqi
 * Date: 2015-07-15 10:48
 */
public class InfoTemplateAction extends MessageBaseAction {
    private static final long serialVersionUID = -6854876851761384410L;
    private InfoTemplate info;

    /**
     * 添加新消息模板
     * admin/common/template!add.action
     */
    public String add() {
        Message message = messageService.addTemplate(info, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite(String.valueOf(info.getId()));
        }
        return NONE;
    }

    /**
     * 删除消息模板信息
     * admin/common/template!delete.action
     */
    public String delete() {
        Message message = messageService.delTemplate(idx, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite("success");
        }
        return NONE;
    }

    /**
     * 修改消息模板信息,获取信息
     * admin/common/template!edit.action
     */
    public String edit() {
        if (idx > 0) {
            info = messageService.get(InfoTemplate.class, idx);
        }
        return "edit";
    }

    /**
     * 修改消息模板信息
     * admin/common/template!update.action
     */
    public String update() {
        Message message = messageService.updateTemplate(info, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite("success");
        }
        return NONE;
    }

    /**
     * 新加消息模板
     * admin/common/template!beforeAdd.action
     */
    public String beforeAdd() {
        if (idx <= 0 || (info = messageService.get(InfoTemplate.class, idx)) == null) {
            info = null;
        }
        return "add";
    }

    /**
     * 获取模板内容
     * admin/common/template!query.action
     */
    public String query() {
        if (idx <= 0 || (info = messageService.get(InfoTemplate.class, idx)) == null) {
            AjaxSupport.sendFailText(ERROR);
        } else {
            AjaxSupport.sendSuccessTextOnly(null, info, "title", "content");
        }

        return NONE;
    }

    /**
     * 保存模板内容
     * admin/common/template!save.action
     */
    public String save() {
        Message message = messageService.addTemplate(info, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(String.valueOf(info.getId()));
        }
        return NONE;
    }

    public InfoTemplate getInfo() {
        return info;
    }

    public void setInfo(InfoTemplate info) {
        this.info = info;
    }
}
