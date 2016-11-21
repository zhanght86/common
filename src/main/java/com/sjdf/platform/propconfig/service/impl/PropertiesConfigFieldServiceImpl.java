package com.sjdf.platform.propconfig.service.impl;

import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;
import com.sjdf.platform.propconfig.service.PropertiesConfigFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2015-08-28
 * 属性配置中和配置entity类相关操作
 *
 * @author wangpeng
 */
@Service("fieldService")
public class PropertiesConfigFieldServiceImpl extends PropertiesConfigBaseServiceImpl implements PropertiesConfigFieldService {

    /**
     * @param fieldBean 属性配置字段记录
     */
    @Override
    public void saveOrUpdateByConfigNameAndEnName(PropertiesConfigFieldBean fieldBean) {
        if (fieldBean == null) {
            throw new IllegalArgumentException("调用PropertiesConfigService.saveOrUpdateByConfigNameAndEnName()方法时参数不能为null");
        }
        List<PropertiesConfigFieldBean> oldFieldList = configDao.findField(fieldBean.getBeanConfigName(), fieldBean.getFieldEnName());
        if (oldFieldList != null && !oldFieldList.isEmpty()) {
            PropertiesConfigFieldBean oldFieldBean = oldFieldList.get(0);
            oldFieldBean.setFieldCnName(fieldBean.getFieldCnName());
            this.update(oldFieldBean);
        } else {
            this.save(fieldBean);
        }
    }

    /**
     * 删除beanConfigName下不包含在newAllfieldNameList里的字段记录
     *
     * @param beanConfigName      属性配置注解的类注解的值
     * @param newAllfieldNameList entity类下所有有<code>PropertiesConfig</code>注解的字段名称
     */
    @Override
    public void deleteExpiredConfig(String beanConfigName, List<String> newAllfieldNameList) {
        List<PropertiesConfigFieldBean> expiredFieldList = configDao.findExpiredBeanList(beanConfigName, newAllfieldNameList);
        if (expiredFieldList != null && !expiredFieldList.isEmpty()) {
            configDao.deleteAllCascadeByField(expiredFieldList);
        }
    }

    /**
     * 删除原来注解为beanConfigName的数据
     *
     * @param newAllConfigNameList 现在所有的注解bean名称
     */
    @Override
    public void deleteExpiredConfig(List<String> newAllConfigNameList) {
        List<PropertiesConfigFieldBean> expiredFieldList = configDao.findExpiredBeanList(newAllConfigNameList);
        if (expiredFieldList != null && !expiredFieldList.isEmpty()) {
            configDao.deleteAllCascadeByField(expiredFieldList);
        }
    }

    /**
     * 根据entity名称和字段名定位一个字段bean
     *
     * @param beanName  注解的entity名称
     * @param filedName 字段名称
     */
    @Override
    public PropertiesConfigFieldBean findField(String beanName, String filedName) {
        List<PropertiesConfigFieldBean> list = configDao.findField(beanName, filedName);
        return list == null || list.isEmpty() ? null : list.get(0);
    }
}
