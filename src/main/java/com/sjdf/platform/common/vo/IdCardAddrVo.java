package com.sjdf.platform.common.vo;

/**
 * 身份证归属地VO
 *
 * @author sjdf
 */
public class IdCardAddrVo {

    private String idCard;

    private String att;

    private String born;

    private String sex;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("[");
        info.append("idCard = ").append(this.idCard).append(",");
        info.append("att = ").append(this.att).append(",");
        info.append("born = ").append(this.born).append(",");
        info.append("sex = ").append(this.sex);
        info.append("]");
        return info.toString();
    }

}
