package com.sjdf.platform.message.task;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.bean.BaseMessage;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 消息延迟队列
 * User: ketqi
 * Date: 2015-07-13 11:59
 */
final class MessageDelayQueue {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageDelayQueue.class);
    private DelayQueue<MessageDelayed> queue = new DelayQueue<>();

    private MessageDelayQueue() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
    }

    /** 缓存实例 */
    private static class SingleHolder {
        private SingleHolder() {
        }

        private static final MessageDelayQueue INSTANCE = new MessageDelayQueue();
    }

    public static MessageDelayQueue getInstance() {
        return MessageDelayQueue.SingleHolder.INSTANCE;
    }

    public <T extends BaseMessage> void offer(T message) {
        if (message == null) {
            return;
        }

        //noinspection unchecked
        queue.offer(new MessageDelayed(message));
    }

    /** 启动延迟队列 */
    private void start() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                MessageDelayed message = queue.take();
                if (message != null) {
                    SendManager.getInstance().send(message.getMessage());
                }
                Thread.sleep(CommonPlatformConstant.LENGTH_1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("message delay take fail:", e);
            }
        }
    }

    private static class MessageDelayed<T extends BaseMessage> implements Delayed {
        private T message;
        private long expiredTime;

        public MessageDelayed(T message) {
            this.message = message;
            this.expiredTime = message.getSendTime().getTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return expiredTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        public T getMessage() {
            return message;
        }
    }
}
