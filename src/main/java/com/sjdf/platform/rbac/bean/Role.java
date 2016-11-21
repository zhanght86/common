package com.sjdf.platform.rbac.bean;

import com.sjdf.platform.common.utils.PlatformUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 10:34
 * 角色信息,表示在系统中进行一类操作的代表信息
 */
@Entity
@Table(name = "p_role")
public class Role extends TreeNode<Role> {
    private static final long serialVersionUID = 1981067666579805964L;

    /** 禁用标记 */
    @Column(name = "forbid_flag")
    private boolean forbidFlag;

    /** 拥有此角色的用户集 */
    @OneToMany(mappedBy = "role")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<User> userList;

    /** 从此角色树上走过的用户集 */
    @ManyToMany
    @JoinTable(name = "r_user_x_role_tree", joinColumns = @JoinColumn(name = "role_tree_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> treedUserList;

    /** 引角色所拥有的权限组集 */
    @ManyToMany
    @JoinTable(name = "r_role_x_permission_node_tree", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_node_tree_id"))
    @OrderBy("level asc")
    private List<PermissionNode> permissionNodeTreeList;

    /** 此角色所拥有的权限集 */
    @ManyToMany
    @JoinTable(name = "r_role_x_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionList;

    /** 是否被选中 */
    @Transient
    private transient boolean checkSet;

    /** 收集所有角色 */
    public void collectList(List<Role> list, boolean addSelf) {
        if (addSelf) {
            list.add(this);
        }
        if (PlatformUtils.isNotEmpty(childList)) {
            for (Role role : childList) {
                if (role.isForbidFlag()) {
                    continue;
                }
                role.collectList(list, true);
            }
        }
    }

    public boolean isForbidFlag() {
        return forbidFlag;
    }

    public void setForbidFlag(boolean forbidFlag) {
        this.forbidFlag = forbidFlag;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getTreedUserList() {
        return treedUserList;
    }

    public void setTreedUserList(List<User> treedUserList) {
        this.treedUserList = treedUserList;
    }

    public List<PermissionNode> getPermissionNodeTreeList() {
        return permissionNodeTreeList;
    }

    public void setPermissionNodeTreeList(List<PermissionNode> permissionNodeTreeList) {
        this.permissionNodeTreeList = permissionNodeTreeList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public boolean isCheckSet() {
        return checkSet;
    }

    public void setCheckSet(boolean checkSet) {
        this.checkSet = checkSet;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Role");
        sb.append("{forbidFlag=").append(forbidFlag);
        sb.append(", userList=").append(userList);
        sb.append(", treedUserList=").append(treedUserList);
        sb.append(", permissionNodeTreeList=").append(permissionNodeTreeList);
        sb.append(", permissionList=").append(permissionList);
        sb.append(", checkSet=").append(checkSet);
        sb.append('}');
        return sb.toString();
    }
}
