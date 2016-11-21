package com.sjdf.platform.propconfig.service;

import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;

import java.util.List;

/**
 * 2015-08-28
 * 属性配置中和配置entity类相关操作
 *
 * @author wangpeng
 */
public interface PropertiesConfigFieldService extends BaseService {

    /**
     * @param fieldBean 属性配置字段记录
     */
    void saveOrUpdateByConfigNameAndEnName(PropertiesConfigFieldBean fieldBean);

    /**
     * 删除beanConfigName下不包含在newAllfieldNameList里的字段记录
     *
     * @param beanConfigName      属性配置注解的类注解的值
     * @param newAllfieldNameList entity类下所有有<code>PropertiesConfig</code>注解的字段名称
     */
    void deleteExpiredConfig(String beanConfigName, List<String> newAllfieldNameList);

    /**
     * 删除原来注解为beanConfigName的数据
     *
     * @param newAllConfigNameList 现在所有的注解bean名称
     */
    void deleteExpiredConfig(List<String> newAllConfigNameList);

    /**
     * 根据entity名称和字段名定位一个字段bean
     *
     * @param beanName  注解的entity名称
     * @param filedName 字段名称
     * @return 属性配置项bean对应的所有属性
     */
    PropertiesConfigFieldBean findField(String beanName, String filedName);
}
