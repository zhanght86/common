package com.sjdf.platform.rbac.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 10:34
 * <p/>
 * 权限, 表示一次点击或操作
 */
@Entity
@Table(name = "p_permission")
public class Permission extends BaseBean {
    private static final long serialVersionUID = 6783398035283617632L;
    /**
     * 系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long systemType;

    /** 权限代码 */
    @Index(name = "i_code")
    private String code;

    /** 类名 */
    @Column(name = "class_name")
    @Index(name = "i_class_name")
    private String className;

    /** 方法名 */
    private String method;

    /** 权限名 */
    private String name;

    /** 请求路径 */
    private String url;

    /**
     * 是否是菜单
     *
     * @see com.sjdf.platform.dictionary.bean.WhetherState
     */
    private long isMenu;

    /** 顺序 */
    private int orderBy;

    /** 其依附的权限 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supported_permission_id")
    private Permission supportedPermission;

    /** 被依附的其他权限列表 */
    @OneToMany(mappedBy = "supportedPermission", cascade = CascadeType.ALL)
    private List<Permission> supportList;

    /** 权限组 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_node_id")
    private PermissionNode permissionNode;

    /** 拥有此权限的角色列表 */
    @ManyToMany
    @JoinTable(name = "r_role_x_permission", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    /** 是否被经由gtip用户修改过 */
    @Column(name = "user_modified")
    private boolean userMoidfied;

    /** 选中状态 */
    @Transient
    private transient boolean checked;

    /** 其依附的权限的代码,临时存储作 */
    @Transient
    private transient String supportedCode;

    @ModifyRecord(name = "系统类型")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    public Permission() {
    }

    public Permission(long systemType, String code, String className, String method, String name, String supportedCode, String url, long isMenu, int orderBy) {
        this.systemType = systemType;
        this.code = code;
        this.className = className;
        this.method = method;
        this.name = name;
        this.supportedCode = supportedCode;
        this.url = url;
        this.isMenu = isMenu;
        this.orderBy = orderBy;
    }

    /**
     * @return xml字符串
     * 将当前对象解析成xml数据
     */
    public String parse() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_500);
        xml.append("<permission>");
        xml.append("<id><![CDATA[").append(getId()).append("]]></id>");
        xml.append("<systemType><![CDATA[").append(systemType).append("]]></systemType>");
        xml.append("<code><![CDATA[").append(code).append("]]></code>");
        xml.append("<className><![CDATA[").append(className).append("]]></className>");
        xml.append("<method><![CDATA[").append(method).append("]]></method>");
        xml.append("<name><![CDATA[").append(name).append("]]></name>");
        xml.append("<url><![CDATA[").append(url).append("]]></url>");
        xml.append("<isMenu><![CDATA[").append(isMenu).append("]]></isMenu>");
        xml.append("<orderBy><![CDATA[").append(orderBy).append("]]></orderBy>");
        xml.append("<supportedCode><![CDATA[").append(supportedCode).append("]]></supportedCode>");
        xml.append("</permission>");
        return xml.toString();
    }

    public boolean isMenu() {
        return isMenu == WhetherState.YES;
    }

    @ModifyRecord(name = "是否是菜单")
    public String getIsMenuInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, isMenu);
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    @ModifyRecord(name = "权限代码")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ModifyRecord(name = "类名")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @ModifyRecord(name = "方法名")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @ModifyRecord(name = "权限名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModifyRecord(name = "请求路径")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(long isMenu) {
        this.isMenu = isMenu;
    }

    @ModifyRecord(name = "顺序")
    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public Permission getSupportedPermission() {
        return supportedPermission;
    }

    public void setSupportedPermission(Permission supportedPermission) {
        this.supportedPermission = supportedPermission;
    }

    public List<Permission> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<Permission> supportList) {
        this.supportList = supportList;
    }

    public PermissionNode getPermissionNode() {
        return permissionNode;
    }

    public void setPermissionNode(PermissionNode permissionNode) {
        this.permissionNode = permissionNode;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public boolean isUserMoidfied() {
        return userMoidfied;
    }

    public void setUserMoidfied(boolean userMoidfied) {
        this.userMoidfied = userMoidfied;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getSupportedCode() {
        return supportedCode;
    }

    public void setSupportedCode(String supportedCode) {
        this.supportedCode = supportedCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Permission that = (Permission) o;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = CommonPlatformConstant.LENGTH_31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        sb.append("Permission");
        sb.append("{id=").append(getId());
        sb.append(", systemType='").append(systemType).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", method='").append(method).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", isMenu='").append(isMenu).append('\'');
        sb.append(", orderBy='").append(orderBy).append('\'');
        sb.append(", supportedCode='").append(supportedCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
