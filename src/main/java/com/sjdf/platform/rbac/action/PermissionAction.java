package com.sjdf.platform.rbac.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 14:20
 * 权限action, 对系统默认权限进行修改操作
 */
public class PermissionAction extends BaseAction {
    private static final long serialVersionUID = 1771299073103417797L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PermissionAction.class);
    @Autowired
    private PermissionService permissionService;
    /** 要操作的权限 */
    private Permission permission;
    /** 返回的权限信息列表 */
    private List<Permission> permissionList;

    /** 列出权限信息:/admin/common/platform/PermissionAction!list.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010401", name = "权限列表")
    public String list() {
        permissionList = permissionService.listPermissionByPage(permission, page);
        return "list";
    }

    /** 修改权限信息,获取信息/admin/common/platform/PermissionAction!edit.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010402", name = "权限编辑")
    public String edit() {
        if (idx > 0) {
            permission = permissionService.get(Permission.class, idx);
        }
        return "edit";
    }

    /** 修改权限信息/admin/common/platform/PermissionAction!update.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010403", name = "权限修改", supportedCode = "010402")
    public String update() {
        Message message = permissionService.updatePermission(permission);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
        }

        permission = (Permission) message.getReturnData();
        String info = message.getInfo();
        if (PlatformUtils.hasText(info)) {
            LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_PERMISSION, SubsystemType.OTHER, OperatorAction.MODIFY, LogType.INFO, permission.getCode(), info, null, 0L);
            LOGGER.infoDB(log);
        }
        return "update";
    }

    public List<? extends Dictionary> getWhetherStateList() {
        return ConfigManager.getInstance().getDictionary(WhetherState.class);
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public List<? extends Dictionary> getSystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SystemType.class);
    }
}
