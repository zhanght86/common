package com.sjdf.platform.check.bean;

/**
 * 2012-9-10 下午7:35:45
 * 接口查询，【所属类别】条件封装实体Bean
 *
 * @author frank
 */
public class ConditionVoBean {
    /** 所属类别--公共配置库具体类 */
    private String key;
    /** 所属类别--公共配置库具体类里的值 */
    private String value;
    /** 标记【所属类别--公共配置库具体类里的值】是否为特殊 */
    private String markValue;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMarkValue() {
        return markValue;
    }

    public void setMarkValue(String markValue) {
        this.markValue = markValue;
    }
}
