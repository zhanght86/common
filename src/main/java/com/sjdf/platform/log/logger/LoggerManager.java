package com.sjdf.platform.log.logger;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.*;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.bean.LogUser;
import com.sjdf.platform.log.vo.LogVo;
import com.sjdf.platform.net.HttpSocket;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 日志接口管理工具
 *
 * @author 王正伟
 */
public class LoggerManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LoggerManager.class);
    private static LoggerManager instance = new LoggerManager();

    public static LoggerManager getInstance() {
        return instance;
    }

    /**
     * 查询日志
     *
     * @param vo   查询条件
     * @param page 分页组件
     * @return 日志列表
     */
    public List<LogBean> list(LogVo vo, Page page) {
        String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String vercode = MD5.md5(connpwd + vertime);

        Map<String, String> postData = new HashMap<>();
        postData.put("vertime", vertime);
        postData.put("vercode", vercode);
        postData.put("page.currentPage", String.valueOf(page.getCurrentPage()));
        postData.put("page.pageSize", String.valueOf(page.getPageSize()));
        if (vo != null) {
            postData.putAll(vo.wrapPostData());
        }

        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.SJDF_COMMON_PLATFORM_API_PREFIX_URL) + "LogAction!findLogList.action";

        HttpSocket socket = new HttpSocket(url, postData);
        try {
            socket.doPost();
            String result = socket.getResponseData();
            List<LogBean> logList = new ArrayList<>(page.getPageSize());
            LogBean.parseXmlToList(result, logList, page);
            return logList;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * @param log 记录info日志
     */
    public void info(LogBean log) {
        log.setLogType(LogType.INFO);

        // 通过完整类名和方法名确认操作信息
        setOperationAttribute(log);

        // 操作日志为空，直接返回不记录日志
        if (!PlatformUtils.hasText(log.getOperationalContent())) {
            return;
        }

        // 判断logBean设置是否完整，如果不完整，跑出异常
        log.validateAttribute();

        // 发送数据
        doSocket(log);
    }

    /**
     * @param log 记录error日志
     */
    public void error(LogBean log) {
        log.setLogType(LogType.ERROR);

        // 通过完整类名和方法名确认操作信息
        setOperationAttribute(log);

        // 操作日志为空，直接返回不记录日志
        if (!PlatformUtils.hasText(log.getOperationalContent())) {
            return;
        }

        // 判断logBean设置是否完整，如果不完整，跑出异常
        log.validateAttribute();

        // 发送数据
        doSocket(log);
    }

    /**
     * 通过配置库查询得到操作函数并设置
     *
     * @param logBean 设定文件
     */
    private void setOperationAttribute(LogBean logBean) {
        // 通过配置库得到操作信息
        // 得到完整类名，方法名，用来设置功能大类，小类机操作动作(注意：如果上面的值 已经存在，说明已经认为设置，则这里不用设置)
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        String clz;
        String method;
        int length = stack.length;
        if (length > CommonPlatformConstant.LENGTH_2) {
            clz = stack[CommonPlatformConstant.LENGTH_2].getClassName();
            method = stack[CommonPlatformConstant.LENGTH_2].getMethodName();
        } else {
            clz = stack[CommonPlatformConstant.LENGTH_2].getClassName();
            method = stack[CommonPlatformConstant.LENGTH_2].getMethodName();
        }

        OperatorFunction dictionary = ConfigManager.getInstance().getDictionaryByVal(OperatorFunction.class, clz + "." + method);
        if (dictionary != null) {
            // 如果操作类型值为空，则自动设置
            if (dictionary.getFunctionClass() != null) {
                logBean.setFunctionClass(dictionary.getFunctionClass().getAttr());
            }
            if (dictionary.getRef() != null) {
                logBean.setFunctionType(dictionary.getRef().getAttr());
            }
            if (dictionary.getOperateAction() != null) {
                logBean.setOperatorAction(dictionary.getOperateAction().getAttr());
            }
            if (dictionary.getSubsystem() != null) {
                logBean.setSubsystemType(dictionary.getSubsystem().getAttr());
            }
        }
    }

    /**
     * @param log 发送数据
     */
    private void doSocket(LogBean log) {
        Map<String, String> postData = new HashMap<>();
        if (!StringUtils.hasText(log.getCreateUser())) {
            log.setCreateUser("system");
        }
        if (!StringUtils.hasText(log.getUpdateUser())) {
            log.setUpdateUser("system");
        }

        postData.put("logger.createUser", log.getCreateUser());
        postData.put("logger.updateUser", log.getUpdateUser());

        // 存放logUser对象的值
        postData.put("logger.logUser.systemType", String.valueOf(log.getLogUser().getSystemType()));
        postData.put("logger.logUser.loginUser", log.getLogUser().getLoginUser());
        postData.put("logger.logUser.company", log.getLogUser().getCompany());
        postData.put("logger.logUser.ipAddress", log.getLogUser().getIpAddress());

        postData.put("logger.id", String.valueOf(log.getId()));
        postData.put("logger.sourceProductId", String.valueOf(log.getSourceServerId()));
        postData.put("logger.sourceServerId", String.valueOf(log.getSourceServerId()));
        postData.put("logger.targetServerId", String.valueOf(log.getTargetServerId()));
        postData.put("logger.srcProductName", log.getSrcProductName());
        postData.put("logger.targetProductName", log.getTargetProductName());
        postData.put("logger.status", String.valueOf(log.getStatus()));
        postData.put("logger.notify", String.valueOf(log.getNotify()));
        postData.put("logger.ipType", String.valueOf(log.getIpType()));
        postData.put("logger.type", String.valueOf(log.getType()));
        postData.put("logger.step", String.valueOf(log.getStep()));
        postData.put("logger.counter", String.valueOf(log.getCounter()));
        postData.put("logger.whether", String.valueOf(log.getWhether()));
        postData.put("logger.message", log.getMessage());

        // 存放操作LogFunction的值
        postData.put("logger.functionClass", String.valueOf(log.getFunctionClass()));
        postData.put("logger.functionType", String.valueOf(log.getFunctionType()));
        postData.put("logger.operatorAction", String.valueOf(log.getOperatorAction()));

        // 其他值
        postData.put("logger.subsystemType", String.valueOf(log.getSubsystemType()));
        postData.put("logger.resourceId", log.getResourceId());
        postData.put("logger.operationalContent", log.getOperationalContent());
        postData.put("logger.logType", String.valueOf(log.getLogType()));
        postData.put("logger.errorInfo", log.getErrorInfo());
        postData.put("logger.showType", String.valueOf(log.getShowType()));
        postData.put("logger.handler", log.getHandler());

        // 验证
        String vertime = DateUtils.formatTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(new StringBuilder(connpwd).append(vertime).toString()));

        final HttpSocket socket = new HttpSocket(ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.LOGGER_SAVE_URL), postData);
        new Thread() {
            @Override
            public void run() {
                try {
                    socket.doPost();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }.start();
    }

    public LogUser getLogUser() {
        LogUser logUser = new LogUser();
        HttpServletRequest request;
        try {
            request = ServletActionContext.getRequest();
        } catch (Exception e) {
            // 未能从web环境中取值，返回测试数据
            return logUser;
        }

        if (request != null) {
            LogUser user = (LogUser) request.getSession().getAttribute(LogUser.KEY);
            // 从session中获取不到logUser，显示异常信息
            if (user != null) {
                logUser = user;
            }
        }

        return logUser;
    }

    /**
     * 通过资源ID获取购买日志记录
     *
     * @param resourceId 资源ID
     * @return 日志
     */
    public LogBean findBuyLogByResourceId(String resourceId) {
        try {
            Map<String, String> postData = new HashMap<>();

            postData.put("log.functionType", String.valueOf(FunctionType.BUY));
            postData.put("log.resourceId", resourceId);

            // 验证
            String vertime = DateUtils.formatTimestamp(new Date());
            String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            postData.put("vertime", vertime);
            postData.put("vercode", MD5.md5(new StringBuilder(connpwd).append(vertime).toString()));

            HttpSocket socket = new HttpSocket();
            socket.setUrl("http://common.51web.com/api/common/LogAction!findLogList.action");
            socket.setPostData(postData);
            socket.doPost();
            String result = socket.getResponseData();

            List<LogBean> logList = LogBean.parseXmlToList(result);
            if (!logList.isEmpty()) {
                return logList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改资源ID
     *
     * @param pkId          日志主键ID
     * @param newResourceId 新的资源ID
     * @return 消息组件
     */
    public Message modifyBuyLogResourceIdById(long pkId, String newResourceId) {
        try {
            Map<String, String> postData = new HashMap<>();

            postData.put("logger.id", String.valueOf(pkId));
            postData.put("logger.resourceId", newResourceId);

            // 验证
            String vertime = DateUtils.formatTimestamp(new Date());
            String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            postData.put("vertime", vertime);
            postData.put("vercode", MD5.md5(new StringBuilder(connpwd).append(vertime).toString()));

            HttpSocket socket = new HttpSocket();
            socket.setUrl("http://common.51web.com/api/common/LogAction!modifyResourceId.action");
            socket.setPostData(postData);
            socket.doPost();
            String result = socket.getResponseData();
            LogBean.parseXmlToList(result);
            return Message.createEmptyMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建日志
     *
     * @param logUser            操作人员
     * @param functionClass      功能大类
     * @param functionType       功能小类
     * @param subsystemType      子系统类型
     * @param operatorAction     操作动作
     * @param logType            日志记录类型
     * @param resourceId         资源id
     * @param operationalContent 操作内容或者异常信息
     * @param showType           日志显示类型
     * @return 日志
     * @see com.sjdf.platform.dictionary.bean.FunctionClass
     * @see com.sjdf.platform.dictionary.bean.FunctionType
     * @see com.sjdf.platform.dictionary.bean.SubsystemType
     * @see com.sjdf.platform.dictionary.bean.OperatorAction
     * @see com.sjdf.platform.dictionary.bean.LogType
     * @see com.sjdf.platform.dictionary.bean.LogShowType
     */
    public LogBean createLog(String logUser, long functionClass, long functionType, long subsystemType, long operatorAction, long logType, String resourceId, String operationalContent, long showType) {
        return createLog(logUser, functionClass, functionType, subsystemType, operatorAction, logType, resourceId, operationalContent, "", showType);
    }

    /**
     * 创建日志
     *
     * @param logUser            操作人员
     * @param functionClass      功能大类
     * @param functionType       功能小类
     * @param subsystemType      子系统类型
     * @param operatorAction     操作动作
     * @param logType            日志记录类型
     * @param resourceId         资源id
     * @param operationalContent 操作内容或者异常信息
     * @param showType           日志显示类型
     * @param errorInfo          错误信息
     * @return 日志
     * @see com.sjdf.platform.dictionary.bean.FunctionClass
     * @see com.sjdf.platform.dictionary.bean.FunctionType
     * @see com.sjdf.platform.dictionary.bean.SubsystemType
     * @see com.sjdf.platform.dictionary.bean.OperatorAction
     * @see com.sjdf.platform.dictionary.bean.LogType
     * @see com.sjdf.platform.dictionary.bean.LogShowType
     */
    public LogBean createLog(String logUser, long functionClass, long functionType, long subsystemType, long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo, long showType) {
        LogBean logBean = new LogBean();
        if (PlatformUtils.hasText(logUser)) {
            LogUser user = new LogUser();
            user.setSystemType(SystemType.getCurrentSystemType());
            user.setLoginUser(logUser);
            user.setIpAddress(Tools.getIpAddress());
            logBean.setLogUser(user);
        }
        logBean.setSubsystemType(subsystemType);
        logBean.setFunctionClass(functionClass);
        logBean.setFunctionType(functionType);
        logBean.setOperatorAction(operatorAction);
        logBean.setResourceId(resourceId);
        if (PlatformUtils.hasText(operationalContent)) {
            logBean.setOperationalContent(operationalContent);
        } else {
            logBean.setOperationalContent(" ");
        }
        if (PlatformUtils.hasText(errorInfo)) {
            logBean.setErrorInfo(errorInfo);
        }
        logBean.setLogType(logType);
        if (showType != 0) {
            logBean.setShowType(showType);
        }

        // 日志类型为错误则设置显示类型为默认状态（0）
        if (logType == LogType.ERROR) {
            logBean.setShowType(0);
        }

        return logBean;
    }
}
