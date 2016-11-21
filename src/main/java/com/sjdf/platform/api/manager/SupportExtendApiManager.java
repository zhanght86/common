package com.sjdf.platform.api.manager;

import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.common.ExtendApiPrefixConfig;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对外扩展接口管理器
 * User: ketqi
 * Date: 2015-07-25 11:18
 */
public class SupportExtendApiManager {
    protected static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(SupportExtendApiManager.class);

    /**
     * 发送请求
     *
     * @param url      访问地址
     * @param postData 提交的数据
     * @return 请求结果
     */
    protected String sendSocket(String url, Map<String, String> postData) {
        HttpSocket socket = new HttpSocket(url, postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(url + postData, e);
            return e.getMessage();
        }

        return socket.getResponseData();
    }

    /**
     * 发送请求
     *
     * @param apiPrefixConfig url前缀
     * @param suffixUrl       url后缀
     * @param postData        提交的数据
     * @return 请求结果
     * @see com.sjdf.platform.dictionary.bean.common.ExtendApiPrefixConfig
     */
    protected String sendSocket(long apiPrefixConfig, String suffixUrl, Map<String, String> postData) {
        String prefixUrl = ConfigManager.getInstance().getValue(ExtendApiPrefixConfig.class, apiPrefixConfig);
        String url = prefixUrl + suffixUrl;
        return sendSocket(url, postData);
    }

    /**
     * 发送请求
     * vercode = MD5.md5(connPwd + middle + vertime)
     * vertime格式:yyyyMMddHHmmssSSS
     *
     * @param apiPrefixConfig url前缀
     * @param suffixUrl       url后缀
     * @param middle          校验码中间数据
     * @param postData        提交的数据
     * @return 请求结果
     * @see com.sjdf.platform.dictionary.bean.common.ExtendApiPrefixConfig
     */
    protected String sendSocket(long apiPrefixConfig, String suffixUrl, String middle, Map<String, String> postData) {
        String prefixUrl = ConfigManager.getInstance().getValue(ExtendApiPrefixConfig.class, apiPrefixConfig);
        String url = prefixUrl + suffixUrl;
        return sendSocket(url, middle, postData);
    }

    /**
     * 发送请求
     *
     * @param url      访问地址
     * @param middle   校验码中间数据
     * @param postData 提交的数据
     * @return 请求结果
     */
    protected String sendSocket(String url, String middle, Map<String, String> postData) {
        Map<String, String> params = postData;
        if (params == null) {
            params = new HashMap<>();
        }

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String vercode = MD5.md5(connPwd + middle + vertime);
        params.put("vertime", vertime);
        params.put("vercode", vercode);

        return sendSocket(url, postData);
    }
}
