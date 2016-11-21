package com.sjdf.platform.propconfig.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigBeanCnNameCache {

    private static final ConfigBeanCnNameCache INSTANCE = new ConfigBeanCnNameCache();

    /**
     * 配置类的<value,name>
     */
    private static final Map<String, String> ENTITY_CACHE = new ConcurrentHashMap<>();
    /**
     * 属性配置的属性名和配置的中文名缓存
     */
    private static final Map<String, String> FIELD_CACHE = new ConcurrentHashMap<>();

    private ConfigBeanCnNameCache() {

    }

    public static ConfigBeanCnNameCache getInstance() {
        return INSTANCE;
    }


    public void putEntity(String key, String value) {
        ENTITY_CACHE.put(key, value);
    }

    public String getEntity(String key) {
        return ENTITY_CACHE.get(key);
    }

    public void putField(String key, String value) {
        FIELD_CACHE.put(key, value);
    }

    public String getField(String key) {
        return FIELD_CACHE.get(key);
    }
}
