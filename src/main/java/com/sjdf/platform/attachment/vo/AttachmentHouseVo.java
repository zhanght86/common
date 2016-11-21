package com.sjdf.platform.attachment.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.attachment.bean.Attachment;
import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.bean.VerifyResultCode;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 附件Vo
 *
 * @author Admin
 */
public class AttachmentHouseVo {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentHouseVo.class);
    /** 附件主键ID */
    private Long id;

    /** 附件所属用户ID */
    private Long userId;

    /** 附件操作人用户名 */
    private String username;

    /** 附件特别标识 */
    private String attachmentSpecialMark;

    /** 附件用途代码 */
    private Long attachmentUseCode;

    /** 附件用途类型 */
    private Long attachmentUseType;

    /** 用途类型标识 */
    private String attachmentUseTypeMark;

    /** 附件所属系统 */
    private Long systemType;

    /** 附件地址 */
    private String path;

    /** 子系统附件保存路径 */
    private String subSystemPath;

    /** 审计结果 */
    private Long auditResult;

    /** 附件始大小 */
    private Long startSize;

    /** 附件末大小 */
    private Long endSize;

    /** 附件始日期 */
    private String beginDate;

    /** 附件末日期 */
    private String endDate;

    /** 附件格式 */
    private Long format;

    /** 是否删除 */
    private Long markDelete;

    /** 是否改变 */
    private Long whetherChanged;

    /** 附件备注 */
    private String remark;

    /** 附件二进制经 base64 encode 后的String字符串 */
    private String attachmentFileString;

    /** 附件原路径 */
    private String sourcePath;

    /** 附件缩小地址 */
    private String resizePath;

    /** 附件审核未通过的审核意见颜色 */
    private Long fontColor;

    /** 特别标识 */
    private Map<Long, String> specialMarkMap = new HashMap<Long, String>();

    /** 上传文件名称 */
    private String uploadFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAttachmentSpecialMark() {
        return attachmentSpecialMark;
    }

    public void setAttachmentSpecialMark(String attachmentSpecialMark) {
        this.attachmentSpecialMark = attachmentSpecialMark;
    }

    public Long getAttachmentUseCode() {
        return attachmentUseCode;
    }

    public void setAttachmentUseCode(Long attachmentUseCode) {
        this.attachmentUseCode = attachmentUseCode;
    }

    public Long getAttachmentUseType() {
        return attachmentUseType;
    }

    public void setAttachmentUseType(Long attachmentUseType) {
        this.attachmentUseType = attachmentUseType;
    }

    public Long getSystemType() {
        return systemType;
    }

    public void setSystemType(Long systemType) {
        this.systemType = systemType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        setSourcePath(path);
    }

    public Long getStartSize() {
        return startSize;
    }

    public void setStartSize(Long startSize) {
        this.startSize = startSize;
    }

    public Long getEndSize() {
        return endSize;
    }

    public void setEndSize(Long endSize) {
        this.endSize = endSize;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getFormat() {
        return format;
    }

    public void setFormat(Long format) {
        this.format = format;
    }

    public Long getMarkDelete() {
        return markDelete;
    }

    public void setMarkDelete(Long markDelete) {
        this.markDelete = markDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachmentFileString() {
        return attachmentFileString;
    }

    public void setAttachmentFileString(String attachmentFileString) {
        this.attachmentFileString = attachmentFileString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getWhetherChanged() {
        return whetherChanged;
    }

    public void setWhetherChanged(Long whetherChanged) {
        this.whetherChanged = whetherChanged;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSubSystemPath() {
        return subSystemPath;
    }

    public void setSubSystemPath(String subSystemPath) {
        this.subSystemPath = subSystemPath;
    }

    public String getAttachmentUseTypeMark() {
        return attachmentUseTypeMark;
    }

    public void setAttachmentUseTypeMark(String attachmentUseTypeMark) {
        this.attachmentUseTypeMark = attachmentUseTypeMark;
    }

    public Long getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Long auditResult) {
        this.auditResult = auditResult;
    }

    public Map<Long, String> getSpecialMarkMap() {
        return specialMarkMap;
    }

    public void setSpecialMarkMap(Map<Long, String> specialMarkMap) {
        this.specialMarkMap = specialMarkMap;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getResizePath() {
        return resizePath;
    }

    public void setResizePath(String resizePath) {
        this.resizePath = resizePath;
    }

    public Long getFontColor() {
        return fontColor;
    }

    public void setFontColor(Long fontColor) {
        this.fontColor = fontColor;
    }

    @Override
    public String toString() {
        return "vo[id=" + id + ", attachmentUseCode=" + attachmentUseCode
                + ", userId=" + userId
                + ", username=" + username
                + ", attachmentSpecialMark=" + attachmentSpecialMark
                + ", attachmentUseType=" + attachmentUseType
                + ", attachmentUseTypeMark=" + attachmentUseTypeMark
                + ", systemType=" + systemType
                + ", format=" + format
                + ", markDelete=" + markDelete
                + ", whetherChanged=" + whetherChanged
                + ", uploadFileName=" + uploadFileName
                + " ]";
    }

    /**
     * 转化为 附件实体 Attachment
     */
    public void toAttachment(Attachment attachment, long operatorAction) {
        try {
            //附件特殊标识
            if (!Tools.isEmpty(attachmentSpecialMark)) {
                attachment.setAttachmentSpecialMark(attachmentSpecialMark);
            }
            //附件用途代码
            if (Tools.longIsNotNUllAndZero(attachmentUseCode)) {
                attachment.setAttachmentUseCode(attachmentUseCode);
            }
            //附件用途类型
            if (Tools.longIsNotNUllAndZero(attachmentUseType)) {
                attachment.setAttachmentUseType(attachmentUseType);
            }
            //附件创建时间
            if (Tools.compareLong(operatorAction, OperatorAction.ADD)) {
                attachment.setCreateTime(DateUtils.getSysDateOfDate());
            }
            //附件更新时间
            if (Tools.compareLong(operatorAction, OperatorAction.ADD) || Tools.compareLong(operatorAction, OperatorAction.MODIFY)) {
                attachment.setUpdateTime(DateUtils.getSysDateOfDate());
            }
            //标记附件未被删除
            if (Tools.longIsNotNUllAndZero(markDelete)) {
                attachment.setMarkDelete(markDelete);
            } else {
                attachment.setMarkDelete(Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.NO)));
            }
            //标记附件备注
            if (!Tools.isEmpty(remark)) {
                attachment.setRemark(remark);
            }
            //标记附件所属系统类别
            if (Tools.longIsNotNUllAndZero(systemType)) {
                attachment.setSystemType(systemType);
            }
            //标记附件所属用户ID
            if (Tools.longIsNotNUllAndZero(userId)) {
                attachment.setUserId(userId);
            }
            //标记附件创建人
            if (!Tools.isEmpty(username) && Tools.compareLong(operatorAction, OperatorAction.ADD)) {
                attachment.setCreateUser(username);
                attachment.setUpdateUser(username);
            }
            //标记附件更新人
            if (!Tools.isEmpty(username) && Tools.compareLong(operatorAction, OperatorAction.MODIFY)) {
                attachment.setUpdateUser(username);
            }
            //标记附件格式
            if (Tools.longIsNotNUllAndZero(format) && (Tools.compareLong(operatorAction, OperatorAction.MODIFY) || Tools.compareLong(operatorAction, OperatorAction.ADD))) {
                attachment.setFormat(format);
            }
            //子系统Path
            if (!Tools.isEmpty(subSystemPath)) {
                attachment.setSubSystemPath(subSystemPath);
            }
            //审计结果
            if (null != auditResult) {
                attachment.setAuditResult(auditResult);
            } else {
                attachment.setAuditResult(VerifyResultCode.NOT_VERIFY);
            }
            //附件用途类型 标识
            if (!Tools.isEmpty(attachmentUseTypeMark)) {
                attachment.setAttachmentUseTypeMark(attachmentUseTypeMark);
            }
            //字体颜色
            if (null != fontColor) {
                attachment.setFontColor(fontColor);
            } else {
                attachment.setFontColor(0L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }


    /**
     * 附件转化为XML数据
     *
     * @param attachmentPath 是否拼接附件
     * @return 附件XML数据
     */
    public String toXml(String attachmentPath) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<attachment>");
        xml.append("<attachmentId><![CDATA[").append(id).append("]]></attachmentId>");
        xml.append("<path><![CDATA[").append(path).append("]]></path>");
        xml.append("<userId><![CDATA[").append(userId).append("]]></userId>");
        xml.append("<username><![CDATA[").append(username).append("]]></username>");
        xml.append("<attachmentUseCode><![CDATA[").append(attachmentUseCode).append("]]></attachmentUseCode>");
        xml.append("<attachmentUseType><![CDATA[").append(attachmentUseType).append("]]></attachmentUseType>");
        xml.append("<attachmentUseTypeMark><![CDATA[").append(attachmentUseTypeMark).append("]]></attachmentUseTypeMark>");
        xml.append("<systemType><![CDATA[").append(systemType).append("]]></systemType>");
        xml.append("<remark><![CDATA[").append(remark).append("]]></remark>");
        //服务端
        if (!Tools.isEmpty(path) && Tools.compareLong(whetherChanged, Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.YES)))) {
            xml.append("<byte><![CDATA[").append(AttachmentHelper.getFileString(new File(attachmentPath + path))).append("]]></byte>");
            //客户端
        } else if (!Tools.isEmpty(attachmentFileString)) {
            xml.append("<byte><![CDATA[").append(attachmentFileString).append("]]></byte>");
        }
        xml.append("<whetherChanged><![CDATA[").append(whetherChanged).append("]]></whetherChanged>");
        xml.append("<markDelete><![CDATA[").append(markDelete).append("]]></markDelete>");
        xml.append("<format><![CDATA[").append(format).append("]]></format>");
        xml.append("<sourcePath><![CDATA[").append(sourcePath).append("]]></sourcePath>");
        xml.append("<subSystemPath><![CDATA[").append(subSystemPath).append("]]></subSystemPath>");
        xml.append("<auditResult><![CDATA[").append(auditResult).append("]]></auditResult>");
        xml.append("<fontColor><![CDATA[").append(fontColor).append("]]></fontColor>");
        if (!specialMarkMap.isEmpty()) {
            xml.append("<attachmentSpecialMark>");
            for (Long key : specialMarkMap.keySet()) {
                xml.append("<element key=\"").append(key).append("\" value=\"").append(specialMarkMap.get(key)).append("\" />");
            }
            xml.append("</attachmentSpecialMark>");
        } else if (!Tools.isEmpty(attachmentSpecialMark)) {
            xml.append("<attachmentSpecialMark>").append("<![CDATA[")
                    .append(attachmentSpecialMark).append("]]>").append("</attachmentSpecialMark>");
        }
        //创建时间
        if (!Tools.isEmpty(beginDate)) {
            xml.append("<createTime><![CDATA[").append(beginDate).append("]]></createTime>");
        }
        //更新时间
        if (!Tools.isEmpty(endDate)) {
            xml.append("<updateTime><![CDATA[").append(endDate).append("]]></updateTime>");
        }

        xml.append("</attachment>");
        return xml.toString();
    }

    /**
     * 自定义设置附件 base64 编码后的String信息
     *
     * @param filePath 附件路径
     */
    public void selfSetAttachmentFileString(String filePath) {
        setAttachmentFileString(AttachmentHelper.getFileString(new File(filePath)));
    }

    /**
     * 附件Vo 自检
     *
     * @param errorBuilder   自检异常Builder
     * @param operatorAction 操作动作
     */
    public void verifySelf(StringBuilder errorBuilder, long operatorAction) {
        if (Tools.compareLong(operatorAction, OperatorAction.QUERY) && Tools.longIsNotNUllAndZero(id)) {
            return;
        }
        if (Tools.compareLong(operatorAction, OperatorAction.MODIFY) && !Tools.longIsNotNUllAndZero(id)) {
            errorBuilder.append("附件主键ID有误！");
        } else {
            return;
        }
        if (!Tools.longIsNotNUllAndZero(userId)) {
            errorBuilder.append("附件所属用户ID有误！");
        }
        if (!Tools.longIsNotNUllAndZero(attachmentUseCode)) {
            errorBuilder.append("附件用途代码有误！");
        }
        if (!Tools.longIsNotNUllAndZero(attachmentUseType)) {
            errorBuilder.append("附件用途类型有误！");
        }
        if (!Tools.longIsNotNUllAndZero(systemType)) {
            errorBuilder.append("附件所属系统有误！");
        }
        if (Tools.compareLong(operatorAction, OperatorAction.MODIFY) || Tools.compareLong(operatorAction, OperatorAction.ADD)) {
            if (!Tools.longIsNotNUllAndZero(format)) {
                errorBuilder.append("附件格式代码有误！");
            }
            if (Tools.isEmpty(username)) {
                errorBuilder.append("附件操作人有误！");
            }
        }
    }
}
