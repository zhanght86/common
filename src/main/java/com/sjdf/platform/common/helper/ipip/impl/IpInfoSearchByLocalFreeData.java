package com.sjdf.platform.common.helper.ipip.impl;

import com.sjdf.platform.common.helper.ipip.IP;
import com.sjdf.platform.common.helper.ipip.IpInfoSearch;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 2015-11-24
 * 通过本地免费版文件检索
 *
 * @author wangpeng
 */
public class IpInfoSearchByLocalFreeData extends IpInfoSearch {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(IpInfoSearchByLocalFreeData.class);

    public IpInfoSearchByLocalFreeData() {
        IP.load(IpInfoSearch.class.getResource("").getPath() + "17monipdb.dat");
    }

    /**
     * 根据ip地址获取ip信息
     *
     * @param ip 地址
     * @return json
     */
    @Override
    public JSONObject ipInfo4json(String ip) {
        JSONObject jsonObject;
        try {
            String[] infoFromLocal = IP.find(ip);
            if (infoFromLocal == null || infoFromLocal.length < MIN_DATA_LENGTH) {
                throw new Exception("通过本地文件查询IP信息失败");
            }
            jsonObject = new JSONObject();
            jsonObject.put("ret", "ok");
            jsonObject.put("data", JSONArray.fromObject(infoFromLocal));
        } catch (Exception e) {
            jsonObject = new JSONObject();
            jsonObject.put("ret", "err");
            jsonObject.put("msg", e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
        return jsonObject;
    }
}
