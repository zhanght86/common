package com.sjdf.platform.api.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.IPHelper;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.helper.ipip.IpInfoSearch;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import net.sf.json.JSONObject;

/**
 * IP信息查询API
 *
 * @author wangpeng
 */
public class IpInfoSearchAction extends BaseAction {
    private static final long serialVersionUID = 1238887382782064993L;

    /** token,即连接密码 */
    private String token;
    /** ip 查询的IP地址 */
    private String ip;

    /**
     * 查询IP地址的详细信息
     *
     * @return IP信息的json字符串
     */
    public String search() {
        JSONObject jsonObject;
        Message message = checkParameter();
        if (message.hasErrorMessage()) {
            jsonObject = buildErrorJson(message.getErrorMessage());
        } else {
            jsonObject = IpInfoSearch.getInstance().ipInfo4json(ip);
        }
        printWrite(jsonObject.toString());
        return NONE;
    }

    /**
     * 查询IP的省份
     *
     * @return IP省份信息的json字符串
     */
    public String getProvince() {
        JSONObject jsonObject;
        Message message = checkParameter();
        if (message.hasErrorMessage()) {
            jsonObject = buildErrorJson(message.getErrorMessage());
        } else {
            jsonObject = buildRightJson(IpInfoSearch.getInstance().getProvinceByIp(ip));
        }
        printWrite(jsonObject.toString());
        return NONE;
    }

    /**
     * 查询IP的国家信息
     *
     * @return IP国家信息的json字符串
     */
    public String getState() {
        JSONObject jsonObject;
        Message message = checkParameter();
        if (message.hasErrorMessage()) {
            jsonObject = buildErrorJson(message.getErrorMessage());
        } else {
            jsonObject = buildRightJson(IpInfoSearch.getInstance().getStateByIp(ip));
        }
        printWrite(jsonObject.toString());
        return NONE;
    }

    /** 构造错误结果 */
    private JSONObject buildErrorJson(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ret", "err");
        jsonObject.put("msg", message);
        return jsonObject;
    }

    /** 构造正确结果 */
    private JSONObject buildRightJson(String data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ret", "ok");
        jsonObject.put("data", data);
        return jsonObject;
    }

    /** 参数验证 */
    private Message checkParameter() {
        String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        if (!PlatformUtils.hasText(token) || !token.equals(MD5.md5(connpwd))) {
            return Message.createMessage("token error");
        }
        if (!PlatformUtils.hasText(ip) || !IPHelper.checkIPV4(ip)) {
            return Message.createMessage("IP error");
        }
        return Message.createEmptyMessage();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
