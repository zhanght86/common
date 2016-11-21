package com.sjdf.platform.common.utils;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Create at 2012-04-06
 * URL辅助工具
 *
 * @author 王正伟
 */
public abstract class URLHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(URLHelper.class);

    /**
     * 判断url是否正确
     */
    public static boolean urlExist(String urlName) {
        try {
            // 设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
            HttpURLConnection.setFollowRedirects(false);

            // 到 URL 所引用的远程对象的连接
            HttpURLConnection con = (HttpURLConnection) new URL(urlName).openConnection();

            /**
             * 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE
             * 以上方法之一是合法的，具体取决于协议的限制。
             */
            con.setRequestMethod("HEAD");

            // 从 HTTP 响应消息获取状态码
            return con.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
