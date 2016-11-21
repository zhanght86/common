package com.sjdf.platform.check.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.AssembleStringSymbols;
import com.sjdf.platform.dictionary.bean.DisplayInfoType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * 2012-8-24 下午1:56:51
 * 展示信息实体类
 *
 * @author frank
 */
@Entity
@Table(name = "p_display_info")
public class DisplayInfoBean extends BaseBean {
    private static final long serialVersionUID = 2882157632658724002L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DisplayInfoBean.class);

    /** 信息内容 */
    @Type(type = "text")
    private String content;

    /** 信息类别 */
    private Long infoType;

    /** 所属类别 */
    @Type(type = "text")
    private String ownerType;

    /** 所属类别拆分后的map */
    @Transient
    private Map<String, List<String>> ownerTypeMap = new LinkedHashMap<>();

    /** 信息类别String字符串 */
    @Transient
    private String infoTypeString;

    @Transient
    private Map<String, String> conditionMap = new HashMap<>();

    @Transient
    private String ownerTypeString;

    @Transient
    private List<ConditionVoBean> conditionVoBeanList = new ArrayList<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getInfoType() {
        return infoType;
    }

    public void setInfoType(Long infoType) {
        this.infoType = infoType;
    }

    public String getOwnerType() {
        getOwnerTypeMap();
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    /** 该方法做两件事：1、将所属类别转换为中文名称；2、将所属类别转化为MAP */
    public Map<String, List<String>> getOwnerTypeMap() {
        try {
            //所属类别中文转换
            StringBuilder ownerTypeStringSb = new StringBuilder();
            if (PlatformUtils.hasText(ownerType)) {
                //分号空格拆分
                String[] semicolonSpaceSplit = ownerType.split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE));
                for (String colonStr : semicolonSpaceSplit) {
                    if (!PlatformUtils.hasText(colonStr)) {
                        continue;
                    }
                    //冒号拆分
                    String[] colonSplit = colonStr.split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON));
                    if (colonSplit.length == 0) {
                        continue;
                    }
                    //所属类别中文转换 -- 类名称
                    ownerTypeStringSb.append(DictionaryHelper.getClassNameMap().get(colonSplit[0]));
                    //所属类别中文转换 -- 类
                    Class tempClass = Class.forName(colonSplit[0]);
                    //所属类别中文转换 -- 冒号拼接
                    ownerTypeStringSb.append(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON));
                    if (colonSplit.length > 1) {
                        //逗号拆分
                        String[] commaSplit = colonSplit[1].split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA));
                        //获取属性所对应的中文名称并拼接
                        for (int i = 0; i < commaSplit.length; i++) {
                            ownerTypeStringSb.append(DictionaryHelper.getName(tempClass, Long.valueOf(commaSplit[i])));
                            if (i < commaSplit.length - 1) {
                                ownerTypeStringSb.append(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA));
                            }
                        }
                        ownerTypeStringSb.append("<br />");
                        //新建List<String>
                        List<String> commaSplitList = Arrays.asList(commaSplit);
                        //将最后拆分的内容 以 key-value
                        ownerTypeMap.put(colonSplit[0], commaSplitList);
                    } else {
                        ownerTypeMap.put(colonSplit[0], new ArrayList<String>());
                    }
                }
                ownerTypeString = ownerTypeStringSb.toString();
                if (!PlatformUtils.hasText(ownerTypeString)) {
                    ownerTypeMap = null;
                }
            } else {
                ownerTypeMap = null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ownerTypeMap;
    }

    public void setOwnerTypeMap(Map<String, List<String>> ownerTypeMap) {
        this.ownerTypeMap = ownerTypeMap;
    }

    public String getInfoTypeString() {
        List<DisplayInfoType> displayInfoTypeList = DictionaryHelper.getDictionary(DisplayInfoType.class);
        for (DisplayInfoType displayInfoType : displayInfoTypeList) {
            if (Tools.compareLong(displayInfoType.getAttr(), infoType)) {
                infoTypeString = displayInfoType.getCnName();
                break;
            }
        }
        return infoTypeString;
    }

    public void setInfoTypeString(String infoTypeString) {
        this.infoTypeString = infoTypeString;
    }

    public Map<String, String> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public String getOwnerTypeString() {
        getOwnerTypeMap();
        return ownerTypeString;
    }

    public void setOwnerTypeString(String ownerTypeString) {
        this.ownerTypeString = ownerTypeString;
    }

    public List<ConditionVoBean> getConditionVoBeanList() {
        return conditionVoBeanList;
    }

    public void setConditionVoBeanList(List<ConditionVoBean> conditionVoBeanList) {
        this.conditionVoBeanList = conditionVoBeanList;
    }

    /**
     * @return 展示信息XML数据
     * 将【展示信息】转换为XML数据
     */
    public String toXML() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_2048);
        builder.append("<displayInfo>");
        if (Tools.longIsNotNUllAndZero(getId())) {
            builder.append("<id><![CDATA[").append(getId()).append("]]></id>");
        }
        if (Tools.stringIsNotNullAndEmpty(content)) {
            builder.append("<content><![CDATA[").append(content).append("]]></content>");
        }
        if (Tools.longIsNotNUllAndZero(infoType)) {
            builder.append("<infoType><![CDATA[").append(infoType).append("]]></infoType>");
        }
        if (Tools.stringIsNotNullAndEmpty(ownerType)) {
            builder.append("<ownerType><![CDATA[").append(ownerType).append("]]></ownerType>");
        }
        if (conditionVoBeanList != null && !conditionVoBeanList.isEmpty()) {
            builder.append("<ownerTypeMap>");
            for (ConditionVoBean conditionVoBean : conditionVoBeanList) {
                builder.append("<ownerType name='");
                builder.append(conditionVoBean.getKey());
                builder.append("' ");
                if (Tools.stringIsNotNullAndEmpty(conditionVoBean.getMarkValue())) {
                    builder.append("special='");
                    builder.append(conditionVoBean.getMarkValue());
                    builder.append("'");
                }
                builder.append(">");
                if (Tools.stringIsNotNullAndEmpty(conditionVoBean.getValue())) {
                    //逗号分裂具体所属类别值
                    String[] commaSplit = conditionVoBean.getValue().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA));
                    for (String commaStr : commaSplit) {
                        builder.append("<element>");
                        builder.append(commaStr);
                        builder.append("</element>");
                    }
                }
                builder.append("</ownerType>");
            }
            builder.append("</ownerTypeMap>");
        }
        builder.append("</displayInfo>");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "DisplayInfoBean [content=" + content
                + ", infoType=" + infoType
                + ", ownerType=" + ownerType
                + ", contentMap = " + this.ownerTypeMap + "]";
    }
}
