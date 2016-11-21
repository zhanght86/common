package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.apache.struts2.components.ContextBean;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.springframework.util.StringUtils;

import java.io.Writer;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-05-20 16:12
 * 需要指定权限才能访问的
 */
public class PermissionRequired extends ContextBean {
    public static final String PERMISSION_ANSWER = "my.sjdf.platform.Permission.answer";
    /** 权限代码 */
    private String code;

    public PermissionRequired(ValueStack stack) {
        super(stack);
    }

    @StrutsTagAttribute(description = "权限代码,用于判断是否可以访问下一段代码")
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean start(Writer writer) {
        //no code?
        if (!StringUtils.hasText(code)) {
            return false;
        }
        User currentUser = UserHelper.getCurrentLoginUser();
        //no user?
        if (currentUser == null) {
            return false;
        }
        //sys administrator,let it go
        if (currentUser.isSysUser()) {
            return true;
        }
        Permission permission = PermissionHelper.getPermissionByCode(code);
        //no rbac? but we think it no one can access it.
        if (permission == null) {
            return false;
        }
        //if rbac is not the real,get the supported
        if (permission.getSupportedPermission() != null) {
            permission = permission.getSupportedPermission();
        }
        //just check the user's mergedPermissionSet contain it
        Map<String, Permission> permissionSet = currentUser.getMergedPermissionMap();
        boolean result = permission != null && permissionSet.containsKey(permission.getCode());
        getStack().getContext().put(PERMISSION_ANSWER, result);
        return result;
    }
}
