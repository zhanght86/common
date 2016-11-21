package com.sjdf.platform.propconfig.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 2015-08-26
 * 属性配置项bean对应的所有属性
 *
 * @author wangpeng
 */
@Entity
@Table(name = "home_properties_config_field")
public class PropertiesConfigFieldBean extends BaseBean {

    private static final long serialVersionUID = -5678698350525307884L;

    /** 属性配置注解的类注解的值 */
    @Column(name = "beanConfigName", nullable = false, length = CommonPlatformConstant.LENGTH_128)
    private String beanConfigName;

    /** 字段名称 */
    @Column(name = "fieldEnName", nullable = false, length = CommonPlatformConstant.LENGTH_64)
    private String fieldEnName;

    /** 属性配置注解的类下的值 */
    @Column(name = "fieldCnName", nullable = false, length = CommonPlatformConstant.LENGTH_64)
    private String fieldCnName;

    public String getBeanConfigName() {
        return beanConfigName;
    }

    public void setBeanConfigName(String beanConfigName) {
        this.beanConfigName = beanConfigName;
    }

    public String getFieldEnName() {
        return fieldEnName;
    }

    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName;
    }

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName;
    }
}
