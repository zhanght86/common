package com.sjdf.platform.location.service;

import com.sjdf.platform.api.vo.JcdmQyxx;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.location.bean.Location;

import java.io.File;
import java.util.List;

/**
 * Create at 2012-11-15 下午5:44:31
 * <p/>
 * 省，市，县（区）的相关信息service
 *
 * @author KETQI
 */
public interface LocationService extends BaseService {

    /**
     * 分页查询地理位置信息
     *
     * @param location 查询条件地理位置信息
     * @param page     分页组件
     * @return 地理位置信息列表
     */
    List<Location> list(Location location, Page page);

    /**
     * 加载指定的地理位置信息
     *
     * @param idx 主键
     * @return 地理位置信息
     */
    Location getLocation(long idx);

    /**
     * 根据code获取地理位置信息
     *
     * @param code 代码
     * @return 地理位置信息
     */
    Location getLocation(String code);

    /**
     * 添加地理位置信息
     *
     * @param location 地理位置信息
     * @return 消息组件
     */
    Message add(Location location);

    /**
     * 修改地理位置
     *
     * @param location 地理位置信息
     * @return 消息组件
     */
    Message update(Location location);

    /**
     * 删除指定的地理位置信息
     *
     * @param idx 地理位置信息的id
     * @return 消息组件
     */
    Message del(long idx);

    /**
     * 省份列表
     *
     * @return 地理位置信息类表
     */
    List<Location> getProvinceList();

    /**
     * 城市列表
     *
     * @param provinceCode 身份代码
     * @return 地理位置信息类表
     */
    List<Location> getCityList(String provinceCode);

    /**
     * 区县列表
     *
     * @param provinceCode 省份代码
     * @param cityCode     城市代码
     * @return 地理位置信息类表
     */
    List<Location> getCountyList(String provinceCode, String cityCode);

    /**
     * 解析并保存位置数据
     *
     * @param xls 文件
     * @return 消息组件
     */
    Message upload(File xls);

    /**
     * 更新邮编和区号
     *
     * @param xls 文件
     * @return 消息组件
     */
    Message update(File xls);

    /**
     * 到处数据
     *
     * @throws Exception
     */
    void download(String path) throws Exception;

    /**
     * @param list 地理位置信息列表
     * @return 添加或者修改地理位置
     */
    Message saveOrupdate(List<JcdmQyxx> list);
}
