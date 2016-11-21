package com.sjdf.platform.pageContent.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 页面内容版本管理
 *
 * @author laberwu
 */
@Entity
@Table(name = "p_page_content_version_manage")
public class VersionManageBean extends BaseBean {
    private static final long serialVersionUID = 3888818767642172377L;
    /** 版本名称 */
    @Column(name = "version_name")
    private String versionName;
    /** 版本说明 */
    @Column(name = "version_description")
    private String versionDescription;
    /** 是否启用 */
    @Column(name = "enable_markup")
    private long enableMarkup;
    /** 是否只读 */
    @Column(name = "read_only")
    private long readOnly;
    /** 系统类别 */
    @Column(name = "system_type")
    private long systemType;
    /** 推送地址 */
    @Column(name = "push_url")
    private String pushUrl;
    /** 预览首页地址 */
    @Column(name = "preview_url")
    private String previewUrl;

    @ModifyRecord(name = "版本名称")
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @ModifyRecord(name = "版本说明")
    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public long getEnableMarkup() {
        return enableMarkup;
    }

    public void setEnableMarkup(long enableMarkup) {
        this.enableMarkup = enableMarkup;
    }

    public long getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(long readOnly) {
        this.readOnly = readOnly;
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    @ModifyRecord(name = "推送地址")
    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    @ModifyRecord(name = "预览首页地址")
    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * 获取是否只读显示名称
     *
     * @return String 是否只读显示名称
     */
    @ModifyRecord(name = "是否只读")
    public String getReadOnlyInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, readOnly);
    }

    /**
     * 获取系统类别显示名称
     *
     * @return String 系统类别显示名称
     */
    @ModifyRecord(name = "系统类别")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    /**
     * 获取是否启用显示名称
     *
     * @return String 是否启用显示名称
     */
    @ModifyRecord(name = "是否启用")
    public String getEnableMarkupInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, enableMarkup);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        info.append("PageContentManageBean [")
                .append("id=").append(this.getId()).append(',')
                .append("versionName=").append(this.versionName).append(',')
                .append("versionDescription=").append(this.versionDescription).append(',')
                .append("enableMarkup=").append(this.enableMarkup).append(',')
                .append("readOnly=").append(this.readOnly).append(',')
                .append("systemType=").append(this.systemType).append(',')
                .append("pushUrl=").append(this.pushUrl)
                .append("]");
        return info.toString();
    }

}
