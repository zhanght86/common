package com.sjdf.platform.common.vo;

import com.sjdf.platform.common.utils.Tools;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品信息显示对象
 * 2013 2013-1-21 上午9:35:13
 *
 * @author laberwu
 */
public class ProductInfoVo {

    /** 域名 */
    private String domain;
    /** 是否世纪东方 */
    private long ourCompany;
    /** 是否需要备案 */
    private long needRecord;
    /** 解析IP */
    private String parseIp;
    /** 产品分类 */
    private long productClass;
    /** 产品分类名称 */
    private String productClassInfo;
    /** 产品信息 */
    private String productInfo;
    /** 产品IP */
    private String productIp;
    /** 产品IP和解析IP是否一致 */
    private long sameIp;
    /** 用户名 */
    private String userName;
    /** 是否代理 */
    private long whetherAgent;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getOurCompany() {
        return ourCompany;
    }

    public void setOurCompany(long ourCompany) {
        this.ourCompany = ourCompany;
    }

    public long getNeedRecord() {
        return needRecord;
    }

    public void setNeedRecord(long needRecord) {
        this.needRecord = needRecord;
    }

    public String getParseIp() {
        return parseIp;
    }

    public void setParseIp(String parseIp) {
        this.parseIp = parseIp;
    }

    public long getProductClass() {
        return productClass;
    }

    public void setProductClass(long productClass) {
        this.productClass = productClass;
    }

    public String getProductClassInfo() {
        return productClassInfo;
    }

    public void setProductClassInfo(String productClassInfo) {
        this.productClassInfo = productClassInfo;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductIp() {
        return productIp;
    }

    public void setProductIp(String productIp) {
        this.productIp = productIp;
    }

    public long getSameIp() {
        return sameIp;
    }

    public void setSameIp(long sameIp) {
        this.sameIp = sameIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getWhetherAgent() {
        return whetherAgent;
    }

    public void setWhetherAgent(long whetherAgent) {
        this.whetherAgent = whetherAgent;
    }

    /**
     * @return
     * @category VO->XML
     */
    public String toXML() {
        StringBuffer productInfoBuilder = new StringBuffer();
        productInfoBuilder.append("<product>");
        productInfoBuilder.append("<error>0</error>");//表示查询产品信息成功
        if (!StringUtils.isEmpty(domain)) {
            productInfoBuilder.append("<domain><![CDATA[").append(domain).append("]]></domain>");
        }
        if (Tools.longIsNotNUllAndZero(ourCompany)) {
            productInfoBuilder.append("<ourCompany>").append(ourCompany).append("</ourCompany>");
        }
        if (Tools.longIsNotNUllAndZero(needRecord)) {
            productInfoBuilder.append("<needRecord>").append(needRecord).append("</needRecord>");
        }
        if (!StringUtils.isEmpty(parseIp)) {
            productInfoBuilder.append("<parseIp><![CDATA[").append(parseIp).append("]]></parseIp>");
        }
        if (Tools.longIsNotNUllAndZero(productClass)) {
            productInfoBuilder.append("<productClass>").append(productClass).append("</productClass>");
        }
        if (!StringUtils.isEmpty(productClassInfo)) {
            productInfoBuilder.append("<productClassInfo><![CDATA[").append(productClassInfo).append("]]></productClassInfo>");
        }
        if (!StringUtils.isEmpty(productInfo)) {
            productInfoBuilder.append("<productInfo><![CDATA[").append(productInfo).append("]]></productInfo>");
        }
        if (!StringUtils.isEmpty(productIp)) {
            productInfoBuilder.append("<productIp><![CDATA[").append(productIp).append("]]></productIp>");
        }
        if (Tools.longIsNotNUllAndZero(sameIp)) {
            productInfoBuilder.append("<sameIp>").append(sameIp).append("</sameIp>");
        }
        if (!StringUtils.isEmpty(userName)) {
            productInfoBuilder.append("<userName><![CDATA[").append(userName).append("]]></userName>");
        }
        productInfoBuilder.append("<whetherAgent><![CDATA[").append(whetherAgent).append("]]></whetherAgent>");
        productInfoBuilder.append("</product>");

        return productInfoBuilder.toString();
    }

    /**
     * @param xml 需要解析的xml数据
     * @return
     * @throws DocumentException
     * @category xml->vo
     */
    @SuppressWarnings("unchecked")
    public static List<ProductInfoVo> xmlToVo(String xml) throws DocumentException {
        List<ProductInfoVo> productList = new ArrayList<>();
        //开始解析
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new StringReader(xml));
        //　验证xml数据正确性
        Node node = document.selectSingleNode("/error");
        if (node != null) {
            throw new RuntimeException(((Element) node).getData().toString());
        }
        List<Element> productXMLElementList = document.selectNodes("/root/product");
        if (!productXMLElementList.isEmpty()) {
            for (Element element : productXMLElementList) {
                List<Element> listProductInfo = element.elements();
                // VO
                ProductInfoVo vo = new ProductInfoVo();
                for (Element productInfoElement : listProductInfo) {
                    // domain
                    if ("domain".equals(productInfoElement.getName())) {
                        vo.setDomain(productInfoElement.getText());
                    } else if ("ourCompany".equals(productInfoElement.getName())) {
                        vo.setOurCompany(Long.parseLong(productInfoElement.getText()));
                    } else if ("needRecord".equals(productInfoElement.getName())) {
                        vo.setNeedRecord(Long.parseLong(productInfoElement.getText()));
                    } else if ("parseIp".equals(productInfoElement.getName())) {
                        vo.setParseIp(productInfoElement.getText());
                    } else if ("productClass".equals(productInfoElement.getName())) {
                        vo.setProductClass(Long.parseLong(productInfoElement.getText()));
                    } else if ("productClassInfo".equals(productInfoElement.getName())) {
                        vo.setProductClassInfo(productInfoElement.getText());
                    } else if ("productInfo".equals(productInfoElement.getName())) {
                        vo.setProductInfo(productInfoElement.getText());
                    } else if ("productIp".equals(productInfoElement.getName())) {
                        vo.setParseIp(productInfoElement.getText());
                    } else if ("sameIp".equals(productInfoElement.getName())) {
                        vo.setSameIp(Long.parseLong(productInfoElement.getText()));
                    } else if ("userName".equals(productInfoElement.getName())) {
                        vo.setUserName(productInfoElement.getText());
                    } else if ("whetherAgent".equals(productInfoElement.getName())) {
                        vo.setWhetherAgent(Long.parseLong(productInfoElement.getText()));
                    }
                }
                productList.add(vo);
            }
        }

        return productList;
    }
}
