package com.sjdf.platform.pageContent.vo;

import com.sjdf.platform.CommonPlatformConstant;

import java.util.List;

/**
 * 节点json对象
 *
 * @author Admin
 */
public class NodesJsonVo {
    /** 数据库对应的id */
    private long databaseId;
    /** 节点名称(菜单名称) */
    private String name;
    /** 节点对应链接 */
    private String url;
    /** 是否可以修改 */
    private boolean readOnly;
    /** 节点是否为父节点 */
    private boolean isParent;
    /** 是否展开 */
    private boolean open;
    /** 版本号 */
    private long versionId;
    /** 子节点集 */
    private List<NodesJsonVo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public List<NodesJsonVo> getChildren() {
        return children;
    }

    public void setChildren(List<NodesJsonVo> children) {
        this.children = children;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        info.append("NodesJsonVo [")
                .append("name=").append(this.name).append(',')
                .append("url=").append(this.url).append(',')
                .append("readOnly=").append(this.readOnly)
                .append("]");
        return info.toString();
    }

}
