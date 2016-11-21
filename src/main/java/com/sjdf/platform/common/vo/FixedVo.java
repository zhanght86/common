package com.sjdf.platform.common.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.InterfaceRetCode;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 固定列表Vo
 *
 * @author 陈绍刚
 */
public class FixedVo {

    private String key;
    private String value;
    private String remark;

    private static final String ATTRIBUTE_NAME_VO = "fixedVo";
    private static final String ATTRIBUTE_NAME_VO_LIST = "fixedVoList";

    public FixedVo() {
    }

    public FixedVo(String key, String value) {
        this(key, value, null);
    }

    public FixedVo(String key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        final int prime = CommonPlatformConstant.LENGTH_31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FixedVo other = (FixedVo) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append("FixedVo [key=");
        builder.append(key);
        builder.append(", value=");
        builder.append(value);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<").append(ATTRIBUTE_NAME_VO).append(">");
        xml.append("<key><![CDATA[").append(key).append("]]></key>");
        xml.append("<value><![CDATA[").append(value).append("]]></value>");
        xml.append("<remark><![CDATA[").append(remark).append("]]></remark>");
        xml.append("</").append(ATTRIBUTE_NAME_VO).append(">");
        return xml.toString();
    }

    public static String toXml(List<FixedVo> fixedVoList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<").append(ATTRIBUTE_NAME_VO_LIST).append(">");
        if (fixedVoList != null && !fixedVoList.isEmpty()) {
            for (FixedVo vo : fixedVoList) {
                xml.append(vo.toXml());
            }
        }
        xml.append("</").append(ATTRIBUTE_NAME_VO_LIST).append(">");
        return xml.toString();
    }

    public static List<FixedVo> parseXmlToList(String xml) throws Exception {
        List<FixedVo> idcList = new ArrayList<>();
        long returnCode = InterfaceRetCode.ERROR;
        String returnMsg = "解析失败";

        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/interface");
            @SuppressWarnings("unchecked")
            List<Element> eleList = root.elements();
            if (eleList != null && !eleList.isEmpty()) {
                for (Element element : eleList) {
                    if ("return_code".equals(element.getName())) {
                        returnCode = Long.parseLong(element.getText());
                    } else if ("return_msg".equals(element.getName())) {
                        returnMsg = element.getText();
                    } else if (ATTRIBUTE_NAME_VO_LIST.equals(element.getName())) {
                        @SuppressWarnings("unchecked")
                        List<Element> list = element.elements();
                        for (Element idcListEl : list) {
                            if (ATTRIBUTE_NAME_VO.equals(idcListEl.getName())) {
                                @SuppressWarnings("unchecked")
                                List<Element> endList = idcListEl.elements();
                                FixedVo fixedVo = new FixedVo();
                                for (Element idcEl : endList) {
                                    if ("key".equals(idcEl.getName())) {
                                        fixedVo.setKey(idcEl.getText());
                                    } else if ("value".equals(idcEl.getName())) {
                                        fixedVo.setValue(idcEl.getText());
                                    } else if ("remark".equals(idcEl.getName())) {
                                        fixedVo.setRemark(idcEl.getText());
                                    }
                                }
                                idcList.add(fixedVo);
                            }
                        }
                    }
                }
            }

            if (returnCode != InterfaceRetCode.SUCCESS) {
                throw new Exception("xml[" + xml + "]" + returnCode + returnMsg);
            }
        } catch (Exception e) {
            throw e;
        }
        return idcList;
    }
}
