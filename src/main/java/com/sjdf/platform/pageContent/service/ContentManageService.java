package com.sjdf.platform.pageContent.service;

import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.pageContent.bean.ContentManageBean;

import java.io.IOException;
import java.util.List;

/**
 * 页面内容管理接口
 *
 * @author laberwu
 */
public interface ContentManageService extends BaseService {

    /**
     * 获取节点下的子数据
     *
     * @param versionId 版本id
     * @param parentId  关联id
     * @return String 结果信息
     */
    String findJSON(long versionId, long parentId);

    /**
     * 通过条件得到节点列表
     *
     * @param contentBean 查询条件
     * @param page        分页对象
     * @return List<PageContentManageBean> 页面内容管理对象列表
     */
    List<ContentManageBean> findList(ContentManageBean contentBean, Page page);

    /**
     * 通过指定关联id和版本id添加节点
     *
     * @param databaseId 指定关联id
     * @param versionId  指定版本id
     * @return String 结果信息
     */
    String addNode(long databaseId, long versionId);

    /**
     * 通过id删除该节点，包含所有子节点
     *
     * @param databaseId 要删除的节点id
     * @return String 结果信息
     */
    String delAllNodeById(long databaseId);

    /**
     * 重命名节点
     *
     * @param databaseId 要重命名的节点id
     * @param name       重命名名称
     * @return String 结果信息
     */
    String reNameNode(long databaseId, long versionId, String name);

    /**
     * 列表排序
     *
     * @param contentList 数据列表
     * @return String 结果信息
     */
    String updateSortList(List<ContentManageBean> contentList);

    /**
     * 修改
     *
     * @param contentManageBean 修改对象
     * @return String 结果信息
     */
    String modify(ContentManageBean contentManageBean) throws IOException;

    /**
     * 通过版本id查询顶级节点
     *
     * @param versionId 版本id
     * @return List<ContentManageBean> 顶级节点列表
     */
    List<ContentManageBean> findNode(long versionId);

    /**
     * 根据条件查询子对象列表
     *
     * @param versionId 版本id
     * @param parentId  关联id
     * @return List<ContentManageBean> 结果列表
     */
    List<ContentManageBean> findNode(long versionId, long parentId);

    /**
     * 通过系统id获取FixedVo
     *
     * @param id 系统id
     * @return List<FixedVo> FixedVoList
     */
    List<FixedVo> findPageMarkListById(long id);
}
