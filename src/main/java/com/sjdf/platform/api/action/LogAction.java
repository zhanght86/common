package com.sjdf.platform.api.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.bean.LogBaseBean;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.log.service.LogService;
import com.sjdf.platform.log.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 日志对外接口
 * User: ketqi
 * Date: 2013-05-21 13:13
 */
public class LogAction extends BaseAction {
    private static final long serialVersionUID = 8339369468646649485L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LogAction.class);
    @Autowired
    private LogService logService;
    private LogBean logger;
    private List<? extends LogBaseBean> logList;
    private LogVo log;

    /**
     * /api/common/LogAction!api.action
     * 日志接口
     */
    public String api() {
        // 连接密码
        try {
            String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);

            // 验证数据有效性
            StringBuilder valid = new StringBuilder(CommonPlatformConstant.LENGTH_64);
            valid.append(connpwd).append(vertime);
            if (!MD5.md5(valid.toString()).equals(vercode)) {
                printWrite("status:-1;result:连接密码验证失败");
                return NONE;
            }

            logService.saveOrUpdate(logger);
            printWrite("status:0");
        } catch (Exception e) {
            LOGGER.error("api", e);
        }

        return NONE;
    }

    /**
     * 接口获取日志
     * /api/common/LogAction!findLogList.action
     *
     * @return result
     */
    public String findLogList() {
        try {
            String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            // 验证数据有效性
            StringBuilder valid = new StringBuilder(CommonPlatformConstant.LENGTH_64);
            valid.append(connpwd).append(vertime);
            if (!MD5.md5(valid.toString()).equals(vercode)) {
                printWrite(createXML(InterfaceRetCode.ERROR, "连接密码验证失败"));
            } else {
                List<LogBean> logList = logService.list(log, page);
                printWrite(createXML(InterfaceRetCode.SUCCESS, page.toXml() + LogBean.toXml(logList), "success"));
            }
        } catch (Exception e) {
            LOGGER.error("findLogList", e);
            printWrite(createXML(InterfaceRetCode.ERROR, e.getMessage()));
        }

        return NONE;
    }

    /**
     * 接口修改资源ID
     * /api/common/LogAction!modifyResourceId.action
     *
     * @return result
     */
    public String modifyResourceId() {
        try {
            String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            // 验证数据有效性
            StringBuilder valid = new StringBuilder(CommonPlatformConstant.LENGTH_64);
            valid.append(connpwd).append(vertime);
            if (!MD5.md5(valid.toString()).equals(vercode)) {
                printWrite(createXML(InterfaceRetCode.ERROR, "连接密码验证失败"));
            } else {
                Message message = logService.updateResourceId(logger);
                if (message.hasErrorMessage()) {
                    printWrite(createXML(InterfaceRetCode.ERROR, message.getErrorMessage()));
                } else {
                    printWrite(createXML(InterfaceRetCode.SUCCESS, "success"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("modifyResourceId", e);
            printWrite(createXML(InterfaceRetCode.ERROR, e.getMessage()));
        }

        return NONE;
    }

    /**
     * /api/common/LogAction!log.action
     * 日志搜索
     */
    public String log() {
        logList = logService.list(log, page);
        return "log";
    }

    /**
     * /api/common/LogAction!history.action
     * 日志搜索
     */
    public String history() {
        logList = logService.listHistory(log, page);
        return "log";
    }

    /**
     * /api/common/LogAction!list.action
     * 日志搜索
     */
    public String list() {
        logList = logService.list(log, page);
        return "list";
    }

    public LogBean getLogger() {
        return logger;
    }

    public void setLogger(LogBean logger) {
        this.logger = logger;
    }

    public List<? extends LogBaseBean> getLogList() {
        return logList;
    }

    public LogVo getLog() {
        return log;
    }

    public void setLog(LogVo log) {
        this.log = log;
    }

    // 系统类型
    public List<? extends Dictionary> getSystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SystemType.class);
    }

    // 子系统类型
    public List<? extends Dictionary> getSubsystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SubsystemType.class);
    }

    // 功能大类
    public List<? extends Dictionary> getFunctionClassList() {
        return ConfigManager.getInstance().getDictionary(FunctionClass.class);
    }

    // 功能小类
    public List<? extends Dictionary> getFunctionTypeList() {
        return ConfigManager.getInstance().getDictionary(FunctionType.class);
    }

    // 操作动作
    public List<? extends Dictionary> getOperatorActionList() {
        return ConfigManager.getInstance().getDictionary(OperatorAction.class);
    }

    // 日志类型
    public List<? extends Dictionary> getLogTypeList() {
        return ConfigManager.getInstance().getDictionary(LogType.class);
    }
}
