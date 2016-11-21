package com.sjdf.platform.message.cache;

import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.message.bean.MessageApiUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 信息接口授权信息缓存
 * User: ketqi
 * Date: 2015-07-03 16:30
 */
public final class MessageApiUserCache {
    private ConcurrentMap<Long, MessageApiUser> cache;

    private MessageApiUserCache() {
        cache = new ConcurrentHashMap<>();
        init();
    }

    /** 单例 */
    private static class SingleHolder {
        private static final MessageApiUserCache INSTANCE = new MessageApiUserCache();
    }

    public static MessageApiUserCache getInstance() {
        return SingleHolder.INSTANCE;
    }

    private void init() {
        BaseService baseService = (BaseService) ApplicationContextManager.getBean("commonBaseService");
        List<MessageApiUser> userList = baseService.listAll(MessageApiUser.class);
        if (userList != null && !userList.isEmpty()) {
            for (MessageApiUser user : userList) {
                put(user);
            }
        }
    }

    public MessageApiUser getByUserId(String userId) {
        if (!PlatformUtils.hasText(userId)) {
            return null;
        }

        for (MessageApiUser user : cache.values()) {
            if (userId.equals(user.getUserId())) {
                return user;
            }
        }
        return null;
    }

    public MessageApiUser get(long idx) {
        return cache.get(idx);
    }

    public long getUserKey(String userId) {
        MessageApiUser user = getByUserId(userId);
        return user != null ? user.getId() : 0L;
    }

    public String getNameInfo(String userId) {
        MessageApiUser user = getByUserId(userId);
        return user != null ? user.getNameInfo() : "";
    }

    public MessageApiUser exist(String userId, String pwd) {
        if (!PlatformUtils.hasText(userId) || !PlatformUtils.hasText(pwd)) {
            return null;
        }

        MessageApiUser user = getByUserId(userId);
        if (user != null && pwd.equals(user.getConnPwd())) {
            return user;
        }
        return null;
    }

    public MessageApiUser put(MessageApiUser user) {
        if (user == null || !PlatformUtils.hasText(user.getUserId())) {
            return null;
        }

        return cache.put(user.getId(), user.initTransient());
    }

    /**
     * 获取指定消息类型的授权信息
     *
     * @param messageType 消息类型
     * @return 授权信息列表
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    public List<MessageApiUser> getList(long messageType) {
        List<MessageApiUser> userList = new ArrayList<>(cache.size());
        for (MessageApiUser user : cache.values()) {
            if (messageType == MessageType.SMS && PlatformUtils.hasText(user.getSmsApi())) {
                userList.add(user);
            } else if (messageType == MessageType.EMAIL && PlatformUtils.hasText(user.getEmailApi())) {
                userList.add(user);
            }
        }
        return userList;
    }

    public List<MessageApiUser> getAllList() {
        return new ArrayList<>(cache.values());
    }
}
