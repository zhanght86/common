package com.sjdf.platform.rbac.action;

import com.sjdf.platform.CommonPlatformConstant;
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
import com.sjdf.platform.rbac.bean.PermissionNode;
import com.sjdf.platform.rbac.bean.TreeNode;
import com.sjdf.platform.rbac.helper.UserHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-04-15 14:21
 * 权限组action,处理权限组的创建和分配
 */
public class PermissionNodeAction extends BaseAction {
    private static final long serialVersionUID = -5997262734515191710L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PermissionNodeAction.class);

    @Autowired
    private PermissionService permissionService;
    /** 权限结点 */
    private PermissionNode permissionNode;
    /** 权限结点列表 */
    private List<TreeNode> treeNodeList;
    /** 权限列表 */
    private Map<SystemType, List<Permission>> permissionMap;

    /** 列出权限组:admin/common/platform/PermissionNodeAction!list.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010501", name = "权限组列表")
    public String list() {
        //构造权限结点与权限的结合
        //构建根结点
        if (idx <= 0) {
            treeNodeList = permissionService.listPermissionNode4Permission(idx);
        }

        //取权限列表
        permissionMap = new HashMap<>();
        SystemType systemType;
        List<Permission> permissionList;
        Map<Long, SystemType> systemTypeMap = ConfigManager.getInstance().getDictionaryMap(SystemType.class);
        List<Permission> allPermissionList = permissionService.listPermissionUnGrantedAndNoSupported();
        for (Permission p : allPermissionList) {
            systemType = systemTypeMap.get(p.getSystemType());
            permissionList = permissionMap.get(systemType);
            if (permissionList == null) {
                permissionList = new ArrayList<>(CommonPlatformConstant.LENGTH_1024);
                permissionMap.put(systemType, permissionList);
            }
            permissionList.add(p);
        }
        return "list";
    }

    /** 添加权限组:admin/common/platform/PermissionNodeAction!add.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010502", name = "添加权限组")
    public String add() {
        Message message = permissionService.addPermissionNode(permissionNode);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
            return NONE;
        } else {
            saveLog(OperatorAction.ADD, LogType.INFO, permissionNode.getName(), message.getInfo(), null);
            return "add";
        }
    }

    /** 修改权限组:admin/common/platform/PermissionNodeAction!edit.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010503", name = "编辑权限组")
    public String edit() {
        if (idx > 0) {
            permissionNode = permissionService.get(PermissionNode.class, idx);
        }
        return "edit";
    }

    /** 更新权限组:admin/common/platform/PermissionNodeAction!update.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010504", name = "修改权限组", supportedCode = "010503")
    public String update() {
        Message message = permissionService.updatePermissionNode(permissionNode);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            permissionNode = (PermissionNode) message.getReturnData();
            String info = message.getInfo();
            if (PlatformUtils.hasText(info)) {
                saveLog(OperatorAction.MODIFY, LogType.INFO, permissionNode.getName(), info, null);
            }
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /** 删除权限组:admin/common/platform/PermissionNodeAction!delete.action */
    @com.sjdf.platform.common.annotations.Permission(code = "06010505", name = "删除权限组")
    public String delete() {
        Message message = permissionService.deletePermissionNode(idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            permissionNode = (PermissionNode) message.getReturnData();
            saveLog(OperatorAction.MODIFY, LogType.INFO, permissionNode.getName(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_PERMISSION, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public Map<SystemType, List<Permission>> getPermissionMap() {
        return permissionMap;
    }

    public PermissionNode getPermissionNode() {
        return permissionNode;
    }

    public void setPermissionNode(PermissionNode permissionNode) {
        this.permissionNode = permissionNode;
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }
}
