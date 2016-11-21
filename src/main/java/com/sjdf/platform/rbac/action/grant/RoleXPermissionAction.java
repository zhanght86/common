package com.sjdf.platform.rbac.action.grant;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.rbac.bean.TreeNode;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色分配权限action
 * User: ketqi
 * Date: 2013-04-15 13:47
 */
public class RoleXPermissionAction extends BaseAction {
    private static final long serialVersionUID = -195659662799471575L;

    @Autowired
    protected PermissionService permissionService;
    /** id数组 */
    private long[] ids;
    /** adminUser的主键 */
    private long adminId;
    /** 权限树列表 */
    private List<TreeNode> permissionList;
    /** 具体的权限值id列表 */
    private long[] permissionIds;


    /** 根据当前待分析的角色取得准备分析的权限树列表:admin/common/platform/rolexpermission!beforeGrant.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010901", name = "角色分配权限-准备数据")
    public String beforeGrant() {
        permissionList = permissionService.listGrantablePermissionForRole(idx);
        return "grant";
    }

    /** 角色分配权限树:admin/common/platform/rolexpermission!grant.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010902", name = "角色分配权限", supportedCode = "010901")
    public String grant() {
        Message message = permissionService.addPermission4Role(idx, ids, permissionIds);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite("success");
        }
        return NONE;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public List<TreeNode> getPermissionList() {
        return permissionList;
    }

    public long[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(long[] permissionIds) {
        this.permissionIds = permissionIds;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }
}
