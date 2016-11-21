package com.sjdf.platform.associate.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Create at 2012-08-07
 * 关联信息
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_associate")
public class Associate extends BaseBean {
    private static final long serialVersionUID = 2032430060839610192L;
    /**
     * 当前系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long currentSystemType;
    /** 当前用户编号 */
    private String currentUser;
    /**
     * 关联系统类型
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    private long associateSystemType;
    /** 关联用户编号 */
    private String associateUser;
    /** 关联用户密码 */
    private String associatePwd;
    /**
     * 有效状态
     *
     * @see com.sjdf.platform.dictionary.bean.ValidMark
     */
    private long valid;
    /** 取消关联时间 */
    private Date cancelTime;
    /** 失效检测时间 */
    private Date invalidTime;
    /** 操作人IP */
    private String ipAddress;

    /** 备注 */
    @Transient
    private transient String remark;

    @ModifyRecord(name = "当前系统类型")
    public String getCurrentSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, currentSystemType);
    }

    @ModifyRecord(name = "关联系统类型")
    public String getAssociateSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, associateSystemType);
    }

    @ModifyRecord(name = "有效状态")
    public String getValidInfo() {
        return ConfigManager.getInstance().getName(ValidMark.class, valid);
    }

    @ModifyRecord(name = "取消关联时间")
    public String getCancelTimeInfo() {
        return DateUtils.formatDateTime(cancelTime);
    }

    @ModifyRecord(name = "失效检测时间")
    public String getInvalidTimeInfo() {
        return DateUtils.formatDateTime(invalidTime);
    }

    public long getCurrentSystemType() {
        return currentSystemType;
    }

    public void setCurrentSystemType(long currentSystemType) {
        this.currentSystemType = currentSystemType;
    }

    @ModifyRecord(name = "当前用户编号")
    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public long getAssociateSystemType() {
        return associateSystemType;
    }

    public void setAssociateSystemType(long associateSystemType) {
        this.associateSystemType = associateSystemType;
    }

    @ModifyRecord(name = "关联用户编号")
    public String getAssociateUser() {
        return associateUser;
    }

    public void setAssociateUser(String associateUser) {
        this.associateUser = associateUser;
    }

    public String getAssociatePwd() {
        return associatePwd;
    }

    public void setAssociatePwd(String associatePwd) {
        this.associatePwd = associatePwd;
    }

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_500);
        builder.append("<associate>");
        builder.append("<id><![CDATA[").append(getId()).append("]]></id>");
        builder.append("<currentSystemType><![CDATA[").append(currentSystemType).append("]]></currentSystemType>");
        builder.append("<currentUser><![CDATA[").append(currentUser).append("]]></currentUser>");
        builder.append("<associateSystemType><![CDATA[").append(associateSystemType).append("]]></associateSystemType>");
        builder.append("<associateUser><![CDATA[").append(associateUser).append("]]></associateUser>");
        builder.append("<valid><![CDATA[").append(valid).append("]]></valid>");
        builder.append("<remark><![CDATA[").append(remark).append("]]></remark>");
        builder.append("</associate>");
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        sb.append("Associate{");
        sb.append("currentSystemType=").append(currentSystemType);
        sb.append(", currentUser='").append(currentUser).append('\'');
        sb.append(", associateSystemType=").append(associateSystemType);
        sb.append(", associateUser='").append(associateUser).append('\'');
        sb.append(", associatePwd='").append(associatePwd).append('\'');
        sb.append(", valid=").append(valid);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
