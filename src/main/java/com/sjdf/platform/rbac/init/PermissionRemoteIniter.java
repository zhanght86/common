package com.sjdf.platform.rbac.init;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.init.StrutsStartupInitable;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.helper.AuthenticateManager;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import org.apache.struts2.dispatcher.Dispatcher;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * User: ketqi Date: 2013-07-22 14:29
 * <p/>
 * 权限初始化, 非common平台使用
 */
public class PermissionRemoteIniter implements StrutsStartupInitable {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(PermissionRemoteIniter.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public long systemType() {
        return SystemType.OTHER;
    }

    public void init(ServletContext servletContext, Dispatcher dispatcher) {
        logger.info("permission platform init begin!");

        // 1.收集Class信息
        logger.info("1.permission platform init collect class info");
        Set<Class<?>> actionSet = PermissionLocalIniter.initBaseActionListData(dispatcher);

        // 2.封装权限列表
        logger.info("2.permission platform init wrap permission list");
        Map<String, Permission> permissionMap = PermissionLocalIniter.fetchAllProgramPermission(actionSet);

        // 3.发送权限列表到common平台
        logger.info("3.permission platform init send permission info to common");
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PERMISSION_COLLECT_URL);
        long systemType = SystemType.getCurrentSystemType();
        String vertime = DateUtils.formatDateTimestamp(new Date());

        // vercode = MD5.md5(systemType + "collect" + connPwd + vertime)
        StringBuilder vercode = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        vercode.append(systemType);
        vercode.append("collect");
        vercode.append(connPwd);
        vercode.append(vertime);

        Map<String, String> postData = new HashMap<>();
        postData.put("xml", PermissionHelper.parse(permissionMap.values()));
        postData.put("idx", String.valueOf(systemType));// 系统类型
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(vercode.toString()));

        HttpSocket socket = new HttpSocket(url, postData);
        try {
            socket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("send permission info to common fail", e);
        }

        // 4.获取返回结果
        String result = socket.getResponseData();
        logger.info("4.common platform return result:" + result);
        Map<String, String> resultMap = PlatformUtils.parse2Map(result);

        // 5.对返回结果进行分析
        String bool = resultMap.get("bool");
        if (!PlatformUtils.hasText(bool)) {
            logger.error("5.send permission info to common fail!");
        } else {
            if ("true".equals(bool)) {
                logger.info("5.send permission info to common success!");
            } else {
                logger.error("5.send permission info to common fail!");
            }
        }

        // 6.获取权限指定平台下的权限信息
        logger.info("6.getAll permission from common platform");
        List<Permission> permissionList = AuthenticateManager.getInstance().getPermissionList();

        // 7.将获取的权限信息加入缓存中,便于权限认证
        logger.info("7.permissionList.size=" + permissionList.size());
        PermissionHelper.addPermissionAll(permissionList);

        logger.info("permission platform init end!");
    }
}
