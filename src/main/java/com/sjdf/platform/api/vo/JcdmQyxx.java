package com.sjdf.platform.api.vo;

public class JcdmQyxx {
    public static final long IS_PROVENCE = 1;
    public static final long IS_CITY = 2;
    public static final long IS_COUNTRY = 3;

    public static final int INT0 = 0;
    public static final int INT2 = 2;
    public static final int INT4 = 4;
    public static final String ZERO2 = "00";
    public static final String ZERO4 = "0000";

    // code
    private long qydmId;
    // 名称
    private String mc;
    // 备案系统省市县标识（1：省，2：市，3：县）
    // 1：代表省，自治区，直辖市和特别行政区
    // 2：代表地级市，自治州，省直辖行政单位和一些地区
    // 3：代表县，县级市和区
    private long lx;

    // 是否有效
    private long sfyx;

    public long getQydmId() {
        return qydmId;
    }

    public void setQydmId(long qydmId) {
        this.qydmId = qydmId;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public long getLx() {
        return lx;
    }

    public void setLx(long lx) {
        this.lx = lx;
    }

    public long getSfyx() {
        return sfyx;
    }

    public void setSfyx(long sfyx) {
        this.sfyx = sfyx;
    }
}
