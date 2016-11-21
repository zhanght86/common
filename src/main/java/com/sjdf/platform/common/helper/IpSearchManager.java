package com.sjdf.platform.common.helper;

import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 2015-11-25
 * IP信息查询辅助器
 *
 * @author wangpeng
 */
public final class IpSearchManager {

    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(IpSearchManager.class);
    private ConfigManager cm = ConfigManager.getInstance();

    private IpSearchManager() {

    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final IpSearchManager INSTANCE = new IpSearchManager();
    }

    public static IpSearchManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 根据ip获取该ip所在省份
     *
     * @param ip IP地址
     * @return 省份
     */
    public String getProvinceByIp(String ip) {
        return getInfoByHttp("getProvince", ip);
    }

    /**
     * 根据ip获取该ip所在国家
     *
     * @param ip IP地址
     * @return 国家
     */
    public String getStateByIp(String ip) {
        return getInfoByHttp("getState", ip);
    }

    /**
     * 通过http调用common中的接口查询ip信息
     *
     * @param method 调用的方法名称
     * @param ip     要查询的ip
     * @return 省份/国家字符串
     */
    private String getInfoByHttp(String method, String ip) {
        if (!PlatformUtils.hasText(ip)) {
            return "";
        }
        Map<String, String> postData = new HashMap<>();
        String connpwd = cm.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        postData.put("token", MD5.md5(connpwd));
        postData.put("ip", ip);
        HttpSocket socket = new HttpSocket(getUrl(method), postData);
        try {
            socket.doPost();
            JSONObject result = JSONObject.fromObject(socket.getResponseData());
            String ret = result.getString("ret");
            if ("ok".equals(ret)) {
                return result.getString("data");
            } else {
                throw new Exception(ip + result.getString("msg"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 获取rul
     *
     * @param method 方法
     */
    private String getUrl(String method) {
        String prefix = cm.getValue(RemoteAccess.class, RemoteAccess.SJDF_COMMON_PLATFORM_API_PREFIX_URL);
        StringBuilder sb = new StringBuilder(prefix);
        if (!prefix.endsWith("/")) {
            sb.append("/");
        }
        sb.append("ipSearch!").append(method).append(".action");
        return sb.toString();
    }
}
