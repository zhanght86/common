package com.sjdf.platform.common.json;


import com.sjdf.platform.CommonPlatformConstant;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Create at 2013年8月8日 下午4:57:52
 * 利用response将对象信息输出到客户端
 *
 * @author KETQI
 */
public final class AjaxSupport {
    private AjaxSupport() {
    }

    /** 控制输出行为 */
    private static boolean write = true;

    /**
     * @param message 信息
     * @param <T>     "message":"message","result":'',"bool":false}
     */
    public static <T> void sendFailText(String message) {
        sendFailText(message, null);
    }

    /**
     * @param message           信息
     * @param t                 发送的对象数据
     * @param includeProperties 在对象t中需要发送的属性列表
     * @param <T>               "message":"message","result":{t},"bool":false}
     */
    public static <T> void sendFailText(String message, T t, String... includeProperties) {
        if (!write) {
            return;
        }
        initIncludeProperties(includeProperties);
        AjaxMessageResult<T> ajaxMessageResult = new AjaxMessageResult<>(false, message, t);
        JsonConvertObject object = new AjaxMessageResultConvertObject(includeProperties, null, null, null, false, false, null, ajaxMessageResult);
        sendText(object);
    }

    /**
     * @param message 信息
     * @param <T>     category {"message":"message","result":'',"bool":true}
     */
    public static <T> void sendSuccessText(String message) {
        sendSuccessText(message, null);
    }

    /**
     * @param message            信息
     * @param t                  对象
     * @param execludeProperties 不需要的属性名称
     * @param <T>                "message":"message","result":{t},"bool":true}
     */
    public static <T> void sendSuccessText(String message, T t, String... execludeProperties) {
        if (!write) {
            return;
        }
        initExcludeProperties(execludeProperties);
        AjaxMessageResult<T> ajaxMessageResult = new AjaxMessageResult<>(true, message, t);
        JsonConvertObject object = new AjaxMessageResultConvertObject(null, execludeProperties, null, null, false, false, null, ajaxMessageResult);
        sendText(object);
    }

    public static <T> void sendSuccessText(String message, T t, Class<?>[] excludeClasses, String... excludeProperties) {
        if (!write) {
            return;
        }
        initExcludeProperties(excludeProperties);
        AjaxMessageResult<T> ajaxMessageResult = new AjaxMessageResult<>(true, message, t);
        JsonConvertObject object = new AjaxMessageResultConvertObject(null, excludeProperties, null, excludeClasses, false, false, null, ajaxMessageResult);
        sendText(object);
    }

    public static <T> void sendSuccessTextNoCollection(String message, T t, String... excludeProperties) {
        if (!write) {
            return;
        }
        AjaxMessageResult<T> ajaxMessageResult = new AjaxMessageResult<>(true, message, t);
        JsonConvertObject object = new AjaxMessageResultConvertObject(null, excludeProperties, null, null, false, true, null, ajaxMessageResult);
        sendText(object);
    }

    public static <T> void sendSuccessTextOnly(String message, T t, String... includeProperties) {
        if (!write) {
            return;
        }
        String[] properties = includeProperties;

        initIncludeProperties(properties);
        if (properties == null) {
            properties = new String[CommonPlatformConstant.LENGTH_2];
            properties[0] = "bool";
            properties[1] = "message";
        } else {
            String[] as = new String[properties.length + CommonPlatformConstant.LENGTH_2];
            System.arraycopy(properties, 0, as, 0, properties.length);
            System.arraycopy(new String[]{"bool", "message"}, 0, as, properties.length, CommonPlatformConstant.LENGTH_2);
            properties = as;
        }
        AjaxMessageResult<T> ajaxMessageResult = new AjaxMessageResult<>(true, message, t);
        JsonConvertObject object = new AjaxMessageResultConvertObject(properties, null, null, null, false, false, null, ajaxMessageResult);
        sendText(object);
    }

    public static Map<String, String> parseJson(String result) {
        Map<String, String> map = new HashMap<>();
        JSONObject jObject = JSONObject.fromObject(result);
        Iterator<?> keys = jObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value = jObject.get(key);
            if (value != null) {
                map.put(key, value.toString());
            } else {
                map.put(key, null);
            }
        }
        return map;
    }

    private static void sendText(JsonConvertObject object) {
        AjaxResponse.sendAjaxText(object);
    }

    private static void initIncludeProperties(String... includeProperties) {
        if (includeProperties != null && includeProperties.length > 0) {
            for (int i = 0; i < includeProperties.length; i++) {
                if (!includeProperties[i].startsWith("result.")) {
                    includeProperties[i] = "result." + includeProperties[i];
                }
            }
        }
    }

    private static void initExcludeProperties(String... excludeProperties) {
        if (excludeProperties != null && excludeProperties.length > 0) {
            for (int i = 0; i < excludeProperties.length; i++) {
                if (!excludeProperties[i].startsWith("result.")) {
                    excludeProperties[i] = "result." + excludeProperties[i];
                }
            }
        }
    }
}
