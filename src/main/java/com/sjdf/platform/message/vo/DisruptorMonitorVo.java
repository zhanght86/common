package com.sjdf.platform.message.vo;

/**
 * 队列监控vo
 * User: ketqi
 * Date: 2016-01-06 10:53
 */
public class DisruptorMonitorVo {
    /** 使用队列的标识 */
    private String userId;
    /** 队列大小 */
    public int bufferSize;
    /** 已发布事件的数目或者已经消费的总数 */
    public long cursor;
    /** 队列剩余空间 */
    public long remainingCapacity;

    public DisruptorMonitorVo(String userId, int bufferSize, long cursor, long remainingCapacity) {
        this.userId = userId;
        this.bufferSize = bufferSize;
        this.cursor = cursor;
        this.remainingCapacity = remainingCapacity;
    }

    public String getUserId() {
        return userId;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public long getCursor() {
        return cursor;
    }

    public long getRemainingCapacity() {
        return remainingCapacity;
    }
}
