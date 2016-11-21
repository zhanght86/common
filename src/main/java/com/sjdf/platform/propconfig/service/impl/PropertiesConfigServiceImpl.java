package com.sjdf.platform.propconfig.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.common.PropertiesConfig;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.propconfig.annotations.PropertiesConfigType;
import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigDictionaryBean;
import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;
import com.sjdf.platform.propconfig.cache.ConfigBeanCnNameCache;
import com.sjdf.platform.propconfig.constant.PropertiesConfigConst;
import com.sjdf.platform.propconfig.service.PropertiesConfigDicService;
import com.sjdf.platform.propconfig.service.PropertiesConfigService;
import com.sjdf.platform.propconfig.vo.CheckboxVo;
import com.sjdf.platform.propconfig.vo.ConfigDefaultVo;
import com.sjdf.platform.propconfig.vo.PropertiesConfigVo;

/**
 * 2015-08-28
 * 属性配置的数据管理
 *
 * @author wangpeng
 */
@Service("propConfigService")
public class PropertiesConfigServiceImpl extends PropertiesConfigBaseServiceImpl implements PropertiesConfigService {
    @Autowired
    private PropertiesConfigDicService dicService;

    /**
     * 将配置库PropertiesConfig中所有配置项初始化进数据库<code>PropertiesConfigBean</code>
     */
    @Override
    public void initAllPropertiesConfig() {
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionary(PropertiesConfig.class);
        if (list != null && !list.isEmpty()) {
            for (Dictionary dic : list) {
                try {
                    initSinglePropertiesConfig((PropertiesConfig) dic);
                } catch (Exception e) {
                    logger.error("初始化配置库PropertiesConfig失败", e);
                }
            }
        }
    }

    /**
     * 初始化一条配置项
     *
     * @param config 配置库PropertiesConfig中的一条数据
     * @throws Exception
     */
    @Override
    public void initSinglePropertiesConfig(PropertiesConfig config) throws Exception {
        String value = config.getValue();
        if (PlatformUtils.hasText(value)) {
            String[] nameAndPath = value.split(PropertiesConfigConst.SEPARATOR);
            if (nameAndPath.length == CommonPlatformConstant.LENGTH_2) {
                // @PropertiesConfigType注解的值
                String beanConfigName = nameAndPath[0];
                // 配置库的class
                String dictionaryPath = nameAndPath[1];
                // 1 根据配置库类的全路径保存/更新 配置库bean
                PropertiesConfigDictionaryBean dicBean = dicService.saveOrUpdate(dictionaryPath);
                if (dicBean == null) {
                    return;
                }
                String initValue = dicBean.getInitValue();
                // 2 @PropertiesConfigType注解的类下所有注解的field
                List<PropertiesConfigFieldBean> fieldList = configDao.findField(beanConfigName, null);
                // 3 组装数据初始化
                for (PropertiesConfigFieldBean fieldBean : fieldList) {
                    PropertiesConfigBean configBean = configDao.findConfigByDicAndField(dicBean.getId(), fieldBean.getId());
                    if (configBean == null) {
                        configBean = new PropertiesConfigBean();
                        configBean.setField(fieldBean);
                        configBean.setDictionary(dicBean);
                        configBean.setConfigValue(initValue);
                        configDao.save(configBean);
                    } else {
                        configBean.setConfigValue(getNewValue(initValue, configBean.getConfigValue()));
                        configDao.update(configBean);
                    }
                }
            }
        }
    }

    /**
     * 根据新的初始化值和旧的数据值获取新的数据值
     *
     * @param initValue 初始化值
     * @param oldValue  旧的数据值
     */
    private String getNewValue(String initValue, String oldValue) {
        JSONObject initJson = JSONObject.fromObject(initValue);
        JSONObject oldJson = JSONObject.fromObject(oldValue);
        JSONObject newJson = new JSONObject();
        for (@SuppressWarnings("rawtypes") Iterator iter = initJson.keys(); iter.hasNext(); ) {
            String key = iter.next().toString();
            Object old = oldJson.get(key);
            newJson.put(key, old == null ? PropertiesConfigConst.NOT_SELECTED : old.toString());
        }
        return newJson.toString();
    }

    /**
     * 获取页面下拉列表
     */
    @Override
    public List<FixedVo> getFixedVoList() {
        List<? extends Dictionary> dicList = ConfigManager.getInstance().getDictionary(PropertiesConfig.class);
        List<FixedVo> showList = new ArrayList<>();
        for (Dictionary dic : dicList) {
            PropertiesConfig pc = (PropertiesConfig) dic;
            FixedVo showVo = new FixedVo();
            showVo.setValue(pc.getCnName());
            showVo.setKey(pc.getAttr() + "");
            showList.add(showVo);
        }
        return showList;
    }

    /**
     * 组装页面显示数据
     *
     * @param attr 配置库attr
     */
    @Override
    public PropertiesConfigVo findConfigVo(long attr) {
        PropertiesConfigVo configVo = new PropertiesConfigVo();
        configVo.setAttr(attr);
        if (attr > 0) {
            String value = ConfigManager.getInstance().getValue(PropertiesConfig.class, attr);
            String[] nameAndClazz = value.split(PropertiesConfigConst.SEPARATOR);
            List<PropertiesConfigFieldBean> fieldBeanList = configDao.findField(nameAndClazz[0], null);
            PropertiesConfigDictionaryBean dicBean = configDao.findDictionaryByPath(nameAndClazz[1]);
            configVo.setDicBeanName(dicBean.getBeanName());
            configVo.setEntityBeanName(ConfigBeanCnNameCache.getInstance().getEntity(nameAndClazz[0]));
            configVo.setDicCnNameList(dicBean.getDicCnNameList());
            List<PropertiesConfigBean> dataList = configDao.findConfigList(dicBean, fieldBeanList);
            configVo.setDataList(dataList);
        }
        return configVo;
    }

    /**
     * 更新数据
     *
     * @param arr 页面参数
     */
    @Override
    public void updateConfig(JSONArray arr) {
        if (arr == null || arr.isEmpty()) {
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            long fieldId = obj.getLong("fieldId");
            long dicId = obj.getLong("dictionaryId");
            String configValue = obj.getString("configValue");
            PropertiesConfigBean bean = configDao.findConfigByDicAndField(dicId, fieldId);
            bean.setConfigValue(configValue);
            configDao.saveOrUpdate(bean);
        }
    }

    /**
     * 通过entity类型，配置库类型和配置库中的属性获取所有选中的字段名称
     *
     * @param beanClass entity类型
     * @param dicClass  配置库类型
     * @param attr      配置库中的属性
     */
    @Override
    public List<String> findFieldEnNames(Class<?> beanClass, Class<?> dicClass, long attr) {
        List<String> list = new ArrayList<>();
        PropertiesConfigType beanAnnotation = beanClass.getAnnotation(PropertiesConfigType.class);
        if (beanAnnotation == null) {
            return list;
        }
        String beanConfigName = beanAnnotation.value();
        List<PropertiesConfigFieldBean> fieldList = configDao.findField(beanConfigName, null);
        if (fieldList == null || fieldList.isEmpty()) {
            return list;
        }
        PropertiesConfigDictionaryBean dicBean = configDao.findDictionaryByPath(dicClass.getName());
        if (dicBean == null) {
            return list;
        }
        List<PropertiesConfigBean> configList = configDao.findConfigList(dicBean, fieldList);
        if (configList == null || configList.isEmpty()) {
            return list;
        }
        String attrStr = String.valueOf(attr);
        for (PropertiesConfigBean bean : configList) {
            JSONObject temp = JSONObject.fromObject(bean.getConfigValue());
            if (temp.getInt(attrStr) == PropertiesConfigConst.SELECTED) {
                PropertiesConfigFieldBean field = bean.getField();
                list.add(field.getFieldEnName());
            }
        }
        return list;
    }

    /**
     * 为设置默认值查询属性配置项
     * @param id 属性配置项Id
     * @return 初始化后的属性配置项
     */
    @Override
    public PropertiesConfigBean findConfigForDefault(long id) {
        PropertiesConfigBean config = configDao.get(PropertiesConfigBean.class, id);
        if (config != null) {
            JSONObject keyNameJson = JSONObject.fromObject(config.getDictionary().getAttrCnNames()); 
            JSONObject oldDefaultJson = new JSONObject();
            if (PlatformUtils.hasText(config.getDefValue())) {
                oldDefaultJson = JSONObject.fromObject(config.getDefValue());
            }
            List<CheckboxVo> checkboxList = config.getCheckboxList();
            if (PlatformUtils.isNotEmpty(checkboxList)) {
                List<FixedVo> defaultVoList = new ArrayList<>(checkboxList.size());
                for (CheckboxVo cv : checkboxList) {
                    FixedVo dv = new FixedVo();
                    dv.setKey(cv.getName()); // key：分类
                    dv.setValue(keyNameJson.getString(cv.getName())); // value:分类中文名称
                    if (oldDefaultJson.has(cv.getName())) {
                        dv.setRemark(oldDefaultJson.getString(cv.getName())); // remark:旧值
                    }
                    defaultVoList.add(dv);
                }
                config.setDefaultVoList(defaultVoList);
            }
        }
        return config;
    }

    /**
     * 设置默认值
     * @param config
     */
    @Override
    public Message updateDefaultConfig(PropertiesConfigBean config) {
        if (config == null || config.getId() <= 0 || !PlatformUtils.hasText(config.getDefValue())) {
            return Message.createMessage("common.checkSum.fail");
        }
        String defValue = config.getDefValue();
        config = configDao.get(PropertiesConfigBean.class, config.getId());
        if (config == null) {
            return Message.createMessage("common.checkSum.fail");
        }
        config.setDefValue(defValue);
        configDao.update(config);
        return Message.createEmptyMessage();
    }

    /**
     * 根据配置库类型查询所有默认值
     * @param dictionary  配置库对象
     * @return  默认值列表
     */
    @Override
    public List<ConfigDefaultVo> findDefaultList(Dictionary dictionary) {
        List<ConfigDefaultVo> list = new ArrayList<>();
        if (dictionary != null) {
            PropertiesConfigDictionaryBean dic = configDao.findDictionaryByPath(dictionary.getClass().getName());
            if (dic != null) {
                List<PropertiesConfigBean> configList = configDao.findConfigList(dic, null);
                if (PlatformUtils.isNotEmpty(configList)) {
                    for (PropertiesConfigBean config : configList) {
                        if (PlatformUtils.hasText(config.getDefValue())) {
                            JSONObject json = JSONObject.fromObject(config.getDefValue());
                            String key = dictionary.getAttr() + "";
                            if (json.has(key) && PlatformUtils.hasText(json.getString(key))) {
                                ConfigDefaultVo dv = new ConfigDefaultVo();
                                dv.setDictionaryId(dic.getId());
                                dv.setFieldId(config.getId());
                                dv.setFieldName(config.getField().getFieldEnName());
                                dv.setFieldVale(json.get(key));
                                list.add(dv);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}
