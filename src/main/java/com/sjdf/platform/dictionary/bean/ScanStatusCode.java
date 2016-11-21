package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 *         Create at 2014-3-31
 * @category 网站检测状态代码
 */
@Entity
@DiscriminatorValue("SCAN_STATUS_CODE")
@BeanName(name = "网站检测状态代码")
public class ScanStatusCode extends Dictionary {

    private static final long serialVersionUID = 3126793147654530415L;
    /** 0: 任务创建成功 */
    @BeanAttrInfo(value = "0", cnName = "任务创建成功")
    public static final long TASK_CREATE_SUCCESS = 0;

    /** 1: 参数错误 */
    @BeanAttrInfo(value = "1", cnName = "参数错误")
    public static final long PARAMETER_ERROR = 1;

    /** 2: 服务异常 */
    @BeanAttrInfo(value = "2", cnName = "服务异常")
    public static final long SERVER_EXCEPTION = 2;

    /** 3: 临时错误 */
    @BeanAttrInfo(value = "3", cnName = "临时错误")
    public static final long TEMPORARY_ERROR = 3;

    /** 4: 系统繁忙 */
    @BeanAttrInfo(value = "4", cnName = "系统繁忙")
    public static final long SYSTEM_BUSY = 4;

    /** 5: 认证失败 */
    @BeanAttrInfo(value = "5", cnName = "认证失败")
    public static final long AUTHENTICATION_FAILURE = 5;

    /** 6: 任务完成 */
    @BeanAttrInfo(value = "6", cnName = "任务完成")
    public static final long TASK_COMPLETE = 6;

    /** 7: 未授权 */
    @BeanAttrInfo(value = "7", cnName = "未授权 ")
    public static final long UNAUTHORIZED = 7;

    /** 11: 重复任务 */
    @BeanAttrInfo(value = "11", cnName = "重复任务")
    public static final long REPEAT_TASK = 11;

    /** 100: 任务检测中 */
    @BeanAttrInfo(value = "100", cnName = "任务检测中")
    public static final long TASK_DETECTION_ING = 100;

    /** 101: 任务不存在 */
    @BeanAttrInfo(value = "101", cnName = "任务不存在")
    public static final long TASK_NO_EXIST = 101;

    /** 201: 任务检测 */
    @BeanAttrInfo(value = "201", cnName = "任务检测")
    public static final long TASK_DETECTION = 201;

}
