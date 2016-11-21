package com.sjdf.platform.common.json;

/**
 * Create at 2013年8月8日 下午4:53:09
 * ajax返回成功信息结果包装类.
 *
 * @param <T>
 * @author KETQI
 */
public class AjaxMessageResult<T> {
    private boolean bool = true;
    private String message;
    private T result;

    public AjaxMessageResult() {
    }

    public AjaxMessageResult(boolean bool, String message, T result) {
        this.bool = bool;
        this.message = message;
        this.result = result;
    }

    /** 结果返回值,默认为true * */
    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    /** 成功信息 */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** 返回具体结果 */
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
