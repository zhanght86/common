package com.sjdf.platform.rbac.action.grant;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.rbac.bean.Role;
import com.sjdf.platform.rbac.bean.TreeNode;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 处理用户分配角色action
 * User: ketqi
 * Date: 2013-04-15 13:47
 */
public class UserXRoleAction extends BaseAction {
    private static final long serialVersionUID = 8891195489831435092L;

    @Autowired
    private PermissionService permissionService;
    /** 角色列表 */
    private List<TreeNode<Role>> roleList;
    /** 待分配的角色id */
    private long roleId;

    /** 分配角色时先展示角色;URL:admin/common/platform/userXrole!beforeGrant.action */
    @SuppressWarnings("unchecked")
    @com.sjdf.platform.common.annotations.Permission(code = "06011001", name = "用户分配角色-数据准备")
    public String beforeGrant() {
        //参数检查
        User currentUser = UserHelper.getCurrentLoginUser();
        Message message = permissionService.listGrantableRoleForUser(currentUser, idx);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
            return "-1";
        }

        roleList = (List<TreeNode<Role>>) message.getReturnData();
        return "grant";
    }

    /** 分配角色;URL:admin/common/platform/userXrole!grant.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06011002", name = "用户分配角色", supportedCode = "011001")
    public String grant() {
        Message message = permissionService.addRole4ChildUser(idx, roleId);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite(message.getReturnData().toString());
        }
        return NONE;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public List<TreeNode<Role>> getRoleList() {
        return roleList;
    }
}
