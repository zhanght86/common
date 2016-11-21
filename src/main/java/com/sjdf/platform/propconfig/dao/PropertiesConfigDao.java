package com.sjdf.platform.propconfig.dao;

import com.sjdf.platform.common.dao.BaseDao;
import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;

import java.util.List;

/**
 * 2015-08-27
 * 属性配置Dao
 *
 * @author wangpeng
 */
public interface PropertiesConfigDao extends BaseDao {

    /**
     * 根据bean的配置名称和字段英文名称查找配置bean字段
     *
     * @param beanConfigName 名称
     * @param fieldEnName    字段名称
     * @return 列表
     */
    List<PropertiesConfigFieldBean> findField(String beanConfigName, String fieldEnName);

    /**
     * 查找过期的字段配置
     *
     * @param beanConfigName      注解bean名称
     * @param newAllfieldNameList 当前bean下注解的字段名称
     * @return 列表
     */
    List<PropertiesConfigFieldBean> findExpiredBeanList(String beanConfigName, List<String> newAllfieldNameList);

    /**
     * 查找过期的字段配置
     *
     * @param newAllConfigNameList 当前所有注解的bean的名称
     * @return 列表
     */
    List<PropertiesConfigFieldBean> findExpiredBeanList(List<String> newAllConfigNameList);

    /**
     * 删除PropertiesConfigFieldBean同时删除对应的PropertiesConfigBean
     *
     * @param fieldBean 属性配置entity的字段Bean
     */
    void deleteCascadeByField(PropertiesConfigFieldBean fieldBean);

    /**
     * 级联删除所有字段
     *
     * @param fieldList 属性配置entity的字段Bean的集合
     */
    void deleteAllCascadeByField(List<PropertiesConfigFieldBean> fieldList);

    /**
     * 根据配置路径查找配置库
     *
     * @param dictionaryPath 配置库类的全路径
     * @return 属性配置项中的配置库
     */
    PropertiesConfigDictionaryBean findDictionaryByPath(String dictionaryPath);

    /**
     * 删除PropertiesConfigDictionaryBean同时删除对应的PropertiesConfigBean
     *
     * @param dicBean 属性配置的配置库
     */
    void deleteCascadeByDic(PropertiesConfigDictionaryBean dicBean);

    /**
     * 根据配置库id和字段id查找配置数据
     *
     * @param dicId   配置库id
     * @param fieldId 字段id
     * @return 属性配置项
     */
    PropertiesConfigBean findConfigByDicAndField(long dicId, long fieldId);

    /**
     * 根据配置库和字段列表查找所有的配置项
     *
     * @param dicBean       配置库
     * @param fieldBeanList 字段列表
     * @return 属性配置项列表
     */
    List<PropertiesConfigBean> findConfigList(PropertiesConfigDictionaryBean dicBean, List<PropertiesConfigFieldBean> fieldBeanList);
}
