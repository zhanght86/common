package com.sjdf.platform.autotask.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.autotask.BaseAutoTask;
import com.sjdf.platform.autotask.bean.AutoTaskTracker;
import com.sjdf.platform.autotask.helper.AutoTaskHelper;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.AutoTaskType;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Create at 2013年8月8日 下午4:45:16
 * 请求vo
 *
 * @author KETQI
 */
public class RequestVo {
    // 更新自动任务类别和状态标识
    public static final int UPDATE_AUTO_TASK_TYPE = 1;
    // 添加自动任务跟踪记录标识
    public static final int ADD_AUTO_TASK_TRACKER = 2;

    private int flag;// 处理标识
    private Map<String, String> dataMap;// 传输的数据

    public RequestVo(int flag, Map<String, String> dataMap) {
        this.flag = flag;
        this.dataMap = dataMap;
    }

    /** 发送自动任务名称,类别,状态等信息 */
    public RequestVo() {
        flag = UPDATE_AUTO_TASK_TYPE;
        dataMap = new HashMap<>();
        int index = 0;
        for (Map.Entry<Class<? extends BaseAutoTask>, AutoTaskType> entry : AutoTaskHelper.getAllAutoTaskMap().entrySet()) {
            dataMap.put("autoTaskVoList[" + index + "].name", entry.getKey().getSimpleName());
            dataMap.put("autoTaskVoList[" + index + "].type", String.valueOf(entry.getValue().getAttr()));
            dataMap.put("autoTaskVoList[" + index + "].status", String.valueOf(AutoTaskHelper.inExecutJobMap(entry.getKey())));
            index++;
        }
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        dataMap.put("vertime", vertime);
        dataMap.put("vercode", MD5.md5(connPwd + vertime));
    }

    /**
     * 发送自动任务监控数据
     *
     * @param autoTaskTracker 监控数据
     */
    public RequestVo(AutoTaskTracker autoTaskTracker) {
        flag = ADD_AUTO_TASK_TRACKER;
        dataMap = autoTaskTracker.toMap();

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        dataMap.put("vertime", vertime);
        dataMap.put("vercode", MD5.md5(new StringBuilder(CommonPlatformConstant.LENGTH_100).append(autoTaskTracker.getSystemType()).append(connPwd).append(autoTaskTracker.getAutoTaskType()).append(vertime).toString()));
    }

    public int getFlag() {
        return flag;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }
}
