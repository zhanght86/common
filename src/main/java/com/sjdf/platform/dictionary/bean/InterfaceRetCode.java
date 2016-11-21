package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-18
 *
 * @author 陈绍刚
 * @category 接口返回代码
 */
@Entity
@DiscriminatorValue("INTERFACE_RET_CODE")
@BeanName(name = "接口返回代码")
public class InterfaceRetCode extends Dictionary {
    private static final long serialVersionUID = 1L;

    /**
     * @category 返回状态标识
     */
    public static final String RESULT_CODE = "resultCode";
    /**
     * @category 返回信息标识
     */
    public static final String RESULT_MSG = "resultMSg";
    /**
     * @category 返回结果标识
     */
    public static final String RESULT = "result";

    /** 操作成功 */
    @BeanAttrInfo(value = "200", cnName = "操作成功")
    public static final long SUCCESS = 200;

    /** 操作失败 */
    @BeanAttrInfo(value = "433", cnName = "操作失败")
    public static final long ERROR = 999;
    /** 验证失败 */
    @BeanAttrInfo(value = "433", cnName = "验证失败")
    public static final long VALIDATE_FAILURE = -1;

    /** 无法找到该产品 */
    @BeanAttrInfo(value = "421", cnName = "无法找到该产品")
    public static final long CANNOT_FIND_THIS_PRODUCT = 421;

    /** 500错误 */
    @BeanAttrInfo(value = "500", cnName = "500错误")
    public static final long ERROR_500 = 500;

    /** 201错误 */
    @BeanAttrInfo(value = "201", cnName = "201错误")
    public static final long ERROR_201 = 201;

    /** 连接超时 */
    @BeanAttrInfo(cnName = "连接超时")
    public static final long NETWORK_TIMEOUT = 1000;
}
