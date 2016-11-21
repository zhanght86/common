package com.sjdf.platform.message.task;

import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.message.bean.BaseMessage;
import com.sjdf.platform.message.bean.MessageApiUser;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.message.vo.DisruptorMonitorVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 发送管理器
 * User: ketqi
 * Date: 2015-11-23 17:20
 */
public final class SendManager {
    private final ConcurrentMap<String, MessageSendDisruptor> cache;

    private SendManager() {
        cache = new ConcurrentHashMap<>();
        for (MessageApiUser user : MessageApiUserCache.getInstance().getAllList()) {
            cache.put(user.getUserId(), new MessageSendDisruptor());
        }
    }

    /** 单例 */
    private static class SingleHolder {
        private static final SendManager INSTANCE = new SendManager();
    }

    public static SendManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    public <T extends BaseMessage> void send(T message) {
        if (message == null) {
            return;
        }

        if (message.isDelayMessage()) {
            MessageDelayQueue.getInstance().offer(message);
            return;
        }

        String userId = message.getUserId();
        if (!PlatformUtils.hasText(userId)) {
            return;
        }

        MessageSendDisruptor disruptor = cache.get(userId);
        if (disruptor == null) {
            synchronized (cache) {
                disruptor = cache.get(userId);
                if (disruptor == null) {
                    disruptor = new MessageSendDisruptor();
                    cache.put(userId, disruptor);
                }
            }
        }

        disruptor.add(message);
    }

    /**
     * 批量发送消息
     *
     * @param messageList 消息列表
     */
    public <T extends BaseMessage> void send(List<T> messageList) {
        if (messageList == null || messageList.isEmpty()) {
            return;
        }

        for (T message : messageList) {
            send(message);
        }
    }

    /**
     * 获取监控数据
     *
     * @return 监控数据列表
     */
    public List<DisruptorMonitorVo> monitorList() {
        List<DisruptorMonitorVo> list = new ArrayList<>(cache.size());
        MessageSendDisruptor disruptor;
        for (Map.Entry<String, MessageSendDisruptor> entry : cache.entrySet()) {
            disruptor = entry.getValue();
            list.add(new DisruptorMonitorVo(entry.getKey(), disruptor.bufferSize(), disruptor.cursor(), disruptor.remainingCapacity()));
        }
        return list;
    }
}
