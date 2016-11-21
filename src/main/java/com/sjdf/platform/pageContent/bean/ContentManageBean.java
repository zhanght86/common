package com.sjdf.platform.pageContent.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.AttachmentConfigure;
import com.sjdf.platform.dictionary.bean.PageContentMarkup;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.image.helper.ImageHelper;
import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * 页面内容管理
 *
 * @author laberwu
 */
@Entity
@Table(name = "p_page_content_manage")
public class ContentManageBean extends BaseBean {
    private static final long serialVersionUID = 2401101694419712533L;
    /** xml转义前缀 */
    private static final String PRE_CDATA = "<![CDATA[";
    /** xml转义后缀 */
    private static final String SUF_CDATA = "]]>";

    /**
     * 页面标示
     *
     * @see com.sjdf.platform.dictionary.bean.PageContentMarkup
     */
    @Index(name = "page_markup")
    @Column(name = "page_markup")
    private long pageMarkup;
    /** 预览地址 */
    @Index(name = "preview_url")
    @Column(name = "preview_url")
    private String previewUrl;
    /** 关联id(父id) */
    @Index(name = "parent_id")
    @Column(name = "parent_id")
    private long parentId;
    /** 图片地址 */
    @Column(name = "image_url")
    private String imageUrl;
    /** 菜单名称 */
    @Column(name = "menu_name")
    private String menuName;
    /** 链接地址 */
    @Column(name = "link_url")
    private String linkUrl;
    /** 显示排序 */
    @Column(name = "display_sort")
    private long displaySort;
    /** 是否显示 */
    @Column(name = "whether_display")
    private long whetherDisplay;
    /** 是否新窗口 */
    @Column(name = "whether_new_window")
    private long whetherNewWindow;
    /** 版本序号 */
    @Column(name = "version_id")
    private long versionId;
    /** 只读(是否可以修改) */
    @Column(name = "read_only")
    private long readOnly;
    /** 是否删除 */
    @Column(name = "deleted")
    private long deleted;
    /** 内容 */
    @Lob
    @Column(name = "remark", columnDefinition = "longtext")
    private String remark;
    /** 真正的备注 */
    @Lob
    @Column(name = "real_remark", columnDefinition = "longtext")
    private String realRemark;
    /** 显示图片地址 */
    @Transient
    private transient String showImageUrl;

    @ModifyRecord(name = "页面标示")
    public String getPageMarkupInfo() {
        return ConfigManager.getInstance().getName(PageContentMarkup.class, pageMarkup);
    }

    @ModifyRecord(name = "是否显示")
    public String getWhetherDisplayInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, whetherDisplay);
    }

    @ModifyRecord(name = "是否新窗口")
    public String getWhetherNewWindowInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, whetherNewWindow);
    }

    @ModifyRecord(name = "只读")
    public String getReadOnlyInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, readOnly);
    }

    @ModifyRecord(name = "是否删除")
    public String getDeletedInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, deleted);
    }

    public long getPageMarkup() {
        return pageMarkup;
    }

    public void setPageMarkup(long pageMarkup) {
        this.pageMarkup = pageMarkup;
    }

    @ModifyRecord(name = "预览地址")
    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @ModifyRecord(name = "图片地址")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ModifyRecord(name = "菜单名称")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @ModifyRecord(name = "链接地址")
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @ModifyRecord(name = "显示排序")
    public long getDisplaySort() {
        return displaySort;
    }

    public void setDisplaySort(long displaySort) {
        this.displaySort = displaySort;
    }

    public long getWhetherDisplay() {
        return whetherDisplay;
    }

    public void setWhetherDisplay(long whetherDisplay) {
        this.whetherDisplay = whetherDisplay;
    }

    public long getWhetherNewWindow() {
        return whetherNewWindow;
    }

    public void setWhetherNewWindow(long whetherNewWindow) {
        this.whetherNewWindow = whetherNewWindow;
    }

    @ModifyRecord(name = "版本序号")
    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public long getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(long readOnly) {
        this.readOnly = readOnly;
    }

    public long getDeleted() {
        return deleted;
    }

    public void setDeleted(long deleted) {
        this.deleted = deleted;
    }

    @ModifyRecord(name = "内容")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ModifyRecord(name = "真正的备注")
    public String getRealRemark() {
        return realRemark;
    }

    public void setRealRemark(String realRemark) {
        this.realRemark = realRemark;
    }

    public String getShowImageUrl() {
        showImageUrl = ImageHelper.getImageUrl(ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.PAGE_CONTENT_SAVE_PATH)) + this.imageUrl;
        return showImageUrl;
    }

    public void setShowImageUrl(String showImageUrl) {
        this.showImageUrl = showImageUrl;
    }

    /**
     * 对象转化为xml
     *
     * @return
     */
    public String toXml() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<pageContent>");
        xml.append("<id>").append(PRE_CDATA).append(this.getId()).append(SUF_CDATA).append("</id>");
        xml.append("<menuName>").append(PRE_CDATA).append(menuName).append(SUF_CDATA).append("</menuName>"); // 菜单名称
        xml.append("<pageMarkup>").append(PRE_CDATA).append(pageMarkup).append(SUF_CDATA).append("</pageMarkup>"); // 页面标识
        xml.append("<parentId>").append(PRE_CDATA).append(parentId).append(SUF_CDATA).append("</parentId>"); // 关联id
        xml.append("<linkUrl>").append(PRE_CDATA).append(linkUrl).append(SUF_CDATA).append("</linkUrl>"); // 链接地址
        xml.append("<displaySort>").append(PRE_CDATA).append(displaySort).append(SUF_CDATA).append("</displaySort>"); // 排序
        xml.append("<whetherDisplay>").append(PRE_CDATA).append(whetherDisplay).append(SUF_CDATA).append("</whetherDisplay>"); // 是否显示
        xml.append("<whetherNewWindow>").append(PRE_CDATA).append(whetherNewWindow).append(SUF_CDATA).append("</whetherNewWindow>"); // 是否新窗口
        xml.append("<showImageUrl>").append(PRE_CDATA).append(ImageHelper.getImageUrl(ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.PAGE_CONTENT_SAVE_PATH))).append(this.imageUrl).append(SUF_CDATA).append("</showImageUrl>"); // 图片访问地址
        xml.append("<remark>").append(PRE_CDATA).append(remark).append(SUF_CDATA).append("</remark>"); // 内容
        xml.append("<realRemark>").append(PRE_CDATA).append(realRemark).append(SUF_CDATA).append("</realRemark>"); // 备注
        xml.append("</pageContent>");

        return xml.toString();
    }

    /** 根据操作动作转化xml */
    public String toXml(long operation) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        xml.append("<operation>").append(PRE_CDATA).append(operation).append(SUF_CDATA).append("</operation>");
        xml.append(this.toXml());

        return xml.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        info.append("PageContentManageBean [")
                .append("id=").append(this.getId()).append(',')
                .append("pageMarkup=").append(this.pageMarkup).append(',')
                .append("previewUrl=").append(this.previewUrl).append(',')
                .append("parentId=").append(this.parentId).append(',')
                .append("imageUrl=").append(this.imageUrl).append(',')
                .append("menuName=").append(this.menuName).append(',')
                .append("linkUrl=").append(this.linkUrl).append(',')
                .append("displaySort=").append(this.displaySort).append(',')
                .append("whetherDisplay=").append(this.whetherDisplay).append(',')
                .append("whetherNewWindow=").append(this.whetherNewWindow).append(',')
                .append("versionId=").append(this.versionId).append(',')
                .append("readOnly=").append(this.readOnly)
                .append("]");
        return info.toString();
    }
}
