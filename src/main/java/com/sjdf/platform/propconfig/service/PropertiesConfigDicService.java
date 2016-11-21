package com.sjdf.platform.propconfig.service;

import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean;

/**
 * 2015-08-28
 * 属性配置中和配置库相关操作
 *
 * @author wangpeng
 */
public interface PropertiesConfigDicService extends BaseService {

    /**
     * 根据配置库类的全路径保存更新
     *
     * @param dictionaryPath 配置库类的全路径
     */
    PropertiesConfigDictionaryBean saveOrUpdate(String dictionaryPath);

    /**
     * 根据配置库类的全路径级联删除数据
     *
     * @param dictionaryPath 配置库类的全路径
     */
    void deleteCascade(String dictionaryPath);
}
