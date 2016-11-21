package com.sjdf.platform.rbac.bean;

import javax.persistence.*;
import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 10:46
 * <p/>
 * 权限树, 表示一组操作的集合
 */
@Entity
@Table(name = "p_permission_node")
public class PermissionNode extends TreeNode<PermissionNode> {
    private static final long serialVersionUID = -4066087717750355682L;

    /** 角色集 */
    @ManyToMany
    @JoinTable(name = "r_role_x_permission_node_tree", joinColumns = @JoinColumn(name = "permission_node_tree_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    /** 权限集 */
    @OneToMany(mappedBy = "permissionNode")
    private List<Permission> permissionList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
