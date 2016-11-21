package com.sjdf.platform.location.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.helper.LocationManager;
import com.sjdf.platform.location.service.LocationService;
import com.sjdf.platform.location.vo.LocationVo;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.notify.service.NotifyTrackService;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create at 2012-11-15 下午5:40:22
 * 省，市，县（区）的相关信息Action
 *
 * @author KETQI
 */
public class LocationAction extends BaseAction {
    private static final long serialVersionUID = 3279764045496295793L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LocationAction.class);
    @Autowired
    private LocationService locationService;
    @Autowired
    private NotifyTrackService notifyTrackService;
    private Location location;
    private List<Location> locationList;
    // 城市列表
    private List<Location> cityList;

    /**
     * <pre>
     * admin/common/LocationAction!list.action
     * </pre>
     *
     * @return 分页查询地理位置信息
     */
    @Permission(code = "06010201", name = "理位置信息列表")
    public String list() {
        locationList = locationService.list(location, page);

        if (location != null && location.getProvince() != null && StringUtils.hasText(location.getProvince().getCode())) {
            cityList = locationService.getCityList(location.getProvince().getCode());
        }
        return "list";
    }

    /**
     * @return 添加地理位置信息
     */
    @Permission(code = "06010202", name = "添加地理位置信息")
    public String add() {
        Message message = locationService.add(location);
        if (message.hasErrorMessage()) {
            printWrite(json(CommonPlatformConstant.LENGTH_500, getText(message)));
        } else {
            location = (Location) message.getReturnData();
            notifyTrackService.saveLocaiotn(location, OperatorAction.ADD);
            saveLog(OperatorAction.ADD, LogType.INFO, location.getCnName(), message.getInfo(), null);

            printWrite(json(CommonPlatformConstant.LENGTH_200, location.getUrl("admin/common/LocationAction!list.action?")));
        }
        return NONE;
    }

    /**
     * <pre>
     * admin/common/LocationAction!edit.action
     * </pre>
     *
     * @return 编辑地理位置信息
     */
    @Permission(code = "06010203", name = "编辑地理位置信息")
    public String edit() {
        if (idx > 0) {
            location = locationService.getLocation(idx);
        }

        if (location != null && location.getProvince() != null && StringUtils.hasText(location.getProvince().getCode())) {
            cityList = locationService.getCityList(location.getProvince().getCode());
        }
        return "post";
    }

    /**
     * <pre>
     * admin/common/LocationAction!update.action
     * </pre>
     *
     * @return 更新地理位置信息
     */
    @Permission(code = "06010204", name = "更新地理位置信息")
    public String update() {
        Message message = locationService.update(location);
        if (message.hasErrorMessage()) {
            printWrite(json(CommonPlatformConstant.LENGTH_500, getText(message)));
        } else {
            location = (Location) message.getReturnData();
            notifyTrackService.saveLocaiotn(location, OperatorAction.MODIFY);
            saveLog(OperatorAction.MODIFY, LogType.INFO, location.getCnName(), message.getInfo(), null);

            printWrite(json(CommonPlatformConstant.LENGTH_200, location.getUrl("admin/common/LocationAction!list.action?")));
        }
        return NONE;
    }

    /**
     * <pre>
     * admin/common/LocationAction!del.action
     * </pre>
     *
     * @return 删除地理位置信息
     */
    @Permission(code = "06010205", name = "删除地理位置信息")
    public String del() {
        Message message = locationService.del(idx);
        if (message.hasErrorMessage()) {
            printWrite(json(CommonPlatformConstant.LENGTH_500, getText(message)));
        } else {
            location = (Location) message.getReturnData();
            notifyTrackService.saveLocaiotn(location, OperatorAction.DELETE);
            saveLog(OperatorAction.DELETE, LogType.INFO, location.getCnName(), message.getInfo(), null);

            printWrite(json(CommonPlatformConstant.LENGTH_200, ""));
        }
        return NONE;
    }

    /**
     * <pre>
     * admin/common/LocationAction!getCityListAjax.action
     * </pre>
     * 级连城市
     *
     * @return result
     */
    public String getCityListAjax() {
        List<LocationVo> list = LocationManager.getInstance().getCityList(vercode);
        AjaxSupport.sendSuccessTextOnly(null, list, "code", "cnName");
        return NONE;
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_DICTIONARY, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    private String json(int status, String message) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_50);
        builder.append("{");
        builder.append("\"status\":\"").append(status).append("\",");
        builder.append("\"message\":\"").append(message).append("\"");
        builder.append("}");
        return builder.toString();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public List<Location> getProvinceList() {
        return locationService.getProvinceList();
    }

    public List<Location> getCityList() {
        if (cityList == null) {
            cityList = new ArrayList<>();
        }
        return cityList;
    }
}
