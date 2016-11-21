package com.sjdf.platform.propconfig.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.propconfig.constant.PropertiesConfigConst;
import com.sjdf.platform.propconfig.vo.CheckboxVo;

/**
 * 2015-08-26
 * 属性配置项
 *
 * @author wangpeng
 */
@Entity
@Table(name = "home_properties_config_bean")
public class PropertiesConfigBean extends BaseBean {
    private static final long serialVersionUID = -2358106779704464413L;

    /** 属性bean */
    @ManyToOne
    @JoinColumn(name = "configBeanId")
    private PropertiesConfigFieldBean field;

    /** 配置库bean */
    @ManyToOne
    @JoinColumn(name = "dictionaryId")
    private PropertiesConfigDictionaryBean dictionary;

    /** 页面勾选的值 */
    @Column(name = "configValue", nullable = false)
    private String configValue;

    /** 每个分类的默认值*/
    @Lob
    @Column(columnDefinition = "longtext")
    private String defValue;

    @Transient
    private List<CheckboxVo> checkboxList;

    @Transient
    private List<FixedVo> defaultVoList;

    public PropertiesConfigFieldBean getField() {
        return field;
    }

    public void setField(PropertiesConfigFieldBean field) {
        this.field = field;
    }

    public PropertiesConfigDictionaryBean getDictionary() {
        return dictionary;
    }

    public void setDictionary(PropertiesConfigDictionaryBean dictionary) {
        this.dictionary = dictionary;
    }

    public final String getDefValue() {
        return defValue;
    }

    public final void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public List<CheckboxVo> getCheckboxList() {
        checkboxList = new ArrayList<>();
        JSONObject json = JSONObject.fromObject(configValue);
        for (Iterator<?> it = json.keys(); it.hasNext(); ) {
            String key = it.next().toString();
            int value = json.getInt(key);
            checkboxList.add(new CheckboxVo(key, value == PropertiesConfigConst.SELECTED));
        }
        return checkboxList;
    }

    public final List<FixedVo> getDefaultVoList() {
        return defaultVoList;
    }

    public final void setDefaultVoList(List<FixedVo> defaultVoList) {
        this.defaultVoList = defaultVoList;
    }
}
