package com.sjdf.platform.common.helper.ipip.impl;

import com.sjdf.platform.common.helper.ipip.IpInfoSearch;
import com.sjdf.platform.dictionary.bean.IPInfoSearchConf;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import net.sf.json.JSONObject;

/**
 * 2015-11-24
 * 通过付费接口API检索
 *
 * @author wangpeng
 */
public class IpInfoSearchByPayApi extends IpInfoSearch {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(IpInfoSearchByPayApi.class);

    /**
     * 根据ip地址获取ip信息
     *
     * @param ip 地址
     * @return json
     */
    @Override
    public JSONObject ipInfo4json(String ip) {
        HttpSocket socket = new HttpSocket();
        socket.setUrl(cm.getValue(IPInfoSearchConf.class, IPInfoSearchConf.PAY_API_URL) + ip);
        try {
            socket.doGet();
            String result = socket.getResponseData();
            return JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
