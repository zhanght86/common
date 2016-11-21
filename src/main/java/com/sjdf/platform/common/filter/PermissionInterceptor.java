package com.sjdf.platform.common.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-04-16 09:53
 * 权限拦截器
 */
public class PermissionInterceptor extends MethodFilterInterceptor {
    private static final long serialVersionUID = 6625292305597109495L;

    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //进行命名空间判断,只有命名空间为admin/开头的才进行拦截
        String namespace = invocation.getProxy().getNamespace();
        if (!namespace.startsWith(UserSessionInterceptor.ADMIN_NAMESPACE)) {
            return invocation.invoke();
        }

        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        String reqWith = request.getHeader(CommonPlatformConstant.X_REQUESTED_WITH);
        boolean isAsync = PlatformUtils.hasText(reqWith) && CommonPlatformConstant.XML_HTTP_REQUEST.equalsIgnoreCase(reqWith);

        //系统用户直接放过
        User currentUser = UserHelper.getCurrentLoginUser();
        if (currentUser == null) {
            return fail(isAsync);
        }
        if (currentUser.isSysUser()) {
            return invocation.invoke();
        }

        //如果此方法上没有权限注解,则直接放过
        String methodName = invocation.getProxy().getMethod();
        Class<?> clazz = invocation.getAction().getClass();
        Method method = clazz.getMethod(methodName);
        com.sjdf.platform.common.annotations.Permission p = method.getAnnotation(com.sjdf.platform.common.annotations.Permission.class);
        if (p == null) {
            return invocation.invoke();
        }

        //权限验证
        Map<String, Permission> permissionMap = currentUser.getMergedPermissionMap();
        if (permissionMap != null && !permissionMap.isEmpty()) {
            //根据className和方法名取权限
            Permission permission = PermissionHelper.getPermissionByClassNameAndMethod(clazz.getName(), methodName);
            //如果有supportedPermission存在
            if (permission != null && permission.getSupportedPermission() != null) {
                permission = permission.getSupportedPermission();
            }

            if (permission != null && !permissionMap.containsKey(permission.getCode())) {
                return fail(isAsync);
            }
        } else {
            return fail(isAsync);
        }

        return invocation.invoke();
    }

    private String fail(boolean isAsync) {
        if(isAsync){
            AjaxSupport.sendFailText("no permission");
            return CommonPlatformConstant.NONE;
        }
        return "permissionFail";
    }
}
