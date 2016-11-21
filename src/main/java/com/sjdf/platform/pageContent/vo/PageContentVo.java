package com.sjdf.platform.pageContent.vo;

import java.util.Comparator;
import java.util.List;

/**
 * 页面内容对象
 *
 * @author laberwu
 */
public class PageContentVo {
    public static final Comparator<PageContentVo> COMPARATOR = new Comparator<PageContentVo>() {
        @Override
        public int compare(PageContentVo o1, PageContentVo o2) {
            return Long.compare(o1.getDisplaySort(), o2.getDisplaySort());
        }
    };

    /** 数据库主键id */
    private long id;
    /** 菜单名称 */
    private String menuName;
    /** 页面位置标识 */
    private long pageMarkup;
    /** 管理id */
    private long parentId;
    /** 链接地址 */
    private String linkUrl;
    /** 显示图片地址 */
    private String showImageUrl;
    /** 排序 */
    private long displaySort;
    /** 是否显示 */
    private long whetherDisplay;
    /** 是否新窗口打开 */
    private long whetherNewWindow;
    /** 内容 */
    private String remark;
    /** 备注 */
    private String realRemark;
    /** 子页面内容对象 */
    private List<PageContentVo> subList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public long getPageMarkup() {
        return pageMarkup;
    }

    public void setPageMarkup(long pageMarkup) {
        this.pageMarkup = pageMarkup;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PageContentVo> getSubList() {
        return subList;
    }

    public void setSubList(List<PageContentVo> subList) {
        this.subList = subList;
    }

    public String getRealRemark() {
        return realRemark;
    }

    public void setRealRemark(String realRemark) {
        this.realRemark = realRemark;
    }

    public String getShowImageUrl() {
        return showImageUrl;
    }

    public void setShowImageUrl(String showImageUrl) {
        this.showImageUrl = showImageUrl;
    }
}
