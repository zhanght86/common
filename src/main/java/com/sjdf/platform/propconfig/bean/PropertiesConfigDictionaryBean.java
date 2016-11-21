package com.sjdf.platform.propconfig.bean;

import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.propconfig.constant.PropertiesConfigConst;
import net.sf.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 2015-08-27
 * 属性配置项中的配置库
 *
 * @author wangpeng
 */
@Entity
@Table(name = "home_properties_config_dictionary")
public class PropertiesConfigDictionaryBean extends BaseBean {
    private static final long serialVersionUID = -8557576695947788289L;

    /** 配置库类的全路径 */
    @Column(name = "dictionaryPath", nullable = false, unique = true)
    private String dictionaryPath;

    /** 配置库名称 */
    @Column(name = "beanName", nullable = false)
    private String beanName;

    /** 配置库所有有效的attr和cnName组成的json字符串 */
    @Column(name = "attrCnNames", nullable = false)
    private String attrCnNames;

    public String getDictionaryPath() {
        return dictionaryPath;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setDictionaryPath(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    public String getAttrCnNames() {
        return attrCnNames;
    }

    public void setAttrCnNames(String attrCnNames) {
        this.attrCnNames = attrCnNames;
    }

    public String getInitValue() {
        JSONObject json = JSONObject.fromObject(attrCnNames);
        JSONObject initValue = new JSONObject();
        for (Iterator<?> it = json.keys(); it.hasNext(); ) {
            initValue.put(it.next().toString(), PropertiesConfigConst.NOT_SELECTED);
        }
        return initValue.toString();
    }

    /** 配置库所有有效的cnName */
    public List<String> getDicCnNameList() {
        JSONObject json = JSONObject.fromObject(attrCnNames);
        List<String> list = new ArrayList<>();
        for (Iterator<?> it = json.keys(); it.hasNext(); ) {
            list.add(json.getString(it.next().toString()));
        }
        return list;
    }
}
