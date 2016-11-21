package com.sjdf.platform.rbac.service.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.*;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.rbac.bean.*;
import com.sjdf.platform.rbac.helper.PermissionHelper;
import com.sjdf.platform.rbac.helper.TreeNodeHelper;
import com.sjdf.platform.rbac.service.PermissionService;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * User: ketqi Date: 2013-04-15 14:45
 * 权限管理service实现
 */
@Service("commonPermissionService")
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {

    /**
     * @param permission 条件限制
     * @param page       分页组件
     * @return 权限列表
     * 按指定条件分页查询权限
     */
    @Override
    public List<Permission> listPermissionByPage(Permission permission, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Permission.class);
        if (permission != null) {
            if (permission.getSystemType() != 0L) {
                criteria.add(Restrictions.eq("systemType", permission.getSystemType()));
            }

            if (PlatformUtils.hasText(permission.getCode())) {
                criteria.add(Restrictions.eq("code", permission.getCode()));
            }

            if (PlatformUtils.hasText(permission.getClassName())) {
                criteria.add(Restrictions.like("className", permission.getClassName(), MatchMode.ANYWHERE));
            }

            if (PlatformUtils.hasText(permission.getMethod())) {
                criteria.add(Restrictions.like("method", permission.getMethod(), MatchMode.ANYWHERE));
            }

            if (PlatformUtils.hasText(permission.getName())) {
                criteria.add(Restrictions.like("name", permission.getName(), MatchMode.ANYWHERE));
            }

            if (permission.getIsMenu() != 0L) {
                criteria.add(Restrictions.eq("isMenu", permission.getIsMenu()));
            }
        }
        return baseDao.listByCriteria(criteria, page, Order.asc("code"));
    }

    /**
     * @param permission 权限信息
     * @return 信息组件
     * 修改权限名称
     */
    @Override
    public Message updatePermission(Permission permission) {
        Permission oldPermission;
        if (permission == null || permission.getId() <= 0 || (oldPermission = baseDao.get(Permission.class, permission.getId())) == null) {
            return Message.createMessage("permission.nonexist");
        }

        // 信息修改
        String info = null;
        if (PlatformUtils.hasText(permission.getName())) {
            oldPermission.setName(permission.getName());
            info = oldPermission.getName() + "-->" + permission.getName();
        }

        // 将修改标记置true
        oldPermission.setUserMoidfied(true);
        // 更新
        baseDao.update(oldPermission);

        return Message.createMessage().setReturnData(oldPermission).setInfo(info);
    }

    /**
     * @param nodeId        当前节点
     * @param permissionIds 权限列表
     * @return 信息组件
     * 给当前节点分配权限
     */
    @Override
    public Message addPermission(long nodeId, long[] permissionIds) {
        PermissionNode permissionNode = baseDao.get(PermissionNode.class, nodeId);
        if (permissionNode == null) {
            return Message.createMessage("permissionNode.nonexist");
        }

        List<Permission> permissionList = new ArrayList<>();
        for (long id : permissionIds) {
            Permission permission = baseDao.get(Permission.class, id);
            if (permission == null) {
                return Message.createMessage("permission.nonexist");
            }
            permissionList.add(permission);
        }

        for (Permission permission : permissionList) {
            permission.setPermissionNode(permissionNode);
            baseDao.update(permission);
        }

        return Message.createEmptyMessage();
    }

    /**
     * @param idx 当前权限id
     * @return 信息组件
     * 将权限节点从当前权限组上移除
     */
    @Override
    public Message deletePermission(long idx) {
        Permission permission = baseDao.get(Permission.class, idx);
        if (permission == null) {
            return Message.createMessage("permission.nonexist");
        }

        if (permission.getPermissionNode() != null) {
            permission.setPermissionNode(null);
            baseDao.update(permission);
        }

        return Message.createEmptyMessage();
    }

    /**
     * @param permissionNode 权限树
     * @return 信息组件
     * 添加权限组
     */
    @Override
    public Message addPermissionNode(PermissionNode permissionNode) {
        if (!PlatformUtils.hasText(permissionNode.getName())) {
            return Message.createMessage("permissionNode.name.empty");
        }

        // 名称重复判断
        if (hasPermissionNodeName(permissionNode.getName())) {
            return Message.createMessage("permissionNode.name.exist", permissionNode.getName());
        }

        if (permissionNode.getParent() == null || permissionNode.getParent().getId() == 0) {
            permissionNode.parent(null);
        } else {
            PermissionNode parent = baseDao.get(PermissionNode.class, permissionNode.getParent().getId());
            if (parent == null) {
                return Message.createMessage("permissionNode.parent.nonexist");
            }
            permissionNode.parent(parent);
        }

        baseDao.save(permissionNode);
        return Message.createMessage().setInfo(permissionNode.wrapUpdateContent(null, false)).setReturnData(permissionNode);
    }

    /**
     * @return 获取所有未授权和关联的权限列表
     */
    @Override
    public List<Permission> listPermissionUnGrantedAndNoSupported() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Permission.class);
        criteria.add(Restrictions.isNull("permissionNode"));
        return baseDao.listByCriteria(criteria, Order.asc("code"));
    }

    /**
     * @param permissionNode 权限组
     * @return 信息组件
     * 更新权限组名称
     */
    @Override
    public Message updatePermissionNode(PermissionNode permissionNode) {
        PermissionNode oldPermissionNode;
        if (permissionNode == null || permissionNode.getId() <= 0 || (oldPermissionNode = baseDao.get(PermissionNode.class, permissionNode.getId())) == null) {
            return Message.createMessage("permissionNode.nonexist");
        }

        // 名称重复判断
        if (EqualsUtils.notEqualsWithNotNull(oldPermissionNode.getName(), permissionNode.getName())) {
            if (hasPermissionNodeName(permissionNode.getName())) {
                return Message.createMessage("permissionNode.name.exist", permissionNode.getName());
            }

            String info = oldPermissionNode.wrapUpdateContent(permissionNode, true);
            // 名称
            if (PlatformUtils.hasText(permissionNode.getName())) {
                oldPermissionNode.setName(permissionNode.getName());
                baseDao.update(oldPermissionNode);
                return Message.createMessage().setReturnData(oldPermissionNode).setInfo(info);
            }
        }

        return Message.createMessage().setReturnData(oldPermissionNode);
    }

    /**
     * @param idx 权限组id
     * @return 删除权限组
     */
    @Override
    public Message deletePermissionNode(long idx) {
        PermissionNode oldPermissionNode;
        if (idx <= 0 || (oldPermissionNode = baseDao.get(PermissionNode.class, idx)) == null) {
            return Message.createMessage("permissionNode.nonexist");
        }

        // 清除permission关联
        Map<String, Object> params = new HashMap<>();
        params.put("idx", oldPermissionNode.getId());
        params.put("path", oldPermissionNode.getSelfPath() + "%");
        baseDao.update("update Permission p set p.permissionNode = null where exists(select pn from PermissionNode pn where pn = p.permissionNode and (pn.id = :idx or pn.path like :path))", params);

        baseDao.delete(oldPermissionNode);
        return Message.createMessage().setInfo(oldPermissionNode.wrapUpdateContent(null, false)).setReturnData(oldPermissionNode);
    }

    /**
     * @param roleId 指定的角色id
     * @return 权限列表
     * 取得当前用户指定角色当前能被分配的权限列表
     */
    @Override
    public List<TreeNode> listGrantablePermissionForRole(long roleId) {
        Role role = baseDao.get(Role.class, roleId);
        if (role == null) {
            return Collections.emptyList();
        }

        Role parentRole = role.getParent();
        // 取权限树及权限信息
        List<PermissionNode> permissionNodeList;
        List<Permission> permissionList;
        if (parentRole != null) {
            permissionNodeList = parentRole.getPermissionNodeTreeList();
            permissionList = parentRole.getPermissionList();
        } else {
            permissionNodeList = baseDao.listAll(PermissionNode.class);
            permissionList = listUnGrantedAndNoSupported();
        }

        // 取得当前角色的权限结点信息
        List<PermissionNode> currentPermissionNodeList = role.getPermissionNodeTreeList();
        List<Permission> currentPermissionList = role.getPermissionList();

        // 进行checked设置
        for (PermissionNode pNode : permissionNodeList) {
            if (currentPermissionNodeList.contains(pNode)) {
                pNode.setChecked(true);
            } else {
                pNode.setChecked(false);
            }
        }

        for (Permission p : permissionList) {
            if (currentPermissionList.contains(p)) {
                p.setChecked(true);
            } else {
                p.setChecked(false);
            }
        }

        // 数据组合
        Map<Long, List<PermissionNode>> permissionNodeMap = TreeNodeHelper.makeTreeNodeListToMap(permissionNodeList);
        Map<Long, List<Permission>> permissionMap = TreeNodeHelper.makePermissionListToMap(permissionList);

        return TreeNodeHelper.convertPermissionMapToSortedTreeList(permissionNodeMap, permissionMap, 0L);
    }

    /**
     * @param roleId            当前角色
     * @param permissionNodeIds 权限节点列表
     * @param permissionIds     权限列表
     * @return 信息组件
     * 角色分配权限树
     */
    @Override
    public Message addPermission4Role(long roleId, long[] permissionNodeIds, long[] permissionIds) {
        Role role = baseDao.get(Role.class, roleId);
        if (role == null) {
            return Message.createMessage("role.nonexist");
        }

        List<PermissionNode> permissionNodeList = new ArrayList<>();
        for (Long id : permissionNodeIds) {
            PermissionNode permissionNode = baseDao.get(PermissionNode.class, id);
            if (permissionNode != null) {
                permissionNodeList.add(permissionNode);
            }
        }

        List<Permission> permissionList = new ArrayList<>();
        if (permissionIds != null) {
            for (long id : permissionIds) {
                Permission permission = baseDao.get(Permission.class, id);
                if (permission != null) {
                    permissionList.add(permission);
                }
            }
        }

        // 角色上下级权限结点范围
        List<Long> validpNodeIds = new ArrayList<>();
        for (PermissionNode input : permissionNodeList) {
            validpNodeIds.add(input.getId());
        }
        if (role.getParent() != null) {
            List<Long> parentpNodeIds = listPermissionNodeIds4Role(role.getParent().getId());
            if (parentpNodeIds.containsAll(validpNodeIds)) {
                Message.createMessage("grant.rolexpermission.permissionNodeList.notMoreThanParentRole");
            }
        }

        // 角色上下级权限范围
        List<Long> validpIds = new ArrayList<>();
        for (Permission input : permissionList) {
            validpIds.add(input.getId());
        }
        if (role.getParent() != null) {
            List<Long> parentpIds = listPermissionIds4Role(role.getParent().getId());
            if (parentpIds.containsAll(validpIds)) {
                Message.createMessage("grant.rolexpermission.permissionList.notMoreThanParentRole");
            }
        }

        // 删除子角色被去掉的权限结点列表
        List<Long> currentpNodeList = listPermissionNodeIds4Role(roleId);
        currentpNodeList.removeAll(validpNodeIds);
        if (!currentpNodeList.isEmpty()) {
            baseDao.currentSession().createSQLQuery("DELETE FROM r_role_x_permission_node_tree WHERE permission_node_tree_id IN (:ids) AND EXISTS (SELECT 1 FROM p_role r WHERE r.id = role_id AND r.path LIKE :path)").setParameterList("ids", currentpNodeList).setString("path", role.getSelfPath() + "%").executeUpdate();
        }

        // 删除子角色被去掉的权限列表
        List<Long> currentpList = listPermissionNodeIds4Role(roleId);
        currentpList.removeAll(validpIds);
        if (!currentpList.isEmpty()) {
            baseDao.currentSession().createSQLQuery("DELETE FROM r_role_x_permission WHERE permission_id IN (:ids) AND EXISTS (SELECT 1 FROM p_role r WHERE r.id = role_id AND r.path LIKE :path)").setParameterList("ids", currentpList).setString("path", role.getSelfPath() + "%").executeUpdate();
        }

        // 设置信息
        role.setPermissionNodeTreeList(permissionNodeList);
        role.setPermissionList(permissionList);
        baseDao.update(role);

        return Message.createEmptyMessage();
    }

    /**
     * @param currentUser 当前用户
     * @param userId      分配的用户
     * @return 信息组件
     * 取得指定用户可以分配的角色列表
     */
    @Override
    public Message listGrantableRoleForUser(User currentUser, long userId) {
        User user = getUser(userId);
        if (user == null) {
            return Message.createMessage("user.nonexist");
        }

        // 上下级关系
        if (!currentUser.isParent(user)) {
            return Message.createMessage("user.parent.false", user.getUsername());
        }

        // 取得可分配的角色列表
        List<Role> grantableRoleList;
        if (currentUser.isSysUser()) {
            grantableRoleList = baseDao.listAll(Role.class);
        } else {
            Role role;
            if (currentUser.getRole() != null && (role = baseDao.get(Role.class, currentUser.getRole().getId())) != null) {
                grantableRoleList = new ArrayList<>(role.getChildList());
                grantableRoleList.add(role);
            } else {
                grantableRoleList = Collections.emptyList();
            }
        }

        if (grantableRoleList.isEmpty()) {
            if (currentUser.isSysUser() || currentUser.isAdmin()) {
                return Message.createMessage("user.createRole.fail");
            }
            return Message.createMessage("user.role.selfNonGranted");
        }

        // 取得当前用户角色列表
        List<Role> currentRoleList = user.getRoleTreeList();

        // 追加自身角色
        if (user.getRole() != null) {
            currentRoleList.add(user.getRole());
        }

        // 进行checked过滤
        for (Role r : grantableRoleList) {
            for (Role t : currentRoleList) {
                if (t.getId() == r.getId()) {
                    r.setChecked(true);
                    break;
                } else {
                    r.setChecked(false);
                }
            }
        }

        // 进行数据组织转化
        // 进行数据组织
        Map<Long, List<Role>> map = TreeNodeHelper.makeTreeNodeListToMap(grantableRoleList);

        // 最大的角色parentId
        Long maxRoleParentId = (currentUser.isSysUser() || currentUser.isAdmin()) ? 0L : ((currentUser.getRole() == null || currentUser.getRole().getParent() == null) ? 0L : currentUser.getRole().getParent().getId());
        List<TreeNode> resultList = TreeNodeHelper.convertTreeNodeMapToSortedTreeList(map, maxRoleParentId);

        return Message.createMessage().setReturnData(resultList);
    }

    /**
     * @param userId 当前操作的用户
     * @param roleId 角色id
     * @return 给子级用户分配角色
     */
    @Override
    public Message addRole4ChildUser(long userId, long roleId) {
        User user = baseDao.get(User.class, userId);
        if (user == null) {
            return Message.createMessage("user.nonexist");
        }

        Role role = baseDao.get(Role.class, roleId);
        if (role == null) {
            return Message.createMessage("role.nonexist");
        }

        if (user.getRole() != null && user.getRole().getId() == role.getId()) {
            return Message.createMessage();
        }

        // 如果是角色重新分配,则取消该用户下所有人员已分配的角色为未分配,重新分配的条件是当前用户已经有角色
        if (user.getRole() != null) {
            String path = user.getSelfPath() + "%";
            // 这里r_user_x_role_tree不能用别名,如加上别名之后,在mysql中delete后必须加别名,而oracle必须不能加,有冲突
            baseDao.updateSql("DELETE FROM r_user_x_role_tree WHERE EXISTS (SELECT 1 FROM p_user u WHERE u.id = user_id AND u.path LIKE ?)", path);

            baseDao.updateHql("update User u set u.role = null where u.path like ?", path);
        }

        // set and update
        // 角色树
        List<Role> roleTreeList = new ArrayList<>();
        for (Role r = role.getParent(); r != null; r = r.getParent()) {
            roleTreeList.add(r);
        }

        user.setRole(role);
        user.setRoleTreeList(roleTreeList);
        baseDao.update(user);

        return Message.createMessage().setReturnData(role.getName());
    }

    /**
     * @param idx 指定的权限组
     * @return 权限组列表
     * 获取所有权限组
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TreeNode> listPermissionNode4Permission(long idx) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PermissionNode.class);
        List<PermissionNode> list;
        if (idx <= 0) {
            list = baseDao.listByCriteria(criteria.add(Restrictions.isNull("parent")));
        } else {
            list = baseDao.listByCriteria(criteria.createAlias("parent", "parent").add(Restrictions.eq("parent.id", idx)));
        }

        if (idx <= 0) {
            TreeNode node = TreeNode.createRootNode(TreeNode.class);
            node.setChildList(convertPermissionNodeToTreeNodeWithPermission(list));
            return Collections.singletonList(node);
        } else {
            return convertPermissionNodeToTreeNodeWithPermission(list);
        }
    }

    /**
     * @param idx 上级id
     * @return 角色列表
     * 角色树
     */
    @Override
    public List<Role> listRoleTree(long idx) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);

        if (idx == 0) {
            criteria.add(Restrictions.isNull("parent"));
        } else {
            criteria.createAlias("parent", "parent").add(Restrictions.eq("parent.id", idx));
        }

        List<Role> list = baseDao.listByCriteria(criteria);
        for (Role role : list) {
            role.hibernateInitialize();
        }

        // 构建根结点
        if (idx <= 0) {
            Role role = TreeNode.createRootNode(Role.class);
            role.setChildList(list);
            return Collections.singletonList(role);
        }

        return list;
    }

    /**
     * @param role 角色信息
     * @return 信息组件
     * 添加角色
     */
    @Override
    public Message addRole(Role role) {
        if (role == null) {
            return Message.createMessage("role.nonexist");
        }

        // 名称空值检查
        if (!PlatformUtils.hasText(role.getName())) {
            return Message.createMessage("role.name.empty");
        }

        // 惟一性检查
        if (hasRoleName(role.getName())) {
            return Message.createMessage("role.name.exist", role.getName());
        }

        // parent
        if (role.getParent() != null && role.getParent().getId() > 0) {
            Role parent = baseDao.get(Role.class, role.getParent().getId());
            if (parent == null) {
                return Message.createMessage("role.parent.nonexist");
            }
            role.parent(parent);
        } else {
            role.parent(null);
        }

        baseDao.save(role);
        return Message.createMessage().setInfo(role.wrapUpdateContent(null, false)).setReturnData(role);
    }

    /**
     * @param idx 角色id
     * @return 信息组件
     * 删除角色
     */
    @Override
    public Message deleteRole(long idx) {
        Role role = baseDao.get(Role.class, idx);
        // 空值检查
        if (role == null) {
            return Message.createMessage("role.nonexist");
        }

        // 用户列表空检查
        if (!role.getUserList().isEmpty() || !role.getTreedUserList().isEmpty()) {
            return Message.createMessage("role.userList.delete.fail", role.getName());
        }

        if (role.getChildList() != null && !role.getChildList().isEmpty()) {
            return Message.createMessage("role.childList.delete.fail", role.getName());
        }

        // 删除信息
        baseDao.delete(role);
        return Message.createMessage().setInfo(role.wrapUpdateContent(null, false)).setReturnData(role);
    }

    /**
     * @param role 角色信息
     * @return 信息组件
     * 修改角色信息
     */
    @Override
    public Message updateRole(Role role) {
        Role oldRole;
        // 空值检查
        if (role == null || role.getId() <= 0 || (oldRole = baseDao.get(Role.class, role.getId())) == null) {
            return Message.createMessage("role.nonexist");
        }

        // 名称空值检查
        if (!PlatformUtils.hasText(role.getName())) {
            return Message.createMessage("role.name.empty");
        }

        // 名称重复检查
        if (!role.getName().equals(oldRole.getName())) {
            if (hasRoleName(role.getName())) {
                return Message.createMessage("role.name.exist", role.getName());
            }
            String info = oldRole.getName() + "-->" + role.getName();
            oldRole.setName(role.getName());
            baseDao.update(oldRole);
            return Message.createMessage().setReturnData(oldRole).setInfo(info);
        }

        return Message.createMessage().setReturnData(oldRole);
    }

    /**
     * @param idx 角色id
     * @return 角色
     * 获取角色
     */
    @Override
    public Role getRole(long idx) {
        Role role = null;
        if (idx > 0 && (role = baseDao.get(Role.class, idx)) != null) {
            Hibernate.initialize(role.getPermissionNodeTreeList());
            Hibernate.initialize(role.getPermissionList());
            Hibernate.initialize(role.getTreedUserList());
        }
        return role;
    }

    /**
     * @param user 条件限制
     * @param page 分页组件
     * @return 用户列表
     * 根据条件分页查询用户
     */
    @Override
    public List<User> listUser(User user, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        if (user != null && user.getParent() != null) {
            User parent = user.getParent();
            if (!parent.isSysUser()) {
                criteria.add(Restrictions.eq("parent.id", parent.getId()));
            }
        }
        criteria.setFetchMode("role", FetchMode.JOIN);
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * @param id 用户id
     * @return 用户信息
     * 获取用户信息
     */
    @Override
    public User getUser(long id) {
        User user = null;
        if (id > 0 && (user = baseDao.get(User.class, id)) != null) {
            Hibernate.initialize(user.getParent());
            Hibernate.initialize(user.getRole());
        }
        return user;
    }

    /**
     * @param user        用户信息
     * @param currentUser 当前用户
     * @return 信息组件
     * 添加用户
     */
    @Override
    public Message addUser(User currentUser, User user) {
        // 空值检查
        if (user == null) {
            return Message.createMessage("user.nonexist");
        }

        if (!PlatformUtils.hasText(user.getName())) {
            return Message.createMessage("user.name.empty");
        }
        if (!PlatformUtils.hasText(user.getUsername())) {
            return Message.createMessage("user.username.empty");
        }
        if (!PlatformUtils.hasText(user.getPassword())) {
            return Message.createMessage("user.password.empty");
        }

        // 长度检查
        if (user.getUsername().length() < CommonPlatformConstant.LENGTH_6) {
            return Message.createMessage("user.username.length.min", String.valueOf(CommonPlatformConstant.LENGTH_6));
        }
        if (user.getPassword().length() < CommonPlatformConstant.LENGTH_6) {
            return Message.createMessage("user.password.length.min", String.valueOf(CommonPlatformConstant.LENGTH_6));
        }

        // 惟一性判断,追加sys判断,不能和sys同名
        if (User.isSysUsername(user.getUsername()) || hasUsername(user.getSystemType(), user.getUsername())) {
            return Message.createMessage("user.username.exist", user.getUsername());
        }

        // 设置相关属性
        // 密码
        user.setPassword(MD5.md5(user.getPassword()));
        user.parent(currentUser);// 上级用户及等级,路径等.

        // 授权用户
        if (currentUser.isSysUser()) {
            // 系统用户
            user.setAdminUser(null);
        } else if (currentUser.isAdmin()) {
            // 授权用户
            user.setAdminUser(currentUser);
        } else {
            // 一般用户
            user.setAdminUser(currentUser.getAdminUser());
        }

        // 保存
        baseDao.save(user);
        return Message.createMessage().setReturnData(user).setInfo(user.wrapUpdateContent(null, false));
    }

    /**
     * @param currentUser 当前用户
     * @param idx         待删除的用户id
     * @return 信息组件
     * 删除用户
     */
    @Override
    public Message deleteUser(User currentUser, long idx) {
        User user;
        if (idx == 0 || (user = baseDao.get(User.class, idx)) == null) {
            return Message.createMessage("user.nonexist");
        }

        if (currentUser.getId() == idx) {
            return Message.createMessage("user.forbid.operate.self");
        }

        if (!currentUser.isParent(user)) {
            return Message.createMessage("user.parent.false", user.getUsername());
        }

        baseDao.delete(user);
        return Message.createMessage().setReturnData(user).setInfo(user.wrapUpdateContent(null, false));
    }

    /**
     * @param currentUserId 当前用户Id
     * @param oldPassword   旧密码
     * @param user          用户信息
     * @return 信息组件
     * 修改密码
     */
    @Override
    public Message changeSelfPassword(long currentUserId, String oldPassword, User user) {
        User currentUser;
        if ((currentUser = baseDao.get(User.class, currentUserId)) == null) {
            return Message.createMessage("user.current.user.nonexit");
        }
        if (!PlatformUtils.hasText(oldPassword)) {
            return Message.createMessage("user.oldPassword.empty");
        }
        if (user == null || !PlatformUtils.hasText(user.getPassword())) {
            return Message.createMessage("user.newPassword.empty");
        }
        if (user.getPassword().length() < CommonPlatformConstant.LENGTH_6) {
            return Message.createMessage("user.password.length.min", String.valueOf(CommonPlatformConstant.LENGTH_6));
        }

        // 旧密码判断是否正确
        if (!currentUser.getPassword().equals(MD5.md5(oldPassword))) {
            return Message.createMessage("user.password.wrong");
        }

        // 修改密码信息
        currentUser.setPassword(MD5.md5(user.getPassword()));
        baseDao.update(currentUser);
        return Message.createMessage().setReturnData(currentUser);
    }

    /**
     * @param currentUser 当前用户
     * @param user        用户信息
     * @return 信息组件
     * 修改其他用户密码
     */
    @Override
    public Message changeOtherPassword(User currentUser, User user) {
        User oldUser;
        if (user == null || user.getId() < 0 || (oldUser = baseDao.get(User.class, user.getId())) == null) {
            return Message.createMessage("user.nonexist");
        }

        // 上下级判断
        if (!currentUser.isParent(oldUser)) {
            return Message.createMessage("user.parent.false", oldUser.getUsername());
        }

        // password can not be empty
        if (!PlatformUtils.hasText(user.getPassword())) {
            return Message.createMessage("user.password.empty");
        }

        // length check
        if (user.getPassword().length() < CommonPlatformConstant.LENGTH_6) {
            return Message.createMessage("user.password.length.min", String.valueOf(CommonPlatformConstant.LENGTH_6));
        }

        oldUser.setPassword(MD5.md5(user.getPassword()));
        baseDao.update(oldUser);

        return Message.createMessage().setReturnData(oldUser);
    }

    /**
     * @param currentUser 当前用户
     * @param user        用户信息
     * @return 信息组件
     * 修改用户信息
     */
    @Override
    public Message updateOther(User currentUser, User user) {
        User oldUser;
        if (user == null || user.getId() < 0 || (oldUser = baseDao.get(User.class, user.getId())) == null) {
            return Message.createMessage("user.nonexist");
        }

        // 上下级判断
        if (!currentUser.isParent(oldUser)) {
            return Message.createMessage("user.parent.false", oldUser.getUsername());
        }

        // 更新信息
        if (PlatformUtils.hasText(user.getName())) {
            oldUser.setName(user.getName());
        }

        // 更新
        baseDao.update(oldUser);
        oldUser.getRole().getName();

        return Message.createMessage().setReturnData(oldUser);
    }

    /**
     * @param currentUserId 当前用户Id
     * @param user          用户信息
     * @return 信息组件
     * 更新自己的用户信息
     */
    @Override
    public Message updateSelf(long currentUserId, User user) {
        User currentUser;
        if (currentUserId < 0 || (currentUser = baseDao.get(User.class, currentUserId)) == null) {
            return Message.createMessage("user.current.user.nonexit");
        }

        // 更新信息
        if (PlatformUtils.hasText(user.getName())) {
            currentUser.setName(user.getName());
        }

        // 更新
        baseDao.update(currentUser);

        return Message.createEmptyMessage();
    }

    /**
     * @param currentUser 当前用户
     * @param idx         待处理的用户id
     * @return 信息组件
     * 启用/禁用用户
     */
    @Override
    public Message forbid(User currentUser, long idx) {
        User oldUser;
        if (idx < 0 || (oldUser = baseDao.get(User.class, idx)) == null) {
            return Message.createMessage("user.nonexist");
        }

        if (currentUser.getId() == idx) {
            return Message.createMessage("user.forbid.operate.self");
        }

        // 上下级判断
        if (!currentUser.isParent(oldUser)) {
            return Message.createMessage("user.parent.false", oldUser.getUsername());
        }

        // 启用/禁用,即原标记的!标记
        long flag = (oldUser.getForbidFlag() == WhetherState.YES) ? WhetherState.NO : WhetherState.YES;
        oldUser.setForbidFlag(flag);
        if (flag == WhetherState.YES) {
            baseDao.updateSql("update User t set t.forbidFlag = ? where t.path like ?", WhetherState.YES, oldUser.getSelfPath() + "%");
        }
        baseDao.update(oldUser);

        return Message.createMessage().setReturnData(oldUser);
    }

    /**
     * @param user 用户信息
     * @return 信息组件
     * 用户登录
     */
    @Override
    public Message getLoginUser(User user) {
        // 用户名
        String username = user.getUsername();
        if (!PlatformUtils.hasText(username)) {
            return Message.createMessage("login.user.username.empty");
        }

        // 密码
        String password = user.getPassword();
        if (!PlatformUtils.hasText(password)) {
            return Message.createMessage("login.user.password.empty");
        }

        // sysuser处理
        if (User.isSysUsername(user.getUsername())) {
            String pwd = MD5.md5(username + DateUtils.formatDate(new Date()));
            if (password.equals(pwd)) {
                // 创建顶级user信息
                return Message.createMessage().setReturnData(User.createSysUser());
            }
            return Message.createMessage("login.user.wrongUsernameAndPassword");
        }

        // 密码运算
        password = MD5.md5(password);

        // 取用户
        List<User> userList = baseDao.find("from User where username = ? and password = ? ", username, password);
        User realUser = userList.isEmpty() ? null : userList.get(0);
        if (realUser == null) {
            return Message.createMessage("login.user.wrongUsernameAndPassword");
        }
        if (realUser.getForbidFlag() == WhetherState.YES) {
            return Message.createMessage("login.user.forbid");
        }

        // 强制初始化相关信息
        // 角色信息
        Hibernate.initialize(realUser.getRole());
        Hibernate.initialize(realUser.getRoleTreeList());

        // 初始化权限集合
        Set<Permission> permissionSet = new HashSet<>();
        if (realUser.getRole() != null) {
            permissionSet.addAll(realUser.getRole().getPermissionList());
            List<PermissionNode> permissionNodeList = realUser.getRole().getPermissionNodeTreeList();
            if (permissionNodeList != null && !permissionNodeList.isEmpty()) {
                for (PermissionNode permissionNode : permissionNodeList) {
                    permissionSet.addAll(permissionNode.getPermissionList());
                }
            }
        }
        realUser.initMergedPermission(permissionSet);

        // 成功
        return Message.createMessage().setReturnData(realUser);
    }

    /**
     * @param permissionList 权限列表
     *                       保存或更新非common平台的权限信息
     */
    @Override
    public void saveOrUpdateAll(List<Permission> permissionList) {
        // 取得两边重复的部分,这部分内容除code之外以程序中的信息为准
        Map<String, Permission> databaseExistMap = new HashMap<>();
        Map<String, Permission> existMap = new HashMap<>();
        // 待添加的权限信息
        Map<String, Permission> addedPermissionMap = new HashMap<>();

        // 组织完毕,现在将两边的进行对比,程序中如果在数据库中已经存在,则移除
        String code;
        Permission databaseExistPermission;
        for (Permission permission : permissionList) {
            code = permission.getCode();
            databaseExistPermission = PermissionHelper.getPermissionByCode(code);
            if (databaseExistPermission == null) {
                addedPermissionMap.put(code, permission);
            } else {
                databaseExistMap.put(code, databaseExistPermission);
                existMap.put(code, permission);
            }
        }

        // 待添加的的解决关联问题
        String supportedCode;
        for (com.sjdf.platform.rbac.bean.Permission p : addedPermissionMap.values()) {
            supportedCode = p.getSupportedCode();
            if (PlatformUtils.hasText(supportedCode)) {
                // 直接设置,未找到时设置为空,找到了就设置被找到的对象
                p.setSupportedPermission(PermissionHelper.findByCode(supportedCode, databaseExistMap, addedPermissionMap));
            }
        }

        // 数据库中已经存在的解决权限信息被修改的问题
        List<Permission> updatedPermissionList = new ArrayList<>();
        for (Map.Entry<String, Permission> e : databaseExistMap.entrySet()) {
            code = e.getKey();
            Permission databaseP = e.getValue();
            Permission methodP = existMap.get(code);
            // 检查是否更新标记,避免过度更新
            boolean updated = false;

            // 进行几个判断,类名,方法名,以及引用信息,名称等
            if (EqualsUtils.notEqualsWithNotNull(databaseP.getClassName(), methodP.getClassName())) {
                updated = true;
                databaseP.setClassName(methodP.getClassName());
            }
            if (EqualsUtils.notEqualsWithNotNull(databaseP.getMethod(), methodP.getMethod())) {
                updated = true;
                databaseP.setMethod(methodP.getMethod());
            }

            // 名称修改,仅在未被用户修改过有效
            if (!databaseP.isUserMoidfied() && EqualsUtils.notEqualsWithNotNull(databaseP.getName(), methodP.getName())) {
                updated = true;
                databaseP.setName(methodP.getName());
            }
            if (!PlatformUtils.hasText(methodP.getSupportedCode())) {
                if (databaseP.getSupportedPermission() != null) {
                    updated = true;
                    databaseP.setSupportedPermission(null);
                }
            } else {
                Permission supportedPermission = PermissionHelper.findByCode(methodP.getSupportedCode(), databaseExistMap, addedPermissionMap);
                // 直接用id作判断
                if (databaseP.getSupportedPermission() == null || supportedPermission.getId() != databaseP.getSupportedPermission().getId()) {
                    updated = true;
                    databaseP.setSupportedPermission(supportedPermission);
                }
            }
            if (updated) {
                updatedPermissionList.add(databaseP);
            }
        }

        updatedPermissionList.addAll(addedPermissionMap.values());

        // 保存和更新修改的或待保存的权限信息
        baseDao.saveOrUpdateAll(updatedPermissionList);

        // 更新缓存至权限列表中
        PermissionHelper.addPermissionAll(updatedPermissionList);
    }

    /**
     * vercode = MD5.md5(systemType + username + pwd + connPwd + vertime)
     *
     * @param systemType 系统类型
     * @param userName   用户名
     * @param pwd        密码(AES.encrypt(MD5.md5(pwd)))
     * @param vertime    时间戳
     * @param vercode    校验码
     * @return 信息组件
     * 用户信息认证
     */
    @Override
    public Message auth(long systemType, String userName, String pwd, String vertime, String vercode) {
        // 1.校验
        // 当前正在处理的权限所属平台
        SystemType type = ConfigManager.getInstance().getDictionary(SystemType.class, systemType);
        if (type == null) {
            return Message.createMessage("message.template.systemType.invalid");
        }

        // 用户名
        if (!PlatformUtils.hasText(userName)) {
            return Message.createMessage("login.user.username.empty");
        }

        // 密码
        String password = pwd;
        if (!PlatformUtils.hasText(password)) {
            return Message.createMessage("login.user.password.empty");
        }
        password = password.replace(" ", "+");

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_30);
        builder.append(systemType);
        builder.append(userName);
        builder.append(password);
        builder.append(connPwd);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            return Message.createMessage("common.checkSum.fail");
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return Message.createMessage("common.date.format.invalidate");
        }

        // 有效时间校验,5分钟失效
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return Message.createMessage("common.current.request.fail");
        }

        try {
            password = AES.decrypt(password);
        } catch (Exception ignore) {
            logger.error(ignore.getMessage(), ignore);
            return Message.createMessage("common.aes.decrypt.password");
        }

        // 2.抓取数据
        // 取用户
        User realUser;
        // 判断在eiss-common平台是否有帐号
        List<User> userList = baseDao.find("from User where username = ? and systemType = ? and password = ?", userName, SystemType.EISS_COMMON, password);
        if (userList == null || userList.isEmpty()) {
            // 判断在指定的平台下是否有帐号
            userList = baseDao.find("from User where username = ? and systemType = ? and password = ?", userName, systemType, password);
        }
        realUser = (userList == null || userList.isEmpty()) ? null : userList.get(0);
        if (realUser == null) {
            // 如果根据会员名称没有查询到该会员信息则新增一天会员记录;新增完了之后需要手工添加权限
            userList = baseDao.find("from User where username = ? and forbidFlag = ?", userName, WhetherState.NO);
            if (userList == null || userList.isEmpty()) {
                realUser = new User();
                realUser.setSystemType(SystemType.EISS_COMMON);
                realUser.setUsername(userName);
                realUser.setPassword(password);
                realUser.setForbidFlag(WhetherState.NO);
                realUser.setPath("/");
                baseDao.save(realUser);
            } else {
                logger.error(String.format("wrong systemType:%s; userName:%s; or password:%s", type.getName(), userName, password));
                return Message.createMessage("login.user.wrongUsernameAndPassword");
            }
        }
        if (realUser.getForbidFlag() == WhetherState.YES) {
            return Message.createMessage("login.user.forbid");
        }

        // 强制初始化相关信息
        // 角色信息
        Hibernate.initialize(realUser.getRole());
        Hibernate.initialize(realUser.getRoleTreeList());

        // 收集合并权限集合
        realUser.assemblePermissionSet();

        // 成功
        return Message.createMessage().setReturnData(realUser);
    }

    /**
     * @param systemType 系统类型
     * @param vercode    校验码vercode = MD5.md5(systemType + "collect" + connPwd +
     *                   vertime)
     * @param vertime    校验时间
     * @param xml        权限xml格式数据
     * @return 消息组件
     * 收集非common平台权限信息
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    @Override
    public Message collect(long systemType, String vercode, String vertime, String xml) {
        // 1.校验
        // vercode = MD5.md5(systemType + "collect" + connPwd + vertime)
        String systemTypeInfo = ConfigManager.getInstance().getName(SystemType.class, systemType);// idx标识当前正在处理的权限所属平台
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_30);
        builder.append(systemType);
        builder.append("collect");
        builder.append(connPwd);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            return Message.createMessage("common.checkSum.fail");
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return Message.createMessage("common.date.format.invalidate");
        }

        // 有效时间校验
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return Message.createMessage("common.current.request.fail");
        }

        // 2.解析xml数据
        List<Permission> permissionList = PermissionHelper.parse(xml);

        // 3.保存或更新权限列表
        try {
            saveOrUpdateAll(permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(systemTypeInfo, e);
            return Message.createMessage("common.message", e.getMessage());
        }

        return Message.createEmptyMessage();
    }

    /**
     * 接口重置密码
     * vercode = MD5(username + conPwd + pwd + vertime)
     *
     * @param username 会员名称
     * @param pwd      新密码(加密之后的密码)
     * @param vertime  时间戳
     * @param vercode  校验码
     * @return 消息组件
     */
    @Override
    public Message resetPassword(String username, String pwd, String vertime, String vercode) {
        // 密码
        String password = pwd;
        if (!PlatformUtils.hasText(password)) {
            return Message.createMessage("login.user.password.empty");
        }
        password = password.replace(" ", "+");

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_30);
        builder.append(username);
        builder.append(connPwd);
        builder.append(password);
        builder.append(vertime);

        if (!MD5.md5(builder.toString()).equals(vercode.replace(" ", "+"))) {
            return Message.createMessage("common.checkSum.fail");
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return Message.createMessage("common.date.format.invalidate");
        }

        // 有效时间校验
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return Message.createMessage("common.current.request.fail");
        }

        User user = baseDao.findOne("from User where username = ?", username);
        if (user == null) {
            return Message.createMessage("login.user.wrongUsernameAndPassword");
        }
        user.setPassword(password);
        user.setUpdateTime(new Date());
        user.setUpdateUser(PlatformConstant.API);
        baseDao.update(user);

        return Message.createEmptyMessage();
    }

    /**
     * 获取root节点下所有有效角色列表
     *
     * @param systemType 系统类型
     * @param method     方法标识
     * @param vertime    时间戳
     * @param vercode    校验码(vercode = MD5.md5(connPwd + method + vertime))
     * @return 角色列表
     */
    @Override
    public Message listRole(long systemType, String method, String vertime, String vercode) {
        SystemType type = ConfigManager.getInstance().getDictionary(SystemType.class, systemType);
        if (type == null) {
            return Message.createMessage("message.template.systemType.invalid");
        }

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_30);
        builder.append(connPwd);
        builder.append(method);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            return Message.createMessage("common.checkSum.fail");
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return Message.createMessage("common.date.format.invalidate");
        }

        // 有效时间校验,5分钟失效
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return Message.createMessage("common.current.request.fail");
        }

        List<Role> list = listRoleTree(0L);
        List<Role> resultList = new ArrayList<>(list.size() << 1);
        for (Role role : list) {
            if (role.isForbidFlag()) {
                continue;
            }
            role.collectList(resultList, false);
        }

        builder = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        for (Role role : resultList) {
            builder.append(role.getId()).append(":").append(role.getName()).append(";");
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return Message.createMessage().setInfo(builder.toString()).setReturnData(resultList);
    }

    /**
     * 为指定用户设置角色
     *
     * @param systemType 系统类型
     * @param userName   用户名
     * @param method     方法
     * @param roleId     角色id
     * @param vertime    时间戳
     * @param vercode    校验码(vercode = MD5.md5(connPwd + userName + method + roleId + vertime))
     * @return 消息组件
     */
    @Override
    public Message updateRole4User(long systemType, String userName, String method, long roleId, String vertime, String vercode) {
        SystemType type = ConfigManager.getInstance().getDictionary(SystemType.class, systemType);
        if (type == null) {
            return Message.createMessage("message.template.systemType.invalid");
        }

        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_30);
        builder.append(connPwd);
        builder.append(userName);
        builder.append(method);
        builder.append(roleId);
        builder.append(vertime);
        if (!MD5.md5(builder.toString()).equals(vercode)) {
            return Message.createMessage("common.checkSum.fail");
        }

        Date date = DateUtils.parseDateTimeStamp(vertime);
        if (date == null) {
            return Message.createMessage("common.date.format.invalidate");
        }

        // 有效时间校验,5分钟失效
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_5);
        if (new Date().compareTo(c.getTime()) >= 0) {
            return Message.createMessage("common.current.request.fail");
        }

        User realUser;
        // 判断在eiss-common平台是否有帐号
        List<User> userList = baseDao.find("from User where username = ? and systemType = ?", userName, SystemType.EISS_COMMON);
        if (userList == null || userList.isEmpty()) {
            // 判断在指定的平台下是否有帐号
            userList = baseDao.find("from User where username = ? and systemType = ?", userName, systemType);
        }
        realUser = (userList == null || userList.isEmpty()) ? null : userList.get(0);
        if (realUser == null) {
            return Message.createMessage("user.nonexist");
        }
        return addRole4ChildUser(realUser.getId(), roleId);
    }

    /** 列出子权限节点的主键 */
    private List<Long> listPermissionNodeIds4Role(long roleId) {
        return baseDao.find("select p.id from Role r inner join r.permissionNodeTreeList p where r.id = ?", roleId);
    }

    /** 取得该角色的权限主键列表 */
    private List<Long> listPermissionIds4Role(long roleId) {
        return baseDao.find("select p.id from Role r inner join r.permissionList p where r.id = ?", roleId);
    }

    /** 列出所有未分配且并不依赖其他权限的权限 */
    private List<Permission> listUnGrantedAndNoSupported() {
        return baseDao.find("from Permission where permissionNode is null and supportedPermission is null order by code asc");
    }

    /** 检测权限节点名称是否存在 */
    private boolean hasPermissionNodeName(String name) {
        return baseDao.countHql("select count(p) from PermissionNode p where p.name = ?", name) > 0;
    }

    /** 检测角色名称是否存在 */
    private boolean hasRoleName(String name) {
        return baseDao.countHql("select count(t) from Role t where t.name = ?", name) > 0;
    }

    /** 检测用户名是否存在 */
    public boolean hasUsername(long systemType, String username) {
        return baseDao.countHql("select count(u) from User u where u.username = ? and systemType = ?", username, systemType) > 0;
    }

    /** 封装成树结构 */
    private List<TreeNode> convertPermissionNodeToTreeNodeWithPermission(List<PermissionNode> nodeList) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (PermissionNode node : nodeList) {
            TreeNode<TreeNode> treeNode = new TreeNode<>(node.getId(), node.getName());
            List<TreeNode> childTreeNodeList = convertPermissionNodeToTreeNodeWithPermission(node.getChildList());
            // add rbac
            for (Permission p : node.getPermissionList()) {
                TreeNode permission = new TreeNode(p.getId(), p.getName());
                permission.setNameParam("pid");
                childTreeNodeList.add(permission);
            }
            treeNode.setChildList(childTreeNodeList);
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }
}
