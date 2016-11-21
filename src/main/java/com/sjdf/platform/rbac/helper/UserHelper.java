package com.sjdf.platform.rbac.helper;

import com.sjdf.platform.rbac.bean.User;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * User: ketqi
 * Date: 2013-04-15 16:38
 * <p/>
 * 会员相关信息辅助
 */
public abstract class UserHelper {
    /**
     * @return 会员信息
     * 得到当前登陆用户
     */
    public static User getCurrentLoginUser() {
        Map<String, Object> map = ServletActionContext.getContext().getSession();
        return (User) map.get(User.CURRENT_LOGIN_USER);
    }

    /**
     * 将user放入session
     *
     * @param user 会员信息
     */
    public static void createCurrentLoginUser(User user) {
        Map<String, Object> map = ServletActionContext.getContext().getSession();
        map.put(User.CURRENT_LOGIN_USER, user);
    }
}
