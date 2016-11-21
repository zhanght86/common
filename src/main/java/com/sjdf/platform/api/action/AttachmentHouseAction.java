package com.sjdf.platform.api.action;

import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.attachment.service.AttachmentHouseService;
import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.ResultVo;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.image.helper.ImageHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Create at 2013-4-13 下午3:14:19
 * 附件库信息控制器（Action）
 *
 * @author frank
 */
public class AttachmentHouseAction extends BaseAction {
    private static final long serialVersionUID = 2882157632658724038L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentHouseAction.class);

    /**
     * 附件Service
     */
    @Autowired
    private AttachmentHouseService attachmentHouseService;

    /** 外部接口，传递参数XML数据 */
    private String xml;
    /** 外部接口，传递数据 */
    private File file;

    /**
     * 更新多个附件（外部接口）
     */
    public void modifyAttachmentList() {
        try {
            if (!MD5.md5(new StringBuilder(AttachmentHelper.CONNPWD).append(vertime).append(xml).toString()).equals(vercode)) {
                printErrorXml("无效访问");
            } else {
                //解析数据
                List<AttachmentHouseVo> voConditionList = AttachmentHelper.parse(xml);
                //校验数据
                AttachmentHelper.verifyAttachmentVoList(voConditionList, OperatorAction.MODIFY);
                //保存数据
                List<AttachmentHouseVo> voDBList = attachmentHouseService.updateAttachmentList(voConditionList);
                //响应数据
                printWrite(AttachmentHelper.parse(voDBList, AttachmentHelper.SAVE_ROOT_PATH));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printErrorXml(e.getMessage());
        }
    }

    /**
     * 保存多个附件（外部接口）
     */
    public void storeAttachmentList() {
        try {
            if (!MD5.md5(new StringBuilder(AttachmentHelper.CONNPWD).append(vertime).append(xml).toString()).equals(vercode)) {
                printErrorXml("无效访问");
            } else {
                //解析数据
                List<AttachmentHouseVo> voConditionList = AttachmentHelper.parse(xml);
                //校验数据
                AttachmentHelper.verifyAttachmentVoList(voConditionList, OperatorAction.ADD);
                //保存数据
                List<AttachmentHouseVo> voDBList = attachmentHouseService.saveAttachmentList(voConditionList);
                //响应数据
                printWrite(AttachmentHelper.parse(voDBList, AttachmentHelper.SAVE_ROOT_PATH));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printErrorXml(e.getMessage());
        }
    }

    /**
     * 获取多个附件（外部接口）
     */
    public void getAttachmentList() {
        try {
            if (!MD5.md5(new StringBuilder(AttachmentHelper.CONNPWD).append(vertime).append(xml).toString()).equals(vercode)) {
                printErrorXml("无效访问");
            } else {
                //解析数据
                List<AttachmentHouseVo> voConditionList = AttachmentHelper.parse(xml);
                //校验数据
                AttachmentHelper.verifyAttachmentVoList(voConditionList, OperatorAction.QUERY);
                //保存数据
                List<AttachmentHouseVo> voDBList = attachmentHouseService.updateOrFindAttachmentList(voConditionList);

                //响应数据
                printWrite(AttachmentHelper.parse(voDBList, AttachmentHelper.SAVE_ROOT_PATH));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printErrorXml(e.getMessage());
        }
    }

    /**
     * 保存图片到静态服务器
     */
    public String saveImage() {
        try {
            // 把文件转换为xml数据
            String xmlData = FileUtils.readFileToString(file, "UTF-8");
            // 解析xml数据，保存图片到静态服务器
            ImageHelper.saveOrUpdateImage(xmlData);
            printWrite(new ResultVo(ResultVo.SUCCESS_CODE, "success").toXml());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            printWrite(new ResultVo(ResultVo.FAIL_CODE, e.getMessage()).toXml());
        }

        return NONE;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public AttachmentHouseService getAttachmentHouseService() {
        return attachmentHouseService;
    }

    public void setAttachmentHouseService(AttachmentHouseService attachmentHouseService) {
        this.attachmentHouseService = attachmentHouseService;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
