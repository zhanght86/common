package com.sjdf.platform.autotask.bean;

import com.sjdf.platform.common.bean.BaseBean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Create at 2012-11-07
 * 自动任务服务器映射列表
 *
 * @author ketqi
 */
public class AutoTaskServer extends BaseBean {
    private static final long serialVersionUID = -7334351513156730067L;
    /** 远程服务器名称(人工填写) */
    private String name;
    /** 远程服务器所在ip地址(系统自动填充,同remotePort标识唯一服务器) */
    @Column(name = "remote_addr")
    private String remoteAddr;
    /** 远程服务器所在端口(系统自动填充,同remoteAddr标识唯一服务器) */
    @Column(name = "remote_port")
    private int remotePort;
    /** 远程服务器真实地址;ip地址+端口号或者域名(人工填写) */
    @Column(name = "real_addr")
    private String realAddr;
    /** 心跳检测失败次数;0:标识正常(系统自动填充) */
    private long heartbeat;
    /** 该服务器下所有自动任务 */
    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AutoTaskInfo> autoTaskList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getRealAddr() {
        return realAddr;
    }

    public void setRealAddr(String realAddr) {
        this.realAddr = realAddr;
    }

    public long getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(long heartbeat) {
        this.heartbeat = heartbeat;
    }

    public List<AutoTaskInfo> getAutoTaskList() {
        return autoTaskList;
    }

    public void setAutoTaskList(List<AutoTaskInfo> autoTaskList) {
        this.autoTaskList = autoTaskList;
    }

    /**
     * @param remoteAddr 远程服务器所在ip地址
     * @param remotePort 远程服务器所在端口
     * @return boolean
     * 验证是否存在
     */
    public boolean valid(String remoteAddr, int remotePort) {
        if (getRemoteAddr() != null && remoteAddr != null && !getRemoteAddr().equals(remoteAddr)) {
            return false;
        }

        if (remotePort > 0 && getRemotePort() != remotePort) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AutoTaskServer");
        sb.append("{heartbeat=").append(heartbeat);
        sb.append(", realAddr='").append(realAddr).append('\'');
        sb.append(", remotePort=").append(remotePort);
        sb.append(", remoteAddr='").append(remoteAddr).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
