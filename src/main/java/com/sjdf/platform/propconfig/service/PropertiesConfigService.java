package com.sjdf.platform.propconfig.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.common.PropertiesConfig;
import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;
import com.sjdf.platform.propconfig.vo.ConfigDefaultVo;
import com.sjdf.platform.propconfig.vo.PropertiesConfigVo;

/**
 * 2015-08-26
 * 属性配置管理
 *
 * @author wangpeng
 */
public interface PropertiesConfigService extends BaseService {

    /**
     * 将配置库PropertiesConfig中所有配置项初始化进数据库<code>PropertiesConfigBean</code>
     */
    void initAllPropertiesConfig();

    /**
     * 初始化一条配置项
     *
     * @param config 配置库PropertiesConfig中的一条数据
     */
    void initSinglePropertiesConfig(PropertiesConfig config) throws Exception;

    /**
     * 获取页面下拉列表
     */
    List<FixedVo> getFixedVoList();

    /**
     * 组装页面显示数据
     *
     * @param attr 配置库attr
     */
    PropertiesConfigVo findConfigVo(long attr);

    /**
     * 更新数据
     *
     * @param arr 页面参数
     */
    void updateConfig(JSONArray arr);

    /**
     * 通过entity类型，配置库类型和配置库中的属性获取所有选中的字段名称
     *
     * @param beanClass entity类型
     * @param dicClass  配置库类型
     * @param attr      配置库中的属性
     */
    List<String> findFieldEnNames(Class<?> beanClass, Class<?> dicClass, long attr);

    /**
     * 为设置默认值查询属性配置项
     * @param id 属性配置项Id
     * @return 初始化后的属性配置项
     */
    PropertiesConfigBean findConfigForDefault(long id);

    /**
     * 设置默认值
     * @param config
     */
    Message updateDefaultConfig(PropertiesConfigBean config);

    /**
     * 根据配置库类型查询所有默认值
     * @param dictionary  配置库对象
     * @return  默认值列表
     */
    List<ConfigDefaultVo> findDefaultList(Dictionary dictionary);
}
