package com.sjdf.platform.api.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: ketqi Date: 2013-07-22 15:19
 * <p/>
 * 权限Api接口Action
 */
public class PermissionAction extends BaseAction {
    private static final long serialVersionUID = -1549977250538959859L;
    @Autowired
    private PermissionService permissionService;

    // 非common平台发送过来的权限信息
    private String xml;

    /**
     * <pre>
     *     api/common/PermissionAction!collect.action
     * </pre>
     * 非common平台权限信息收集
     *
     * @return String
     */
    public String collect() {
        Message message = permissionService.collect(idx, vercode, vertime, xml);
        if (message.hasErrorMessage()) {
            printWrite(false, getText(message));
            return NONE;
        }

        printWrite(true, "success");
        return NONE;
    }

    /**
     * <pre>
     *     api/common/PermissionAction!getAll.action
     * </pre>
     *
     * @return result 获取指定平台下的所有权限
     */
    public String getAll() {
        // 校验 vercode = MD5.md5(systemType + "getAll" + connPwd + vertime)
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append(idx);
        builder.append("getAll");
        builder.append(connPwd);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            printWrite(getText("common.checkSum.fail"));
            return NONE;
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            printWrite(getText("common.date.format.invalidate"));
            return NONE;
        }

        // 有效时间校验
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            printWrite(getText("common.current.request.fail"));
            return NONE;
        }

        Permission permission = new Permission();
        permission.setSystemType(idx);
        List<Permission> list = permissionService.listPermissionByPage(permission, null);
        printWrite(PermissionHelper.parse(list));
        return NONE;
    }

    /**
     * <pre>
     *     api/common/PermissionAction!resetPwd.action
     * </pre>
     * 重置密码
     *
     * @return result
     */
    public String resetPwd() {
        Message message = permissionService.resetPassword(tipMessage, xml, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(false, getText(message));
            return NONE;
        }

        printWrite(true, "success");
        return NONE;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
