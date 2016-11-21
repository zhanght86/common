package com.sjdf.platform.rbac.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.Role;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 14:24
 * 角色action, 处理角色创建
 */
public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 5361100612170067769L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(RoleAction.class);

    @Autowired
    private PermissionService permissionService;
    /** 所操作的角色对象 */
    private Role role;
    /** 返回的角色信息列表 */
    private List<Role> roleList;

    /** 列出角色信息:admin/common/platform/RoleAction!list.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010601", name = "角色列表")
    public String list() {
        //idx为上级id
        roleList = permissionService.listRoleTree(idx);
        return "list";
    }

    /** 添加新角色:admin/common/platform/RoleAction!add.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010602", name = "添加角色", supportedCode = "010606")
    public String add() {
        Message message = permissionService.addRole(role);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            saveLog(OperatorAction.ADD, LogType.INFO, role.getName(), message.getInfo(), null);
            printWrite(String.valueOf(role.getId()));
        }
        return NONE;
    }

    /** 删除角色信息:admin/common/platform/RoleAction!delete.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010603", name = "删除角色")
    public String delete() {
        Message message = permissionService.deleteRole(idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            role = (Role) message.getReturnData();
            saveLog(OperatorAction.DELETE, LogType.INFO, role.getName(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /** 修改角色信息,获取信息:admin/common/platform/RoleAction!edit.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010604", name = "编辑角色")
    public String edit() {
        if (idx > 0) {
            role = permissionService.get(Role.class, idx);
        }
        return "edit";
    }

    /** 修改角色信息:admin/common/platform/RoleAction!update.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010605", name = "修改角色", supportedCode = "010604")
    public String update() {
        Message message = permissionService.updateRole(role);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            role = (Role) message.getReturnData();
            String info = message.getInfo();
            if (PlatformUtils.hasText(info)) {
                saveLog(OperatorAction.MODIFY, LogType.INFO, role.getName(), info, null);
            }

            printWrite(SUCCESS);
        }
        return NONE;
    }

    /** 新加角色:admin/common/platform/RoleAction!beforeAdd.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010606", name = "角色添加页面")
    public String beforeAdd() {
        if (idx <= 0 || (role = permissionService.get(Role.class, idx)) == null) {
            role = null;
        }
        return "add";
    }

    /** 显示一个角色信息:admin/common/platform/RoleAction!show.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010607", name = "角色详情")
    public String show() {
        if (idx > 0) {
            role = permissionService.getRole(idx);
        }
        return "show";
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_ROLE, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}
