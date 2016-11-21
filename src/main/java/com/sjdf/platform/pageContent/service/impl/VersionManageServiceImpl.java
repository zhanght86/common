package com.sjdf.platform.pageContent.service.impl;

import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.ResultVo;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.pageContent.bean.ContentManageBean;
import com.sjdf.platform.pageContent.bean.VersionManageBean;
import com.sjdf.platform.pageContent.helper.PageContentHelper;
import com.sjdf.platform.pageContent.service.ContentManageService;
import com.sjdf.platform.pageContent.service.VersionManageService;
import com.sjdf.platform.pageContent.util.PageContentUtil;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 页面内容版本管理实现
 *
 * @author laberwu
 */
@Service
public class VersionManageServiceImpl extends BaseServiceImpl implements VersionManageService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(VersionManageServiceImpl.class);
    private static final String FAIL = "fail";
    private static final String SUCCESS = "success";

    /** 内容管理 */
    @Autowired
    private ContentManageService contentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VersionManageBean> findList(VersionManageBean versionManageBean, Page page) {
        // 构建查询对象
        DetachedCriteria criteria = buildCriteria(versionManageBean);
        // 构建排序参数
        Order[] orders = buildOrderArrays();

        return baseDao.listByCriteria(criteria, page, orders);
    }

    /**
     * 构建排序
     *
     * @return 排序数组
     */
    private Order[] buildOrderArrays() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.asc("enableMarkup"));
        orderList.add(Order.desc("id"));

        Order[] orders = new Order[orderList.size()];

        return orderList.toArray(orders);
    }

    /**
     * 构建查询对象
     *
     * @param versionManageBean 查询条件
     */
    private DetachedCriteria buildCriteria(VersionManageBean versionManageBean) {
        DetachedCriteria criteria = DetachedCriteria.forClass(VersionManageBean.class);
        if (versionManageBean != null) {
            // enableMarkup
            if (versionManageBean.getEnableMarkup() != 0) {
                criteria.add(Restrictions.eq("enableMarkup", versionManageBean.getEnableMarkup()));
            }
            // systemType
            if (versionManageBean.getSystemType() != 0) {
                criteria.add(Restrictions.eq("systemType", versionManageBean.getSystemType()));
            }
        }
        return criteria;
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception
     */
    @Override
    public long addCopyVersionById(long versionId, VersionManageBean versionManageBean) {
        long id = 0;
        try {
            // 获取当前版本
            VersionManageBean currentVersion = get(VersionManageBean.class, versionId);
            if (currentVersion != null) {
                // 依次复制
                // 创建新版本
                VersionManageBean newVersion = new VersionManageBean();
                newVersion.setEnableMarkup(WhetherState.NO); // 默认不启用
                newVersion.setReadOnly(WhetherState.NO); // 不是只读
                newVersion.setPushUrl(currentVersion.getPushUrl()); // 复制推送地址
                newVersion.setSystemType(currentVersion.getSystemType()); // 复制系统类型
                newVersion.setPreviewUrl(currentVersion.getPreviewUrl()); // 复制预览地址
                if (StringUtils.hasText(versionManageBean.getVersionName())) {
                    newVersion.setVersionName(versionManageBean.getVersionName());
                } else {
                    newVersion.setVersionName(currentVersion.getVersionName() + DateUtils.formatDate(new Date())); // 设置版本名称
                }
                // 版本描述
                newVersion.setVersionDescription(versionManageBean.getVersionDescription());

                save(newVersion);
                saveLog(OperatorAction.ADD, LogType.INFO, newVersion.getVersionName(), newVersion.wrapUpdateContent(null, false), null);

                // 复制树
                addCopyTree(newVersion.getId(), versionId, 0, 0);
                id = newVersion.getId();
            }
        } catch (Exception e) {
            LOGGER.error("复制版本", e);
            throw new RuntimeException(e.getMessage());
        }
        return id;
    }

    /**
     * 复制树
     *
     * @param versionId   版本id
     * @param newParentId 顶级查询对象
     * @throws IOException
     */
    private void addCopyTree(long newVersionId, long versionId, long oldParentId, long newParentId) throws IOException {
        List<ContentManageBean> topList = contentService.findNode(versionId, oldParentId);
        if (topList != null && !topList.isEmpty()) {
            for (ContentManageBean tempBean : topList) {
                // 复制单个对象
                ContentManageBean newContent = new ContentManageBean();
                BeanUtils.copyProperties(tempBean, newContent, new String[]{"id", "parentId", "versionId"});
                newContent.setParentId(newParentId); // 设置新关联id
                newContent.setVersionId(newVersionId); // 设置新版本id
                newContent.setReadOnly(WhetherState.NO); // 设置只读为否
                save(newContent);

                // 如果有图片，拷贝图片,更新图片新地址
                if (StringUtils.hasText(tempBean.getImageUrl())) {
                    String oldPath = PageContentUtil.getUploadPath(tempBean.getImageUrl());
                    String newPath = PageContentUtil.getUploadPath(PageContentUtil.getRelativePath(newContent));
                    FileUtils.copyFile(new File(oldPath), new File(newPath));
                    newContent.setImageUrl(PageContentUtil.getRelativePath(newContent));
                    update(newContent);
                }

                saveLog(OperatorAction.ADD, LogType.INFO, newContent.getMenuName(), newContent.wrapUpdateContent(null, false), null);

                // 递归复制
                addCopyTree(newVersionId, versionId, tempBean.getId(), newContent.getId());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String modify(VersionManageBean versionManageBean) {
        VersionManageBean oldVersion = get(VersionManageBean.class, versionManageBean.getId());
        if (oldVersion != null) {
            String info = oldVersion.wrapUpdateContent(versionManageBean, true);
            // 只读，只修改预览地址和推送地址
            if (oldVersion.getReadOnly() == WhetherState.NO) {
                oldVersion.setVersionName(versionManageBean.getVersionName());
                oldVersion.setVersionDescription(versionManageBean.getVersionDescription());
            }

            oldVersion.setPushUrl(versionManageBean.getPushUrl());
            oldVersion.setPreviewUrl(versionManageBean.getPreviewUrl());
            update(oldVersion);

            saveLog(OperatorAction.MODIFY, LogType.INFO, oldVersion.getVersionName(), info, null);
        }
        return SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionManageBean findEnabledVersionBySystemType(long systemType) {
        VersionManageBean result = null;

        VersionManageBean query = new VersionManageBean();
        query.setSystemType(systemType); // 系统类型
        query.setEnableMarkup(WhetherState.YES); // 启用

        List<VersionManageBean> resultList = findList(query, null);
        if (resultList != null && !resultList.isEmpty()) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentManageBean> findContentListBySystem(long systemType) {
        List<ContentManageBean> contentList = new ArrayList<>();
        VersionManageBean version = findEnabledVersionBySystemType(systemType);
        if (version != null) {
            // 根据版本获取数据
            contentList = findContentListByVersion(version.getId());
        }

        return contentList;
    }

    /**
     * 通过版本id查询页面内容数据
     *
     * @param versionId 版本id
     * @return List<ContentManageBean> 页面内容数据集合
     */
    private List<ContentManageBean> findContentListByVersion(long versionId) {
        return baseDao.find("from ContentManageBean where versionId = ? and deleted = ?", versionId, ValidMark.VALID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findXmlBySystem(long systemType) {
        String xml = "";
        List<ContentManageBean> contentList = findContentListBySystem(systemType);
        if (contentList != null && !contentList.isEmpty()) {
            xml = PageContentHelper.toXml(contentList);
        }
        return xml;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String pushContentById(long versionId) {
        String msg = FAIL;
        VersionManageBean version = get(VersionManageBean.class, versionId);
        if (version != null) {
            String url = version.getPushUrl() + "!flushContent.action";
            // 推送缓存
            ResultVo vo = PageContentHelper.pushContent(url);
            if (vo.code == ResultVo.SUCCESS_CODE) {
                // 推送成功后，清空要推送的缓存
                PageContentHelper.clearCacheMap();
                msg = SUCCESS;
            } else {
                msg = "推送失败" + vo.msg;
            }
        }

        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String pushDestroyPreview(long versionId) {
        String msg = FAIL;
        VersionManageBean version = get(VersionManageBean.class, versionId);
        if (version != null) {
            String url = version.getPushUrl() + "!flushDestroyPreview.action";
            ResultVo vo = PageContentHelper.pushDestroyPreview(url);
            msg = vo.msg;
        }
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updatePushVersion(long versionId) {
        String msg = FAIL;
        VersionManageBean version = get(VersionManageBean.class, versionId);
        if (version != null && version.getEnableMarkup() != WhetherState.YES) {
            // 推送地址
            String url = version.getPushUrl() + "!flushVersion.action";
            // 推送数据
            String xmlData = "";
            List<ContentManageBean> contentList = findContentListByVersion(version.getId());
            if (contentList != null && !contentList.isEmpty()) {
                xmlData = PageContentHelper.toXml(contentList);
            }
            ResultVo vo = PageContentHelper.pushVersion(url, xmlData);
            if (vo.code == ResultVo.SUCCESS_CODE) {
                // 更新数据库
                List<VersionManageBean> list = baseDao.find("from VersionManageBean where enableMarkup = ? and systemType = ?", WhetherState.YES, version.getSystemType());
                if (list != null) {
                    for (VersionManageBean tmp : list) {
                        tmp.setEnableMarkup(WhetherState.NO);
                        update(tmp);
                    }
                }
                version.setEnableMarkup(WhetherState.YES);
                msg = SUCCESS;
            } else {
                msg = vo.msg;
            }
        }
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String pushPreviewVersion(long versionId) {
        VersionManageBean version = get(VersionManageBean.class, versionId);
        String preview = FAIL;
        if (version != null) {
            // 推送地址
            String url = version.getPushUrl() + "!flushPreviewVersion.action";
            // 预览地址
            preview = version.getPreviewUrl();

            String xmlData = "";
            List<ContentManageBean> contentList = findContentListByVersion(version.getId());
            if (contentList != null && !contentList.isEmpty()) {
                xmlData = PageContentHelper.toXml(contentList);
            }
            // 推送数据
            PageContentHelper.pushVersion(url, xmlData);
        }

        return preview;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String pushPreviewContent(long versionId, long databaseId) {
        String preview = FAIL;
        if (versionId != 0) {
            // 预览首页
            VersionManageBean version = get(VersionManageBean.class, versionId);
            if (version != null) {
                String url = version.getPushUrl() + "!flushPreviewContent.action";
                // 更改预览临时缓存
                PageContentHelper.pushPreviewContent(url);
                preview = version.getPreviewUrl();
            }
        } else {
            // 预览对应的页面
            ContentManageBean content = get(ContentManageBean.class, databaseId);
            if (content != null) {
                VersionManageBean version = get(VersionManageBean.class, content.getVersionId());
                if (version != null) {
                    String url = version.getPushUrl() + "!flushPreviewContent.action";
                    // 更改预览临时缓存
                    PageContentHelper.pushPreviewContent(url);
                    // 返回预览地址
                    preview = content.getPreviewUrl();
                }
            }
        }

        return preview;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String pushPreview(long versionId, long databaseId) {
        String preview = FAIL;
        // 有版本id,没有页面对象id,表示预览首页
        if (versionId != 0 && databaseId == 0) {
            VersionManageBean version = get(VersionManageBean.class, versionId);
            // 当前版本启用中，更新预览内容
            if (version.getEnableMarkup() == WhetherState.YES) {
                return flushPreviewContent(null, version);
            } else {
                // 推送预览版本
                return flushPreviewVersion(null, version);
            }
        } else if (versionId == 0 && databaseId != 0) {
            // 无版本id,有页面对象，表示预览对应页面
            ContentManageBean content = get(ContentManageBean.class, databaseId);
            VersionManageBean version = get(VersionManageBean.class, content.getVersionId());
            // 当前版本启用中，更新预览内容
            if (version.getEnableMarkup() == WhetherState.YES) {
                return flushPreviewContent(content, version);
            } else {
                return flushPreviewVersion(content, version);
            }
        }

        return preview;
    }

    /**
     * 推送当前启用版本的内容，获取预览地址
     *
     * @param content 页面对象
     * @param version 版本对象
     */
    private String flushPreviewContent(ContentManageBean content, VersionManageBean version) {
        String preview;
        // 验证数据
        if (content != null && !StringUtils.hasText(content.getPreviewUrl())) {
            preview = "预览地址为空";
            return preview;
        }
        if (!StringUtils.hasText(version.getPreviewUrl())) {
            preview = "预览地址为空";
            return preview;
        }

        String url = version.getPushUrl() + "!flushPreviewContent.action";
        // 更改预览临时缓存
        ResultVo vo = PageContentHelper.pushPreviewContent(url);
        if (vo.code == ResultVo.SUCCESS_CODE) {
            if (content != null) {
                preview = SUCCESS + "❤" + content.getPreviewUrl(); // 预览地址
            } else {
                preview = SUCCESS + "❤" + version.getPreviewUrl(); // 首页预览地址
            }
        } else {
            preview = vo.msg;
        }

        return preview;
    }

    /** 推送未启用版本的内容，获取预览地址 */
    private String flushPreviewVersion(ContentManageBean content, VersionManageBean version) {
        String preview;
        // 验证数据
        if (content != null && !StringUtils.hasText(content.getPreviewUrl())) {
            preview = "预览地址为空";
            return preview;
        }
        if (!StringUtils.hasText(version.getPreviewUrl())) {
            preview = "预览地址为空";
            return preview;
        }

        String url = version.getPushUrl() + "!flushPreviewVersion.action";
        String xmlData = "";
        List<ContentManageBean> contentList = findContentListByVersion(version.getId());
        if (contentList != null && !contentList.isEmpty()) {
            xmlData = PageContentHelper.toXml(contentList);
        }
        // 推送数据
        ResultVo vo = PageContentHelper.pushVersion(url, xmlData);
        if (vo.code == ResultVo.SUCCESS_CODE) {
            if (content != null) {
                preview = SUCCESS + "❤" + content.getPreviewUrl(); // 预览地址
            } else {
                preview = SUCCESS + "❤" + version.getPreviewUrl(); // 首页预览地址
            }
        } else {
            preview = vo.msg;
        }

        return preview;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delVersionById(long versionId) {
        VersionManageBean version = get(VersionManageBean.class, versionId);
        if (version != null) {
            // 删除对应版本的页面内容数据
            List<ContentManageBean> contentList = baseDao.find("from ContentManageBean where versionId = ?", versionId);
            if (contentList != null && !contentList.isEmpty()) {
                for (ContentManageBean content : contentList) {
                    // 删除图片
                    try {
                        if (StringUtils.hasText(content.getImageUrl())) {
                            File file = new File(PageContentUtil.getUploadPath(content.getImageUrl()));
                            file.delete();
                        }
                    } catch (Exception e) {
                        LOGGER.error("删除图片失败", e);
                    }
                    // 删除页面内容
                    delete(content);
                }
            }
            // 删除版本
            delete(version);

            saveLog(OperatorAction.DELETE, LogType.INFO, version.getVersionName(), version.wrapUpdateContent(null, false), null);
        }

        return SUCCESS;
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
