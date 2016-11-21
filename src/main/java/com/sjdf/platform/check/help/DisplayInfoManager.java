package com.sjdf.platform.check.help;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.check.bean.ConditionVoBean;
import com.sjdf.platform.check.bean.DisplayInfoBean;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.AssembleStringSymbols;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

/**
 * 2012-8-29 下午5:17:20
 * 展示信息】管理类
 *
 * @author frank
 */
public final class DisplayInfoManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DisplayInfoManager.class);
    private static final DisplayInfoManager INSTANCE = new DisplayInfoManager();

    private DisplayInfoManager() {
    }

    /**
     * HttpSocket 访问【展示信息Action】
     *
     * @param conditionXml xml
     */
    public List<DisplayInfoBean> getDisplayInfoBeanList(String conditionXml) {
        // 验证
        if (conditionXml == null || "".equals(conditionXml)) {
            throw new RuntimeException("模板不能为空");
        }

        // 发送socket获取数据
        HttpSocket httpSocket = new HttpSocket();
        String connectUrl = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.QUERY_DISPLAY_INFO_URL);
        httpSocket.setUrl(connectUrl);
        LOGGER.debug("url:" + connectUrl);

        Map<String, String> postData = new HashMap<>();
        postData.put("xml", conditionXml);

        httpSocket.setPostData(postData);

        try {
            httpSocket.doPost();
        } catch (Exception e) {
            LOGGER.error(PlatformUtils.getStackTrace(e));
            return Collections.emptyList();
        }
        String xml = httpSocket.getResponseData();
        // 解析xml数据
        return DisplayInfoManager.parse(xml);
    }

    /**
     * @param key                所属类别数组，如{'com.sjdf.platform.dictionary.bean.RecordInfoSort'}
     * @param value              所属类别数组分别对应的二位数组,如{{1,2,3}}
     * @param displayInfoId      展示信息ID
     * @param displayInfoContent 展示信息内容
     * @param displayInfoType    展示信息--信息类别
     * @return XML
     * 生成查询【展示信息】的【展示信息List】条件
     */
    public List<DisplayInfoBean> getDisplayInfoBeanList(String[] key, long[][] value, String[] special, long displayInfoId, String displayInfoContent, long displayInfoType) {
        String conditionXml = parse(generateConditionDisplayInfoBeanList(key, value, special, displayInfoId, displayInfoContent, displayInfoType));
        // 解析xml数据
        return getDisplayInfoBeanList(conditionXml);
    }

    /**
     * @param displayInfoBeanList 【展示信息】List
     * @return 【展示信息】的XML数据
     * 拼装XML数据
     */
    public static String parse(List<DisplayInfoBean> displayInfoBeanList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<displayInfoList>");
        for (DisplayInfoBean displayInfoBean : displayInfoBeanList) {
            xml.append(displayInfoBean.toXML());
        }
        xml.append("</displayInfoList>");
        return xml.toString();
    }

    /**
     * @param xml 源XML数据
     * @return 【展示信息】List集合
     * 解析XML 数据，转换为【展示信息】List集合
     */
    @SuppressWarnings("unchecked")
    public static List<DisplayInfoBean> parse(String xml) {
        List<DisplayInfoBean> displayInfoBeanList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/displayInfoList");
            if (root == null) {
                return Collections.emptyList();
            }
            List<Element> elementList = root.elements();
            if (elementList != null && !elementList.isEmpty()) {
                for (Element element : elementList) {
                    List<Element> xmlTemplate = element.elements();
                    DisplayInfoBean displayInfoBean = new DisplayInfoBean();
                    for (Element temp : xmlTemplate) {
                        if ("id".equals(temp.getName())) {
                            Object o = temp.getData();
                            long id = 0;
                            if (o != null) {
                                try {
                                    id = Long.parseLong(o.toString());
                                } catch (Exception e1) {
                                    LOGGER.error(e1.getMessage(), e1);
                                }
                            }
                            displayInfoBean.setId(id);
                        } else if ("content".equals(temp.getName())) {
                            displayInfoBean.setContent(temp.getData().toString());
                        } else if ("infoType".equals(temp.getName())) {
                            displayInfoBean.setInfoType(Long.valueOf(temp.getData().toString()));
                            // 解析所属类别，封装为条件Map
                        } else if ("ownerTypeMap".equals(temp.getName())) {
                            List<ConditionVoBean> conditionVoBeanList = new ArrayList<>();
                            List<Element> ownerTypeMapElementList = temp.elements();
                            // ownerTypeMap 下ownerType节点
                            for (Element ownerTypeMapElement : ownerTypeMapElementList) {
                                ConditionVoBean conditionVoBean = new ConditionVoBean();

                                String conditionKey = ownerTypeMapElement.attributeValue("name");
                                conditionVoBean.setKey(conditionKey);

                                if (Tools.stringIsNotNullAndEmpty(ownerTypeMapElement.attributeValue("special"))) {
                                    conditionVoBean.setMarkValue(ownerTypeMapElement.attributeValue("special"));
                                }
                                StringBuilder conditionValueBuilder = new StringBuilder();
                                // ownerType节点 下的element节点,获取值并以","方式间隔
                                List<Element> ownerTypeElementList = ownerTypeMapElement.elements();
                                for (Element ownerTypeElement : ownerTypeElementList) {
                                    conditionValueBuilder.append(ownerTypeElement.getText());
                                    conditionValueBuilder.append(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA));
                                }
                                conditionVoBean.setValue(conditionValueBuilder.toString().substring(0, conditionValueBuilder.length() - 1));
                                conditionVoBeanList.add(conditionVoBean);
                            }
                            displayInfoBean.setConditionVoBeanList(conditionVoBeanList);
                        } else if ("ownerType".equals(temp.getName())) {
                            displayInfoBean.setOwnerType(temp.getData().toString());
                        }
                    }
                    displayInfoBeanList.add(displayInfoBean);
                }
            }
        } catch (Exception e) {
            LOGGER.error("解析XML 数据，转换为【展示信息】List集合:" + xml, e);
        }
        return displayInfoBeanList;
    }

    /**
     * @param key                所属类别数组，如{'com.sjdf.platform.dictionary.bean.RecordInfoSort'}
     * @param value              所属类别数组分别对应的二位数组,如{{1,2,3}}
     * @param displayInfoId      展示信息ID
     * @param displayInfoContent 展示信息内容
     * @param displayInfoType    展示信息--信息类别
     * @return 生成查询【展示信息】的【展示信息List】条件
     */
    private static List<DisplayInfoBean> generateConditionDisplayInfoBeanList(String[] key, long[][] value, String[] special, long displayInfoId, String displayInfoContent, long displayInfoType) {
        DisplayInfoBean conditionDisplayInfoBean = new DisplayInfoBean();
        List<ConditionVoBean> conditionVoBeanList = new ArrayList<>();
        for (int i = 0; i < key.length; i++) {
            ConditionVoBean conditionVoBean = new ConditionVoBean();
            conditionVoBean.setKey(key[i]);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < value[i].length; j++) {
                sb.append(String.valueOf(value[i][j]));
                if (j < value[i].length - 1) {
                    sb.append(",");
                }
            }
            conditionVoBean.setValue(sb.toString());
            // 特殊标记
            if (Tools.stringIsNotNullAndEmpty(special[i])) {
                conditionVoBean.setMarkValue(special[i]);
            }
            conditionVoBeanList.add(conditionVoBean);
        }
        // 展示信息ID条件
        if (Tools.longIsNotNUllAndZero(displayInfoId)) {
            conditionDisplayInfoBean.setId(displayInfoId);
        }
        // 展示信息content条件
        if (Tools.stringIsNotNullAndEmpty(displayInfoContent)) {
            conditionDisplayInfoBean.setContent(displayInfoContent);
        }
        // 展示信息infoType条件
        if (Tools.longIsNotNUllAndZero(displayInfoType)) {
            conditionDisplayInfoBean.setInfoType(displayInfoType);
        }
        conditionDisplayInfoBean.setConditionVoBeanList(conditionVoBeanList);
        List<DisplayInfoBean> conditionDisplayList = new ArrayList<>();
        conditionDisplayList.add(conditionDisplayInfoBean);
        return conditionDisplayList;
    }

    public static DisplayInfoManager getInstance() {
        return INSTANCE;
    }
}
