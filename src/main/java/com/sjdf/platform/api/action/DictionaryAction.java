package com.sjdf.platform.api.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-05-21 11:41
 * 配置库对外接口;api/common/platform
 */
public class DictionaryAction extends BaseAction {
    private static final long serialVersionUID = -2892551177648558229L;
    /** 配置库数据的对象的class全名称 */
    private String clazz;
    /** 配置库数据的属性 */
    private long attr;

    /** 外部访问API, 根据clazz, attr获取配置信息,返回xml格式数据 */
    public String get() {
        Message message = get(clazz, attr, vercode, vertime);
        if (message.hasErrorMessage()) {
            printWrite("status:-1;result:" + getText(message));
        } else {
            @SuppressWarnings("unchecked")
            List<? extends Dictionary> list = (List<? extends Dictionary>) message.getReturnData();
            String xml = DictionaryHelper.parse(list);
            printWrite("status:0;result:" + xml);
        }
        return NONE;
    }

    /** 外部访问API, 根据clazz, attr获取配置信息,返回json格式数据 */
    public String getJson() {
        Message message = get(clazz, attr, vercode, vertime);
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            @SuppressWarnings("unchecked")
            List<? extends Dictionary> list = (List<? extends Dictionary>) message.getReturnData();
            AjaxSupport.sendSuccessTextOnly(null, list, "attr", "value", "name");
        }
        return NONE;
    }

    /** 客户端初始化, 发送数据 */
    public String initClient() {
        // 获取数据
        String xml = DictionaryHelper.parse(DictionaryHelper.getAllDictionary());
        printWrite(xml);
        return NONE;
    }

    /**
     * @param clazz   类型
     * @param attr    属性值
     * @param vercode 校验码
     * @param vertime 校验时间
     * @return 消息组件
     * 根据指定条件获取数据
     */
    private Message get(String clazz, long attr, String vercode, String vertime) {
        // 连接密码
        String connpwd = DictionaryHelper.getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);

        // 验证数据有效性
        StringBuilder valid = new StringBuilder();
        valid.append(connpwd).append(clazz).append(attr).append(vertime);
        if (!MD5.md5(valid.toString()).equals(vercode)) {
            return Message.createMessage("common.checkSum.fail");
        }

        // 获取数据
        List<? extends Dictionary> list;
        if (attr != 0) {
            list = DictionaryHelper.getDictionary(clazz, attr);
        } else {
            list = DictionaryHelper.getDictionary(clazz);
        }

        return Message.createMessage().setReturnData(list);
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public long getAttr() {
        return attr;
    }

    public void setAttr(long attr) {
        this.attr = attr;
    }
}
