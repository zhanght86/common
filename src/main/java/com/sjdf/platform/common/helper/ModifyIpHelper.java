package com.sjdf.platform.common.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.vo.ProductAndDomainVo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Create at 2013年8月8日 下午4:52:16
 * <p/>
 * 修改辅助器
 *
 * @author KETQI
 */
public final class ModifyIpHelper {
    private ModifyIpHelper() {
    }

    /**
     * 将VOList转化为XML字符串
     *
     * @param voList 产品域名VO集合
     * @param fillIn 是否填充XML头
     * @return XML字符串
     * @throws Throwable
     */
    public static String parse(List<ProductAndDomainVo> voList, boolean fillIn) throws Throwable {
        if (voList == null || voList.isEmpty()) {
            throw new Throwable("参数集合为空！请检查！");
        }
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        if (fillIn) {
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("<productList>");
        }
        for (ProductAndDomainVo vo : voList) {
            xml.append(vo.toXML());
        }
        if (fillIn) {
            xml.append("</productList>");
        }

        return xml.toString();
    }

    /**
     * 将XML数据解析成ProductAndDomainVo集合
     *
     * @param xml XML
     * @return ProductAndDomainVo 集合
     */
    @SuppressWarnings("unchecked")
    public static List<ProductAndDomainVo> parse(String xml) throws Exception {

        List<ProductAndDomainVo> voList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/productList");
            List<Element> xmlDictionaryList = root.elements();
            if (xmlDictionaryList != null && !xmlDictionaryList.isEmpty()) {
                for (Element element : xmlDictionaryList) {
                    List<Element> xmlDictionary = element.elements();
                    ProductAndDomainVo vo = new ProductAndDomainVo();
                    for (Element e : xmlDictionary) {
                        if ("productInfo".equals(e.getName())) {
                            List<Element> productInfo = e.elements();
                            for (Element t : productInfo) {
                                if ("webName".equals(t.getName())) {
                                    vo.setWebName(t.getData().toString());
                                } else if ("type".equals(t.getName()) && !Tools.isEmpty(t.getData().toString())) {
                                    vo.setType(Long.valueOf(t.getData().toString()));
                                } else if ("nameOrPort".equals(t.getName())) {
                                    vo.setNameOrPort(t.getData().toString());
                                } else if ("password".equals(t.getName())) {
                                    vo.setPassword(t.getData().toString());
                                } else if ("serverPlace".equals(t.getName()) && !Tools.isEmpty(t.getData().toString())) {
                                    vo.setServerPlace(Long.valueOf(t.getData().toString()));
                                } else if ("returnCode".equals(t.getName()) && !Tools.isEmpty(t.getData().toString())) {
                                    vo.setReturnCode(Long.valueOf(t.getData().toString()));
                                } else if ("remark".equals(t.getName()) && !Tools.isEmpty(t.getData().toString())) {
                                    vo.setRemark(t.getData().toString());
                                } else if ("returnMessage".equals(t.getName()) && !Tools.isEmpty(t.getData().toString())) {
                                    vo.setReturnMessage(t.getData().toString());
                                } else if ("publicIpList".equals(t.getName())) {
                                    List<Element> publicIpList = t.elements();
                                    for (Element ip : publicIpList) {
                                        if ("ip".equals(ip.getName())) {
                                            vo.getIpList().add(ip.getData().toString());
                                        }
                                    }
                                }

                            }
                        }
                        if ("domainList".equals(e.getName())) {
                            List<Element> domainList = e.elements();
                            for (Element domain : domainList) {
                                if ("domain".equals(domain.getName())) {
                                    vo.getDomainList().add(domain.getData().toString());
                                }
                            }
                        }
                    }
                    voList.add(vo);
                }
            }
            return voList;
        } catch (Exception e) {
            throw e;
        }
    }
}
