package com.sjdf.platform.rbac.cache;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.rbac.bean.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 会员缓存
 * User: ketqi
 * Date: 2013-07-23 09:19
 */
public final class UserCache {
    //key:uniqueIdentifier,value:user
    private ConcurrentMap<String, User> userMap;

    private UserCache() {
        userMap = new ConcurrentHashMap<>(CommonPlatformConstant.LENGTH_128);

        //缓存清理
        cacheClear();
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final UserCache INSTANCE = new UserCache();
    }

    public static UserCache getInstance() {
        return UserCache.SingletonHolder.INSTANCE;
    }

    /**
     * 缓存会员信息
     *
     * @param user 会员信息
     */
    public void cacheUser(User user) {
        if (user != null) {
            user.setUpdateTime(new Date());
            userMap.put(user.getUniqueIdentifier(), user);
        }
    }

    /**
     * 从缓存中移除登录的会员信息
     *
     * @param user 会员信息
     */
    public void clearUser(User user) {
        if (user != null) {
            userMap.remove(user.getUniqueIdentifier());
        }
    }

    /**
     * 从缓存中移除所有会员信息
     */
    public void clearAll() {
        userMap.clear();
    }

    /**
     * 根据用户名和密码获取会员信息
     *
     * @param systemType 系统类型
     * @param userName   用户名
     * @param pwd        密码(AES.encrypt(MD5.md5(password)))
     * @return 从缓存中获取会员信息
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    public User get(long systemType, String userName, String pwd) {
        String key = User.getUniqueIdentifier(systemType, userName, pwd);
        User user = userMap.get(key);
        if (user != null) {
            user.setUpdateTime(new Date());
        }
        return user;
    }

    //缓存清理
    private void cacheClear() {
        new Thread() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                while (true) {
                    c.setTime(new Date());
                    c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - CommonPlatformConstant.LENGTH_30);
                    for (Map.Entry<String, User> entry : userMap.entrySet()) {
                        if (c.getTime().compareTo(entry.getValue().getUpdateTime()) >= 0) {
                            userMap.remove(entry.getKey());
                        }
                    }

                    try {
                        Thread.sleep(CommonPlatformConstant.LENGTH_3 * CommonPlatformConstant.LENGTH_100 * CommonPlatformConstant.LENGTH_1000);//暂停5分钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }.start();
    }
}
