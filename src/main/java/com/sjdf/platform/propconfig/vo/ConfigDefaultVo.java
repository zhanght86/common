package com.sjdf.platform.propconfig.vo;

/**
 * create in 2016年9月19日
 * @category 默认值Vo
 * @author wangpeng
 */
public class ConfigDefaultVo {

    private String fieldName; // 属性名

    private Object fieldVale; // 属性值

    /**
     * @see com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean
     */
    private long dictionaryId;

    /**
     * @see com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean
     */
    private long fieldId;

    public final String getFieldName() {
        return fieldName;
    }

    public final void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public final Object getFieldVale() {
        return fieldVale;
    }

    public final void setFieldVale(Object fieldVale) {
        this.fieldVale = fieldVale;
    }

    public final long getDictionaryId() {
        return dictionaryId;
    }

    public final void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public final long getFieldId() {
        return fieldId;
    }

    public final void setFieldId(long fieldId) {
        this.fieldId = fieldId;
    }
}
