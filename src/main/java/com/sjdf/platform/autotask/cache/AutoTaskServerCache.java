package com.sjdf.platform.autotask.cache;

import com.sjdf.platform.autotask.bean.AutoTaskServer;
import com.sjdf.platform.autotask.service.AutoTaskService;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-05-14 10:53
 * 自动任务服务器缓存
 */
public final class AutoTaskServerCache {
    /** 数据缓存:Map<id,AutoTaskServer> */
    private Map<Long, AutoTaskServer> allMap;

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final AutoTaskServerCache INSTANCE = new AutoTaskServerCache();
    }

    private AutoTaskServerCache() {
        ApplicationContext applicationContext = ApplicationContextManager.getApplicationContext();
        AutoTaskService autoTaskService = (AutoTaskService) applicationContext.getBean("commonAutoTaskService");
        List<AutoTaskServer> list = autoTaskService.listAllServer(null, null);
        allMap = new HashMap<>(list.size());

        for (AutoTaskServer server : list) {
            updateCache(server);
        }
    }

    public static AutoTaskServerCache getInstance() {
        return AutoTaskServerCache.SingletonHolder.INSTANCE;
    }

    /**
     * @param server 待更新的服务器信息
     *               更新缓存
     */
    public synchronized void updateCache(AutoTaskServer server) {
        allMap.put(server.getId(), server);
    }

    /**
     * @param idx 待移出对象的主键
     *            移出缓存
     */
    public synchronized void removeCache(long idx) {
        allMap.remove(idx);
    }

    /**
     * @param idx 主键
     * @return 根据id获取自动任务服务器信息
     */
    public AutoTaskServer get(long idx) {
        return allMap.get(idx);
    }

    /**
     * @param remoteAddr ip地址
     * @param remotePort 端口
     * @return 获取自动任务服务器信息
     */
    public AutoTaskServer get(String remoteAddr, int remotePort) {
        for (AutoTaskServer server : allMap.values()) {
            if (server.valid(remoteAddr, remotePort)) {
                return server;
            }
        }
        return null;
    }
}
