package com.sjdf.platform.associate.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.associate.bean.Associate;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
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
 * Create at 2013年8月8日 下午4:42:42
 * 关联信息管理器
 *
 * @author KETQI
 */
public final class AssociateManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AssociateManager.class);
    private static AssociateManager instance = new AssociateManager();

    private AssociateManager() {
    }

    public static AssociateManager getInstance() {
        return instance;
    }

    /**
     * 保存或更新关联信息
     *
     * @param associate 关联信息
     * @return result
     */
    public String saveOrUpdate(Associate associate) {
        validate(associate);

        if (associate.getAssociateSystemType() <= 0) {
            throw new RuntimeException("关联的系统类型不能为空");
        }
        if (!StringUtils.hasText(associate.getAssociateUser())) {
            throw new RuntimeException("关联的用户名不能为空");
        }
        if (!StringUtils.hasText(associate.getAssociatePwd())) {
            throw new RuntimeException("关联用户的登录密码不能为空");
        }

        // 发送socket获取数据
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.ASSOCIATE_SAVE_OR_UPDATE_URL);
        LOGGER.info("url:" + url);

        Map<String, String> postData = new HashMap<>();
        postData.put("associate.currentSystemType", String.valueOf(associate.getCurrentSystemType()));
        postData.put("associate.currentUser", associate.getCurrentUser());
        postData.put("associate.associateSystemType", String.valueOf(associate.getAssociateSystemType()));
        postData.put("associate.associateUser", associate.getAssociateUser());
        postData.put("associate.associatePwd", associate.getAssociatePwd());

        HttpSocket httpSocket = new HttpSocket(url, postData);

        try {
            httpSocket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return httpSocket.getResponseData();
    }

    /**
     * 获取关联信息;当前系统类型和当前用户必须填写
     *
     * @param associate 关联信息
     * @return 关联信息列表
     */
    public List<Associate> get(Associate associate) {
        validate(associate);
        // 发送socket获取数据
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.ASSOCIATE_GET_URL);
        LOGGER.info("url:" + url);

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String vercode = MD5.md5(connPwd + vertime);
        Map<String, String> postData = new HashMap<>();
        postData.put("vertime", vertime);
        postData.put("vercode", vercode);
        postData.put("associate.currentSystemType", String.valueOf(associate.getCurrentSystemType()));
        postData.put("associate.currentUser", associate.getCurrentUser());

        HttpSocket httpSocket = new HttpSocket(url, postData);

        try {
            httpSocket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

        String xml = httpSocket.getResponseData();

        return parse(xml);
    }

    /**
     * 解析list-->xml
     *
     * @param associateList 关联信息列表
     */
    public static String parse(List<Associate> associateList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<associateList>");
        for (Associate a : associateList) {
            xml.append(a.toXml());
        }
        xml.append("</associateList>");
        return xml.toString();
    }

    /**
     * 解析list-->xml
     *
     * @param xml xml
     */
    @SuppressWarnings("unchecked")
    public static List<Associate> parse(String xml) {
        List<Associate> associateList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/associateList");
            List<Element> elementList = root.elements();
            if (elementList != null && !elementList.isEmpty()) {
                for (Element element : elementList) {
                    List<Element> xmlTemplate = element.elements();
                    Associate associate = new Associate();
                    for (Element temp : xmlTemplate) {
                        if ("id".equals(temp.getName())) {
                            Object o = temp.getData();
                            long id = 0;
                            if (o != null) {
                                try {
                                    id = Long.parseLong(o.toString());
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                    LOGGER.error(e1.getMessage(), e1);
                                }
                            }
                            associate.setId(id);
                        } else if ("currentSystemType".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            associate.setCurrentSystemType(num);
                        } else if ("currentUser".equals(temp.getName())) {
                            associate.setCurrentUser(temp.getData().toString());
                        } else if ("associateSystemType".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            associate.setAssociateSystemType(num);
                        } else if ("associateUser".equals(temp.getName())) {
                            associate.setAssociateUser(temp.getData().toString());
                        } else if ("valid".equals(temp.getName())) {
                            int num = Integer.parseInt(temp.getData().toString());
                            associate.setValid(num);
                        } else if ("remark".equals(temp.getName())) {
                            associate.setRemark(temp.getData().toString());
                        }
                    }
                    associateList.add(associate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return associateList;
    }

    /**
     * 基本信息验证
     *
     * @param associate 关联信息
     */
    private void validate(Associate associate) {
        if (associate == null) {
            throw new RuntimeException("关联信息不能为空");
        }
        if (associate.getCurrentSystemType() <= 0) {
            throw new RuntimeException("当前系统类型不能为空");
        }
        if (!StringUtils.hasText(associate.getCurrentUser())) {
            throw new RuntimeException("当前用户名不能为空");
        }
    }
}
