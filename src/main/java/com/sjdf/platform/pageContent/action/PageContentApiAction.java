package com.sjdf.platform.pageContent.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.ResultVo;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.pageContent.helper.PageContentHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * 外部接口，用于操作缓存数据
 *
 * @author laberwu
 */
public class PageContentApiAction extends BaseAction {
    private static final long serialVersionUID = -1128043343441145687L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PageContentApiAction.class);
    /** 触发请求内容 */
    private File file;

    /**
     * 刷新内容缓存（正式生效）
     *
     * @return result
     */
    public String flushContent() {
        try {
            // 把文件转换为xml数据
            String xmlData = FileUtils.readFileToString(file, "UTF-8");
            // 解析xml数据, 刷新缓存
            PageContentHelper.flushContent(xmlData, false);
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    /**
     * 刷新预览内容缓存
     *
     * @return result
     */
    public String flushPreviewContent() {
        try {
            // 把文件转换为xml数据
            String xmlData = FileUtils.readFileToString(file, "UTF-8");
            // 更新预览缓存
            PageContentHelper.flushPreviewContent(xmlData);
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    /**
     * 刷新版本缓存（正式切换）
     *
     * @return result
     */
    public String flushVersion() {
        try {
            // 把文件转换为xml数据
            String xmlData = FileUtils.readFileToString(file, "UTF-8");
            // 启用版本
            PageContentHelper.init(xmlData, false);
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    /**
     * 刷新预览版本缓存
     *
     * @return result
     */
    public String flushPreviewVersion() {
        try {
            // 把文件转换为xml数据
            String xmlData = FileUtils.readFileToString(file, "UTF-8");
            // 更新预览缓存
            PageContentHelper.init(xmlData, true);
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    /**
     * 销毁预览缓存
     *
     * @return result
     */
    public String flushDestroyPreview() {
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        // MD5校验
        String md5Str = MD5.md5(connpwd + vertime);
        if (!md5Str.equals(vercode)) {
            printErrorXml("校验失败，无效访问");
            return NONE;
        }

        try {
            PageContentHelper.flushDestroyPreview();
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
