package com.sjdf.platform.rbac.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.*;

/**
 * 用户信息
 * User: ketqi
 * Date: 2013-04-15 13:47
 */
@Entity
@Table(name = "p_user")
public class User extends TreeNode<User> {
    private static final long serialVersionUID = -26752995310886392L;

    private static final String SYSTEM_USER_NAME = "sjdf_admin";
    public static final String VERIFY_CODE = "verify-code";
    public static final String CURRENT_LOGIN_USER = "session-current-login-user";

    /**
     * 系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long systemType;

    /** 用户名 */
    @Index(name = "i_username")
    private String username;

    /** 密码 */
    @Column(name = "f_password")
    private String password;

    /**
     * 是否禁用
     *
     * @see com.sjdf.platform.dictionary.bean.WhetherState
     */
    @Column(name = "forbid_flag")
    private long forbidFlag;

    /**
     * 创建用户标记
     *
     * @see com.sjdf.platform.dictionary.bean.WhetherState
     */
    @Column(name = "create_flag")
    private long createFlag;

    /** 授权用户 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id")
    private User adminUser;

    /** 此用户所在角色 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    /** 角色树 */
    @ManyToMany
    @JoinTable(name = "r_user_x_role_tree", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_tree_id"))
    @OrderBy("level asc ")
    private List<Role> roleTreeList;

    /** 所有由角色权限代码组合成的权限集合 */
    @Transient
    private Map<String, Permission> mergedPermissionMap;

    /** 所有由角色权限代码组合成的权限集合 */
    @Transient
    private Map<Long, Set<Permission>> permissionSetMap;

    /** 判读是否是系统用户 */
    public static boolean isSysUsername(String username) {
        return SYSTEM_USER_NAME.equals(username);
    }

    /** 判断是否系统用户,限制如下,用户名限制,等级为0,path为/,上级用户没有,授权用户没有 */
    @Transient
    public boolean isSysUser() {
        return isSysUsername(username) && getLevel() == 0 && getPath() == null && getParent() == null && getAdminUser() == null;
    }

    /** 判断是否授权用户,限制如下:等级为1,授权用户没有,path=/ */
    @Transient
    public boolean isAdmin() {
        return getLevel() == 1 && getAdminUser() == null && getPath().equals(PATH_SEPARATOR);
    }

    public String getSelfPath() {
        if (isSysUser()) {
            return PATH_SEPARATOR;
        }
        return getPath() + getId() + PATH_SEPARATOR;
    }

    /** 设置一个父级用户,并设置相应的level和path值 */
    public void parent(User parent) {
        if (parent.isSysUser()) {
            setParent(null);
            level = 1;
            path = PATH_SEPARATOR;
        } else {
            setParent(parent);
            if (level == 0) {
                level = parent.getLevel() + 1;
            }
            if (path == null) {
                path = parent.getPath() + parent.getId() + PATH_SEPARATOR;
            }
        }
    }

    /** 创建sys用户 */
    public static User createSysUser() {
        User sysUser = new User();
        sysUser.setUsername(SYSTEM_USER_NAME);
        sysUser.setName("administrator");
        sysUser.setLevel(0);
        sysUser.setAdminUser(null);
        sysUser.setParent(null);
        sysUser.setPath(null);
        sysUser.setCreateFlag(WhetherState.YES);
        return sysUser;
    }

    @ModifyRecord(name = "是否禁用")
    public String getForbidInfor() {
        return ConfigManager.getInstance().getName(WhetherState.class, forbidFlag);
    }

    /** 收集合并权限集合 */
    public void assemblePermissionSet() {
        Set<Permission> permissionSet = new HashSet<>();
        if (role != null) {
            permissionSet.addAll(role.getPermissionList());
            List<PermissionNode> permissionNodeList = role.getPermissionNodeTreeList();
            if (permissionNodeList != null && !permissionNodeList.isEmpty()) {
                for (PermissionNode permissionNode : permissionNodeList) {
                    permissionSet.addAll(permissionNode.getPermissionList());
                }
            }
        }

        initMergedPermission(permissionSet);
    }

    /**
     * @return 唯一标识
     * 获取会员信息的唯一标识
     */
    public String getUniqueIdentifier() {
        return User.getUniqueIdentifier(systemType, username, password);
    }

    /**
     * @param systemType 系统类型
     * @param username   会员名
     * @param password   密码(AES.encrypt(MD5.md5(pwd)))
     * @return 唯一标识
     * 获取会员信息的唯一标识
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    public static String getUniqueIdentifier(long systemType, String username, String password) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_100);
        builder.append(systemType);
        builder.append("-");
        builder.append(username);
        builder.append("-");
        builder.append(password);
        return builder.toString();
    }

    @ModifyRecord(name = "系统类型")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    @ModifyRecord(name = "创建用户标记")
    public String getCreateFlagInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, createFlag);
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    @ModifyRecord(name = "用户名")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getForbidFlag() {
        return forbidFlag;
    }

    public void setForbidFlag(long forbidFlag) {
        this.forbidFlag = forbidFlag;
    }

    public long getCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(long createFlag) {
        this.createFlag = createFlag;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleTreeList() {
        return roleTreeList;
    }

    public void setRoleTreeList(List<Role> roleTreeList) {
        this.roleTreeList = roleTreeList;
    }

    public void initMergedPermission(Set<Permission> mergedPermissionSet) {
        mergedPermissionMap = new HashMap<>();
        permissionSetMap = new HashMap<>();

        Set<Permission> pSet;
        for (Permission p : mergedPermissionSet) {
            mergedPermissionMap.put(p.getCode(), p);

            pSet = permissionSetMap.get(p.getSystemType());
            if (pSet == null) {
                pSet = new HashSet<>();
                permissionSetMap.put(p.getSystemType(), pSet);
            }
            pSet.add(p);
        }
    }

    public Map<String, Permission> getMergedPermissionMap() {
        return mergedPermissionMap;
    }

    public Map<Long, Set<Permission>> getPermissionSetMap() {
        return permissionSetMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        sb.append("User{");
        sb.append("idx=").append(getId());
        sb.append(", systemType=").append(systemType);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", forbidFlag=").append(forbidFlag);
        sb.append(", createFlag=").append(createFlag);
        sb.append('}');
        return sb.toString();
    }
}
