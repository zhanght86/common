package com.sjdf.platform.location.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.Message;
import org.hibernate.annotations.Index;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create at 2012-11-15 下午2:07:18
 * 省，市，县（区）的相关信息(代码,邮编,区号)
 *
 * @author KETQI
 */
@Entity
@Table(name = "p_location")
public class Location extends BaseBean {
    private static final long serialVersionUID = -9057128118930085259L;

    /** 代码 */
    @Index(name = "index_code")
    private String code;

    /** 中文名称 */
    private String cnName;

    /** 英文名称 */
    private String enName;

    /** 邮编 */
    private String postal;

    /** 区号 */
    private String areaCode;

    /** 简称 */
    private String abbreviation;

    /** 所属省份 */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provinceId")
    private Location province;

    /** 所属城市 */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cityId")
    private Location city;


    /** 省份下所有城市 */
    @OneToMany(mappedBy = "province")
    private List<Location> cityList = new ArrayList<>();

    /** 城市下所有区县 */
    @OneToMany(mappedBy = "city")
    private List<Location> countyList = new ArrayList<>();

    public Location() {
    }

    public Location(String code) {
        this.code = code;
    }

    @ModifyRecord(name = "所属省份")
    public String getProvinceInfo() {
        return province != null ? province.getCnName() : "";
    }

    @ModifyRecord(name = "所属城市")
    public String getCityInfo() {
        return city != null ? city.getCnName() : "";
    }

    @ModifyRecord(name = "代码")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ModifyRecord(name = "中文名称")
    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    @ModifyRecord(name = "英文名称")
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @ModifyRecord(name = "邮编")
    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @ModifyRecord(name = "区号")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @ModifyRecord(name = "简称")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Location getProvince() {
        return province;
    }

    public void setProvince(Location province) {
        this.province = province;
    }

    public Location getCity() {
        return city;
    }

    public void setCity(Location city) {
        this.city = city;
    }

    public List<Location> getCityList() {
        return cityList;
    }

    public void setCityList(List<Location> cityList) {
        this.cityList = cityList;
    }

    public List<Location> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<Location> countyList) {
        this.countyList = countyList;
    }


    public String getUrl(String action) {
        StringBuilder builder = new StringBuilder();
        builder.append(action);
        builder.append("location.province.code=");
        if (province != null) {
            builder.append(province.getCode());
        }
        builder.append("&location.city.code=");
        if (city != null) {
            builder.append(city.getCode());
        }
        return builder.toString();
    }

    public static Message validate(Location location) {
        if (location == null) {
            return Message.createMessage("location.null");
        }
        if (!StringUtils.hasText(location.getAreaCode())) {
            return Message.createMessage("location.areaCode.null");
        }
        if (!StringUtils.hasText(location.getCode())) {
            return Message.createMessage("location.code.null");
        }
        if (!StringUtils.hasText(location.getCnName())) {
            return Message.createMessage("location.cn.name.null");
        }
        if (!StringUtils.hasText(location.getEnName())) {
            return Message.createMessage("location.en.name.null");
        }
        if (!StringUtils.hasText(location.getPostal())) {
            return Message.createMessage("location.postal.null");
        }
        return Message.createEmptyMessage();
    }

    public String toXML() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        xml.append("<location>");
        xml.append("<id><![CDATA[").append(getId()).append("]]></id>");
        xml.append("<code><![CDATA[").append(code).append("]]></code>");
        xml.append("<cnName><![CDATA[").append(cnName).append("]]></cnName>");
        xml.append("<enName><![CDATA[").append(enName).append("]]></enName>");
        xml.append("<postal><![CDATA[").append(postal).append("]]></postal>");
        xml.append("<areaCode><![CDATA[").append(areaCode).append("]]></areaCode>");
        xml.append("<abbreviation><![CDATA[").append(abbreviation).append("]]></abbreviation>");

        xml.append("<provinceCode><![CDATA[");
        if (province != null) {
            xml.append(province.getCode());
        }
        xml.append("]]></provinceCode>");

        xml.append("<cityCode><![CDATA[");
        if (city != null) {
            xml.append(city.getCode());
        }
        xml.append("]]></cityCode>");

        xml.append("</location>");
        return xml.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_200);
        builder.append("Location [id=");
        builder.append(getId());
        builder.append(", code=");
        builder.append(code);
        builder.append(", cnName=");
        builder.append(cnName);
        builder.append(", enName=");
        builder.append(enName);
        builder.append(", postal=");
        builder.append(postal);
        builder.append(", areaCode=");
        builder.append(areaCode);
        builder.append(", abbreviation=");
        builder.append(abbreviation);
        if (province != null) {
            builder.append(", province=");
            builder.append(province.getId());
        }
        if (city != null) {
            builder.append(", city=");
            builder.append(city);
        }
        builder.append("]");
        return builder.toString();
    }
}
