package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送状态
 * User: ketqi
 * Date: 2015-07-01 10:42
 */
@Entity
@DiscriminatorValue("SEND_STATUS")
@BeanName(name = "发送状态")
public class SendStatus extends Dictionary {
    private static final long serialVersionUID = -3066214844498532613L;
    /** 等待发送 */
    @BeanAttrInfo(cnName = "等待发送")
    public static final long WAIT_SEND = 1L;
    /** 发送成功 */
    @BeanAttrInfo(cnName = "发送成功")
    public static final long SEND_SUCCESS = 5L;
    /** 发送失败 */
    @BeanAttrInfo(cnName = "发送失败")
    public static final long SEND_FAIL = 10L;
    /** 无效数据 */
    @BeanAttrInfo(cnName = "无效数据")
    public static final long INVALIDATE_DATA = 15L;
    /** 人工处理完毕 */
    @BeanAttrInfo(cnName = "人工处理完毕")
    public static final long MANUAL_HANDLE_COMPLETE = 20L;

    private static List<Long> waitSendList;

    static {
        waitSendList = new ArrayList<>();
        waitSendList.add(WAIT_SEND);
        waitSendList.add(SEND_FAIL);
    }

    public static boolean isSendStatus(long status) {
        return waitSendList.contains(status);
    }

    public static List<Long> getWaitSendList() {
        return new ArrayList<>(waitSendList);
    }
}
