package com.sjdf.platform.rbac.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.api.vo.AuthenticateVo;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.helper.ResourceBundleHelper;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.common.CommonGlobalConfig;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.cache.UserCache;

import java.util.*;

/**
 * User: ketqi
 * Date: 2013-07-23 10:55
 * <p/>
 * 认证管理器
 */
public final class AuthenticateManager {
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());

    private AuthenticateManager() {
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final AuthenticateManager INSTANCE = new AuthenticateManager();
    }

    public static AuthenticateManager getInstance() {
        return AuthenticateManager.SingletonHolder.INSTANCE;
    }

    /**
     * 当前系统中会员信息验证
     *
     * @param userName 会员名
     * @param password 密码(AES.encrypt(MD5.md5(password)))
     */
    public AuthenticateVo auth(String userName, String password) {
        return auth(SystemType.getCurrentSystemType(), userName, password);
    }

    /**
     * 会员信息验证
     *
     * @param systemType 当前会员所在的系统类型
     * @param userName   会员名
     * @param password   密码(AES.encrypt(MD5.md5(password)))
     */
    public AuthenticateVo auth(long systemType, String userName, String password) {
        // 在缓存中认证
        User user = UserCache.getInstance().get(systemType, userName, password);
        if (user != null && !"1".equals(ConfigManager.getInstance().getValue(CommonGlobalConfig.class, CommonGlobalConfig.REAL_TIME_RBAC_AUTH))) {
            //向session中添加用户信息,防止同一用户多个session的问题
            UserHelper.createCurrentLoginUser(user);
            return AuthenticateVo.createSuccessVo("", user);
        }

        AuthenticateVo authenticateVo = createOrGetUser(systemType, userName, password);
        if (authenticateVo.isBool()) {
            UserCache.getInstance().cacheUser(authenticateVo.getUser());
            UserHelper.createCurrentLoginUser(authenticateVo.getUser());
        }
        return authenticateVo;
    }

    /**
     * @param systemType 当前会员所在的系统类型
     * @param userName   会员名
     * @param password   密码(AES.encrypt(MD5.md5(password)))
     *                   会员信息验证
     * @param vertime    时间戳
     * @param vercode    验证码(MD5.md5(systemType + userName + pwd + connPwd + vertime))
     * @return 权限认证结果vo
     */
    public AuthenticateVo auth(long systemType, String userName, String password, String vertime, String vercode) {
        // 当前正在处理的权限所属平台
        SystemType type = ConfigManager.getInstance().getDictionary(SystemType.class, systemType);
        if (type == null) {
            return AuthenticateVo.cteateFailVo(ResourceBundleHelper.getInstance().getText("message.template.systemType.invalid"));
        }

        // 用户名
        if (!PlatformUtils.hasText(userName)) {
            return AuthenticateVo.cteateFailVo(ResourceBundleHelper.getInstance().getText("login.user.username.empty"));
        }

        // 密码
        if (!PlatformUtils.hasText(password)) {
            return AuthenticateVo.cteateFailVo(ResourceBundleHelper.getInstance().getText("login.user.password.empty"));
        }

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        builder.append(systemType);
        builder.append(userName);
        builder.append(password);
        builder.append(connPwd);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            return AuthenticateVo.cteateFailVo(ResourceBundleHelper.getInstance().getText("common.checkSum.fail"));
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return AuthenticateVo.cteateFailVo(ResourceBundleHelper.getInstance().getText("common.date.format.invalidate"));
        }

        // 有效时间校验,5分钟失效
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return AuthenticateVo.cteateFailVo("common.current.request.fail");
        }

        return auth(systemType, userName, password);
    }

    /**
     * 重置密码
     * vercode = MD5(username + conPwd + pwd + vertime)
     *
     * @param username 会员名称
     * @param pwd      新密码(加密之后的密码)
     * @return 消息组件
     */
    public Message resetPwd(String username, String pwd) {
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append(username);
        builder.append(connPwd);
        builder.append(pwd);
        builder.append(vertime);

        String prefix = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PLATFORM_API_PREFIX_URL);
        prefix += "PermissionAction!resetPwd.action";

        Map<String, String> postMap = new HashMap<>();
        postMap.put("tipMessage", username);
        postMap.put("xml", pwd);
        postMap.put("vertime", vertime);
        postMap.put("vercode", MD5.md5(builder.toString()));

        HttpSocket socket = new HttpSocket(prefix, postMap);
        try {
            socket.doPost();
        } catch (Exception ignore) {
            ignore.printStackTrace();
            logger.error("send reset password info to common fail", ignore);
            return Message.createMessage("common.message", ignore.getMessage());
        }

        String result = socket.getResponseData();
        Map<String, String> map = PlatformUtils.parse2Map(result);
        if (!"true".equals(map.get("bool"))) {
            return Message.createMessage("common.message", map.get("message"));
        }

        return Message.createEmptyMessage();
    }

    /**
     * 获取权限信息
     *
     * @param systemType 系统类型
     * @return 权限列表
     */
    public List<Permission> getPermissionList(long systemType) {
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PERMISSION_GETALL_URL);
        String vertime = DateUtils.formatDateTimestamp(new Date());

        // vercode = MD5.md5(systemType + "getAll" + connPwd + vertime)
        StringBuilder vercode = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        vercode.append(systemType);
        vercode.append("getAll");
        vercode.append(connPwd);
        vercode.append(vertime);

        Map<String, String> postData = new HashMap<>();
        postData.put("idx", String.valueOf(systemType));// 系统类型
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(vercode.toString()));

        HttpSocket socket = new HttpSocket(url, postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("send request to common fail", e);
            return Collections.emptyList();
        }

        String result = socket.getResponseData();
        if (!result.startsWith("<?xml")) {
            logger.info("common platform return result:" + result);
            return Collections.emptyList();
        }

        return PermissionHelper.parse(result);
    }

    /**
     * 获取权限信息
     *
     * @return 权限列表
     */
    public List<Permission> getPermissionList() {
        return getPermissionList(SystemType.getCurrentSystemType());
    }

    /**
     * 创建或者获取用户信息
     *
     * @param userName 会员名
     * @param password 密码(AES.encrypt(MD5.md5(password)))
     * @return 权限认证结果vo
     */
    public AuthenticateVo createOrGetUser(String userName, String password) {
        return createOrGetUser(SystemType.getCurrentSystemType(), userName, password);
    }

    /**
     * 创建或者获取用户信息
     *
     * @param systemType 当前会员所在的系统类型
     * @param userName   会员名
     * @param password   密码(AES.encrypt(MD5.md5(password)))
     * @return 权限认证结果vo
     */
    public AuthenticateVo createOrGetUser(long systemType, String userName, String password) {
        /**
         * <pre>
         * 到common平台认证
         * api/common/AuthenticateAction!auth.action
         * vercode = MD5.md5(idx + userName + pwd + connPwd + vertime)
         * 参数如下:
         * idx:systemType
         * userName
         * pwd(AES.encrypt(MD5.md5(pwd)))
         * vertime
         * vercode
         * </pre>
         */
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_AUTHENTICATE_URL);
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());

        StringBuilder vercode = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        vercode.append(systemType);
        vercode.append(userName);
        vercode.append(password);
        vercode.append(connPwd);
        vercode.append(vertime);

        Map<String, String> postMap = new HashMap<>();
        postMap.put("idx", String.valueOf(systemType));
        postMap.put("userName", userName);
        postMap.put("pwd", password);
        postMap.put("vertime", vertime);
        postMap.put("vercode", MD5.md5(vercode.toString()));

        HttpSocket socket = new HttpSocket(url, postMap);
        try {
            socket.doPost();
        } catch (Exception ignore) {
            ignore.printStackTrace();
            logger.error("send authenticate info to common fail", ignore);
            return AuthenticateVo.cteateFailVo(ignore.getMessage());
        }
        String xml = socket.getResponseData();

        return AuthenticateVo.parse(xml);
    }
}
