package com.sjdf.platform.common.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.api.vo.AuthenticateVo;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.rbac.helper.AuthenticateManager;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User: ketqi Date: 2013-04-16 10:20
 * <p/>
 * 用户会话拦截器, 如果此用户在指定需要用户信息的菜单中未登陆, 则拒绝其进一步访问
 */
public class UserSessionInterceptor extends MethodFilterInterceptor {
    private static final long serialVersionUID = -8346418371552233773L;
    public static final String ADMIN_NAMESPACE = "/admin";

    protected String doIntercept(ActionInvocation invocation) throws Exception {
        // 进行命名空间判断,只有命名空间为admin/开头的才进行拦截
        String namespace = invocation.getProxy().getNamespace();
        if (!namespace.startsWith(ADMIN_NAMESPACE)) {
            return invocation.invoke();
        }

        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        String reqWith = request.getHeader(CommonPlatformConstant.X_REQUESTED_WITH);
        boolean isAsync = PlatformUtils.hasText(reqWith) && CommonPlatformConstant.XML_HTTP_REQUEST.equalsIgnoreCase(reqWith);

        // 除此之外,除取用户信息,如果不存在,则拦截之
        if (UserHelper.getCurrentLoginUser() == null) {
            // 如果参数列表中有以下参数的值,并且通过验证,则直接通过
            // systemType, userName, pwd, vertime, vercode
            /**
             * <pre>
             * vercode = MD5.md5(systemType + userName + pwd + connPwd + vertime)
             * 参数如下:
             * systemType:系统类型
             * userName
             * pwd(AES.encrypt(MD5.md5(pwd)))
             * vertime
             * vercode
             * </pre>
             */
            Map<String, Object> params = invocation.getInvocationContext().getParameters();
            String[] systemTypes = (String[]) params.get("systemType");
            String[] userNames = (String[]) params.get("userName");
            String[] pwds = (String[]) params.get("pwd");
            String[] vertimes = (String[]) params.get("vertime");
            String[] vercodes = (String[]) params.get("vercode");
            if (systemTypes != null && systemTypes.length > 0 && userNames != null && userNames.length > 0 && pwds != null && pwds.length > 0 && vertimes != null && vertimes.length > 0 && vercodes != null && vercodes.length > 0) {
                long systemType = 0;
                try {
                    systemType = Long.parseLong(systemTypes[0]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                String pwd = pwds[0];
                if (PlatformUtils.hasText(pwd)) {
                    pwd = pwd.replace(' ', '+');
                }

                AuthenticateVo vo = AuthenticateManager.getInstance().auth(systemType, userNames[0], pwd, vertimes[0], vercodes[0]);
                if (vo != null && vo.isBool()) {
                    return invocation.invoke();
                }
            }

            return fail(isAsync);
        }
        return invocation.invoke();
    }

    private String fail(boolean isAsync) {
        if(isAsync){
            AjaxSupport.sendFailText("no session");
            return CommonPlatformConstant.NONE;
        }
        return "userSessionFail";
    }
}
