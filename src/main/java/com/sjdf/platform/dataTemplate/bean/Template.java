package com.sjdf.platform.dataTemplate.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.TemplateClass;
import com.sjdf.platform.dictionary.bean.TemplateType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.annotations.Index;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-05-29
 * 数据模板库
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_data_template")
public class Template extends BaseBean {
    private static final long serialVersionUID = 4380580260702294623L;
    /**
     * 系统类别
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long systemType;

    /** 会员编号 */
    @Index(name = "memberNumber")
    private String memberNumber;

    /**
     * 模板大分类
     *
     * @see com.sjdf.platform.dictionary.bean.TemplateClass
     */
    @Index(name = "clazz")
    private long clazz;

    /**
     * 模板类别
     *
     * @see com.sjdf.platform.dictionary.bean.TemplateType
     */
    @Index(name = "type")
    private long type;

    /** 模板名称 */
    private String name;

    /** 模板系统名称 */
    @Index(name = "systemName")
    private String systemName;

    /** 模板数据 */
    @ElementCollection(targetClass = String.class)
    @MapKeyClass(String.class)
    @CollectionTable(name = "p_data_template_data", joinColumns = @JoinColumn(name = "template_id"))
    @MapKeyColumn(name = "template_key")
    @Column(name = "template_value")
    private Map<String, String> data = new HashMap<>();

    /** 临时数据: 模板类别列表 */
    @Transient
    private transient String typeList;

    @ModifyRecord(name = "系统类别")
    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    @ModifyRecord(name = "模板大分类")
    public String getClazzInfo() {
        return ConfigManager.getInstance().getName(TemplateClass.class, clazz);
    }

    @ModifyRecord(name = "模板类别")
    public String getTypeInfo() {
        return ConfigManager.getInstance().getName(TemplateType.class, type);
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    @ModifyRecord(name = "会员编号")
    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public long getClazz() {
        return clazz;
    }

    public void setClazz(long clazz) {
        this.clazz = clazz;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    @ModifyRecord(name = "模板名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModifyRecord(name = "模板系统名称")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    /** 临时数据 :获取指定的模板类别列表 */
    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    /** 临时数据 :获取指定的模板类别列表 */
    public List<Long> getAllTypeList() {
        List<Long> list = new ArrayList<>();
        if (StringUtils.hasText(getTypeList())) {
            String[] strs = getTypeList().split(",");
            for (String s : strs) {
                if (StringUtils.hasText(s)) {
                    list.add(Long.parseLong(s));
                }
            }
        }
        return list;
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        builder.append("<template>");
        builder.append("<id><![CDATA[").append(getId()).append("]]></id>");
        builder.append("<systemType><![CDATA[").append(systemType).append("]]></systemType>");
        builder.append("<memberNumber><![CDATA[").append(memberNumber).append("]]></memberNumber>");
        builder.append("<clazz><![CDATA[").append(clazz).append("]]></clazz>");
        builder.append("<type><![CDATA[").append(type).append("]]></type>");
        builder.append("<name><![CDATA[").append(name).append("]]></name>");
        builder.append("<systemName><![CDATA[").append(systemName).append("]]></systemName>");
        builder.append("<data>");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append("<entry>");
            builder.append("<key><![CDATA[").append(entry.getKey()).append("]]></key>");
            builder.append("<value><![CDATA[").append(entry.getValue()).append("]]></value>");
            builder.append("</entry>");
        }
        builder.append("</data>");
        builder.append("</template>");
        return builder.toString();
    }

    public String toJson() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        builder.append("{");
        String value;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            value = entry.getValue();
            builder.append("\"").append(entry.getKey()).append("\":").append("\"");
            if (PlatformUtils.hasText(value)) {
                builder.append(value);
            }
            builder.append("\",");
        }
        if (!data.isEmpty()) {
            builder.delete(builder.length() - 1, builder.length());
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        builder.append("Template [");
        builder.append("id=");
        builder.append(getId());
        builder.append(", systemType=");
        builder.append(systemType);
        builder.append(", memberNumber=");
        builder.append(memberNumber);
        builder.append(", clazz=");
        builder.append(clazz);
        builder.append(", type=");
        builder.append(type);
        builder.append(", name=");
        builder.append(name);
        builder.append(", systemName=");
        builder.append(systemName);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
