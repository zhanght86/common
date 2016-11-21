package com.sjdf.platform.cache;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式缓存通知事件队列
 * User: ketqi
 * Date: 2015-07-28 14:21
 */
public final class NotifyEventDisruptor {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(NotifyEventDisruptor.class);
    private RingBuffer<ValueEvent> ringBuffer;

    private NotifyEventDisruptor() {
        init();
    }

    /** 缓存实例 */
    private static class SingleHolder {
        private SingleHolder() {
        }

        private static final NotifyEventDisruptor INSTANCE = new NotifyEventDisruptor();
    }

    public static NotifyEventDisruptor getInstance() {
        return NotifyEventDisruptor.SingleHolder.INSTANCE;
    }

    /** 初始化 */
    @SuppressWarnings("unchecked")
    private void init() {
        ExecutorService exec = Executors.newCachedThreadPool();
        Disruptor<ValueEvent> disruptor = new Disruptor<>(new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new NotifyEventDisruptor.ValueEvent();
            }
        }, CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_32, exec, ProducerType.MULTI, new SleepingWaitStrategy());

        //消费者线程处理器
        disruptor.handleEventsWith(new NotifyEventHandler(this));

        //启动队列
        ringBuffer = disruptor.start();
    }

    /**
     * 向队列中添加事件记录
     *
     * @param event 消息事件列表
     */
    public void add(NotifyEvent event) {
        if (event == null) {
            return;
        }

        long seq = ringBuffer.next();
        try {
            //noinspection unchecked
            ringBuffer.get(seq).setEvent(event);
        } finally {
            ringBuffer.publish(seq);
        }
    }

    /**
     * 向队列中添加事件记录
     *
     * @param eventList 事件记录列表
     */
    public void add(List<NotifyEvent> eventList) {
        if (eventList == null || eventList.isEmpty()) {
            return;
        }

        for (NotifyEvent rec : eventList) {
            add(rec);
        }
    }

    /** 事件持有者 */
    private static class ValueEvent {
        private NotifyEvent event;

        public NotifyEvent getEvent() {
            return event;
        }

        public void setEvent(NotifyEvent event) {
            this.event = event;
        }
    }

    /** 消费者线程处理器 */
    private static class NotifyEventHandler implements EventHandler<ValueEvent> {
        private NotifyEventDisruptor disruptor;
        private List<String> urlList;

        public NotifyEventHandler(NotifyEventDisruptor disruptor) {
            this.disruptor = disruptor;
            this.urlList = CacheManager.getInstance().generateAddress();
        }

        @Override
        public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
            NotifyEvent notifyEvent = event.getEvent();
            if (notifyEvent != null) {
                if (!notifyEvent.getCacheClassName().contains("VerifyCodeManager")) {
                    try {
                        //延长执行时间,防止数据库没有更新,就去加载数据
                        Thread.sleep(CommonPlatformConstant.LENGTH_2048);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                LOGGER.debug("NotifyEvent:" + notifyEvent.toString());
                if (PlatformUtils.hasText(notifyEvent.getAgainSynAddress())) {
                    syncOneAddress(notifyEvent, notifyEvent.getAgainSynAddress());
                } else {
                    for (String address : urlList) {
                        syncOneAddress(notifyEvent, address);
                    }
                }
                event.setEvent(null);
            }
        }

        /**
         * 同步事件到指定的地址
         *
         * @param notifyEvent 事件
         * @param address     地址
         */
        private void syncOneAddress(NotifyEvent notifyEvent, String address) {
            try {
                ReplicatorCache cache = (ReplicatorCache) Naming.lookup(address);
                if (cache == null) {
                    LOGGER.error(address + " invalidate", notifyEvent.toString());
                    return;
                }

                if (notifyEvent.isRemove()) {
                    cache.removeCache(notifyEvent.getCacheClassName(), false, notifyEvent.getKeys());
                } else {
                    cache.updateCache(notifyEvent.getCacheClassName(), false, notifyEvent.getKeys());
                }
            } catch (MalformedURLException | RemoteException e) {
                int counter = notifyEvent.getCounter().incrementAndGet();
                if (counter < CommonPlatformConstant.LENGTH_2) {
                    disruptor.add(notifyEvent.setAgainSynAddress(address));
                } else {
                    LOGGER.debug(address, e);
                }
            } catch (Exception e) {
                LOGGER.error(notifyEvent.toString(), e);
            }
        }
    }
}
