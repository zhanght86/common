package com.sjdf.platform.autotask.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.dictionary.bean.AutoTaskType;
import com.sjdf.platform.dictionary.bean.ExecuteStatus;
import com.sjdf.platform.dictionary.bean.ProductClass;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-11-07
 * 自动任务监控记录
 *
 * @author ketqi
 */
@Entity
@Table(name = "p_auto_task_tracker")
public class AutoTaskTracker extends BaseBean {
    private static final long serialVersionUID = -7542768399093807747L;

    /**
     * 系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    @Index(name = "i_systemType")
    private long systemType;

    /**
     * 自动任务类型
     *
     * @see com.sjdf.platform.dictionary.bean.AutoTaskType
     */
    @Index(name = "i_autoTaskType")
    private long autoTaskType;

    /**
     * 产品分类
     *
     * @see com.sjdf.platform.dictionary.bean.ProductClass
     */
    @Index(name = "i_productClass")
    private long productClass;

    /** 自动任务跟踪目标所在的master服务器ip地址 ;即服务器列表中的单个ip地址;用于标识自动任务的来源 */
    @Index(name = "i_masterIpAddress")
    private String masterIpAddress;

    /** 自动任务跟踪目标的名称;产品:虚拟主机名,域名,数据库名称,邮局名称等;服务器:ip地址 */
    @Index(name = "i_target")
    private String target;

    /**
     * 执行状态
     *
     * @see com.sjdf.platform.dictionary.bean.ExecuteStatus
     */
    @Index(name = "i_status")
    private long status;

    /** 执行过程信息 */
    private String executeProcess;

    /** 执行结果信息 */
    private String result;

    /** 备注 */
    private String remark;

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public long getAutoTaskType() {
        return autoTaskType;
    }

    public void setAutoTaskType(long autoTaskType) {
        this.autoTaskType = autoTaskType;
    }

    public long getProductClass() {
        return productClass;
    }

    public void setProductClass(long productClass) {
        this.productClass = productClass;
    }

    public String getMasterIpAddress() {
        return masterIpAddress;
    }

    public void setMasterIpAddress(String masterIpAddress) {
        this.masterIpAddress = masterIpAddress;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getExecuteProcess() {
        return executeProcess;
    }

    public void setExecuteProcess(String executeProcess) {
        this.executeProcess = executeProcess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    public String getAutoTaskTypeInfo() {
        return ConfigManager.getInstance().getName(AutoTaskType.class, autoTaskType);
    }

    public String getProductClassInfo() {
        return ConfigManager.getInstance().getName(ProductClass.class, productClass);
    }

    public String getStatusInfo() {
        return ConfigManager.getInstance().getName(ExecuteStatus.class, status);
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("tracker.id", String.valueOf(getId()));
        map.put("tracker.systemType", String.valueOf(systemType));
        map.put("tracker.autoTaskType", String.valueOf(autoTaskType));
        map.put("tracker.productClass", String.valueOf(productClass));
        map.put("tracker.masterIpAddress", masterIpAddress);
        map.put("tracker.target", target);
        map.put("tracker.status", String.valueOf(status));
        map.put("tracker.executeProcess", executeProcess);
        map.put("tracker.result", result);
        map.put("tracker.remark", remark);
        return map;
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<AutoTaskTracker>");
        builder.append("<id><![CDATA[").append(getId()).append("]]></id>");
        builder.append("<systemType><![CDATA[").append(systemType).append("]]></systemType>");
        builder.append("<autoTaskType><![CDATA[").append(autoTaskType).append("]]></autoTaskType>");
        builder.append("<productClass><![CDATA[").append(productClass).append("]]></productClass>");
        builder.append("<masterIpAddress><![CDATA[").append(masterIpAddress).append("]]></masterIpAddress>");
        builder.append("<target><![CDATA[").append(target).append("]]></target>");
        builder.append("<status><![CDATA[").append(status).append("]]></status>");
        builder.append("<executeProcess><![CDATA[").append(executeProcess).append("]]></executeProcess>");
        builder.append("<result><![CDATA[").append(result).append("]]></result>");
        builder.append("<remark><![CDATA[").append(remark).append("]]></remark>");
        builder.append("</AutoTaskTracker>");
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        sb.append("AutoTaskTracker");
        sb.append("{systemType=").append(systemType);
        sb.append(", autoTaskType=").append(autoTaskType);
        sb.append(", productClass=").append(productClass);
        sb.append(", masterIpAddress='").append(masterIpAddress).append('\'');
        sb.append(", target='").append(target).append('\'');
        sb.append(", status=").append(status);
        sb.append(", executeProcess='").append(executeProcess).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static String parse(List<AutoTaskTracker> trackerList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024 * trackerList.size());
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<AutoTaskTrackerList>");
        for (AutoTaskTracker tracker : trackerList) {
            xml.append(tracker.toXml());
        }
        xml.append("</AutoTaskTrackerList>");
        return xml.toString();
    }

    public static List<AutoTaskTracker> parse() {
        return new ArrayList<>(CommonPlatformConstant.LENGTH_1024);
    }
}
