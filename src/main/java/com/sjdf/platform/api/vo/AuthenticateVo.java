package com.sjdf.platform.api.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.AES;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.bean.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 认证信息接口对象
 * User: ketqi
 * Date: 2013-07-19 16:46
 */
public class AuthenticateVo {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AuthenticateVo.class);
    //成功失败标记
    private boolean bool;
    //提示信息
    private String message;
    //会员信息
    private User user;

    public static AuthenticateVo cteateFailVo(String message) {
        AuthenticateVo vo = new AuthenticateVo();
        vo.setBool(false);
        vo.setMessage(message);
        return vo;
    }

    public static AuthenticateVo createSuccessVo(String message, User user) {
        AuthenticateVo vo = new AuthenticateVo();
        vo.setBool(true);
        vo.setMessage(message);
        vo.setUser(user);
        return vo;
    }

    /**
     * 将当前对象解析成xml字符串
     *
     * @return xml字符串
     */
    public String parse() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<authenticate>");
        xml.append("<bool><![CDATA[").append(bool).append("]]></bool>");
        xml.append("<message><![CDATA[").append(PlatformUtils.hasText(message) ? message : "").append("]]></message>");
        if (bool && user != null) {
            xml.append("<idx><![CDATA[").append(user.getId()).append("]]></idx>");
            xml.append("<systemType><![CDATA[").append(user.getSystemType()).append("]]></systemType>");
            xml.append("<userName><![CDATA[").append(user.getUsername()).append("]]></userName>");
            xml.append("<name><![CDATA[").append(user.getName()).append("]]></name>");
            xml.append("<pwd><![CDATA[").append(AES.encrypt(user.getPassword())).append("]]></pwd>");
            xml.append("<permissionList>");
            Map<String, Permission> permissionMap = user.getMergedPermissionMap();
            if (permissionMap != null && !permissionMap.isEmpty()) {
                for (Permission p : permissionMap.values()) {
                    xml.append(p.parse());
                }
            }
            xml.append("</permissionList>");
        }
        xml.append("</authenticate>");
        return xml.toString();
    }

    /**
     * 将xml字符串解析成AuthenticateVo对象
     *
     * @param xml xml字符串
     * @return AuthenticateVo
     */
    @SuppressWarnings("unchecked")
    public static AuthenticateVo parse(String xml) {
        AuthenticateVo vo = new AuthenticateVo();
        vo.setUser(new User());
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/authenticate");
            List<Element> xmlAuthenticateList = root.elements();
            if (xmlAuthenticateList != null && !xmlAuthenticateList.isEmpty()) {
                for (Element element : xmlAuthenticateList) {
                    if ("bool".equals(element.getName())) {
                        vo.setBool(Boolean.parseBoolean(element.getData().toString()));
                    } else if ("message".equals(element.getName())) {
                        vo.setMessage(element.getData().toString());
                    } else if ("idx".equals(element.getName())) {
                        vo.getUser().setId(Long.parseLong(element.getData().toString()));
                    } else if ("systemType".equals(element.getName())) {
                        vo.getUser().setSystemType(Long.parseLong(element.getData().toString()));
                    } else if ("userName".equals(element.getName())) {
                        vo.getUser().setUsername(element.getData().toString());
                    } else if ("name".equals(element.getName())) {
                        vo.getUser().setName(element.getData().toString());
                    } else if ("pwd".equals(element.getName())) {
                        vo.getUser().setPassword(element.getData().toString());
                    } else if ("permissionList".equals(element.getName())) {
                        Set<Permission> permissionList = new HashSet<>(CommonPlatformConstant.LENGTH_1024);
                        Permission permission;
                        List<Element> xmlPermissionList = element.elements();
                        for (Element e : xmlPermissionList) {
                            permission = new Permission();
                            if ("permission".equals(e.getName())) {
                                List<Element> xmlList = e.elements();
                                for (Element p : xmlList) {
                                    if ("id".equals(p.getName())) {
                                        permission.setId(Long.parseLong(p.getData().toString()));
                                    } else if ("systemType".equals(p.getName())) {
                                        permission.setSystemType(Long.parseLong(p.getData().toString()));
                                    } else if ("code".equals(p.getName())) {
                                        permission.setCode(p.getData().toString());
                                    } else if ("className".equals(p.getName())) {
                                        permission.setClassName(p.getData().toString());
                                    } else if ("method".equals(p.getName())) {
                                        permission.setMethod(p.getData().toString());
                                    } else if ("name".equals(p.getName())) {
                                        permission.setName(p.getData().toString());
                                    } else if ("url".equals(p.getName())) {
                                        permission.setUrl(p.getData().toString());
                                    } else if ("isMenu".equals(p.getName())) {
                                        permission.setIsMenu(Long.parseLong(p.getData().toString()));
                                    } else if ("orderBy".equals(p.getName())) {
                                        permission.setOrderBy(Integer.parseInt(p.getData().toString()));
                                    }
                                }
                            }
                            permissionList.add(permission);
                        }
                        vo.getUser().initMergedPermission(permissionList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            vo.setBool(false);
            vo.setMessage("communication error");
            LOGGER.error(e.getMessage(), e);
        }

        return vo;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticateVo{");
        sb.append("bool=").append(bool);
        sb.append(", message='").append(message).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
