package com.sjdf.platform.rbac.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.rbac.bean.*;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 14:44
 * <p/>
 * 权限管理service
 */
public interface PermissionService extends BaseService {
    /**
     * @param permission 条件限制
     * @param page       分页组件
     * @return 权限列表
     * 按指定条件分页查询权限
     */
    List<Permission> listPermissionByPage(Permission permission, Page page);

    /**
     * @param permission 权限信息
     * @return 信息组件
     * 修改权限名称
     */
    Message updatePermission(Permission permission);

    /**
     * @param nodeId        当前节点
     * @param permissionIds 权限列表
     * @return 信息组件
     * 给当前节点分配权限
     */
    Message addPermission(long nodeId, long[] permissionIds);

    /**
     * @param idx 当前权限id
     * @return 信息组件
     * 将权限节点从当前权限上移除
     */
    Message deletePermission(long idx);

    /**
     * @param permissionNode 权限树
     * @return 信息组件
     * 添加权限组
     */
    Message addPermissionNode(PermissionNode permissionNode);

    /**
     * @param permissionNode 权限组
     * @return 信息组件
     * 更新权限组名称
     */
    Message updatePermissionNode(PermissionNode permissionNode);

    /**
     * @param idx 权限组id
     * @return 信息组件
     * 删除权限组
     */
    Message deletePermissionNode(long idx);

    /**
     * @return 权限列表
     * 获取所有未授权和关联的权限列表
     */
    List<Permission> listPermissionUnGrantedAndNoSupported();

    /**
     * @param roleId 指定的角色id
     * @return 权限列表
     * 取得当前用户指定角色当前能被分配的权限列表
     */
    List<TreeNode> listGrantablePermissionForRole(long roleId);

    /**
     * @param roleId            当前角色
     * @param permissionNodeIds 权限节点列表
     * @param permissionIds     权限列表
     * @return 信息组件
     * 角色分配权限树
     */
    Message addPermission4Role(long roleId, long[] permissionNodeIds, long[] permissionIds);

    /**
     * @param currentUser 当前用户
     * @param userId      分配的用户
     * @return 信息组件
     * 取得指定用户可以分配的角色列表
     */
    Message listGrantableRoleForUser(User currentUser, long userId);

    /**
     * @param userId 当前操作的用户
     * @param roleId 角色id
     * @return 信息组件
     * 给子级用户分配角色
     */
    Message addRole4ChildUser(long userId, long roleId);

    /**
     * @param idx 指定的权限组
     * @return 权限组列表
     * 获取所有权限组
     */
    List<TreeNode> listPermissionNode4Permission(long idx);

    /**
     * @param idx 上级id
     * @return 角色树列表
     * 角色树
     */
    List<Role> listRoleTree(long idx);

    /**
     * @param role 角色信息
     * @return 信息组件
     * 添加角色
     */
    Message addRole(Role role);

    /**
     * @param idx 角色id
     * @return 信息组件
     * 删除角色
     */
    Message deleteRole(long idx);

    /**
     * @param role 角色信息
     * @return 信息组件
     * 修改角色信息
     */
    Message updateRole(Role role);

    /**
     * @param idx 角色id
     * @return 角色信息
     * 获取角色
     */
    Role getRole(long idx);

    /**
     * @param user 条件限制
     * @param page 分页组件
     * @return 用户列表
     * 根据条件分页查询用户
     */
    List<User> listUser(User user, Page page);

    /**
     * @param id 用户id
     * @return 用户信息
     * 获取用户信息
     */
    User getUser(long id);

    /**
     * @param user        用户信息
     * @param currentUser 当前用户
     * @return 信息组件
     * 添加用户
     */
    Message addUser(User currentUser, User user);

    /**
     * @param currentUser 当前用户
     * @param idx         待删除的用户id
     * @return 信息组件
     * 删除用户
     */
    Message deleteUser(User currentUser, long idx);

    /**
     * @param currentUserId 当前用户Id
     * @param oldPassword   旧密码
     * @param user          用户信息
     * @return 信息组件
     * 修改密码
     */
    Message changeSelfPassword(long currentUserId, String oldPassword, User user);

    /**
     * @param currentUser 当前用户
     * @param user        用户信息
     * @return 信息组件
     * 修改其他用户密码
     */
    Message changeOtherPassword(User currentUser, User user);

    /**
     * @param currentUser 当前用户
     * @param user        用户信息
     * @return 信息组件
     * 修改用户信息
     */
    Message updateOther(User currentUser, User user);

    /**
     * @param currentUserId 当前用户Id
     * @param user          用户信息
     * @return 信息组件
     * 更新自己的用户信息
     */
    Message updateSelf(long currentUserId, User user);

    /**
     * @param currentUser 当前用户
     * @param idx         待处理的用户id
     * @return 信息组件
     * 启用/禁用用户
     */
    Message forbid(User currentUser, long idx);

    /**
     * @param user 用户信息
     * @return 信息组件
     * 用户登录
     */
    Message getLoginUser(User user);

    /**
     * 保存或更新非common平台的权限信息
     *
     * @param permissionList 权限列表
     */
    void saveOrUpdateAll(List<Permission> permissionList);

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
    Message auth(long systemType, String userName, String pwd, String vertime, String vercode);

    /**
     * @param systemType 系统类型
     * @param vercode    校验码vercode = MD5.md5(systemType + "collect" + connPwd + vertime)
     * @param vertime    校验时间
     * @param xml        权限xml格式数据
     * @return 消息组件
     * 收集非common平台权限信息
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    Message collect(long systemType, String vercode, String vertime, String xml);

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
    Message resetPassword(String username, String pwd, String vertime, String vercode);

    /**
     * 获取root节点下所有有效角色列表
     *
     * @param systemType 系统类型
     * @param method     方法标识
     * @param vertime    时间戳
     * @param vercode    校验码(vercode = MD5.md5(systemType + method + connPwd + vertime))
     * @return 角色列表
     */
    Message listRole(long systemType, String method, String vertime, String vercode);

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
    Message updateRole4User(long systemType, String userName, String method, long roleId, String vertime, String vercode);
}
