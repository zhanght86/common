package com.sjdf.platform.messageTemplate.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.MessageTemplateType;
import com.sjdf.platform.dictionary.bean.MessageTemplateVariable;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 邮件或短信模板库管理器辅助类
 * Create at 2012-08-02
 *
 * @author 王正伟
 */
public abstract class MessageTemplateHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageTemplateHelper.class);
    // 缓存Map<systemType,Map<templateType,MessageTemplate>>
    private static Map<Long, Map<Long, MessageTemplate>> systemTypeCache = new ConcurrentHashMap<>();
    //缓存Map<templateType,MessageTemplate>
    private static Map<Long, MessageTemplate> cache = new ConcurrentHashMap<>();

    public static void init(List<MessageTemplate> list) {
        add(list);
    }

    /**
     * 获取模板
     *
     * @param messageTemplateType 模板类型
     * @return 模板
     */
    public static MessageTemplate get(long messageTemplateType) {
        return cache.get(messageTemplateType);
    }

    /**
     * @param systemType          系统类型
     * @param messageTemplateType 模板类型
     * @return 从缓存中获取指定的邮件短信模板
     */
    public static List<MessageTemplate> get(long systemType, long messageTemplateType) {
        if (messageTemplateType > 0) {
            List<MessageTemplate> list = new ArrayList<>();
            MessageTemplate mt = cache.get(messageTemplateType);
            if (mt != null) {
                list.add(mt);
            }
            return list;
        }

        // 如果系统类型为空,则返回所有模板数据
        if (systemType <= 0) {
            return Collections.emptyList();
        }

        Map<Long, MessageTemplate> map = systemTypeCache.get(systemType);
        if (map == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(map.values());
    }

    /**
     * @param list 添加模板
     */
    public static void add(List<MessageTemplate> list) {
        for (MessageTemplate mt : list) {
            add(mt);
        }
    }

    /**
     * @param mt 添加模板
     */
    public static void add(MessageTemplate mt) {
        if (mt == null) {
            return;
        }

        cache.put(mt.getTemplateType(), mt);
        Map<Long, MessageTemplate> map = systemTypeCache.get(mt.getSystemType());
        if (map == null) {
            map = new ConcurrentHashMap<>();
            systemTypeCache.put(mt.getSystemType(), map);
        }
        map.put(mt.getTemplateType(), mt);
    }

    /**
     * @param list 删除模板
     */
    public static void delete(List<MessageTemplate> list) {
        for (MessageTemplate mt : list) {
            if (mt == null) {
                continue;
            }
            delete(mt);
        }
    }

    /**
     * 删除模板
     *
     * @param mt 模板
     */
    public static void delete(MessageTemplate mt) {
        cache.remove(mt.getTemplateType());

        Map<Long, MessageTemplate> map = systemTypeCache.get(mt.getSystemType());
        if (map != null) {
            map.remove(mt.getTemplateType());
        }
    }

    /**
     * @param templateList 模板类表
     * @return 将模板对象封装为XML数据文件
     */
    public static String parse(List<MessageTemplate> templateList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<templateList>");
        for (MessageTemplate template : templateList) {
            xml.append(template.toXml());
        }
        xml.append("</templateList>");
        return xml.toString();
    }

    /**
     * 解析xml数据
     *
     * @param xml xml数据
     * @return List<MessageTemplate>
     */
    @SuppressWarnings("unchecked")
    public static List<MessageTemplate> parse(String xml) {
        List<MessageTemplate> templateList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/templateList");
            List<Element> elementList = root.elements();
            if (elementList != null && !elementList.isEmpty()) {
                for (Element element : elementList) {
                    List<Element> xmlTemplate = element.elements();
                    MessageTemplate template = new MessageTemplate();
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
                            template.setId(id);
                        } else if ("systemType".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            template.setSystemType(num);
                        } else if ("templateType".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            template.setTemplateType(num);
                        } else if ("title".equals(temp.getName())) {
                            template.setTitle(temp.getData().toString());
                        } else if ("email".equals(temp.getName())) {
                            template.setEmail(temp.getData().toString());
                        } else if ("sms".equals(temp.getName())) {
                            template.setSms(temp.getData().toString());
                        } else if ("emailValid".equals(temp.getName())) {
                            long num = Long.parseLong(temp.getData().toString());
                            template.setEmailValid(num);
                        } else if ("smsValid".equals(temp.getName())) {
                            long num = Long.parseLong(temp.getData().toString());
                            template.setSmsValid(num);
                        }
                    }
                    templateList.add(template);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return templateList;
    }

    /**
     * @param messageTemplateType 模板类型
     * @param isMobile            是否是手机
     * @return 获取邮件或短信模板标签变量
     */
    public static List<Dictionary> getMessageTemplateVariable(long messageTemplateType, boolean isMobile) {
        MessageTemplateType templateType = ConfigManager.getInstance().getDictionary(MessageTemplateType.class, messageTemplateType);
        if (templateType == null) {
            return Collections.emptyList();
        }

        // 格式:mobile:1;mail:1,13,15,14,16,17,19,20,21,22
        if (StringUtils.hasText(templateType.getValue())) {
            String[] vs = templateType.getValue().split(";");
            // 获取目标字符串,如mail:1,13,15,14,16,17,19,20,21,22
            String target = null;
            for (String s : vs) {
                if (isMobile) {
                    if (s.contains("mobile")) {
                        target = s;
                        break;
                    }
                } else {
                    if (s.contains("mail")) {
                        target = s;
                        break;
                    }
                }
            }

            if (target != null) {
                // 获取标签变量值,如:1,13,15,14,16,17,19,20,21,22
                String[] temps = target.split(":");
                if (temps.length == CommonPlatformConstant.LENGTH_2) {
                    // 抓取数字数组:1 13 15 14 16 17 19 20 21 22
                    String[] items = temps[1].split(",");
                    List<Dictionary> list = new ArrayList<>();
                    for (String s : items) {
                        if (StringUtils.hasText(s)) {
                            long attr = 0;
                            try {
                                attr = Long.parseLong(s);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            if (MessageTemplateManager.getSystemEmailFooterList().contains(attr)) {
                                continue;
                            }

                            Dictionary d = ConfigManager.getInstance().getDictionary(MessageTemplateVariable.class, attr);
                            if (d != null) {
                                list.add(d);
                            }
                        }
                    }
                    if (!isMobile) {
                        list.addAll(MessageTemplateManager.getSystemEmailFooterDicList());
                    }
                    return list;
                }
            }
        }
        return Collections.emptyList();
    }
}
