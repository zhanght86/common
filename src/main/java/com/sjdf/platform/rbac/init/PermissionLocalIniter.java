package com.sjdf.platform.rbac.init;

import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.init.StrutsStartupInitable;
import com.sjdf.platform.common.utils.EqualsUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.helper.ActionUtils;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.apache.struts2.dispatcher.Dispatcher;
import org.hibernate.Hibernate;

import javax.servlet.ServletContext;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * User: ketqi
 * Date: 2013-04-15 15:07
 * <p/>
 * 权限管理器, 负责管理权限的加载和删除
 */
public class PermissionLocalIniter implements StrutsStartupInitable {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(PermissionLocalIniter.class);

    /** all action that extends BaseAction */
    private Set<Class<?>> actionSet;
    private PermissionService permissionService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public long systemType() {
        return SystemType.EISS_COMMON;
    }

    public void init(ServletContext servletContext, Dispatcher dispatcher) {
        logger.info("common platform permission init begin");
        permissionService = (PermissionService) ApplicationContextManager.getBean("commonPermissionService");

        //设置信息
        //the actionList
        actionSet = initBaseActionListData(dispatcher);
        try {
            init();
        } catch (Exception e) {
            logger.error("common platform permission init fail", e);
            throw new RuntimeException(e);
        }

        logger.info("common platform permission init end");
    }

    /**
     * 获取代码中所有权限信息
     *
     * @param actionSet 使用权限的action的class
     * @return 权限列表
     */
    public static Map<String, Permission> fetchAllProgramPermission(Set<Class<?>> actionSet) {
        Map<String, Permission> permissionMap = new HashMap<>();
        for (Class<?> clazz : actionSet) {
            String className = clazz.getName();
            //这里只取出这个类已声明的所有public方法,且参数为空的方法
            List<Method> methods = readDeclaredPublicMethod(clazz);
            for (Method method : methods) {
                com.sjdf.platform.common.annotations.Permission permission = method.getAnnotation(com.sjdf.platform.common.annotations.Permission.class);
                if (permission != null) {
                    //组装成permission
                    String methodName = method.getName();
                    String code = permission.code();
                    if (!PlatformUtils.hasText(code) || code.length() != CommonPlatformConstant.LENGTH_8) {
                        throw new RuntimeException(permission.name() + " Permission:" + className + "." + methodName + " code invalidate");
                    }

                    long systemType = Long.parseLong(code.substring(0, CommonPlatformConstant.LENGTH_2));
                    if (!ConfigManager.getInstance().existAttr(SystemType.class, systemType)) {
                        throw new RuntimeException(permission.name() + " Permission:" + className + "." + methodName + " systemType invalidate");
                    }

                    com.sjdf.platform.rbac.bean.Permission p = new com.sjdf.platform.rbac.bean.Permission(systemType, code, className, methodName, permission.name(), permission.supportedCode(), permission.url(), permission.menu() ? WhetherState.YES : WhetherState.NO, permission.orderBy());
                    //以code为键,设置到map中
                    permissionMap.put(p.getCode(), p);
                }
            }
        }
        return permissionMap;
    }

    /** 读取指定方法上声明的所有公用的且参数为空类型的方法 */
    public static List<Method> readDeclaredPublicMethod(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    private Map<String, Permission> fetchAllDatabasePermission() {
        //取所有权限
        List<com.sjdf.platform.rbac.bean.Permission> databasePermissionList = permissionService.listAll(com.sjdf.platform.rbac.bean.Permission.class);
        //将数据库中的对象重新组织,以code为键
        Map<String, com.sjdf.platform.rbac.bean.Permission> databasePermissionMap = new HashMap<>();
        for (com.sjdf.platform.rbac.bean.Permission p : databasePermissionList) {
            //强制初始化supportedPermission
            if (p.getSupportedPermission() != null) {
                Hibernate.initialize(p.getSupportedPermission());
            }
            databasePermissionMap.put(p.getCode(), p);
        }
        return databasePermissionMap;
    }

    /** 初始化actionList数据 */
    public static Set<Class<?>> initBaseActionListData(Dispatcher dispatcher) {
        Set<Class<?>> actionSet = new HashSet<>();
        Map<String, Map<String, ActionConfig>> actionConfigMap = dispatcher.getConfigurationManager().getConfiguration().getRuntimeConfiguration().getActionConfigs();
        for (Map<String, ActionConfig> configMap : actionConfigMap.values()) {
            for (ActionConfig config : configMap.values()) {
                String className = config.getClassName();
                Class<?> clazz = ActionUtils.getClassByConfigClassName(className);
                if (clazz == null) {
                    continue;
                }
                actionSet.add(clazz);
            }
        }

        return actionSet;
    }

    /** 初始化,将权限加载至数据库及系统缓存,并进行权限的信息修改 */
    public void init() {
        //准备数据
        //取得两边重复的部分,这部分内容除code之外以程序中的信息为准
        Map<String, Permission> databaseExistMap = new HashMap<>();
        Map<String, Permission> existMap = new HashMap<>();
        //数据库中待删除的,这部分是由于程序中不再使用某一个权限,而没有作相应的注解
        //程序中待增加的,程序中新增加的权限注解
        Map<String, Permission> addedPermissionMap;

        //获取所有程序中的权限信息
        Map<String, Permission> permissionMap = fetchAllProgramPermission(actionSet);
        //程序获取完毕,获取数据库信息
        Map<String, Permission> databasePermissionMap = fetchAllDatabasePermission();

        //组织完毕,现在将两边的进行对比,程序中如果在数据库中已经存在,则移除
        for (Iterator<Map.Entry<String, Permission>> iterator = permissionMap.entrySet().iterator(); iterator.hasNext(); ) {
            //直接比较key 一般来说应该比较permission,这里key(code)与permission有一一映射,不存在重复问题
            Map.Entry<String, Permission> e = iterator.next();
            String key = e.getKey();

            //加入缓存
            PermissionHelper.addPermission(e.getValue());

            if (databasePermissionMap.containsKey(key)) {
                databaseExistMap.put(key, databasePermissionMap.remove(key));
                existMap.put(key, e.getValue());
                iterator.remove();
            }
        }
        addedPermissionMap = permissionMap;

        //待添加的的解决关联问题
        for (com.sjdf.platform.rbac.bean.Permission p : addedPermissionMap.values()) {
            String supportedCode = p.getSupportedCode();
            if (PlatformUtils.hasText(supportedCode)) {
                //直接设置,未找到时设置为空,找到了就设置被找到的对象
                p.setSupportedPermission(PermissionHelper.findByCode(supportedCode, databaseExistMap, addedPermissionMap));
            }
        }

        //数据库中已经存在的解决权限信息被修改的问题
        List<Permission> updatedPermissionList = new ArrayList<>();
        for (Map.Entry<String, Permission> e : databaseExistMap.entrySet()) {
            String code = e.getKey();
            Permission databaseP = e.getValue();
            Permission methodP = existMap.get(code);
            //检查是否更新标记,避免过度更新
            boolean updated = false;

            //进行几个判断,类名,方法名,以及引用信息,名称等
            if (EqualsUtils.notEqualsWithNotNull(databaseP.getClassName(), methodP.getClassName())) {
                updated = true;
                databaseP.setClassName(methodP.getClassName());
            }
            if (EqualsUtils.notEqualsWithNotNull(databaseP.getMethod(), methodP.getMethod())) {
                updated = true;
                databaseP.setMethod(methodP.getMethod());
            }

            //名称修改,仅在未被用户修改过有效
            if (!databaseP.isUserMoidfied() && EqualsUtils.notEqualsWithNotNull(databaseP.getName(), methodP.getName())) {
                updated = true;
                databaseP.setName(methodP.getName());
            }
            if (!PlatformUtils.hasText(methodP.getSupportedCode())) {
                if (databaseP.getSupportedPermission() != null) {
                    updated = true;
                    databaseP.setSupportedPermission(null);
                }
            } else {
                Permission supportedPermission = PermissionHelper.findByCode(methodP.getSupportedCode(), databaseExistMap, addedPermissionMap);
                //直接用id作判断
                if (databaseP.getSupportedPermission() == null || supportedPermission.getId() != databaseP.getSupportedPermission().getId()) {
                    databaseP.setSupportedPermission(supportedPermission);
                    updated = true;
                }
            }
            if (updated) {
                updatedPermissionList.add(databaseP);
            }
        }

        updatedPermissionList.addAll(addedPermissionMap.values());

        //保存和更新修改的或待保存的权限信息
        permissionService.saveOrUpdate(updatedPermissionList);

        // 保存至权限列表中
        PermissionHelper.addPermissionAll(permissionService.listAll(com.sjdf.platform.rbac.bean.Permission.class));
    }
}
