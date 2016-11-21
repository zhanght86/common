package com.sjdf.platform.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * 发送https请求
 * create in 2016年8月3日
 * @category 发送https请求
 * @author wangpeng
 */
public final class HttpsRequest {

    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(HttpsRequest.class);

    private static final String UTF8 = "UTF-8";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    public static String doPost(String url, String outputStr) throws Exception {
        return sendRequest(url, POST_METHOD, outputStr);
    }

    public static String doGet(String url, String outputStr) throws Exception {
        return sendRequest(url, GET_METHOD, outputStr);
    }

    /**
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return  请求返回内容
     * @throws Exception
     */
    private static String sendRequest(String requestUrl, String requestMethod,  String outputStr) throws Exception{
        HttpsURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new TrustAnyTrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes(UTF8));
                outputStream.close();
            }

            // 从输入流读取返回内容
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, UTF8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (ConnectException ce) {
            LOGGER.error("连接超时：{}", ce);
            throw ce;
        } catch (Exception e) {
            LOGGER.error("https请求异常：{}", e);
            throw e;
        } finally {
            // 释放资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    LOGGER.error("释放资源失败：{}", e);
                    throw e;
                }
                bufferedReader = null;
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    LOGGER.error("释放资源失败：{}", e);
                    throw e;
                }
                inputStreamReader = null;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    LOGGER.error("释放资源失败：{}", e);
                    throw e;
                }
                inputStream = null;
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    LOGGER.error("释放资源失败：{}", e);
                    throw e;
                }
                outputStream = null;
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * @category 自定义信任管理器
     * @author wangpeng
     * @date 2014年4月12日下午
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
}
