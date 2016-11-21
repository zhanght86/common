package com.sjdf.platform.rbac.action.grant;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限组与权限分配action
 * User: ketqi
 * Date: 2013-04-15 13:47
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class PermissionNodeXPermissionAction extends BaseAction {
    private static final long serialVersionUID = -8564269779132215807L;

    @Autowired
    private PermissionService permissionService;
    /** id数组 */
    private long[] ids;

    /** 对权限结点分配权限:admin/common/platform/GrantPermissionAction!grant.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010801", name = "权限组分配权限")
    public String grant() {
        Message message = permissionService.addPermission(idx, ids);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite("success");
        }
        return NONE;
    }

    /** 将指定的权限从当前所在的权限结点上移除:admin/common/platform/GrantPermissionAction!remove.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010802", name = "权限组取消权限")
    public String remove() {
        Message message = permissionService.deletePermission(idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            printWrite("success");
        }
        return NONE;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }
}
