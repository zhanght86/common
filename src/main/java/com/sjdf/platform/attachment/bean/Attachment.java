package com.sjdf.platform.attachment.bean;

import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Create at 2013-4-13 下午3:13:47
 * 附件库实体类
 *
 * @author frank
 */
@Entity
@Table(name = "p_attachment_house")
public class Attachment extends BaseBean {
    private static final long serialVersionUID = 2882157632658724037L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(Attachment.class);

    /** 附件所属用户ID */
    private Long userId;

    /** 附件特别标识 */
    @Lob
    @Column(name = "attachmentSpecialMark", columnDefinition = "longtext")
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

    /** 附件缩小地址 */
    private String resizePath;

    /** 子系统附件保存路径 */
    private String subSystemPath;

    /** 附件审核标识 */
    private Long auditResult;

    /** 附件大小 */
    private Long size;

    /** 附件格式 */
    private Long format;

    /** 是否删除 */
    private Long markDelete;

    /** 附件审核未通过的审核意见颜色 */
    private Long fontColor;

    /** 附件格式 */
    private String remark;

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
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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

    public String getResizePath() {
        return resizePath;
    }

    public String getSubSystemPath() {
        return subSystemPath;
    }

    public void setSubSystemPath(String subSystemPath) {
        this.subSystemPath = subSystemPath;
    }

    public void setResizePath(String resizePath) {
        this.resizePath = resizePath;
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

    public Long getFontColor() {
        return fontColor;
    }

    public void setFontColor(Long fontColor) {
        this.fontColor = fontColor;
    }

    public void toVo(AttachmentHouseVo attachmentVo) {
        try {
            //附件主键ID
            attachmentVo.setId(super.getId());
            //附件特殊标识
            attachmentVo.setAttachmentSpecialMark(attachmentSpecialMark);
            //附件用途代码
            attachmentVo.setAttachmentUseCode(attachmentUseCode);
            //附件用途类型
            attachmentVo.setAttachmentUseType(attachmentUseType);
            //标记附件未被删除
            attachmentVo.setMarkDelete(Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.NO)));
            //标记附件备注
            attachmentVo.setRemark(remark);
            //标记附件所属系统类别
            attachmentVo.setSystemType(systemType);
            //标记附件所属用户ID
            attachmentVo.setUserId(userId);
            //标记附件更新人
            attachmentVo.setUsername(super.getUpdateUser());
            //标记附件格式
            attachmentVo.setFormat(format);
            //审计结果
            attachmentVo.setAuditResult(auditResult);
            //用途类型标识
            attachmentVo.setAttachmentUseTypeMark(attachmentUseTypeMark);
            //字体颜色
            attachmentVo.setFontColor(fontColor);
            //更新时间
            attachmentVo.setEndDate(DateUtils.formatDateTime(super.getCreateTime()));
            //创建时间
            attachmentVo.setBeginDate(DateUtils.formatDateTime(super.getUpdateTime()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }
}
