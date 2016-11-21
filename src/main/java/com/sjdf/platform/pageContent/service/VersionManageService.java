package com.sjdf.platform.pageContent.service;

import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.pageContent.bean.ContentManageBean;
import com.sjdf.platform.pageContent.bean.VersionManageBean;

import java.util.List;

/**
 * 页面内容版本管理接口
 *
 * @author laberwu
 */
public interface VersionManageService extends BaseService {

    /**
     * 获取所有的版本列表
     *
     * @param versionManageBean 查询条件
     * @param page              分页对象
     * @return List<VersionManageBean> 版本列表
     */
    List<VersionManageBean> findList(VersionManageBean versionManageBean, Page page);

    /**
     * 复制版本
     *
     * @param versionId 版本id
     * @return long 新版本id
     */
    long addCopyVersionById(long versionId, VersionManageBean versionManageBean);

    /**
     * 修改版本
     *
     * @param versionManageBean 修改对象
     * @return String 结果信息
     */
    String modify(VersionManageBean versionManageBean);

    /**
     * 通过系统类型获取对应启用有效版本
     *
     * @param systemType 系统类型
     * @return VersionManageBean 查询版本结果
     */
    VersionManageBean findEnabledVersionBySystemType(long systemType);

    /**
     * 通过系统类型获取对应启用有效版本页面对象列表
     *
     * @param systemType 系统类型
     * @return List<ContentManageBean> 页面对象列表
     */
    List<ContentManageBean> findContentListBySystem(long systemType);

    /**
     * 通过系统类型获取xml数据
     *
     * @param systemType 系统类型
     * @return String xml数据
     */
    String findXmlBySystem(long systemType);

    /**
     * 根据版本id推送页面更新内容
     *
     * @param versionId 版本id
     */
    String pushContentById(long versionId);

    /**
     * 预览内容
     *
     * @param versionId  版本id
     * @param databaseId 数据库id
     */
    String pushPreviewContent(long versionId, long databaseId);

    /**
     * 销毁预览缓存
     *
     * @param versionId 版本id
     */
    String pushDestroyPreview(long versionId);

    /**
     * 推送启用版本
     *
     * @param versionId 版本id
     */
    String updatePushVersion(long versionId);

    /**
     * 推送预览版本
     *
     * @param versionId 版本id
     */
    String pushPreviewVersion(long versionId);

    /**
     * 推送预览
     *
     * @param versionId  版本id
     * @param databaseId 页面对象id
     * @return String 预览地址
     */
    String pushPreview(long versionId, long databaseId);

    /**
     * 根据版本id删除版本
     *
     * @param versionId 版本id
     * @return String 结果信息
     */
    String delVersionById(long versionId);
}
