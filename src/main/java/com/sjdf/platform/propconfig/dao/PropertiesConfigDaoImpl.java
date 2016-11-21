package com.sjdf.platform.propconfig.dao;

import com.sjdf.platform.common.dao.impl.BaseDaoImpl;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 2015-08-27
 *
 * @author wangpeng
 */
@Component
public class PropertiesConfigDaoImpl extends BaseDaoImpl implements PropertiesConfigDao {

    /**
     * 根据bean的配置名称和字段英文名称查找配置bean字段
     *
     * @param beanConfigName 注解bean名称
     * @param fieldEnName    字段名称(该参数可选)
     * @return 列表
     */
    @Override
    public List<PropertiesConfigFieldBean> findField(String beanConfigName, String fieldEnName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigFieldBean.class);
        criteria.add(Restrictions.eq("beanConfigName", beanConfigName));
        if (PlatformUtils.hasText(fieldEnName)) {
            criteria.add(Restrictions.eq("fieldEnName", fieldEnName));
        }
        return listByCriteria(criteria);
    }

    /**
     * 查找过期的字段配置
     *
     * @param beanConfigName      注解bean名称
     * @param newAllfieldNameList 当前bean下注解的字段名称
     * @return 列表
     */
    @Override
    public List<PropertiesConfigFieldBean> findExpiredBeanList(String beanConfigName, List<String> newAllfieldNameList) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigFieldBean.class);
        criteria.add(Restrictions.eq("beanConfigName", beanConfigName));
        criteria.add(Restrictions.not(Restrictions.in("fieldEnName", newAllfieldNameList)));
        return listByCriteria(criteria);
    }

    /**
     * 查找过期的字段配置
     *
     * @param newAllConfigNameList 当前所有注解的bean的名称
     * @return 列表
     */
    @Override
    public List<PropertiesConfigFieldBean> findExpiredBeanList(List<String> newAllConfigNameList) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigFieldBean.class);
        criteria.add(Restrictions.not(Restrictions.in("beanConfigName", newAllConfigNameList)));
        return listByCriteria(criteria);
    }

    /**
     * 删除PropertiesConfigFieldBean同时删除对应的PropertiesConfigBean
     *
     * @param fieldBean 属性配置entity的字段Bean
     */
    @Override
    public void deleteCascadeByField(PropertiesConfigFieldBean fieldBean) {
        String sql = "delete from home_properties_config_bean where configBeanId = ?";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setLong(0, fieldBean.getId());
        sqlQuery.executeUpdate();
        delete(fieldBean);
    }

    /**
     * 级联删除所有字段
     *
     * @param fieldList 属性配置entity的字段Bean的集合
     */
    @Override
    public void deleteAllCascadeByField(List<PropertiesConfigFieldBean> fieldList) {
        if (fieldList != null && !fieldList.isEmpty()) {
            for (PropertiesConfigFieldBean fieldBean : fieldList) {
                deleteCascadeByField(fieldBean);
            }
        }
    }

    /**
     * 根据配置路径查找配置库
     *
     * @param dictionaryPath 配置库类的全路径
     * @return 属性配置项中的配置库
     */
    @Override
    public PropertiesConfigDictionaryBean findDictionaryByPath(String dictionaryPath) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigDictionaryBean.class);
        criteria.add(Restrictions.eq("dictionaryPath", dictionaryPath));
        List<PropertiesConfigDictionaryBean> list = listByCriteria(criteria);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    /**
     * 删除PropertiesConfigDictionaryBean同时删除对应的PropertiesConfigBean
     *
     * @param dicBean 属性配置的配置库
     */
    @Override
    public void deleteCascadeByDic(PropertiesConfigDictionaryBean dicBean) {
        String hql = "delete from home_properties_config_bean where dictionaryId = ?";
        Query query = currentSession().createQuery(hql);
        query.setLong(0, dicBean.getId());
        query.executeUpdate();
        delete(dicBean);
    }

    /**
     * 根据配置库id和字段id查找配置数据
     *
     * @param dicId   配置库id
     * @param fieldId 字段id
     * @return 属性配置项
     */
    @Override
    public PropertiesConfigBean findConfigByDicAndField(long dicId, long fieldId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigBean.class);
        criteria.add(Restrictions.eq("field.id", fieldId));
        criteria.add(Restrictions.eq("dictionary.id", dicId));
        List<PropertiesConfigBean> list = listByCriteria(criteria);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    /**
     * 根据配置库和字段列表查找所有的配置项
     *
     * @param dicBean       配置库
     * @param fieldBeanList 字段列表
     * @return 属性配置项列表
     */
    @Override
    public List<PropertiesConfigBean> findConfigList(PropertiesConfigDictionaryBean dicBean, List<PropertiesConfigFieldBean> fieldBeanList) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PropertiesConfigBean.class);
        criteria.add(Restrictions.eq("dictionary.id", dicBean.getId()));
        if (fieldBeanList != null && !fieldBeanList.isEmpty()) {
            criteria.add(Restrictions.in("field", fieldBeanList));
        }
        return listByCriteria(criteria);
    }
}
