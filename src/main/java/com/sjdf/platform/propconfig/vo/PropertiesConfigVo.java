package com.sjdf.platform.propconfig.vo;

import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;

import java.util.List;

/**
 * 2015-08-28
 * 属性配置vo类
 *
 * @author wangpeng
 */
public class PropertiesConfigVo {

    /** 下拉列表选择的值 */
    private long attr;

    /** 配置库类别中文名称 */
    private String dicBeanName;

    /** entity类别注解中文名称 */
    private String entityBeanName;

    /** 页面展示的配置数据 */
    private List<PropertiesConfigBean> dataList;

    /** 配置库中数据中文名称 */
    private List<String> dicCnNameList;

    public long getAttr() {
        return attr;
    }

    public void setAttr(long attr) {
        this.attr = attr;
    }

    public String getDicBeanName() {
        return dicBeanName;
    }

    public void setDicBeanName(String dicBeanName) {
        this.dicBeanName = dicBeanName;
    }

    public String getEntityBeanName() {
        return entityBeanName;
    }

    public void setEntityBeanName(String entityBeanName) {
        this.entityBeanName = entityBeanName;
    }

    public List<PropertiesConfigBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<PropertiesConfigBean> dataList) {
        this.dataList = dataList;
    }

    public List<String> getDicCnNameList() {
        return dicCnNameList;
    }

    public void setDicCnNameList(List<String> dicCnNameList) {
        this.dicCnNameList = dicCnNameList;
    }
}
