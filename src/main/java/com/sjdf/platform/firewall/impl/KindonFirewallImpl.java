package com.sjdf.platform.firewall.impl;

import com.sjdf.platform.dictionary.bean.FirewallList;
import com.sjdf.platform.net.HttpSocket;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-08-02
 * 金盾防火墙Impl
 *
 * @author Chen Mohan
 */
public class KindonFirewallImpl extends Firewall {

    private FirewallList firewallList;

    public KindonFirewallImpl(FirewallList firewallList) {
        this.firewallList = firewallList;
    }

    @Override
    public void importDomain(List<String> domainList) throws Exception {

        // 金盾防火墙域名管理规则
        // 【+】号表示允许域名通过，格式为【+ 域名】，例如【+ www.abc.com】
        // 【-】号表示禁止域名通过，格式为【- 域名】，例如【- www.abc.com】
        // 【.】号表示泛域名，例如【+ .abc.com】，意思是允许abc.com下级任何域名，但不包括abc.com本身
        // 例子1：【+ abc.com】允许abc.com
        // 例子2：【+ www.abc.com】允许www.abc.com
        // 例子3：【- abc.com】禁止abc.com
        // 例子4：【- www.abc.com】禁止www.abc.com
        // 例子5：【+ .abc.com】允许bbs.abc.com、www.abc.com、dd.ff.abc.com等
        // 例子6：【+ .】允许所有域名
        // 例子7：【- .】禁止所有域名
        String url = firewallList.getEnName(); // 防火墙URL地址
        String[] userAndPass = firewallList.getValue().split("\\|");
        if (userAndPass.length != 3) {
            throw new Exception("硬件防火墙列表配置错误：" + firewallList.getValue());
        }
        String userName = userAndPass[0]; // 防火墙登录帐号
        String userPass = userAndPass[1]; // 防火墙登录密码
        String paramSubmitType = userAndPass[2]; // 防火墙上传方法

        // 将域名转换为每行一个
        StringBuilder uploadDomain = new StringBuilder();
        for (String domain : domainList) {
            uploadDomain.append("+ ").append(domain).append("\r\n");
        }

        // 这里在最后加一个【- .】表示禁止所有域名访问，该规则需要增加在最后
        uploadDomain.append("- .\r\n");

        // 登录防火墙
        Map<String, String> postData = new HashMap<>();
        postData.put("param_type", "login");
        postData.put("param_username", userName);
        postData.put("param_password", userPass);

        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(url + "/cgi-bin/login.cgi");
        httpSocket.setPostData(postData);
        httpSocket.setReceiveCharsetName("GBK");
        httpSocket.doPost();

        // 定义登录成功后的HTML返回代码
        StringBuilder loginSuccess = new StringBuilder();
        loginSuccess.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        loginSuccess.append("<?xml-stylesheet type=\"text/xsl\" version=\"1.0\" href=\"/redirect.xsl\"?>");
        loginSuccess.append("<redirect_page>/cgi-bin/status_global.cgi</redirect_page>");

        if (!httpSocket.getResponseData().contains("/cgi-bin/status_global.cgi")) {
            throw new Exception("登录失败，返回信息：" + httpSocket.getResponseData());
        }

        // 获取Cookies
        StringBuilder newCookies = new StringBuilder();
        List<String> cookieList = httpSocket.getResponseHeaderList("Set-Cookie");
        for (String cookie : cookieList) {
            newCookies.append(cookie);
            newCookies.append(";");
        }
        if (newCookies.length() > 1) {
            newCookies = newCookies.deleteCharAt(newCookies.length() - 1);
        }

        // 上传域名列表
        postData = new HashMap<>();
        postData.put("param_submit_type", paramSubmitType);

        httpSocket = new HttpSocket();
        httpSocket.setUrl(url + "/cgi-bin/status_domains.cgi");
        httpSocket.addHeader("Cookie", newCookies.toString());
        httpSocket.setPostData(postData);
        httpSocket.addAttachment("param_import_domains", "domain.txt", "text/plain", new ByteArrayInputStream(uploadDomain.toString().getBytes()));
        httpSocket.setReceiveCharsetName("GBK");
        httpSocket.doPostMultipart();

        if (!(httpSocket.getResponseData().contains("success") && httpSocket.getResponseData().contains("/cgi-bin/status_domains.cgi"))) {
            throw new Exception("导入失败，返回信息：" + httpSocket.getResponseData());
        }
    }
}
