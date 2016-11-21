package com.sjdf.platform.api.action;

import com.sjdf.platform.check.bean.DisplayInfoBean;
import com.sjdf.platform.check.help.DisplayInfoManager;
import com.sjdf.platform.check.service.DisplayInfoService;
import com.sjdf.platform.common.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 2012-9-3 下午4:13:32
 * 展示信息  Action，查询外部接口调用
 *
 * @author frank
 */
public class DisplayInfoAction extends BaseAction {

    private static final long serialVersionUID = 5920447537580629123L;

    /** 展示信息Service接口 */
    @Autowired
    private DisplayInfoService displayInfoService;
    /** HttpSocket XML数据 */
    private String xml;

    //接口调用
    public String getDisplayInfoList() throws Exception {
        List<DisplayInfoBean> conditionDisplayInfoList = DisplayInfoManager.parse(xml);
        if (conditionDisplayInfoList != null && !conditionDisplayInfoList.isEmpty()) {
            List<DisplayInfoBean> displayInfoBeanList = displayInfoService.findDisplayInfoBeanListByCondition(conditionDisplayInfoList.get(0));
            printWrite(DisplayInfoManager.parse(displayInfoBeanList));
        }
        return NONE;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

}
