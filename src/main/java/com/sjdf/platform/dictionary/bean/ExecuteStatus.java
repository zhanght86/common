package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;

/**
 * 自动任务或者维护工具的执行状态
 * Create at 2012-04-05
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("EXECUTE_STATUS")
@BeanName(name = "执行状态")
public class ExecuteStatus extends Dictionary {
    private static final long serialVersionUID = -7376468970192886459L;
    /** 开始 */
    @BeanAttrInfo(orderBy = 1, value = "1", cnName = "开始")
    public static final long BEGIN = 1;

    /** 成功 */
    @BeanAttrInfo(orderBy = 2, value = "2", cnName = "成功")
    public static final long SUCCESS = 2;

    /** 失败 */
    @BeanAttrInfo(orderBy = 3, value = "3", cnName = "失败")
    public static final long FAIL = 3;

    /** 中断 */
    @BeanAttrInfo(orderBy = 4, value = "4", cnName = "中断")
    public static final long ERRUPT = 4;

    /** 停止 */
    @BeanAttrInfo(orderBy = 5, value = "5", cnName = "停止")
    public static final long STOP = 5;

    /** 人工处理 */
    @BeanAttrInfo(orderBy = 6, value = "6", cnName = "人工处理")
    public static final long MANUAL = 6;

    /** 完成 */
    @BeanAttrInfo(orderBy = 7, value = "7", cnName = "完成")
    public static final long COMPLTED = 7;

    /** 执行中 */
    @BeanAttrInfo(orderBy = 8, value = "8", cnName = "执行中")
    public static final long EXECUTING = 8;

    /** 不处理 */
    @BeanAttrInfo(orderBy = 10, value = "10", cnName = "不处理")
    public static final long STATUS_NOT_CHECK = 10;

    public static List<ExecuteStatus> getStatusList() {
        List<ExecuteStatus> list = new ArrayList<>();
        for (long attr : getStatusSet()) {
            list.add(ConfigManager.getInstance().getDictionary(ExecuteStatus.class, attr));
        }
        Collections.sort(list, Dictionary.COMPARATOR);
        return list;
    }

    public static Set<Long> getStatusSet() {
        Set<Long> set = new HashSet<>();
        set.add(SUCCESS);
        set.add(FAIL);
        set.add(COMPLTED);
        set.add(EXECUTING);
        return set;
    }
}
