package com.sjdf.platform.pageContent.util;

import com.sjdf.platform.dictionary.bean.AttachmentConfigure;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.pageContent.bean.ContentManageBean;

import java.io.File;

/**
 * 页面内容辅助工具类
 *
 * @author laberwu
 */
public abstract class PageContentUtil {

    /**
     * 获取临时文件目录
     *
     * @param contentBean 页面内容管理
     */
    public static String getTempPath(ContentManageBean contentBean) {
        String tempDir = ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.PAGE_CONTENT_TEMP_SAVE_PATH);
        String destPath = null;
        if (contentBean != null) {
            destPath = tempDir + File.separator + contentBean.getVersionId() + File.separator + contentBean.getId() + ".jpg";
        }

        return destPath;
    }

    /**
     * 获取实际存放目录
     *
     * @param imageUrl 图片地址
     */
    public static String getUploadPath(String imageUrl) {
        return ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.PAGE_CONTENT_SAVE_PATH) + imageUrl;
    }

    /**
     * 获取相对地址
     *
     * @param contentBean 页面内容管理
     */
    public static String getRelativePath(ContentManageBean contentBean) {
        String destPath = "";
        if (contentBean != null) {
            destPath = File.separator + contentBean.getVersionId() + File.separator + contentBean.getId() + ".jpg";
        }

        return destPath;
    }
}
