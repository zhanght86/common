package com.sjdf.platform.location.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.init.InitManager;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.location.vo.LocationVo;
import com.sjdf.platform.net.HttpSocket;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-11-16 上午9:49:59
 * 地理位置信息管理器
 *
 * @author KETQI
 */
public final class LocationManager extends InitManager {

    private LocationManager() {
        super();
        // 获取xml数据
        String xmlData = getData();
        destroyData();

        LocationHelper.init(xmlData);
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final LocationManager INSTANCE = new LocationManager();
    }

    public static LocationManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /** 获取远程配置数据 */
    protected String getXmlData() {
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.LOCATION_INIT_URL);

        Map<String, String> postData = new HashMap<>();
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(new StringBuilder(CommonPlatformConstant.LENGTH_64).append(connpwd).append(vertime).toString()));

        // 初始化cacheMap
        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(url);
        httpSocket.setPostData(postData);
        logger.info("url:" + url);

        try {
            httpSocket.doPost();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return httpSocket.getResponseData();
    }

    /**
     * 获取所有省份
     *
     * @return 值对象列表
     */
    public List<LocationVo> getProvinceList() {
        List<LocationVo> list = LocationHelper.getProvinceList();
        if (PlatformUtils.isNotEmpty(list)) {
            Collections.sort(list);
        }
        return list;
    }

    /**
     * 获取指定省份下的所有城市
     *
     * @param provinceCode 省份代码
     * @return 值对象列表
     */
    public List<LocationVo> getCityList(String provinceCode) {
        List<LocationVo> list = LocationHelper.getCityList(provinceCode);
        if (PlatformUtils.isNotEmpty(list)) {
            Collections.sort(list);
        }
        return list;
    }

    /**
     * 获取指定城市下的所有区县
     *
     * @param cityCode 城市代码
     * @return 值对象列表
     */
    public List<LocationVo> getCountyList(String cityCode) {
        List<LocationVo> list = LocationHelper.getCountyList(cityCode);
        if (PlatformUtils.isNotEmpty(list)) {
            Collections.sort(list);
        }
        return list;
    }

    /**
     * 获取指定的区县
     *
     * @param countyCode 区县代码
     * @return 值对象列表
     */
    public LocationVo getCounty(String countyCode) {
        return LocationHelper.getCounty(countyCode);
    }

    /**
     * 根据指定代码获取数据
     *
     * @param code 代码
     * @return 值对象
     */
    public LocationVo getLocation(String code) {
        return LocationHelper.getLocation(code);
    }
}
