package com.sjdf.platform.rbac.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 14:27
 * 用户管理
 */
public class UserAction extends BaseAction {
    private static final long serialVersionUID = 7915741702342320928L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(UserAction.class);
    @Autowired
    private PermissionService permissionService;
    /** 要操作的用户对象 */
    private User user;
    /** 用户对象列表 */
    private List<User> userList;
    /** 旧密码，修改密码时用 */
    private String oldPassword;

    /** 列出当前以下用户列表;URL:admin/common/platform/UserAction!list.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010701", name = "用户列表")
    public String list() {
        if (user == null) {
            user = new User();
        }
        user.setParent(UserHelper.getCurrentLoginUser());

        //用户列表
        userList = permissionService.listUser(user, page);
        return "list";
    }

    public String listAjax() {
        userList = permissionService.listUser(user, null);
        return NONE;
    }

    /** 添加新的用户;URL:admin/common/platform/UserAction!add.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010702", name = "添加用户")
    public String add() {
        User currentUser = UserHelper.getCurrentLoginUser();
        // 创建用户标记判断
        if (!currentUser.isSysUser() && currentUser.getCreateFlag() == WhetherState.NO) {
            tipMessage = getText("user.createFlag.fail");
            return "-1";
        }

        // 添加用户,取得信息
        Message message = permissionService.addUser(currentUser, user);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
            return "-1";
        } else {
            saveLog(OperatorAction.ADD, LogType.INFO, user.getUsername(), message.getInfo(), null);
        }
        return "add";
    }

    /** 删除用户信息;URL:admin/common/platform/UserAction!delete.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010703", name = "删除用户")
    public String delete() {
        User currentUser = UserHelper.getCurrentLoginUser();
        Message message = permissionService.deleteUser(currentUser, idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            user = (User) message.getReturnData();
            saveLog(OperatorAction.DELETE, LogType.INFO, user.getUsername(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /** 修改密码 */
    @com.sjdf.platform.common.annotations.Permission(code = "06010704", name = "修改用户密码")
    public String changeSelfPassword() {
        User currentUser = UserHelper.getCurrentLoginUser();
        if (currentUser.isSysUser()) {
            tipMessage = getText("user.sys.operation.noNecessary");
            return "-1";
        }

        Message message = permissionService.changeSelfPassword(currentUser.getId(), oldPassword, user);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
            return "-1";
        } else {
            user = (User) message.getReturnData();
            saveLog(OperatorAction.MODIFY, LogType.INFO, user.getUsername(), "change password", null);
        }
        return SUCCESS;
    }

    /** 修改下级用户密码;URL:admin/common/platform/UserAction!changeOtherPassword.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010705", name = "修改下级用户密码")
    public String changeOtherPassword() {
        User currentUser = UserHelper.getCurrentLoginUser();
        Message message = permissionService.changeOtherPassword(currentUser, user);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
            return NONE;
        } else {
            user = (User) message.getReturnData();
            saveLog(OperatorAction.MODIFY, LogType.INFO, user.getUsername(), "change password", null);
        }
        printWrite("success");
        return NONE;
    }

    /** 显示自己信息 */
    @com.sjdf.platform.common.annotations.Permission(code = "06010706", name = "用户详情")
    public String showSelf() {
        User currentUser = UserHelper.getCurrentLoginUser();
        //重新取当前用户信息,与系统中使用用户分开
        user = permissionService.getUser(currentUser.getId());
        return "showSelf";
    }

    /** 显示下级用户信息 */
    @com.sjdf.platform.common.annotations.Permission(code = "06010707", name = "下级用户详情")
    public String showOther() {
        User currentUser = UserHelper.getCurrentLoginUser();
        user = permissionService.getUser(idx);
        if (user == null) {
            tipMessage = getText("user.nonexist");
        }
        if (!currentUser.isParent(user)) {
            tipMessage = getText("user.parent.false", user.getUsername());
        }
        return "showOther";
    }

    /** 修改下级用户信息,获取信息;URL:admin/common/platform/UserAction!editOther.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010708", name = "编辑下级用户信息")
    public String editOther() {
        User currentUser = UserHelper.getCurrentLoginUser();
        user = permissionService.getUser(idx);
        if (user == null) {
            tipMessage = getText("user.nonexist");
        }
        if (!currentUser.isParent(user)) {
            tipMessage = getText("user.parent.false", user.getUsername());
        }
        return "editOther";
    }

    /** 修改下级用户信息;URL:admin/common/platform/UserAction!updateOther.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010709", name = "修改下级用户信息", supportedCode = "010708")
    public String updateOther() {
        User currentUser = UserHelper.getCurrentLoginUser();
        Message message = permissionService.updateOther(currentUser, user);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
            return "-1";
        } else {
            user = (User) message.getReturnData();
        }
        return "updateOther";
    }

    /** 修改自己信息,获取信息 */
    @com.sjdf.platform.common.annotations.Permission(code = "06010710", name = "编辑用户信息")
    public String editSelf() {
        User currentUser = UserHelper.getCurrentLoginUser();
        user = permissionService.getUser(currentUser.getId());
        return "editSelf";
    }

    /** 修改自己信息 */
    @com.sjdf.platform.common.annotations.Permission(code = "06010711", name = "修改用户信息", supportedCode = "010710")
    public String updateSelf() {
        User currentUser = UserHelper.getCurrentLoginUser();
        if (currentUser.isSysUser()) {
            printWrite(getText("user.sys.operation.noNecessary"));
            return NONE;
        }

        Message message = permissionService.updateSelf(currentUser.getId(), user);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
            return NONE;
        }
        return SUCCESS;
    }

    /** 启用/禁用;URL:admin/common/platform/UserAction!forbid.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010712", name = "启用,禁用用户")
    public String forbid() {
        User currentUser = UserHelper.getCurrentLoginUser();
        Message message = permissionService.forbid(currentUser, idx);
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
            return NONE;
        } else {
            user = (User) message.getReturnData();
            String info = user.getForbidInfor();
            saveLog(OperatorAction.MODIFY, LogType.INFO, user.getUsername(), info, null);
            AjaxSupport.sendSuccessText(info);
        }

        return NONE;
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_USER, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public List<? extends Dictionary> getSystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SystemType.class);
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
