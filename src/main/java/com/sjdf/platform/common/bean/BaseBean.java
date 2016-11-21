package com.sjdf.platform.common.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.annotations.Json;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Create at 2012-04-05
 * Bean的基类
 *
 * @author 王正伟
 */
@MappedSuperclass
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 5730161330404835746L;
    @Transient
    protected transient final SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    /** 更新人 */
    private String updateUser;

    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    /**
     * 获取更新记录
     *
     * @param bean 新数据
     * @return 更新记录
     */
    public String wrapUpdateContent(BaseBean bean, boolean isFouce) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_256);
        BeanName beanName = getClass().getAnnotation(BeanName.class);
        if (beanName != null) {
            builder.append(beanName.name()).append(":");
        }

        Method[] methods = getClass().getMethods();
        try {
            for (Method method : methods) {
                //忽略带有参数方法
                if (method.isVarArgs()) {
                    continue;
                }

                //忽略没有注解的方法
                ModifyRecord meta = method.getAnnotation(ModifyRecord.class);
                if (meta == null) {
                    continue;
                }

                method.setAccessible(true);

                Object oldObj = method.invoke(this);
                String oldValue = oldObj == null ? "" : oldObj.toString();
                if (bean == null) {
                    if (isFouce || PlatformUtils.hasText(oldValue)) {
                        builder.append(meta.name()).append(":").append(oldValue).append(";");
                    }
                } else {
                    Object newObj = method.invoke(bean);
                    String newValue = newObj == null ? "" : newObj.toString();
                    if (!oldValue.equals(newValue) && !("0".equals(newValue) || "0.00".equals(newValue) || "0.0".equals(newValue)) && (isFouce || PlatformUtils.hasText(newValue)) && (isFouce || PlatformUtils.hasText(oldValue))) {
                        builder.append(meta.name()).append(":").append(oldValue).append("-->").append(newValue).append(";");
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return PlatformUtils.getStackTrace(e);
        }

        int size = builder.length();
        if (size > 1) {
            builder.deleteCharAt(size - 1);
        }
        return builder.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Json(format = DateUtils.DATETIME_FORMAT)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Json(format = DateUtils.DATETIME_FORMAT)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Json(excluded = true)
    public String getCreateTimeInfo() {
        return createTime != null ? DateUtils.formatDateTime(createTime) : "";
    }

    @Json(excluded = true)
    public String getUpdateTimeInfo() {
        return updateTime != null ? DateUtils.formatDateTime(updateTime) : "";
    }

    @Override
    public int hashCode() {
        final int prime = CommonPlatformConstant.LENGTH_31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> CommonPlatformConstant.LENGTH_32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseBean other = (BaseBean) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "BaseBean [id=" + id + "]";
    }
}
