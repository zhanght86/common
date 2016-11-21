package com.sjdf.platform.message.cache;

import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.message.bean.SendApiConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 发送接口配置缓存
 * User: ketqi
 * Date: 2015-07-03 16:14
 */
public final class SendApiConfigCache {
    private ConcurrentMap<Long, SendApiConfig> cache;

    private SendApiConfigCache() {
        cache = new ConcurrentHashMap<>();
        init();
    }

    /** 单例 */
    private static class SingleHolder {
        private static final SendApiConfigCache INSTANCE = new SendApiConfigCache();
    }

    public static SendApiConfigCache getInstance() {
        return SingleHolder.INSTANCE;
    }

    private void init() {
        BaseService baseService = (BaseService) ApplicationContextManager.getBean("commonBaseService");
        List<SendApiConfig> configList = baseService.listAll(SendApiConfig.class);
        if (configList != null && !configList.isEmpty()) {
            for (SendApiConfig config : configList) {
                put(config);
            }
        }
    }

    public SendApiConfig get(long idx) {
        return cache.get(idx);
    }

    public String getName(long idx) {
        SendApiConfig config = get(idx);
        return config != null ? config.getName() : "";
    }

    public SendApiConfig put(SendApiConfig config) {
        if (config == null) {
            return null;
        }

        return cache.put(config.getId(), config);
    }

    public void remove(SendApiConfig config) {
        if (config != null) {
            cache.remove(config.getId());
        }
    }

    /**
     * 获取接口配置列表
     *
     * @param messageType 消息类型
     * @return 列表
     */
    public List<SendApiConfig> getConfigList(long messageType) {
        List<SendApiConfig> list = new ArrayList<>(cache.size());
        if (messageType == 0L) {
            list.addAll(cache.values());
            return list;
        }

        for (SendApiConfig config : cache.values()) {
            if (config.getMessageType() == messageType) {
                list.add(config);
            }
        }
        return list;
    }
}
