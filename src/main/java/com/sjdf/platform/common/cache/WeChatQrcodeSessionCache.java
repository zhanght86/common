package com.sjdf.platform.common.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.sjdf.platform.common.constant.WeChatConst;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.WeChatApiManager;
import com.sjdf.platform.common.vo.WeChatQrcodeVo;

/**
 * 
 * @category 用户浏览器sessionId和微信二维码缓存
 * @author wangpeng
 *
 */
public class WeChatQrcodeSessionCache {

    /**
     * @category 二维码缓存
     */
    private Map<String, WeChatQrcodeVo> cache = new ConcurrentHashMap<>();

    private WeChatQrcodeSessionCache() {
        new Cleaner().start();
    }

    private static final class SingletonHolder {
        private static final WeChatQrcodeSessionCache INSTANCE = new WeChatQrcodeSessionCache();
    }

    public static WeChatQrcodeSessionCache getInstance() {
        return WeChatQrcodeSessionCache.SingletonHolder.INSTANCE;
    }

    public void sendCreateRequest(final String sessionId) {
        WeChatQrcodeVo weChatQrcodeVo = cache.get(sessionId);
        if (weChatQrcodeVo == null || weChatQrcodeVo.getErrcode() > 0L || weChatQrcodeVo.isExpired()) {
            cache.put(sessionId, new WeChatQrcodeVo());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cache.put(sessionId, WeChatApiManager.getInstance().createQRCode(WeChatConst.QRCODE_USES_PREFIX_LOGIN + sessionId,Boolean.TRUE));
                }
            }).start();
        }
    }

    public void updateCache(String sessionId,String userName) {
        WeChatQrcodeVo weChatQrcodeVo = cache.get(sessionId);
        if (weChatQrcodeVo != null) {
            if (PlatformUtils.hasText(userName)) {
                weChatQrcodeVo.setUserName(userName);
                weChatQrcodeVo.setCheckPass(Boolean.TRUE);
            } else {
                weChatQrcodeVo.setCheckPass(Boolean.FALSE);
            }
        }
    }

    public void updateCache(String sessionId,WeChatQrcodeVo weChatQrcodeVo) {
        cache.put(sessionId, weChatQrcodeVo);
    }

    public void removeCache(String sessionId) {
        cache.remove(sessionId);
    }

    /**
     * @category 检查登录情况
     * @param sessionId
     *          用户的sessionId
     * @return
     *          null 表示用户未扫描二维码
     *          false 表示用户未绑定微信
     *          true 表示用户已经绑定微信，可以直接登录
     */
    public Boolean checkWhenLogin(String sessionId) {
        WeChatQrcodeVo weChatQrcodeVo = cache.get(sessionId);
        if (weChatQrcodeVo != null) {
            return weChatQrcodeVo.getCheckPass();
        }
        return null;
    }

    /**
     * @category 根据sessionId获取要登录的用户名
     * @param sessionId
     *          用户的sessionId
     * @return
     *          sessionId对应的用户名
     */
    public String getUserName(String sessionId) {
        WeChatQrcodeVo weChatQrcodeVo = cache.get(sessionId);
        if (weChatQrcodeVo != null) {
            return weChatQrcodeVo.getUserName();
        }
        return "";
    }

    /**
     * @category 使用session获取二维码地址
     * @param sessionId
     *              浏览器的sessionId
     * @return
     *              二维码链接地址
     */
    public String getQrcodeUrl(String sessionId) {
        WeChatQrcodeVo weChatQrcodeVo = cache.get(sessionId);
        if (weChatQrcodeVo != null && PlatformUtils.hasText(weChatQrcodeVo.getTicket())) {
            return weChatQrcodeVo.exchangeUrlWithTicket();
        }
        return null;
    }

    /**
     * 
     * 2015-12-30
     * @category 清洁工：清除缓存中过期的二维码
     * @author wangpeng
     *
     */
    private class Cleaner extends Thread {

        private long frequency = 5 * 1000 * 60;

        @Override
        public void run() {
            if (!cache.isEmpty()) {
                Iterator<Entry<String, WeChatQrcodeVo>> it = cache.entrySet().iterator();
                while(it.hasNext()) {
                    Entry<String, WeChatQrcodeVo> entry = it.next();
                    WeChatQrcodeVo weChatQrcodeVo = entry.getValue();
                    if (weChatQrcodeVo != null && weChatQrcodeVo.isExpired()) {
                        it.remove();
                    }
                }
            } try {
                Thread.sleep(frequency);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
