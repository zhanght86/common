package com.sjdf.platform.common.vo;

/**
 * 手机号码归属地VO
 *
 * @author sjdf
 */
public class MobileAddrVo {

    /** 手机号码 */
    private String tel;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 区号 */
    private String areaCode;
    /** 邮政编码 */
    private String postCode;
    /** 公司 */
    private String corp;
    /** 卡类型 */
    private String card;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

}
