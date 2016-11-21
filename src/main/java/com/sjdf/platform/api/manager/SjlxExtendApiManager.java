package com.sjdf.platform.api.manager;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.dictionary.bean.common.ExtendApiPrefixConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 世纪利信对外扩展接口管理器
 * User: ketqi
 * Date: 2015-07-25 11:07
 */
public final class SjlxExtendApiManager extends SupportExtendApiManager {
    private SjlxExtendApiManager() {
    }

    /** 单例holder */
    private static class SingleHolder {
        private static SjlxExtendApiManager INSTANCE = new SjlxExtendApiManager();
    }

    public static SjlxExtendApiManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 更新订单服务状态或者过期时间
     * /api/address!updateOrderStatus.action
     * vercode = MD5.md5(connPwd + idx + vertime)
     * vertime格式:yyyyMMddHHmmssSSS
     * idx:订单id
     *
     * @param orderId 订单id
     * @return 消息组件
     */
    public Message updateServiceStatus(long orderId) {
        String idx = String.valueOf(orderId);
        Map<String, String> postData = new HashMap<>();
        postData.put("idx", idx);

        String result = sendSocket(ExtendApiPrefixConfig.SJLX_CMS, "api!updateOrderStatus.action", idx, postData);
        if (!CommonPlatformConstant.RESULT_SUCCESS.equals(result)) {
            return Message.createMessage("common.message", result);
        }
        return Message.createEmptyMessage();
    }
}
