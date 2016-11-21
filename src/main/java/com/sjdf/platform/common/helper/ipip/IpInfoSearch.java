package com.sjdf.platform.common.helper.ipip;

import com.sjdf.platform.common.helper.ipip.impl.IpInfoSearchByLocalFreeData;
import com.sjdf.platform.common.helper.ipip.impl.IpInfoSearchByPayApi;
import com.sjdf.platform.dictionary.bean.IPInfoSearchConf;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2015-11-24
 * IP信息查询接口
 *
 * @author wangpeng
 */
public abstract class IpInfoSearch {
    protected static ConfigManager cm = ConfigManager.getInstance();

    private static final int STATE_INDEX = 0;
    private static final int PROVINCE_INDEX = 1;
    protected static final int MIN_DATA_LENGTH = 3;

    /**
     * IP检索所使用的类别缓存
     */
    private static final Map<String, IpInfoSearch> IP_INFO_SEARCH_HELPER = new ConcurrentHashMap<String, IpInfoSearch>();

    /**
     * 根据ip获取该ip所在国家
     *
     * @param ip IP地址
     * @return 国家
     */
    public String getStateByIp(String ip) {
        JSONObject json = ipInfo4json(ip);
        if (json == null) {
            return "";
        }
        String ret = json.getString("ret");
        if ("ok".equals(ret)) {
            JSONArray data = json.getJSONArray("data");
            if (data.size() >= MIN_DATA_LENGTH) {
                return data.getString(STATE_INDEX);
            }
        }
        return "";
    }

    /**
     * 根据ip获取该ip所在省份
     *
     * @param ip IP地址
     * @return 省份
     */
    public String getProvinceByIp(String ip) {
        JSONObject json = ipInfo4json(ip);
        if (json == null) {
            return "";
        }
        String ret = json.getString("ret");
        if ("ok".equals(ret)) {
            JSONArray data = json.getJSONArray("data");
            if (data.size() >= MIN_DATA_LENGTH) {
                return data.getString(PROVINCE_INDEX);
            }
        }
        return "";
    }

    /**
     * 根据ip地址获取ip信息
     *
     * @param ip 地址
     * @return json
     */
    public abstract JSONObject ipInfo4json(String ip);

    public static IpInfoSearch getInstance() {
        String indentify = cm.getValue(IPInfoSearchConf.class, IPInfoSearchConf.CURRENT_IP_INFO_SEARCH_CLASS);
        IpInfoSearch instance = IP_INFO_SEARCH_HELPER.get(indentify);
        if (instance == null) {
            synchronized ("") {
                if (instance == null) {
                    if ("1".equals(indentify)) {
                        instance = new IpInfoSearchByPayApi();
                    } else if ("2".equals(indentify)) {
                        instance = new IpInfoSearchByLocalFreeData();
                    } else {
                        instance = new IpInfoSearchByLocalFreeData();
                    }
                    IP_INFO_SEARCH_HELPER.put(indentify, instance);
                }
            }
        }
        return instance;
    }
}
