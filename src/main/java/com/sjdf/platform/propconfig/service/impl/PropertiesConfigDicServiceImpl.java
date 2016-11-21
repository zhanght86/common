package com.sjdf.platform.propconfig.service.impl;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean;
import com.sjdf.platform.propconfig.service.PropertiesConfigDicService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2015-08-28
 * 属性配置中和配置库相关操作
 *
 * @author wangpeng
 */
@Service
public class PropertiesConfigDicServiceImpl extends PropertiesConfigBaseServiceImpl implements PropertiesConfigDicService {

    /**
     * 根据配置库类的全路径保存更新
     *
     * @param dictionaryPath 配置库类的全路径
     */
    @Override
    public PropertiesConfigDictionaryBean saveOrUpdate(String dictionaryPath) {
        List<? extends Dictionary> dicList = ConfigManager.getInstance().getDictionary(dictionaryPath);
        if (dicList == null || dicList.isEmpty()) {
            return null;
        }
        JSONObject initValue = getInitValue(dicList);
        PropertiesConfigDictionaryBean dicBean = configDao.findDictionaryByPath(dictionaryPath);
        if (dicBean == null) {
            dicBean = new PropertiesConfigDictionaryBean();
            dicBean.setDictionaryPath(dictionaryPath);
        }
        dicBean.setAttrCnNames(initValue.toString());
        try {
            Class<?> clazz = Class.forName(dictionaryPath);
            BeanName beanName = clazz.getAnnotation(BeanName.class);
            dicBean.setBeanName(beanName.name());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(dictionaryPath + "对应的配置库不存在", e);
        }
        baseDao.saveOrUpdate(dicBean);
        return dicBean;
    }

    /**
     * 根据配置库类的全路径级联删除数据
     *
     * @param dictionaryPath 配置库类的全路径
     */
    @Override
    public void deleteCascade(String dictionaryPath) {
        PropertiesConfigDictionaryBean dicBean = configDao.findDictionaryByPath(dictionaryPath);
        if (dicBean != null) {
            configDao.deleteCascadeByDic(dicBean);
        }
    }

    /**
     * 将配置库封装为初始化的值
     */
    private JSONObject getInitValue(List<? extends Dictionary> dicList) {
        JSONObject initValue = new JSONObject();
        for (Dictionary dic : dicList) {
            initValue.put(dic.getAttr(), dic.getCnName());
        }
        return initValue;
    }
}
