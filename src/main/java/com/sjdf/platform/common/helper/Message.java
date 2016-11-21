package com.sjdf.platform.common.helper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create at 2012-05-07
 * 信息组件,用于封装在服务处理中返回的错误信息
 *
 * @author 王正伟
 */
public final class Message {
    /** 单例,用于返回无任何信息的信息数据 */
    private static final Message EMPTY_MESSAGE = new Message(null, Collections.emptyList());
    /** 错误信息 */
    private final String errorMessage;
    /** 信息参数 */
    private final List<?> parameterList;
    /** 返回的结果数据 */
    private Object returnData;
    /** 返回的结果消息 */
    private String info;

    private Message(String errorMessage, List<?> parameterList) {
        this.errorMessage = errorMessage;
        this.parameterList = parameterList;
    }

    /** 单例无任何错误信息的信息对象 */
    public static Message createEmptyMessage() {
        return EMPTY_MESSAGE;
    }

    /** 创建无错误信息的message对象 */
    public static Message createMessage() {
        return new Message(null, Collections.emptyList());
    }

    /** 根据错误信息创建message对象 */
    public static Message createMessage(String message, List<?> parameterList) {
        return new Message(message, parameterList);
    }

    /** 根据错误创建message对象 */
    public static Message createMessage(String message, Object... parameters) {
        return new Message(message, Arrays.asList(parameters));
    }

    /** 是否有返回信息 */
    public boolean hasErrorMessage() {
        return errorMessage != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<?> getParameterList() {
        return parameterList;
    }

    public Object getReturnData() {
        return returnData;
    }

    public Message setReturnData(Object returnData) {
        if (this == EMPTY_MESSAGE) {
            throw new RuntimeException("EmptyMessage forbid transfer data");
        }
        this.returnData = returnData;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Message setInfo(String info) {
        if (this == EMPTY_MESSAGE) {
            throw new RuntimeException("EmptyMessage forbid transfer data");
        }
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" + "message='" + errorMessage + '\'' + ", parameterList=" + parameterList + '}';
    }
}
