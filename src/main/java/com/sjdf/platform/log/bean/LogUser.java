package com.sjdf.platform.log.bean;

import com.sjdf.platform.common.utils.RemoteIpAddressContext;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.annotations.Index;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 日志记录用户
 * Create at 2013年8月8日 下午5:21:56
 *
 * @author KETQI
 */
@Embeddable
public class LogUser implements Serializable {
    private static final long serialVersionUID = 8115867918663476287L;

    /** session中取值时候所用key */
    public static final String KEY = "logUser";
    /**
     * 系统类型;1:企业互联网服务系统;2:财务系统;3:备案系统;4:域名管理系统;等等
     *
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    @Index(name = "systemTypeIndex")
    private long systemType;
    /** 操作人(登录账号) */
    @Index(name = "loginUserIndex")
    private String loginUser;
    /** 操作人中文姓名（单位名称） */
    private String company;
    /** 操作人ip地址 */
    @Index(name = "ipAddressIndex")
    private String ipAddress;

    /**
     * 默认构造方法，从session中取得值
     */
    public LogUser() {
        // 测试用
        this.systemType = SystemType.EISS;
        this.loginUser = "测试登陆者";
        this.company = "测试公司";
        this.ipAddress = RemoteIpAddressContext.getInstance().get();
    }

    /**
     * @param systemType 系统类型
     * @param loginUser  操作人员
     * @param company    名称
     * @param ipAddress  ip地址
     */
    public LogUser(long systemType, String loginUser, String company, String ipAddress) {
        this.systemType = systemType;
        this.loginUser = loginUser;
        this.company = company;
        this.ipAddress = ipAddress;
    }

    public String getSystemTypeInfo() {
        return ConfigManager.getInstance().getName(SystemType.class, systemType);
    }

    /** 验证参数属性是否设值 */
    void validateAttribute() {
        if (systemType <= 0) {
            throw new IllegalArgumentException("the argument[systemType] must be set!!!");
        }
        if (loginUser == null || loginUser.isEmpty()) {
            throw new IllegalArgumentException("the argument[loginUser] must be set!!!");
        }
        if (company == null || company.isEmpty()) {
            throw new IllegalArgumentException("the argument[company] must be set!!!");
        }
        if (ipAddress == null || ipAddress.isEmpty()) {
            throw new IllegalArgumentException("the argument[ipAddress] must be set!!!");
        }
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogUser{");
        sb.append("systemType=").append(systemType);
        sb.append(", loginUser='").append(loginUser).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
