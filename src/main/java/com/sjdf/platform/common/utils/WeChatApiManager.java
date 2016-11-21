package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.vo.WeChatQrcodeVo;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.weixin.WeiXinConst;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.constant.MassMessageType;
import com.sjdf.platform.message.vo.WechatMaterialVo;
import com.sjdf.platform.net.HttpSocket;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 2015-12-03
 * 微信接口辅助器
 *
 * @author wangpeng
 */
public class WeChatApiManager {

    private ConfigManager cm = ConfigManager.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(WeChatApiManager.class);

    private static class SingletonHolder {
        private static final WeChatApiManager INSTANCE = new WeChatApiManager();
    }

    private WeChatApiManager() {

    }

    public static WeChatApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 调用微信接口生成二维码
     * 测试代码：
     * WeChatQrcodeVo qrcodeVo = new WeChatQrcodeVo();
     * qrcodeVo.setTicket("gQFL8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1FFT3I3NTdtZXFVRHkxQnRSMnZGAAIEyyiDVgMEAAAAAA==");
     * qrcodeVo.setUrl("http://weixin.qq.com/q/QEOr757meqUDy1BtR2vF");
     * return qrcodeVo;
     * @param prefix 创建二维码的前缀标示
     * @param async 是否异步执行
     * @return 微信服务器返回的Json字符串
     */
    public WeChatQrcodeVo createQRCode(String prefix ,boolean async) {
        Map<String, String> postData = new HashMap<>();
        postData.put("userName", prefix);
        postData.put("async", async ? "true" : "false");

        JSONObject object = doPost("createQrCode", postData);
        WeChatQrcodeVo qrcodeVo = new WeChatQrcodeVo();
        if (object.has("errcode")) {
            qrcodeVo.setErrcode(object.getLong("errcode"));
            qrcodeVo.setErrmsg(object.getString("errmsg"));
        } else {
            qrcodeVo.setTicket(object.has("ticket") ? object.getString("ticket") : null);
            qrcodeVo.setUrl(object.has("url") ? object.getString("url") : null);
        }
        return qrcodeVo;
    }

    /**
     * 调用微信接口获取图文类永久素材列表
     */
    public List<WechatMaterialVo> batchGetNewsMaterial() {
        Map<String, String> postData = new HashMap<>();
        JSONObject json = doPost("batchGetMaterial", postData);
        if (json.has("errcode")) {
            throw new RuntimeException(json.getString("errmsg"));
        }
        List<WechatMaterialVo> list = new ArrayList<>();
        if (json.has("item_count") && json.getInt("item_count") > 0) {
            JSONArray items = json.getJSONArray("item");
            for (int i = 0; i < items.size(); i++) {
                JSONObject item = items.getJSONObject(i);
                WechatMaterialVo vo = new WechatMaterialVo();
                vo.setMediaId(item.getString("media_id"));
                vo.setTitle(item.getJSONObject("content").getJSONArray("news_item").getJSONObject(0).getString("title"));
                list.add(vo);
            }
        }
        return list;
    }

    /**
     * 调用微信接口分组群发消息
     *
     * @param isToAll 是否发送给全部用户
     * @param groupId 用户组别
     * @param content 如果是图文消息，content表示图文消息的media_id；如果是文本消息，content表示文本消息的内容
     * @param msgType 消息类型
     */
    public JSONObject sendAllMass(boolean isToAll, int groupId, String content, String msgType) {
        Map<String, String> postData = new HashMap<>();
        if (isToAll) {
            postData.put("massSendAll.isToAll", "true");
        } else {
            postData.put("massSendAll.isToAll", "false");
            postData.put("massSendAll.groupId", String.valueOf(groupId));
        }
        if (MassMessageType.TEXT.equals(msgType)) {
            postData.put("massSendAll.content", content);
        } else {
            postData.put("massSendAll.mediaId", content);
            postData.put("massSendAll.msgType", MassMessageType.NEWS);
        }
        return doPost("sendAllMass", postData);
    }

    /**
     * 调用微信接口根据OpenID列表群发
     *
     * @param touser  OpenID列表
     * @param content 如果是图文消息，content表示图文消息的media_id；
     *                如果是文本消息，content表示文本消息的内容
     * @param msgType 消息类型
     */
    public JSONObject sendMass(List<String> touser, String content, String msgType) {
        if (touser == null || touser.isEmpty()) {
            JSONObject json = new JSONObject();
            json.put("errcode", "99999");
            json.put("errmsg", "选择根据OpenID列表群发时，OpenID列表不能为空");
            return json;
        }
        Map<String, String> postData = new HashMap<>();
        for (int i = 0; i < touser.size(); i++) {
            postData.put("massSend.touser[" + i + "]", touser.get(i));
        }
        if (MassMessageType.TEXT.equals(msgType)) {
            postData.put("massSend.content", content);
            postData.put("massSend.msgType", MassMessageType.TEXT);
        } else {
            postData.put("massSend.mediaId", content);
            postData.put("massSend.msgType", MassMessageType.NEWS);
        }
        return doPost("sendAll", postData);
    }

    /**
     * 根据微信号预览消息
     */
    public JSONObject preView(String toWxName, String content, String msgType) {
        Map<String, String> postData = new HashMap<>();
        postData.put("massPreView.toWxName", toWxName);
        if (MassMessageType.TEXT.equals(msgType)) {
            postData.put("massPreView.content", content);
        } else {
            postData.put("massPreView.mediaId", content);
            postData.put("massPreView.msgType", MassMessageType.NEWS);
        }
        return doPost("sendAll", postData);
    }

    /**
     * 向微信平台POST请求
     */
    private JSONObject doPost(String method, Map<String, String> postData) {
        // 增加三个必要参数：company,vertime和vercode
        String connpwd = cm.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = format.format(new Date());

        StringBuilder md5Source = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        md5Source.append(MD5.md5(connpwd));
        md5Source.append(vertime);

        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(md5Source.toString()));
        postData.put("company", cm.getValue(ConstGlobal.class, ConstGlobal.CURR_COMPANY_CLASS));

        String url = cm.getValue(WeiXinConst.class, WeiXinConst.WEIXIN_API_URL);
        if (!url.endsWith("/")) {
            url += "/";
        }
        // 调用微信平台接口返回数据
        HttpSocket socket = new HttpSocket(url + "wechat!" + method + ".action", postData);
        try {
            socket.doPost();
            return JSONObject.fromObject(socket.getResponseData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONObject json = new JSONObject();
            json.put("errcode", "99999");
            json.put("errmsg", "调用微信平台接口失败：" + e.getMessage());
            return json;
        }
    }
}
