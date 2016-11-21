/**
 *
 */
package com.sjdf.platform.notify.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.ExecuteStatus;
import com.sjdf.platform.dictionary.bean.MessageEngineConfig;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2013-1-8 下午3:23:37
 * 消息引擎数据推送跟踪信息
 *
 * @author ketqi
 */
@Entity
@Table(name = "p_notify_track")
public class NotifyTrack extends BaseBean {
    private static final long serialVersionUID = -6571631672811354578L;

    /** 跟踪信息的上级 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private NotifyTrack parent;

    /** parent下所有子节点 */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<NotifyTrack> childList = new ArrayList<>();

    /**
     * 消息引擎配置
     *
     * @see com.sjdf.platform.dictionary.bean.MessageEngineConfig
     */
    private long mec;

    /**
     * 操作动作
     *
     * @see com.sjdf.platform.dictionary.bean.OperatorAction
     */
    private long operateType;

    /** 数据接收者地址 */
    private String url;

    /**
     * 执行状态
     *
     * @see com.sjdf.platform.dictionary.bean.ExecuteStatus
     */
    private long status;

    /** 数据或者失败原因 */
    @Lob
    @Column(columnDefinition = "text")
    private String data;

    /** 扩展数据;格式:url格式;key1=value1&key2=value2;so value中不要包含特殊符号& */
    @Lob
    @Column(columnDefinition = "text")
    private String extendData;

    public NotifyTrack() {
    }

    /**
     * @param mec         消息引擎配置
     * @param operateType 操作动作
     * @param data        数据
     */
    public NotifyTrack(long mec, long operateType, String data) {
        this(mec, operateType, data, "");
    }

    /**
     * @param mec         消息引擎配置
     * @param operateType 操作动作
     * @param data        数据
     * @param extendData  扩展数据
     */
    public NotifyTrack(long mec, long operateType, String data, String extendData) {
        this.mec = mec;
        this.operateType = operateType;
        this.data = data;
        this.extendData = extendData;
        this.status = ExecuteStatus.BEGIN;
    }

    /**
     * @param mec           消息引擎配置
     * @param operateType   操作动作
     * @param data          数据
     * @param extendDataMap 扩展数据
     */
    public NotifyTrack(long mec, long operateType, String data, Map<String, String> extendDataMap) {
        this(mec, operateType, data);
        setExtendDataMap(extendDataMap);
    }

    /**
     * 默认状态是失败
     *
     * @param parent 消息引擎数据推送跟踪信息
     * @param url    地址
     */
    public NotifyTrack(NotifyTrack parent, String url, String data) {
        this(parent.getMec(), parent.getOperateType(), data);
        this.parent = parent;
        this.url = url;
        this.status = ExecuteStatus.FAIL;
    }

    public NotifyTrack getParent() {
        return parent;
    }

    public void setParent(NotifyTrack parent) {
        this.parent = parent;
    }

    public List<NotifyTrack> getChildList() {
        return childList;
    }

    public void setChildList(List<NotifyTrack> childList) {
        this.childList = childList;
    }

    public long getMec() {
        return mec;
    }

    public void setMec(long mec) {
        this.mec = mec;
    }

    public long getOperateType() {
        return operateType;
    }

    public void setOperateType(long operateType) {
        this.operateType = operateType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtendData() {
        return extendData;
    }

    public void setExtendData(String extendData) {
        this.extendData = extendData;
    }

    public String setExtendDataMap(Map<String, String> extendDataMap) {
        StringBuilder builder = new StringBuilder();
        if (extendDataMap != null) {
            for (Map.Entry<String, String> entry : extendDataMap.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (builder.length() > 1) {
                builder.deleteCharAt(builder.length() - 1);
                extendData = builder.toString();
            }
        }
        return extendData;
    }

    public Map<String, String> getExtendDataMap() {
        Map<String, String> map = new HashMap<>();
        if (extendData != null && !"".equals(extendData)) {
            String[] items = extendData.split("&");
            for (String item : items) {
                if (item != null && !"".equals(item)) {
                    String[] result = item.split("=");
                    if (result.length == CommonPlatformConstant.LENGTH_2) {
                        map.put(result[0], result[1]);
                    }
                }
            }
        }
        return map;
    }

    public String getMecInfo() {
        return ConfigManager.getInstance().getName(MessageEngineConfig.class, mec);
    }

    public String getOperateTypeInfo() {
        return ConfigManager.getInstance().getName(OperatorAction.class, operateType);
    }

    public String getStatusInfo() {
        return ConfigManager.getInstance().getName(ExecuteStatus.class, status);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        builder.append("NotifyTrack [");
        builder.append(super.toString());
        builder.append(",parent=");
        builder.append(parent);
        builder.append(", mec=");
        builder.append(mec);
        builder.append(", operateType=");
        builder.append(operateType);
        builder.append(", url=");
        builder.append(url);
        builder.append(", status=");
        builder.append(status);
        builder.append(", data=");
        builder.append(data);
        builder.append(",extendData=");
        builder.append(extendData);
        builder.append("]");
        return builder.toString();
    }
}
