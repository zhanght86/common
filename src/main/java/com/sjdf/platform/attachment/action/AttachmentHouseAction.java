package com.sjdf.platform.attachment.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.attachment.bean.Attachment;
import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.attachment.service.AttachmentHouseService;
import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2013-4-13 下午3:14:19
 * 附件库信息控制器（Action）
 *
 * @author frank
 */
public class AttachmentHouseAction extends BaseAction {
    private static final long serialVersionUID = 2882157632658724038L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentHouseAction.class);
    /** 附件Service */
    @Autowired
    private AttachmentHouseService attachmentHouseService;
    /** 数据库附件集合 */
    private List<Attachment> sourceAttachmentList;
    /** 附件查询条件VO */
    private AttachmentHouseVo attachmentHouseVo;
    /** 外部接口，传递参数XML数据 */
    private String xml;
    /** 附件显示的URL */
    private String attachmentDisplayUrl;
    /** 附件下载相关*/
    /** contentType：响应头类别 */
    private String contentType;
    /** inputStream：文件流 */
    private InputStream inputStream;
    /** fileName:文件名称 */
    private String fileName;
    /** 上传的File */
    private File upload;
    /** 上传的文件名称 */
    private String uploadFileName;
    /** 上传的文件名称 */
    private String uploadContentType;

    /**
     * 显示图片
     */
    public void showImage() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("image/jpeg");
        try {
            //替换临时路径
            String tempDir = ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.ATTACHMENT_TEMP_PATH);
            if (attachmentDisplayUrl.contains(tempDir)) {
                attachmentDisplayUrl = attachmentDisplayUrl.replace(tempDir, AttachmentHelper.SAVE_ROOT_PATH);
            }
            outImage(attachmentDisplayUrl, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 根据图片路径，访问图片并通过响应输出流输出
     *
     * @param path     图片路径
     * @param response 响应类
     * @throws Exception
     */
    private void outImage(String path, HttpServletResponse response) throws Exception {
        FileInputStream is = null;
        ServletOutputStream sos = null;
        try {
            is = new FileInputStream(path);
            sos = response.getOutputStream();
            byte[] buffer = new byte[CommonPlatformConstant.LENGTH_1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                sos.write(buffer, 0, len);
            }
            is.close();
            sos.flush();
            sos.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
            if (sos != null) {
                sos.close();
            }
        }
    }

    /**
     * （有条件的）显示附件列表（附件管理页面）
     */
    public String showAttachmentList() {
        try {
            //默认按照更新时间倒序排序
            sourceAttachmentList = attachmentHouseService.findAttachmentPageList(attachmentHouseVo, page, Order.desc("updateTime"));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return "showAttachmentList";
    }

    /**
     * 显示单个附件（附件管理页面）
     */
    public String showAttachment() {
        try {
            attachmentHouseVo = attachmentHouseService.getAttachmentHouseVo(attachmentHouseVo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return "post";
    }

    /**
     * 删除附件（附件管理页面）
     */
    public String delAttachment() {
        try {
            attachmentHouseVo.setMarkDelete(Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.YES)));
            attachmentHouseService.updateAttachment(attachmentHouseVo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return "delAttachment";
    }

    /**
     * 更新单个附件（附件管理页面）
     */
    public String modifyAttachment() {
        try {
            //文件格式
            String dotnet = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.EN_PERIOD);
            String currentFormat = uploadFileName.substring(uploadFileName.lastIndexOf(dotnet) + 1);
            attachmentHouseVo.setFormat(getAttachmentFormatStringMap().get(currentFormat));

            //处理File为base64编码后的String字符串
            attachmentHouseVo.setAttachmentFileString(AttachmentHelper.getFileString(upload));


            List<AttachmentHouseVo> voList = new ArrayList<AttachmentHouseVo>();
            voList.add(attachmentHouseVo);
            attachmentHouseService.updateAttachmentList(voList);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

        return "afterModify";
    }

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
     * 下载附件
     */
    public String downloadAttachment() {
        try {
            Attachment attachment = attachmentHouseService.getAttachment(attachmentHouseVo.getId());
            String filePath = AttachmentHelper.SAVE_ROOT_PATH + attachment.getPath();
            inputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(filePath)));
            fileName = filePath.substring(filePath.lastIndexOf(AttachmentHelper.SLASH_SYMBOL) + 1);
            contentType = "octet-stream";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return "download";
    }

    public List<Attachment> getSourceAttachmentList() {
        return sourceAttachmentList;
    }

    public void setSourceAttachmentList(List<Attachment> sourceAttachmentList) {
        this.sourceAttachmentList = sourceAttachmentList;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public AttachmentHouseVo getAttachmentHouseVo() {
        return attachmentHouseVo;
    }

    public void setAttachmentHouseVo(AttachmentHouseVo attachmentHouseVo) {
        this.attachmentHouseVo = attachmentHouseVo;
    }

    public String getAttachmentDisplayUrl() {
        return attachmentDisplayUrl;
    }

    public void setAttachmentDisplayUrl(String attachmentDisplayUrl) {
        this.attachmentDisplayUrl = attachmentDisplayUrl;
    }

    public AttachmentHouseService getAttachmentHouseService() {
        return attachmentHouseService;
    }

    public void setAttachmentHouseService(
            AttachmentHouseService attachmentHouseService) {
        this.attachmentHouseService = attachmentHouseService;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /** 附件所属系统类别 */
    @SuppressWarnings("unchecked")
    public Map<Long, String> getSystemTypeMap() {
        List<SystemType> systemTypeList = (List<SystemType>) DictionaryHelper.getDictionary(SystemType.class);
        Map<Long, String> systemTypeMap = new HashMap<Long, String>();
        for (SystemType systemType : systemTypeList) {
            systemTypeMap.put((Long) systemType.getAttr(), systemType.getCnName());
        }
        return systemTypeMap;
    }

    /** 附件所属用途类别 */
    @SuppressWarnings("unchecked")
    public Map<Long, String> getAttachmentUseTypeMap() {
        List<AttachmentUseType> attachmentUseTypeList = (List<AttachmentUseType>) DictionaryHelper.getDictionary(AttachmentUseType.class);
        Map<Long, String> attachmentUseTypeMap = new HashMap<Long, String>();
        for (AttachmentUseType attachmentUseType : attachmentUseTypeList) {
            attachmentUseTypeMap.put((Long) attachmentUseType.getAttr(), attachmentUseType.getCnName());
        }
        return attachmentUseTypeMap;
    }

    /** 附件用途代码 */
    @SuppressWarnings("unchecked")
    public Map<Long, String> getAttachmentUseCodeMap() {
        List<AttachmentUseCode> attachmentUseCodeList = (List<AttachmentUseCode>) DictionaryHelper.getDictionary(AttachmentUseCode.class);
        Map<Long, String> attachmentUseCodeMap = new HashMap<Long, String>();
        for (AttachmentUseCode attachmentUseCode : attachmentUseCodeList) {
            attachmentUseCodeMap.put((Long) attachmentUseCode.getAttr(), attachmentUseCode.getCnName());
        }
        return attachmentUseCodeMap;
    }

    /** 附件格式 */
    @SuppressWarnings("unchecked")
    public Map<Long, String> getAttachmentFormatMap() {
        List<AttachmentFormat> attachmentFormatList = (List<AttachmentFormat>) DictionaryHelper.getDictionary(AttachmentFormat.class);
        Map<Long, String> attachmentFormatMap = new HashMap<Long, String>();
        for (AttachmentFormat attachmentFormat : attachmentFormatList) {
            attachmentFormatMap.put((Long) attachmentFormat.getAttr(), attachmentFormat.getCnName());
        }
        return attachmentFormatMap;
    }

    /** 附件格式 */
    public Map<String, Long> getAttachmentFormatStringMap() {
        return AttachmentHelper.getAttachmentFormatStringMap();
    }


}
