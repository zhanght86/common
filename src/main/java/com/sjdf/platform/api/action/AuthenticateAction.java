package com.sjdf.platform.api.action;

import com.sjdf.platform.api.vo.AuthenticateVo;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.rbac.bean.User;
import com.sjdf.platform.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: ketqi
 * Date: 2013-07-19 16:20
 * 权限认证接口
 */
public class AuthenticateAction extends BaseAction {
    private static final long serialVersionUID = 498609186056525259L;
    @Autowired
    private PermissionService permissionService;
    //用户名
    private String userName;
    //密码AES(MD5之后的密码)
    private String pwd;
    //角色id
    private long roleId;

    /**
     * <pre>
     * api/common/AuthenticateAction!auth.action
     * vercode = MD5.md5(idx + username + pwd + connPwd + vertime)
     * 参数如下:
     * idx:systemType
     * username
     * pwd(AES.encrypt(MD5.md5(pwd)))
     * vertime
     * vercode
     * </pre>
     * 会员信息验证接口
     */
    public String auth() {
        Message message = permissionService.auth(idx, userName, pwd, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(AuthenticateVo.cteateFailVo(getText(message)).parse());
        } else {
            User user = (User) message.getReturnData();
            user.setSystemType(idx);
            printWrite(AuthenticateVo.createSuccessVo("success", user).parse());
        }
        return NONE;
    }

    /**
     * 获取有效角色列表
     * <pre>
     * api/common/AuthenticateAction!roleList.action
     * vercode = MD5.md5(connPwd + "roleList" + vertime)
     * 参数如下:
     * idx:systemType
     * vertime
     * vercode
     * </pre>
     *
     * @return result
     */
    public String roleList() {
        Message message = permissionService.listRole(idx, "roleList", vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(false, getText(message));
        } else {
            printWrite(message.getInfo());
        }
        return NONE;
    }

    /**
     * 为指定用户设置角色
     * <pre>
     * api/common/AuthenticateAction!setRole.action
     * vercode = MD5.md5(connPwd + userName + "setRole" + roleId + vertime)
     * 参数如下:
     * idx:systemType
     * vertime
     * vercode
     * </pre>
     *
     * @return result
     */
    public String setRole() {
        Message message = permissionService.updateRole4User(idx, userName, "setRole", roleId, vertime, vercode);
        if (message.hasErrorMessage()) {
            printWrite(false, getText(message));
        } else {
            printWrite(true, SUCCESS);
        }
        return NONE;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
