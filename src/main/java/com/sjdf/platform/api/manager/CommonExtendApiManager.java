package com.sjdf.platform.api.manager;

import com.sjdf.platform.common.conf.ProfileMapHelper;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * common平台对外扩展接口管理器
 * User: ketqi
 * Date: 2016-06-12 11:24
 */
public final class CommonExtendApiManager extends SupportExtendApiManager {
    private CommonExtendApiManager() {
    }

    /** 单例holder */
    private static class SingleHolder {
        private static CommonExtendApiManager INSTANCE = new CommonExtendApiManager();
    }

    public static CommonExtendApiManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 获取有效角色列表
     * <pre>
     * api/common/AuthenticateAction!roleList.action
     * vercode = MD5.md5(connPwd + "roleList" + vertime)
     * 参数如下:
     * idx:systemType
     * vertime
     * vercode
     * </pre>
     *
     * @param systemType 系统类型
     * @return 消息组件(Map<Long, String>)
     */
    public Message roleList(long systemType) {
        String url = "http://" + ProfileProvider.getValue(ProfileMapHelper.PLATFORM_COMMON_51WEB_COM_DOMAIN) + "/api/common/AuthenticateAction!roleList.action";
        Map<String, String> params = new HashMap<>();
        params.put("idx", String.valueOf(systemType));

        String result = sendSocket(url, "roleList", params);
        Map<String, String> map = PlatformUtils.parse2Map(result);
        if ("false".equals(map.get("bool"))) {
            String message = map.get("message");
            return Message.createMessage("common.message", PlatformUtils.hasText(message) ? message : result);
        }

        Map<Long, String> resultMap = new HashMap<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            long key = PlatformUtils.parse2Long(entry.getKey());
            if (key != 0L) {
                resultMap.put(key, entry.getValue());
            }
        }
        return Message.createMessage().setReturnData(resultMap);
    }

    /**
     * 为指定用户设置角色
     * <pre>
     * api/common/AuthenticateAction!setRole.action
     * vercode = MD5.md5(connPwd + userName + "setRole" + roleId + vertime)
     * 参数如下:
     * idx:systemType
     * vertime
     * vercode
     * </pre>
     *
     * @param systemType 系统类型
     * @param userName   用户名
     * @param roleId     角色id
     * @return result
     */
    public Message setRole(long systemType, String userName, long roleId) {
        String url = "http://" + ProfileProvider.getValue(ProfileMapHelper.PLATFORM_COMMON_51WEB_COM_DOMAIN) + "/api/common/AuthenticateAction!setRole.action";

        Map<String, String> params = new HashMap<>();
        params.put("idx", String.valueOf(systemType));
        params.put("userName", userName);
        params.put("roleId", String.valueOf(roleId));

        String result = sendSocket(url, userName + "setRole" + roleId, params);
        Map<String, String> map = PlatformUtils.parse2Map(result);
        if ("false".equals(map.get("bool"))) {
            String message = map.get("message");
            return Message.createMessage("common.message", PlatformUtils.hasText(message) ? message : result);
        }

        return Message.createEmptyMessage();
    }
}
