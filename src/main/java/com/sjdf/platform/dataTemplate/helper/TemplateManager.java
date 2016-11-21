package com.sjdf.platform.dataTemplate.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.associate.bean.Associate;
import com.sjdf.platform.associate.helper.AssociateManager;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Create at 2012-05-30
 * 数据模板库管理器
 *
 * @author 王正伟
 */
public final class TemplateManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(TemplateManager.class);
    private static TemplateManager instance = new TemplateManager();

    private TemplateManager() {
    }

    public static TemplateManager getInstance() {
        return instance;
    }

    /**
     * 获取指定的模板数据
     *
     * @param template 查询条件
     * @return 数据模板列表
     */
    public List<Template> get(Template template) {
        // 验证
        if (template == null) {
            throw new RuntimeException("模板不能为空");
        }

        if (!(template.getId() != 0 || StringUtils.hasText(template.getMemberNumber()))) {
            throw new RuntimeException("id或会员编号不能为空");
        }

        // 发送socket获取数据
        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.TEMPLATE_GET_URL));
        LOGGER.info("url:" + ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.TEMPLATE_GET_URL));

        Map<String, String> postData = new HashMap<>();
        if (template.getId() > 0) {
            postData.put("template.id", Long.toString(template.getId()));
        }
        if (template.getSystemType() > 0) {
            postData.put("template.systemType", String.valueOf(template.getSystemType()));
        }
        if (StringUtils.hasText(template.getMemberNumber())) {
            postData.put("template.memberNumber", template.getMemberNumber());
        }
        if (StringUtils.hasText(template.getSystemName())) {
            postData.put("template.systemName", template.getSystemName());
        }
        if (template.getClazz() > 0) {
            postData.put("template.clazz", String.valueOf(template.getClazz()));
        }
        if (template.getType() > 0) {
            postData.put("template.type", String.valueOf(template.getType()));
        }
        if (StringUtils.hasText(template.getTypeList())) {
            postData.put("template.typeList", String.valueOf(template.getTypeList()));
        }
        httpSocket.setPostData(postData);

        try {
            httpSocket.doPost();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
        String xml = httpSocket.getResponseData();
        // 解析xml数据
        return TemplateManager.parse(xml);
    }

    /**
     * 注意:
     * template.systemType      当前系统类型
     * template.type            当前模板类型
     * template.memberNumber    当前用户的用户名或者用户编号
     * 必须赋值
     * 获取指定的模板数据和关联用户下所有模板数据
     *
     * @param template 数据模板
     * @return 数据模板列表
     */
    public List<Template> getAll(Template template) {
        List<Template> templateList = new ArrayList<>();
        // 获取指定的模板数据
        long type = template.getType();
        template.setType(0);
        List<Template> list = get(template);

        //将指定系统的指定类型放在最前面
        if (type != 0) {
            for (ListIterator<Template> iterator = list.listIterator(); iterator.hasNext(); ) {
                Template t = iterator.next();
                if (t.getType() == type) {
                    templateList.add(t);
                    iterator.remove();
                }
            }
        }

        //将指定系统的其他模板添加在后面
        templateList.addAll(list);

        // 获取关联用户下所指定模板数据
        Associate associate = new Associate();
        associate.setCurrentSystemType(template.getSystemType());
        associate.setCurrentUser(template.getMemberNumber());
        List<Associate> associateList = AssociateManager.getInstance().get(associate);
        for (Associate a : associateList) {
            if (ValidMark.VALID == a.getValid()) {
                Template t = new Template();
                t.setMemberNumber(a.getAssociateUser());
                t.setSystemType(a.getAssociateSystemType());
                t.setClazz(template.getClazz());// 设置模板类型大分类
                List<Template> tempList = get(t);
                templateList.addAll(tempList);
            }
        }

        return templateList;
    }

    public List<Template> getAll(Template template, long... templateType) {
        // 获取指定的模板数据
        List<Template> templateList = get(template);

        // 封装指定模板类型
        StringBuilder typeList = new StringBuilder();
        if (templateType != null) {
            for (long type : templateType) {
                typeList.append(type).append(",");
            }
            if (typeList.length() > 0) {
                typeList.deleteCharAt(typeList.length() - 1);
            }
        }

        // 获取关联用户下所有模板数据
        Associate associate = new Associate();
        associate.setCurrentSystemType(template.getSystemType());
        associate.setCurrentUser(template.getMemberNumber());
        List<Associate> associateList = AssociateManager.getInstance().get(associate);
        for (Associate a : associateList) {
            if (ValidMark.VALID == a.getValid()) {
                Template t = new Template();
                t.setMemberNumber(a.getAssociateUser());
                t.setSystemType(a.getAssociateSystemType());
                t.setClazz(template.getClazz());// 设置模板类型大分类
                t.setTypeList(typeList.toString());// 设置指定模板类型
                List<Template> tempList = get(t);
                templateList.addAll(tempList);
            }
        }

        return templateList;
    }

    /**
     * 保存或更新模板
     *
     * @param template 带操作的模板数据
     */
    public void saveOrUpdate(Template template) {
        // 验证
        if (template == null) {
            throw new RuntimeException("模板不能为空");
        }
        if (template.getSystemType() <= 0) {
            throw new RuntimeException("系统类型不能为空");
        }
        if (!StringUtils.hasText(template.getMemberNumber())) {
            throw new RuntimeException("会员编号不能为空");
        }
        if (template.getType() <= 0) {
            throw new RuntimeException("模板类型不能为空");
        }
        if (!StringUtils.hasText(template.getSystemName())) {
            throw new RuntimeException("模板系统名称不能为空");
        }

        // 封装数据
        final Map<String, String> postData = new HashMap<>();
        postData.put("template.systemType", String.valueOf(template.getSystemType()));
        postData.put("template.memberNumber", template.getMemberNumber());
        postData.put("template.systemName", template.getSystemName());
        postData.put("template.type", String.valueOf(template.getType()));
        postData.put("template.name", StringUtils.hasText(template.getName()) ? template.getName() : template.getSystemName());
        postData.putAll(template.getData());
        LOGGER.info("url:" + ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.TEMPLATE_ADD_OR_UPDATE_URL));

        // 异步发送socket获取数据
        new Thread() {
            @Override
            public void run() {
                HttpSocket httpSocket = new HttpSocket();
                httpSocket.setUrl(ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.TEMPLATE_ADD_OR_UPDATE_URL));
                httpSocket.setPostData(postData);
                try {
                    httpSocket.doPost();
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }.start();
    }

    /**
     * @param templateList 数据模板列表
     * @return 将模板对象封装为XML数据文件
     */
    public static String parse(List<Template> templateList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<templateList>");
        for (Template template : templateList) {
            xml.append(template.toXml());
        }
        xml.append("</templateList>");
        return xml.toString();
    }

    /**
     * @param xml xml
     * @return List<Template>
     * 解析xml数据
     */
    @SuppressWarnings("unchecked")
    public static List<Template> parse(String xml) {
        List<Template> templateList = new ArrayList<>();
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
                    Template template = new Template();
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
                        } else if ("memberNumber".equals(temp.getName())) {
                            template.setMemberNumber(temp.getData().toString());
                        } else if ("clazz".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            template.setClazz(num);
                        } else if ("type".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            template.setType(num);
                        } else if ("name".equals(temp.getName())) {
                            template.setName(temp.getData().toString());
                        } else if ("systemName".equals(temp.getName())) {
                            template.setSystemName(temp.getData().toString());
                        } else if ("data".equals(temp.getName())) {
                            List<Element> xmlEntryList = temp.elements();
                            if (xmlEntryList != null && !xmlEntryList.isEmpty()) {
                                for (Element xmlEntry : xmlEntryList) {
                                    List<Element> xmlSubElement = xmlEntry.elements();
                                    String key = "";
                                    String value = "";
                                    for (Element sub : xmlSubElement) {
                                        if ("key".equals(sub.getName())) {
                                            key = sub.getData().toString();
                                        } else if ("value".equals(sub.getName())) {
                                            value = sub.getData().toString();
                                        }
                                    }
                                    template.getData().put(key, value);
                                }
                            }
                        }
                    }
                    templateList.add(template);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return templateList;
    }
}
