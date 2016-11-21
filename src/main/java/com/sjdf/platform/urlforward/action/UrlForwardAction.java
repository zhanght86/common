package com.sjdf.platform.urlforward.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.ConstRetCode;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.urlforward.bean.UrlForwardBean;
import com.sjdf.platform.urlforward.service.UrlForwardServiceImpl;
import org.apache.struts2.ServletActionContext;

import java.io.PrintWriter;
import java.net.IDN;

/**
 * 转发服务器Action
 *
 * @author laberwu
 */
public class UrlForwardAction extends BaseAction {
    private static final long serialVersionUID = -3618417874110429066L;
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(UrlForwardAction.class);

    /** 保存或者更新url转发记录 */
    public void saveOrUpdateUrlForward() {
        // 获取连接密码
        String conPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Status:");
        
        /*
         * 获取参数
         */
        String timestamp = request.getParameter("timestamp"); // 时间戳
        String domain = request.getParameter("domain"); // 域名
        String url = request.getParameter("url"); // 转发地址
        String checkSum = request.getParameter("checkSum"); // 验证字符串

        try {
            // 生成验证字符串
            String md5 = MD5.md5(domain + url + conPwd + timestamp);
            logger.info("获取参数" + domain + url + conPwd + timestamp);
            logger.info("验证字符串" + checkSum);
            logger.info("生成字符串" + md5);
            // 验证通过
            if (checkSum.equals(md5)) {
                // 域名转换为 IDN 域名
                domain = IDN.toASCII(domain);
                // 生成url地址
                url = this.formatUrl(url);

                // 生成bean
                UrlForwardBean urlBean = new UrlForwardBean();
                urlBean.setDomain(domain);
                urlBean.setUrl(url);
                // 保存或者更新
                UrlForwardServiceImpl urlForwardService = new UrlForwardServiceImpl();
                urlForwardService.saveOrUpdateUrlByDomain(urlBean);
                stringBuilder.append(ConstRetCode.SUCCESS);
            } else {
                stringBuilder.append(ConstRetCode.CONNECT_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            stringBuilder.append(ConstRetCode.FIND_FAILED);
            stringBuilder.append(";Reason:");
            stringBuilder.append(e.getMessage());
            logger.error("保存或者更新url转发记录", e);
        }

        this.commonData(stringBuilder.toString());
    }

    /** 删除url转发记录 */
    public void deleteUrlForward() {
        // 获取连接密码
        String conPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Status:");
        
        /*
         * 获取参数
         */
        String timestamp = request.getParameter("timestamp"); // 时间戳
        String domain = request.getParameter("domain"); // 域名
        String checkSum = request.getParameter("checkSum"); // 验证字符串

        try {
            // 生成验证字符串
            logger.info("获取参数" + domain + conPwd + timestamp);
            String md5 = MD5.md5(domain + conPwd + timestamp);
            logger.info("验证字符串" + checkSum);
            logger.info("生成字符串" + md5);
            // 验证通过
            if (checkSum.equals(md5)) {
                // 域名转换为 IDN 域名
                domain = IDN.toASCII(domain);
                // 删除
                UrlForwardServiceImpl urlForwardService = new UrlForwardServiceImpl();
                urlForwardService.deleteUrlbyDomain(domain);
                stringBuilder.append(ConstRetCode.SUCCESS);
            } else {
                stringBuilder.append(ConstRetCode.CONNECT_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            stringBuilder.append(ConstRetCode.FIND_FAILED);
            stringBuilder.append(";Reason:");
            stringBuilder.append(e.getMessage());
            logger.error("删除url转发记录", e);
        }
        this.commonData(stringBuilder.toString());
    }

    /** 输出数据 */
    private void commonData(String message) {
        // 初始化一些参数和设置
        PrintWriter printWriter = null;
        ServletActionContext.getResponse().setContentType("text/xml");
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");

        try {
            // 输出数据
            printWriter = ServletActionContext.getResponse().getWriter();
            printWriter.write(message);
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("输出数据", e);
        } finally {
            // 关闭资源
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * 格式化url地址
     *
     * @param url url
     */
    private String formatUrl(String url) {
        String lowerUrl = url.toLowerCase();
        if (!lowerUrl.startsWith("http://")) {
            // 生成url地址
            lowerUrl = "http://" + lowerUrl;
        }

        return lowerUrl;
    }

}
