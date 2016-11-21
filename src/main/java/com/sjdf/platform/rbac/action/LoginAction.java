package com.sjdf.platform.rbac.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.bean.LogUser;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.cache.UserCache;
import com.sjdf.platform.rbac.service.PermissionService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-04-22 17:02
 * 登录
 */
public class LoginAction extends BaseAction {
    private static final long serialVersionUID = -6090882572378765283L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LoginAction.class);
    @Autowired
    private PermissionService permissionService;
    private User user;
    private String code;//验证码

    /** 登陆处理;URL:other/common/LoginAction!login.action */
    public String login() {
        //如果用户信息不存在,直接返回登陆界面
        if (user == null) {
            return "input";
        }
        String veryCode = (String) session.getAttribute(com.sjdf.platform.rbac.bean.User.VERIFY_CODE);
        if (!PlatformUtils.hasText(code)) {
            tipMessage = getText("login.user.very.code.empty");
            return "input";
        }
        if (!code.equalsIgnoreCase(veryCode)) {
            tipMessage = getText("login.user.very.code.invalidete");
            return "input";
        }

        /** 取正常的用户信息 */
        Message message = permissionService.getLoginUser(user);
        if (message.hasErrorMessage()) {
            tipMessage = getText(message);
            saveLog(user.getUsername(), FunctionType.MEMBER_LOGIN, OperatorAction.LOGIN, LogType.ERROR, user.getUsername(), "login fail", tipMessage);
            return "input";
        }

        user = (User) message.getReturnData();
        //将用户放入当前session中
        Map<String, Object> session = ServletActionContext.getContext().getSession();
        session.put(User.CURRENT_LOGIN_USER, user);
        session.put(LogUser.KEY, new LogUser(SystemType.EISS_COMMON, user.getUsername(), user.getName(), Tools.getIpAddress()));
        saveLog(user.getUsername(), FunctionType.MEMBER_LOGIN, OperatorAction.LOGIN, LogType.INFO, user.getUsername(), "login success", null);
        return "redirectIndex";
    }

    /** 退出登陆;URL:other/common/LoginAction!logout.action */
    public String logout() {
        Map<String, Object> sesson = ServletActionContext.getContext().getSession();
        user = (User) sesson.remove(User.CURRENT_LOGIN_USER);
        UserCache.getInstance().clearUser(user);
        ServletActionContext.getRequest().getSession(true).invalidate();
        saveLog(user.getUsername(), FunctionType.MEMBER_LOGOUT, OperatorAction.LOGIN, LogType.INFO, user.getUsername(), "logout success", null);
        return "logout";
    }

    private void saveLog(String operator, long functionTYpe, long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(operator, FunctionClass.COMMON_PLATFORM, functionTYpe, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
