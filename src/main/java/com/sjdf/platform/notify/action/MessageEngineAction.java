/**
 *
 */
package com.sjdf.platform.notify.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.MessageEngineConfig;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.helper.LocationHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateHelper;
import com.sjdf.platform.notify.MessageEngine;

import java.util.List;
import java.util.Map;

/**
 * 2013-1-8 下午12:00:10
 * 消息引擎通知接收端
 *
 * @author ketqi
 */
public class MessageEngineAction extends BaseAction {
    private static final long serialVersionUID = 8402516277646252216L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageEngineAction.class);
    /**
     * 数据类型(配置库,邮件短信模板,位置信息)
     *
     * @see com.sjdf.platform.dictionary.bean.MessageEngineConfig
     */
    private long dataType;
    /**
     * 操作动作(添加,删除,修改)
     *
     * @see com.sjdf.platform.dictionary.bean.OperatorAction
     */
    private long operateType;
    private String data;// 发送过来的数据

    /**
     * <pre>
     * URL:admin/common/platform/MessageEngineAction!handle.action
     * </pre>
     * 数据接收并处理
     */
    public String handle() {
        try {
            // 配置库
            if (dataType == MessageEngineConfig.CONFIGURATION_LIBRARY) {
                List<? extends Dictionary> list = DictionaryHelper.parse(data);
                DictionaryHelper.fillRreference(list);
                if (OperatorAction.ADD == operateType) {
                    DictionaryHelper.add(list);
                } else if (OperatorAction.DELETE == operateType) {
                    DictionaryHelper.delete(list);
                } else if (OperatorAction.MODIFY == operateType) {
                    DictionaryHelper.modify(list);
                }
            } else if (dataType == MessageEngineConfig.MAIL_SMS_TEMPLATE) {
                // 邮件短信模板
                List<MessageTemplate> list = MessageTemplateHelper.parse(data);
                if (OperatorAction.ADD == operateType) {
                    MessageTemplateHelper.add(list);
                } else if (OperatorAction.MODIFY == operateType) {
                    MessageTemplateHelper.add(list);
                } else if (OperatorAction.DELETE == operateType) {
                    MessageTemplateHelper.delete(list);
                }
            } else if (dataType == MessageEngineConfig.LOCATION) {
                // 地理位置信息
                Map<String, Location> map = LocationHelper.parse(data);
                if (OperatorAction.ADD == operateType) {
                    LocationHelper.add(map);
                } else if (OperatorAction.MODIFY == operateType) {
                    LocationHelper.modify(map);
                } else if (OperatorAction.DELETE == operateType) {
                    LocationHelper.delete(map);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new MessageEngine.ResultVo(MessageEngine.FAIL_CODE, e.getMessage()).toXml());
        }
        printWrite(new MessageEngine.ResultVo(MessageEngine.SUCCESS_CODE, "update successfully.").toXml());
        return NONE;
    }

    public long getDataType() {
        return dataType;
    }

    public void setDataType(long dataType) {
        this.dataType = dataType;
    }

    public long getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
