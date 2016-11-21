package com.sjdf.platform.rbac.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.Permission;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: ketqi
 * Date: 2013-04-15 15:12
 * <p/>
 * 权限辅助器
 */
public abstract class PermissionHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PermissionHelper.class);
    /** 以code为键的权限映射 */
    private static final ConcurrentHashMap<String, Permission> CODE_PERMISSION_MAP = new ConcurrentHashMap<>();
    /** 以className+method为键的权限映射 */
    private static final ConcurrentHashMap<String, Permission> METHOD_PERMISSION_MAP = new ConcurrentHashMap<>();

    /** 添加权限定义 */
    public static void addPermission(Permission p) {
        CODE_PERMISSION_MAP.put(p.getCode(), p);
        METHOD_PERMISSION_MAP.put(p.getClassName() + p.getMethod(), p);
    }

    /** 添加权限集合 */
    public static void addPermissionAll(Collection<Permission> permissionCollection) {
        for (Permission p : permissionCollection) {
            addPermission(p);
        }
    }

    /** 根据code取权限 */
    public static Permission getPermissionByCode(String code) {
        return CODE_PERMISSION_MAP.get(code);
    }

    /** 根据className+method取权限 */
    public static Permission getPermissionByClassNameAndMethod(String className, String method) {
        return METHOD_PERMISSION_MAP.get(className + method);
    }

    /** 取得权限列表 */
    public static Collection<Permission> getPermissions() {
        return Collections.unmodifiableCollection(CODE_PERMISSION_MAP.values());
    }

    /**
     * @param permissionList 权限列表
     * @return xml字符串
     * 将permissionList解析成xml文档
     */
    public static String parse(Collection<Permission> permissionList) {
        Collection<Permission> list = permissionList;
        if (list == null) {
            list = Collections.emptyList();
        }

        StringBuilder xml = new StringBuilder(list.size() * CommonPlatformConstant.LENGTH_200);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<permissionList>");
        for (Permission p : list) {
            xml.append(p.parse());
        }
        xml.append("</permissionList>");
        return xml.toString();
    }

    /**
     * @param xml xml字符串
     * @return 权限类表
     * 将xml文件字符串解析成对象列表
     */
    @SuppressWarnings("unchecked")
    public static List<Permission> parse(String xml) {
        if (!PlatformUtils.hasText(xml)) {
            return Collections.emptyList();
        }

        List<Permission> permissionList = new ArrayList<>(xml.length() / CommonPlatformConstant.LENGTH_200);
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/permissionList");
            List<Element> xmlRootList = root.elements();
            if (xmlRootList != null && !xmlRootList.isEmpty()) {
                for (Element element : xmlRootList) {
                    if ("permission".equals(element.getName())) {
                        List<Element> xmlPermissionList = element.elements();
                        Permission permission = new Permission();
                        for (Element e : xmlPermissionList) {
                            if ("id".equals(e.getName())) {
                                permission.setId(Long.parseLong(e.getData().toString()));
                            } else if ("systemType".equals(e.getName())) {
                                permission.setSystemType(Long.parseLong(e.getData().toString()));
                            } else if ("code".equals(e.getName())) {
                                permission.setCode(e.getData().toString());
                            } else if ("className".equals(e.getName())) {
                                permission.setClassName(e.getData().toString());
                            } else if ("method".equals(e.getName())) {
                                permission.setMethod(e.getData().toString());
                            } else if ("name".equals(e.getName())) {
                                permission.setName(e.getData().toString());
                            } else if ("url".equals(e.getName())) {
                                permission.setUrl(e.getData().toString());
                            } else if ("isMenu".equals(e.getName())) {
                                permission.setIsMenu(Long.parseLong(e.getData().toString()));
                            } else if ("orderBy".equals(e.getName())) {
                                permission.setOrderBy(Integer.parseInt(e.getData().toString()));
                            } else if ("supportedCode".equals(e.getName())) {
                                permission.setSupportedCode(e.getData().toString());
                            }
                        }
                        permissionList.add(permission);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
        return permissionList;
    }

    /**
     * @param code               权限代码
     * @param databaseExistMap   数据库中存在的权限map
     * @param addedPermissionMap 待添加的权限map
     * @return 权限
     * 在传入的权限map中查找权限
     */
    public static Permission findByCode(String code, Map<String, Permission> databaseExistMap, Map<String, Permission> addedPermissionMap) {
        //先从数据库map中找,为的是用持久化对象来存储引用对象,而不是程序中的对象,后者还没被持久化
        com.sjdf.platform.rbac.bean.Permission supportedPermission = databaseExistMap.get(code);
        //如果未找到,表示此对象还未持久化,从程序map找,接下来可以一并持久化入数据库中
        if (supportedPermission == null) {
            supportedPermission = addedPermissionMap.get(code);
        }
        return supportedPermission;
    }
}
