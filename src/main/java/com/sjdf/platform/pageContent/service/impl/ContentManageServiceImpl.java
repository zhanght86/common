package com.sjdf.platform.pageContent.service.impl;

import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.pageContent.bean.ContentManageBean;
import com.sjdf.platform.pageContent.bean.VersionManageBean;
import com.sjdf.platform.pageContent.helper.PageContentHelper;
import com.sjdf.platform.pageContent.service.ContentManageService;
import com.sjdf.platform.pageContent.util.PageContentUtil;
import com.sjdf.platform.pageContent.vo.NodesJsonVo;
import com.sjdf.platform.rbac.helper.UserHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 页面内容管理接口实现
 *
 * @author laberwu
 */
@Service
public class ContentManageServiceImpl extends BaseServiceImpl implements ContentManageService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ContentManageServiceImpl.class);
    /** 获取节点下的list */
    private static final String LIST_NODE_URL = "admin/common/PageContentManageAction!list4Node.action";
    /** 获取当前节点的信息 */
    private static final String NODE_INFO_URL = "admin/common/PageContentManageAction!nodeInfo.action";
    /** 成功标识 */
    private static final String SUCCESS = "success";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentManageBean> findList(ContentManageBean contentBean, Page page) {
        // 封装构建查询对象
        DetachedCriteria criteria = buildCriteria(contentBean);
        // 封装构建排序数组
        Order[] orders = buildOrderArrays(page);
        List<ContentManageBean> resultList;
        if (page == null) {
            resultList = baseDao.listByCriteria(criteria, orders);
        } else {
            resultList = baseDao.listByCriteria(criteria, page, orders);
        }

        return resultList;
    }

    /**
     * 根据条件查询子对象列表
     *
     * @param parentId 父对象
     * @return List<ContentManageBean> 结果列表
     */
    public List<ContentManageBean> findNode(long versionId, long parentId) {
        // 封装构建查询对象
        DetachedCriteria criteria = buildCriteria4Node(versionId, parentId);
        // 封装构建排序数组
        Order[] orders = buildOrderArrays(null);

        return baseDao.listByCriteria(criteria, orders);
    }

    /**
     * {@inheritDoc}
     */
    public String findJSON(long versionId, long parentId) {
        String jsonString;

        // 当查询条件关联id=0时，需要构造根节点
        if (parentId == 0) {
            // 获取当前查询的版本
            VersionManageBean versionBean = get(VersionManageBean.class, versionId);
            // 构建节点数据
            NodesJsonVo voTop = new NodesJsonVo();
            // 通过版本号查询是否存在数据
            List<ContentManageBean> versionList = findNode(versionId);
            if (versionList != null && !versionList.isEmpty()) {
                voTop.setIsParent(true); // 是否父节点
                voTop.setOpen(true); // 默认打开
                voTop.setUrl(LIST_NODE_URL + "?contentManageBean.versionId=" + versionId); // 访问链接地址
            }
            // 版本号
            voTop.setVersionId(versionBean.getId());
            // 版本名称
            voTop.setName(versionBean.getVersionName());
            // 不可修改
            if (versionBean.getReadOnly() == WhetherState.YES) {
                voTop.setReadOnly(true);
            }

            // 设置子节点
            List<NodesJsonVo> jsonList = getJsonList(parentId, versionBean);

            voTop.setChildren(jsonList);

            JSONObject jsonObject = JSONObject.fromObject(voTop);
            jsonString = jsonObject.toString();
        } else {
            // 获取当前查询的版本
            VersionManageBean versionBean = get(VersionManageBean.class, versionId);
            List<NodesJsonVo> jsonList = getJsonList(parentId, versionBean);

            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            jsonString = jsonArray.toString();
        }

        return jsonString;
    }

    /**
     * 获取子节点
     *
     * @param parentId    页面对象
     * @param versionBean 版本对象
     * @return List<NodesJsonVo> 节点列表
     */
    private List<NodesJsonVo> getJsonList(long parentId, VersionManageBean versionBean) {
        List<NodesJsonVo> jsonList = new ArrayList<>();
        List<ContentManageBean> resuList = findNode(versionBean.getId(), parentId);
        if (resuList != null && !resuList.isEmpty()) {

            for (ContentManageBean content : resuList) {
                NodesJsonVo vo = new NodesJsonVo();

                vo.setDatabaseId(content.getId());  // 对应数据库id
                vo.setVersionId(versionBean.getId()); // 版本id
                vo.setName(content.getMenuName()); // 菜单名称
                if (versionBean.getReadOnly() == WhetherState.YES) {
                    vo.setReadOnly(true); // 不可修改
                }

                // 判断有无子节点
                List<ContentManageBean> tempList = findNode(content.getVersionId(), content.getId());
                // 有子节点，设置连接为查询子节点列表，否则设置为显示详细信息
                if (tempList != null && !tempList.isEmpty()) {
                    vo.setIsParent(true);
                    vo.setUrl(LIST_NODE_URL + "?contentManageBean.versionId=" + versionBean.getId() + "&contentManageBean.parentId=" + content.getId());
                } else {
                    vo.setUrl(NODE_INFO_URL + "?databaseId=" + content.getId());
                }

                jsonList.add(vo);
            }

        }
        return jsonList;
    }

    /**
     * 构建排序
     *
     * @return Order[] 排序数组
     */
    private Order[] buildOrderArrays(Page page) {
        List<Order> orderList = new ArrayList<>();

        if (page == null) {
            orderList.add(Order.asc("displaySort"));
        } else {
            // 设置排序
            if (StringUtils.hasText(page.getOrderBy())) {
                String[] columns = page.getOrderBy().split(";");
                if ("desc".equals(page.getOrderType())) {
                    for (String column : columns) {
                        orderList.add(Order.desc(column));
                    }
                } else {
                    for (String column : columns) {
                        orderList.add(Order.asc(column));
                    }
                }
            } else {
                orderList.add(Order.asc("displaySort"));
            }
        }

        Order[] orders = new Order[orderList.size()];

        return orderList.toArray(orders);
    }

    /**
     * 构建查询对象
     *
     * @param contentBean 页面内容管理查询对象
     */
    private DetachedCriteria buildCriteria(ContentManageBean contentBean) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ContentManageBean.class);

        // 查询对象为空，默认查询所有的顶级节点
        if (contentBean != null) {
            // versionId
            if (contentBean.getVersionId() != 0) {
                criteria.add(Restrictions.eq("versionId", contentBean.getVersionId()));
            }
            // parentId
            criteria.add(Restrictions.eq("parentId", contentBean.getParentId()));
            // deleted
            criteria.add(Restrictions.eq("deleted", ValidMark.VALID));
        }

        return criteria;
    }

    /**
     * 构建查询对象(节点)
     *
     * @param versionId 页面内容管理查询对象
     */
    private DetachedCriteria buildCriteria4Node(long versionId, long parentId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ContentManageBean.class);
        // 查询对象为空，默认查询所有的顶级节点
        // versionId
        if (versionId != 0) {
            criteria.add(Restrictions.eq("versionId", versionId));
        }
        // parentId
        criteria.add(Restrictions.eq("parentId", parentId));
        // deleted
        criteria.add(Restrictions.eq("deleted", ValidMark.VALID));

        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addNode(long databaseId, long versionId) {
        ContentManageBean newBean = new ContentManageBean();
        newBean.setParentId(databaseId);
        newBean.setVersionId(versionId);

        newBean.setDeleted(ValidMark.VALID);
        newBean.setDisplaySort(1);
        newBean.setMenuName("菜单名称");
        newBean.setReadOnly(WhetherState.NO);
        newBean.setWhetherDisplay(WhetherState.NO);
        newBean.setWhetherNewWindow(WhetherState.NO);

        save(newBean);
        saveLog(OperatorAction.ADD, LogType.INFO, newBean.getMenuName(), newBean.wrapUpdateContent(null, false), null);

        // 构造返回json数据
        NodesJsonVo vo = new NodesJsonVo();

        vo.setDatabaseId(newBean.getId());
        vo.setName(newBean.getMenuName());
        vo.setVersionId(versionId);
        vo.setUrl(NODE_INFO_URL + "?databaseId=" + newBean.getId());

        JSONObject jsonObject = JSONObject.fromObject(vo);

        return jsonObject.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delAllNodeById(long databaseId) {
        // 判断有无子节点，有则递归删除
        List<ContentManageBean> tempList = baseDao.find("from ContentManageBean where parentId = ? and deleted = ?", databaseId, ValidMark.VALID);
        if (tempList != null && !tempList.isEmpty()) {
            for (ContentManageBean tempBean : tempList) {
                // 递归调用
                delAllNodeById(tempBean.getId());
            }
        }

        // 删除当前节点
        ContentManageBean currentBean = get(ContentManageBean.class, databaseId);
        // 删除图片
        try {
            if (StringUtils.hasText(currentBean.getImageUrl())) {
                File file = new File(PageContentUtil.getUploadPath(currentBean.getImageUrl()));
                file.delete();
                currentBean.setImageUrl("");
            }
        } catch (Exception e) {
            LOGGER.error("删除图片失败", e);
        }
        currentBean.setDeleted(ValidMark.INVALID);
        update(currentBean);

        saveLog(OperatorAction.DELETE, LogType.INFO, currentBean.getMenuName(), currentBean.wrapUpdateContent(null, false), null);

        return SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String reNameNode(long databaseId, long versionId, String name) {

        // 查询内容节点
        if (databaseId != 0) {
            ContentManageBean contentBean = get(ContentManageBean.class, databaseId);
            contentBean.setMenuName(name);
            update(contentBean);
            saveLog(OperatorAction.MODIFY, LogType.INFO, contentBean.getMenuName(), contentBean.wrapUpdateContent(null, false), null);
        } else {
            // 修改版本名称
            VersionManageBean versionBean = get(VersionManageBean.class, versionId);
            versionBean.setVersionName(name);
            update(versionBean);
            saveLog(OperatorAction.MODIFY, LogType.INFO, versionBean.getVersionName(), versionBean.wrapUpdateContent(null, false), null);
        }

        return SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateSortList(List<ContentManageBean> contentList) {
        if (contentList != null) {
            for (ContentManageBean bean : contentList) {
                ContentManageBean oldBean = get(ContentManageBean.class, bean.getId());
                oldBean.setDisplaySort(bean.getDisplaySort());
                update(oldBean);
            }
        }
        return SUCCESS;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException
     */
    @Override
    public String modify(ContentManageBean contentManageBean) throws IOException {
        // 保存图片
        if (contentManageBean != null) {
            // 修改
            ContentManageBean oldBean = get(ContentManageBean.class, contentManageBean.getId());
            if (oldBean != null) {
                String info = oldBean.wrapUpdateContent(contentManageBean, true);
                // 修改图片地址不为空，
                if (StringUtils.hasText(contentManageBean.getImageUrl()) && !contentManageBean.getImageUrl().equals(oldBean.getImageUrl())) {
                    File srcFile = new File(contentManageBean.getImageUrl());
                    String relDest = PageContentUtil.getRelativePath(oldBean); // 相对地址
                    File destFile = new File(PageContentUtil.getUploadPath(relDest)); // 物理地址
                    FileUtils.copyFile(srcFile, destFile);
                    srcFile.delete();
                    contentManageBean.setImageUrl(relDest);
                }

                BeanUtils.copyProperties(contentManageBean, oldBean, new String[]{"id", "parentId", "versionId", "readOnly", "deleted"});

                update(oldBean);

                saveLog(OperatorAction.MODIFY, LogType.INFO, oldBean.getMenuName(), info, null);
            }
        }

        return SUCCESS;
    }

    /** 更新 */
    private void update(ContentManageBean bean) {
        baseDao.update(bean);
        // 查询版本,更改数据为启用中的版本才添加缓存
        VersionManageBean version = get(VersionManageBean.class, bean.getVersionId());
        if (version != null && version.getEnableMarkup() == WhetherState.YES) {
            if (bean.getDeleted() == ValidMark.VALID) {
                // 加入更新缓存
                PageContentHelper.addUpdateCache(bean);
            } else {
                // 加入删除缓存
                PageContentHelper.addDelCache(bean);
            }
        }

        saveLog(OperatorAction.MODIFY, LogType.INFO, bean.getMenuName(), bean.wrapUpdateContent(null, false), null);
    }

    /** 添加 */
    private void save(ContentManageBean bean) {
        baseDao.save(bean);
        // 查询版本,更改数据为启用中的版本才添加缓存
        VersionManageBean version = get(VersionManageBean.class, bean.getVersionId());
        if (version != null && version.getEnableMarkup() == WhetherState.YES) {
            // 加入添加缓存
            PageContentHelper.addAddCache(bean);
        }
        saveLog(OperatorAction.ADD, LogType.INFO, bean.getMenuName(), bean.wrapUpdateContent(null, false), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentManageBean> findNode(long versionId) {
        return findNode(versionId, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FixedVo> findPageMarkListById(long id) {
        List<FixedVo> fixedList = new ArrayList<>();

        ContentManageBean contentManageBean = get(ContentManageBean.class, id);
        VersionManageBean versionManageBean = get(VersionManageBean.class, contentManageBean.getVersionId());
        List<? extends Dictionary> dictionarys = ConfigManager.getInstance().getListDictionary(PageContentMarkup.class.getName(), versionManageBean.getSystemType());

        // 当前版本使用中的页面标示
        String hql = "select distinct pageMarkup from ContentManageBean where deleted = ? and pageMarkup <> ? and versionId = ?";
        List<Long> markList = baseDao.find(hql, ValidMark.VALID, contentManageBean.getPageMarkup(), contentManageBean.getVersionId());
        if (dictionarys != null) {
            for (Dictionary dic : dictionarys) {
                // 标识未使用，则添加
                if (!markList.contains(dic.getAttr())) {
                    FixedVo fixedVo = new FixedVo();
                    fixedVo.setKey(String.valueOf(dic.getAttr()));
                    fixedVo.setValue(dic.getName());
                    fixedList.add(fixedVo);
                }
            }
        }

        return fixedList;
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_CONTENT_MANAGE, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }
}
