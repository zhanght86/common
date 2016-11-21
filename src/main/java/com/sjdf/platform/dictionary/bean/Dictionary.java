package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.annotations.Json;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Comparator;

/**
 * Create at 2012-04-05
 * 配置库, 数据字典
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_dictionary")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dictionary_type", discriminatorType = DiscriminatorType.STRING, length = 100)
@DiscriminatorValue("DICTIONARY")
@BeanName(name = "数据字典")
public class Dictionary extends BaseBean {
    private static final long serialVersionUID = -244496756933887654L;
    public static final Comparator<Dictionary> COMPARATOR = new Comparator<Dictionary>() {
        @Override
        public int compare(Dictionary o1, Dictionary o2) {
            int cls = o1.getClass().getName().compareTo(o2.getClass().getName());
            if (cls == 0) {
                cls = Long.compare(o1.getOrderBy(), o2.getOrderBy());
            }
            return cls;
        }
    };
    /** 属性 */
    private long attr;
    /** 值 */
    @Lob
    @Column(columnDefinition = "longtext")
    private String value;
    /** 名称(en) */
    private String enName;
    /** 名称(cn) */
    private String cnName;
    /** 显示顺序 */
    private long orderBy;
    /**
     * 名称语言类别
     *
     * @see com.sjdf.platform.dictionary.bean.LangType
     */
    private long langType;
    /** 有效标记;1:无效;2:有效 */
    private long valid;

    /** 引用;在操作函数【OperatorFunction】中，用作功能小分类 */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "refId")
    private Dictionary ref;

    /** 系统类别 */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "systemId")
    private Dictionary systemType;

    /** 子系统 */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "subsystemId")
    private Dictionary subsystem;

    /** 功能大类 */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "functionId")
    private Dictionary functionClass;

    /** 操作动作 */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "operateId")
    private Dictionary operateAction;

    /** 临时数据: 配置库类型clazz全名称 */
    @Transient
    private transient String clazz;
    /** 临时数据: 当前记录所引用的类型名称 */
    @Transient
    private transient String refClazz;

    public boolean isValid() {
        return valid == CommonPlatformConstant.LENGTH_2;
    }

    @Json(excluded = true)
    public long getLongValue() {
        return Long.parseLong(value);
    }

    @ModifyRecord(name = "有效状态")
    public String getValidInfo() {
        return isValid() ? "有效" : "无效";
    }

    @ModifyRecord(name = "属性")
    public long getAttr() {
        return attr;
    }

    public void setAttr(long attr) {
        this.attr = attr;
    }

    @ModifyRecord(name = "值")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ModifyRecord(name = "名称(en)")
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @ModifyRecord(name = "名称(cn)")
    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    @ModifyRecord(name = "排序")
    public long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(long orderBy) {
        this.orderBy = orderBy;
    }

    public long getLangType() {
        return langType;
    }

    public void setLangType(long langType) {
        this.langType = langType;
    }

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    public Dictionary getRef() {
        return ref;
    }

    public void setRef(Dictionary ref) {
        this.ref = ref;
    }

    /** 临时数据 */
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /** 临时数据 */
    public String getRefClazz() {
        return refClazz;
    }

    public void setRefClazz(String refClazz) {
        this.refClazz = refClazz;
    }

    /** 获取配置库的形象名称 */
    public String getClassName() {
        return DictionaryHelper.getClazzHumaneName(getClass());
    }

    /** 根据语言类型获取该条记录的名称 */
    public String getName() {
        return DictionaryHelper.getName(getClass(), attr, langType);
    }

    /** 获取语言类型名称 */
    public String getLangName() {
        return DictionaryHelper.getName(LangType.class, langType);
    }

    /** 获取引用信息 */
    public String getValueInfo() {
        if (StringUtils.hasText(value)) {
            return value;
        }
        return getRefInfo(getRef());
    }

    /** 获取真实记录,如果ref==null则返回this,如果ref!=null返回所引用的最后一条记录 */
    public Dictionary getSelf() {
        if (getRef() == null) {
            return this;
        }
        return getSelf(getRef());
    }

    /** 获取真实记录,如果ref==null则返回this,如果ref!=null返回所引用的最后一条记录 */
    private Dictionary getSelf(Dictionary ref) {
        if (ref.getRef() == null || ref.getRef().getId() == 0) {
            return ref;
        } else {
            return getSelf(ref.getRef());
        }
    }

    /** 递归获取引用信息 */
    private String getRefInfo(Dictionary ref) {
        if (ref == null) {
            return null;
        }

        if (ref.getRef() == null) {
            // 引用值显示：引用类别.属性：值
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(ref.getClass())).append(".").append(ref.getAttr()).append(":").append(ref.getValue()).toString();
        } else {
            return getRefInfo(ref.getRef());
        }
    }

    /** 获取直接引用 */
    @ModifyRecord(name = "引用")
    public String getDirectRef() {
        if (ref != null) {
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(ref.getClass())).append(".").append(ref.getAttr()).toString();
        }
        return null;
    }

    /** 获取系统类型 */
    @ModifyRecord(name = "系统类型")
    public String getSystemTypeRef() {
        if (systemType != null) {
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(systemType.getClass())).append(".").append(systemType.getAttr()).toString();
        }
        return null;
    }

    /** 获取子系统类型 */
    @ModifyRecord(name = "子系统类型")
    public String getSubsystemRef() {
        if (subsystem != null) {
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(subsystem.getClass())).append(".").append(subsystem.getAttr()).toString();
        }
        return null;
    }

    /** 获取功能大类 */
    @ModifyRecord(name = "功能大类")
    public String getFunctionClassRef() {
        if (functionClass != null) {
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(functionClass.getClass())).append(".").append(functionClass.getAttr()).toString();
        }
        return null;
    }

    /** 获取操作动作 */
    @ModifyRecord(name = "操作动作")
    public String getOperateActionRef() {
        if (operateAction != null) {
            return new StringBuilder(CommonPlatformConstant.LENGTH_128).append(DictionaryHelper.getClazzHumaneName(operateAction.getClass())).append(".").append(operateAction.getAttr()).toString();
        }
        return null;
    }

    public Dictionary getSystemType() {
        return systemType;
    }

    public void setSystemType(Dictionary systemType) {
        this.systemType = systemType;
    }

    public Dictionary getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(Dictionary subsystem) {
        this.subsystem = subsystem;
    }

    public Dictionary getFunctionClass() {
        return functionClass;
    }

    public void setFunctionClass(Dictionary functionClass) {
        this.functionClass = functionClass;
    }

    public Dictionary getOperateAction() {
        return operateAction;
    }

    public void setOperateAction(Dictionary operateAction) {
        this.operateAction = operateAction;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_500);
        builder.append("Dictionary [id=");
        builder.append(getId());
        builder.append(", attr=");
        builder.append(attr);
        builder.append(", value=");
        builder.append(value);
        builder.append(", enName=");
        builder.append(enName);
        builder.append(", cnName=");
        builder.append(cnName);
        builder.append(", orderBy=");
        builder.append(orderBy);
        builder.append(", langType=");
        builder.append(langType);
        builder.append(", valid=");
        builder.append(valid);
        builder.append(", ref=");
        builder.append(ref);
        builder.append(", systemType=");
        builder.append(systemType);
        builder.append(", subsystem=");
        builder.append(subsystem);
        builder.append(", functionType=");
        builder.append(functionClass);
        builder.append(", operateAction=");
        builder.append(operateAction);
        builder.append("]");
        return builder.toString();
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        xml.append("<dictionary>");
        xml.append("<clazz><![CDATA[").append(getClass().getName()).append("]]></clazz>");
        xml.append("<id><![CDATA[").append(getId()).append("]]></id>");
        xml.append("<attr><![CDATA[").append(getAttr()).append("]]></attr>");
        xml.append("<value><![CDATA[").append(getValue()).append("]]></value>");
        xml.append("<enName><![CDATA[").append(getEnName()).append("]]></enName>");
        xml.append("<cnName><![CDATA[").append(getCnName()).append("]]></cnName>");
        xml.append("<langType><![CDATA[").append(getLangType()).append("]]></langType>");
        xml.append("<valid><![CDATA[").append(getValid()).append("]]></valid>");
        xml.append("<orderBy><![CDATA[").append(getOrderBy()).append("]]></orderBy>");
        xml.append("<ref><![CDATA[").append(getRef() != null ? getRef().getId() : 0).append("]]></ref>");
        xml.append("<systemType><![CDATA[").append(getSystemType() != null ? getSystemType().getId() : 0).append("]]></systemType>");
        xml.append("<subsystem><![CDATA[").append(getSubsystem() != null ? getSubsystem().getId() : 0).append("]]></subsystem>");
        xml.append("<functionClass><![CDATA[").append(getFunctionClass() != null ? getFunctionClass().getId() : 0).append("]]></functionClass>");
        xml.append("<operateAction><![CDATA[").append(getOperateAction() != null ? getOperateAction().getId() : 0).append("]]></operateAction>");
        xml.append("</dictionary>");
        return xml.toString();
    }
}
