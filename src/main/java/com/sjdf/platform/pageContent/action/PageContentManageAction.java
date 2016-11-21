package com.sjdf.platform.pageContent.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.pageContent.bean.ContentManageBean;
import com.sjdf.platform.pageContent.bean.VersionManageBean;
import com.sjdf.platform.pageContent.service.ContentManageService;
import com.sjdf.platform.pageContent.service.VersionManageService;
import com.sjdf.platform.pageContent.util.PageContentUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面内容管理Action
 *
 * @author laberwu
 */
public class PageContentManageAction extends BaseAction {
    private static final long serialVersionUID = -6967010799762822841L;
    /** 显示临时图片链接 */
    private static final String SHOW_TEMP_ACTION = "admin/common/PageContentManageAction!showTempImage.action";
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(PageContentManageAction.class);
    /** 页面内容管理服务 */
    @Autowired
    private ContentManageService contentService;
    /** 页面内容版本管理服务 */
    @Autowired
    private VersionManageService versionService;
    /** 页面内容管理对象 */
    private ContentManageBean contentManageBean;
    /** 页面内容版本管理对象 */
    private VersionManageBean versionManageBean;
    /** 页面内容管理列表 */
    private List<ContentManageBean> contentList;
    /** 版本列表 */
    private List<VersionManageBean> versionList;
    /** 节点对应版本id */
    private long versionId;
    /** 节点对应数据库id */
    private long databaseId;
    /** 节点名称 */
    private String name;
    /** 上传文件 */
    private File file;
    /** 上传文件名后缀 */
    private String fileNameSuffixes;

    public String turnToLeft() {
        return "left";
    }

    /**
     * 跳转至树形页面
     *
     * @return result
     */
    public String turnToIndex() {
        versionManageBean = versionService.get(VersionManageBean.class, versionId);
        return "index";
    }

    /**
     * 上传图片
     *
     * @return result
     */
    public String uploadImage() {
        Map<String, String> map = new HashMap<>();

        // 验证
        if (file == null) {
            map.put("errmsg", "文件不能为空");
        } else if (!StringUtils.hasText(fileNameSuffixes) || !("jpg".equalsIgnoreCase(fileNameSuffixes) || "gif".equals(fileNameSuffixes))) {
            map.put("errmsg", "请上传jpg或者gif文件");
        } else {
            // 获取页面内容对象
            ContentManageBean contentBean = contentService.get(ContentManageBean.class, databaseId);
            if (contentBean != null) {
                String destPath = PageContentUtil.getTempPath(contentBean);
                try {
                    FileUtils.copyFile(file, new File(destPath));
                    map.put("tmpPath", destPath);
                    map.put("tmpAction", SHOW_TEMP_ACTION + "?databaseId=" + contentBean.getId());
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        JSONObject jsonObject = JSONObject.fromObject(map);
        printWrite(jsonObject.toString());
        return NONE;
    }

    /**
     * 显示临时图片
     *
     * @return result
     */
    public String showTempImage() {
        ContentManageBean bean = contentService.get(ContentManageBean.class, databaseId);
        if (bean != null) {
            String dest = PageContentUtil.getTempPath(bean);
            File destFile = new File(dest);
            printImage(destFile);
        }
        return NONE;
    }

    /**
     * 修改
     *
     * @return result
     * @throws IOException
     */
    public String modify() throws IOException {
        String logMsg = contentService.modify(contentManageBean);
        logger.info(logMsg);
        databaseId = contentManageBean.getId();
        return "redirect_modify";
    }

    /**
     * 信息详细显示，修改
     *
     * @return result
     */
    public String nodeInfo() {
        contentManageBean = contentService.get(ContentManageBean.class, databaseId);
        versionManageBean = versionService.get(VersionManageBean.class, contentManageBean.getVersionId());
        return "modify";
    }

    /**
     * 添加树形节点
     *
     * @return result
     */
    public String addNode() {
        String jsonStr = contentService.addNode(databaseId, versionId);
        printWrite(jsonStr);
        return NONE;
    }

    /**
     * 删除树形节点（包含子节点）
     *
     * @return result
     */
    public String delNode() {
        String logMsg;
        try {
            logMsg = contentService.delAllNodeById(databaseId);
            logger.info(logMsg);
        } catch (Exception e) {
            logger.error("删除树形节点失败", e);
        }

        return NONE;
    }

    /**
     * 重命名节点
     *
     * @return result
     */
    public String reNameNode() {
        String jsonStr = contentService.reNameNode(databaseId, versionId, name);
        logger.info(jsonStr);
        return NONE;
    }

    /**
     * 得到树形数据
     *
     * @return result
     */
    public String findJSON() {
        if (versionId == 0) {
            versionId = 1;
        }

        String jsonStr = contentService.findJSON(versionId, databaseId);
        printWrite(jsonStr);

        return NONE;
    }

    /**
     * 通过条件得到节点列表
     *
     * @return result
     */
    public String list4Node() {
        contentList = contentService.findList(contentManageBean, page);
        return "content_list";
    }

    /**
     * 列表排序
     *
     * @return result
     */
    public String sortList() {
        String logMsg = contentService.updateSortList(contentList);
        logger.info(logMsg);
        return "redirect";
    }

    /**
     * 获取所有的版本列表
     *
     * @return result
     */
    public String list4Version() {
        versionList = versionService.findList(versionManageBean, page);
        return "version_list";
    }

    /**
     * 复制版本
     *
     * @return result
     */
    public String copyVersion() {
        tipMessage = "复制版本成功";
        tipMessageUrl = "admin/common/PageContentManageAction!turnToIndex.action?versionId=" + versionId;
        try {
            long newVersionId = versionService.addCopyVersionById(versionId, versionManageBean);
            tipMessageUrl = "admin/common/PageContentManageAction!turnToIndex.action?versionId=" + newVersionId;
            return "success";
        } catch (Exception e) {
            logger.error("复制版本失败", e);
            tipMessage = "复制版本失败:" + e.getMessage();
            return "error";
        }
    }

    /**
     * 准备复制版本
     *
     * @return result
     */
    public String preCopyVersion() {
        return "version_copy";
    }

    /**
     * 准备修改版本
     *
     * @return result
     */
    public String preModifyVersion() {
        versionManageBean = versionService.get(VersionManageBean.class, versionId);
        return "version_modify";
    }

    /**
     * 修改版本
     *
     * @return result
     */
    public String modifyVersion() {
        tipMessage = "修改成功";
        tipMessageUrl = "admin/common/PageContentManageAction!preModifyVersion.action?versionId=" + versionManageBean.getId();
        String logMsg = versionService.modify(versionManageBean);
        logger.info(logMsg);

        return "success";
    }

    /**
     * 删除版本
     *
     * @return result
     */
    public String delVersion() {
        tipMessage = "删除版本成功";
        tipMessageUrl = "admin/common/PageContentManageAction!list4Version.action";
        try {
            String logMsg = versionService.delVersionById(versionId);
            logger.info(logMsg);
        } catch (Exception e) {
            logger.error("删除版本失败", e);
            tipMessage = "删除版本失败：" + e.getMessage();
            return "error";

        }

        return "success";
    }

    /**
     * 推送内容
     *
     * @return result
     */
    public String pushContent() {
        String msg = versionService.pushContentById(versionId);
        printWrite(msg);
        return NONE;
    }

    /**
     * 推送版本
     *
     * @return result
     */
    public String pushVersion() {
        String msg = versionService.updatePushVersion(versionId);
        printWrite(msg);
        return NONE;
    }

    /**
     * 推送预览版本
     *
     * @return result
     */
    public String pushPreviewVersion() {
        String msg = versionService.pushPreviewVersion(versionId);
        printWrite(msg);
        return NONE;
    }

    /**
     * 推送预览内容
     *
     * @return result
     */
    public String pushPreviewContent() {
        String msg = versionService.pushPreviewContent(versionId, databaseId);
        printWrite(msg);

        return NONE;
    }

    /**
     * 推送预览
     *
     * @return result
     */
    public String pushPreview() {
        String msg = versionService.pushPreview(versionId, databaseId);
        printWrite(msg);

        return NONE;
    }


    /**
     * 销毁预览缓存
     *
     * @return result
     */
    public String pushDestroyPreview() {
        String msg = versionService.pushDestroyPreview(versionId);
        printWrite(msg);
        return NONE;
    }

    public ContentManageBean getContentManageBean() {
        return contentManageBean;
    }

    public void setContentManageBean(ContentManageBean contentManageBean) {
        this.contentManageBean = contentManageBean;
    }

    public VersionManageBean getVersionManageBean() {
        return versionManageBean;
    }

    public void setVersionManageBean(VersionManageBean versionManageBean) {
        this.versionManageBean = versionManageBean;
    }

    public List<ContentManageBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentManageBean> contentList) {
        this.contentList = contentList;
    }

    public List<VersionManageBean> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<VersionManageBean> versionList) {
        this.versionList = versionList;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(long databaseId) {
        this.databaseId = databaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileNameSuffixes() {
        return fileNameSuffixes;
    }

    public void setFileNameSuffixes(String fileNameSuffixes) {
        this.fileNameSuffixes = fileNameSuffixes;
    }

    public List<FixedVo> getWhetherList() {
        return ConfigManager.getInstance().getFixedList(WhetherState.class);
    }

    public List<FixedVo> getPageMarkList() {
        return contentService.findPageMarkListById(databaseId);
    }
}
