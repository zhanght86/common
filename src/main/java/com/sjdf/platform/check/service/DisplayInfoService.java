package com.sjdf.platform.check.service;

import com.sjdf.platform.check.bean.DisplayInfoBean;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.vo.SearchVo;

import java.util.List;

/**
 * 2012-8-24 下午2:06:49
 * 【展示信息】业务接口
 *
 * @author frank
 */
public interface DisplayInfoService extends BaseService {
    /**
     * 根据条件查询【展示信息】集合
     *
     * @param searchVo 【展示信息】Vo类
     * @param page     分页实体类
     * @return 【展示信息】集合数据
     * @throws Exception
     */
    List<DisplayInfoBean> findDisplayInfoList(SearchVo searchVo, Page page) throws Exception;

    /**
     * 保存或者更新【展示信息】
     *
     * @param displayInfoBean 展示信息实体
     * @param user            当前操作的用户
     * @throws Exception
     */
    String saveOrUpdateDisplayInfo(DisplayInfoBean displayInfoBean, String user) throws Exception;

    /**
     * 根据ID获取【展示信息】
     *
     * @param id ID
     * @return 【展示信息】实体
     * @throws Exception
     */
    DisplayInfoBean getDisplayInfoById(Long id) throws Exception;

    /**
     * 根据ID删除【展示信息】
     *
     * @param id ID
     * @throws Exception
     */
    void deleteDisplayInfo(Long id) throws Exception;

    /**
     * 根据条件查询【展示信息List】数据
     *
     * @param displayInfoBean 条件封装的【展示信息bean】
     * @return 展示信息List】数据
     * @throws Exception
     */
    List<DisplayInfoBean> findDisplayInfoBeanListByCondition(DisplayInfoBean displayInfoBean) throws Exception;
}
