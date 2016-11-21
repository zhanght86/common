package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 结果值对象
 * 2013-1-8 下午2:02:58
 *
 * @author ketqi
 */
public class ResultVo {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ResultVo.class);
    // 成功代码
    public static final int SUCCESS_CODE = 1000;
    // 失败代码
    public static final int FAIL_CODE = 2000;

    public int code;
    public String msg;

    public ResultVo() {
    }

    public ResultVo(int code) {
        this.code = code;
    }

    public ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append("ResultVo [code=");
        builder.append(code);
        builder.append(", msg=");
        builder.append(msg);
        builder.append("]");
        return builder.toString();
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        builder.append("<result>");
        builder.append("<code>").append(code).append("</code>");
        builder.append("<msg><![CDATA[").append(msg).append("]]></msg>");
        builder.append("</result>");
        return builder.toString();
    }

    /**
     * 解析结果
     *
     * @param result 结果xml数据
     */
    public static ResultVo parse(String result) {
        ResultVo resultVo = new ResultVo();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(result.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            //　验证xml数据正确性
            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/result");
            @SuppressWarnings("unchecked")
            List<Element> elementList = root.elements();
            if (elementList != null && !elementList.isEmpty()) {
                for (Element element : elementList) {
                    if ("code".equals(element.getName())) {
                        resultVo.code = Integer.parseInt(element
                                .getStringValue());
                    } else if ("msg".equals(element.getName())) {
                        resultVo.msg = element.getStringValue();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            resultVo.code = FAIL_CODE;
            resultVo.msg = e.getMessage();
        }
        return resultVo;
    }
}
