package com.sjdf.platform.api.action;

import com.sjdf.platform.api.vo.JcdmQyxx;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.InterfaceRetCode;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.helper.LocationHelper;
import com.sjdf.platform.location.service.LocationService;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-05-21 11:48
 * 地理位置信息对外接口
 */
public class LocationAction extends BaseAction {
    private static final long serialVersionUID = 3279764045496295793L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LocationAction.class);

    @Autowired
    private LocationService locationService;

    /* 区域信息 */
    private List<JcdmQyxx> list;

    /**
     * <pre>
     * api/common/LocationAction!init.action
     * </pre>
     * 外部接口,初始化外部信息
     */
    public String init() {
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        if (!MD5.md5(connpwd + vertime).equals(vercode)) {
            printErrorXml("无效访问");
            return NONE;
        }

        List<Location> locationList = locationService.list(null, null);
        printWrite(LocationHelper.parse(locationList));
        return NONE;
    }

    /**
     * <pre>
     * api/common/LocationAction!update.action
     * </pre>
     * 外部接口,用于备案同步省市县地理位置信息
     */
    public String update() {
        Map<String, Object> reusltMap = new HashMap<>();
        try {
            String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            if (!MD5.md5(connPwd + vertime).equals(vercode)) {
                printErrorXml("无效访问");
                return NONE;
            }
            locationService.saveOrupdate(list);
            reusltMap.put(InterfaceRetCode.RESULT_CODE, InterfaceRetCode.SUCCESS);
            reusltMap.put(InterfaceRetCode.RESULT_MSG, "同步公共平台成功！");
        } catch (Exception e) {
            reusltMap.put(InterfaceRetCode.RESULT_CODE, InterfaceRetCode.ERROR);
            reusltMap.put(InterfaceRetCode.RESULT_MSG, e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
        printWrite(JSONObject.fromObject(reusltMap).toString());
        return NONE;
    }

    public List<JcdmQyxx> getList() {
        return list;
    }

    public void setList(List<JcdmQyxx> list) {
        this.list = list;
    }
}
