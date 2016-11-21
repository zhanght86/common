package com.sjdf.platform.log.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBaseBean;
import com.sjdf.platform.log.service.LogService;
import com.sjdf.platform.log.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 日志管理Action
 *
 * @author 王正伟
 */
public class LogAction extends BaseAction {
    private static final long serialVersionUID = -3611984340514188039L;

    @Autowired
    private LogService logService;
    private LogVo log;
    private List<? extends LogBaseBean> logList;

    /**
     * 日志搜索
     * /admin/common/LogAction!log.action
     *
     * @return result
     */
    public String log() {
        logList = logService.list(log, page);
        return "log";
    }

    /**
     * 历史日志搜索
     * /admin/common/LogAction!history.action
     *
     * @return result
     */
    public String history() {
        logList = logService.listHistory(log, page);
        return "log";
    }

    /**
     * 日志搜索
     * /admin/common/LogAction!search.action
     *
     * @return result
     */
    public String search() {
        return "search";
    }

    /**
     * 日志搜索
     * /admin/common/LogAction!list.action
     *
     * @return result
     */
    public String list() {
        logList = logService.list(log, page);
        return "list";
    }

    public LogVo getLog() {
        return log;
    }

    public void setLog(LogVo log) {
        this.log = log;
    }

    public List<? extends LogBaseBean> getLogList() {
        return logList;
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
