package com.sjdf.platform.common.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Create at 2013-5-15 上午9:47:46
 * 产品域名VO类
 *
 * @author frank
 */
public class ProductAndDomainVo {

    /** 产品类型 */
    private long type;
    /** 产品名称或端口 */
    private String nameOrPort;
    /** 产品密码 */
    private String password;
    /** 产品IP地址集合 */
    private List<String> ipList = new ArrayList<String>();
    /** 产品下所有域名集合 */
    private List<String> domainList = new ArrayList<String>();
    /** 远程访问后返回的状态码 */
    private long returnCode;
    /** 远程访问后返回的信息 */
    private String returnMessage;
    /** 远程访问后返回的网站名称 */
    private String webName;
    /** 产品所在服务器放置地(服务器所在省份ID) */
    private long serverPlace;
    /** 调用接口变更IP备注 */
    private String remark;

    public ProductAndDomainVo() {

    }

    public ProductAndDomainVo(long type, String nameOrPort, String password, List<String> ipList, List<String> domainList, long serverPlace) {
        this.type = type;
        this.nameOrPort = nameOrPort;
        this.password = password;
        this.ipList = ipList;
        this.domainList = domainList;
        this.serverPlace = serverPlace;
    }

    public ProductAndDomainVo(long type, String nameOrPort, String password, List<String> ipList, List<String> domainList, long returnCode, String returnMessage) {
        this.type = type;
        this.nameOrPort = nameOrPort;
        this.password = password;
        this.ipList = ipList;
        this.domainList = domainList;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


    public String getNameOrPort() {
        return nameOrPort;
    }

    public void setNameOrPort(String nameOrPort) {
        this.nameOrPort = nameOrPort;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }

    public long getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(long returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public long getServerPlace() {
        return serverPlace;
    }

    public void setServerPlace(long serverPlace) {
        this.serverPlace = serverPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 将产品信息和域名信息实体转化为XML数据（调用方使用）
     *
     * @return XML字符串
     */
    public String toXML() {
        if (Tools.isEmpty(nameOrPort)) {
            throw new NullPointerException("产品名称或端口为空！请检查！");
        }
        if (ipList == null || ipList.isEmpty()) {
            throw new NullPointerException("产品IP为空！请检查！");
        }
        if (domainList == null || domainList.isEmpty()) {
            throw new NullPointerException("产品所对应的域名为空！请检查！");
        }
        //拼装XML
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);

        xml.append("<product>");

        xml.append("<productInfo>");
        xml.append("<webName><![CDATA[").append(webName).append("]]></webName>");
        xml.append("<type><![CDATA[").append(type).append("]]></type>");
        xml.append("<nameOrPort><![CDATA[").append(nameOrPort).append("]]></nameOrPort>");
        xml.append("<password><![CDATA[").append(password).append("]]></password>");
        xml.append("<serverPlace><![CDATA[").append(serverPlace).append("]]></serverPlace>");
        if (!Tools.isEmpty(remark)) {
            xml.append("<remark><![CDATA[").append(remark).append("]]></remark>");
        }
        if (Tools.longIsNotNUllAndZero(returnCode)) {
            xml.append("<returnCode><![CDATA[").append(returnCode).append("]]></returnCode>");
        }
        if (!Tools.isEmpty(returnMessage)) {
            xml.append("<returnMessage><![CDATA[").append(returnMessage).append("]]></returnMessage>");
        }
        xml.append("<publicIpList>");
        for (String ip : ipList) {
            xml.append("<ip><![CDATA[").append(ip).append("]]></ip>");
        }
        xml.append("</publicIpList>");

        xml.append("</productInfo>");

        xml.append("<domainList>");
        for (String domain : domainList) {
            xml.append("<domain><![CDATA[").append(domain).append("]]></domain>");
        }
        xml.append("</domainList>");
        xml.append("</product>");

        return xml.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductAndDomainVo [type=");
        builder.append(type);
        builder.append(", nameOrPort=");
        builder.append(nameOrPort);
        builder.append(", password=");
        builder.append(password);
        builder.append(", ipList=");
        builder.append(ipList);
        builder.append(", domainList=");
        builder.append(domainList);
        builder.append(", returnCode=");
        builder.append(returnCode);
        builder.append(", returnMessage=");
        builder.append(returnMessage);
        builder.append(", webName=");
        builder.append(webName);
        builder.append(", serverPlace=");
        builder.append(serverPlace);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
}