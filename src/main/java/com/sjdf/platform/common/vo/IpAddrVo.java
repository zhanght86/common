package com.sjdf.platform.common.vo;

/**
 * ip归属地VO对象
 *
 * @author sjdf
 */
public class IpAddrVo {

    /** 国家编码 */
    private String countryId;
    /** 国家 */
    private String country;
    /** 区域编码 */
    private long areaId;
    /** 区域 */
    private String area;
    /** 省编码 */
    private long provinceId;
    /** 省 */
    private String province;
    /** 城市编码 */
    private long cityId;
    /** 城市 */
    private String city;
    /** 县编码 */
    private long countyId;
    /** 县 */
    private String county;
    /** 网络服务提供者编码 */
    private long ispId;
    /** 网络服务提供者 */
    private String isp;
    /** ipxx */
    private String ip;
    /**
     * 我司线路
     *
     * @see com.sjdf.platform.dictionary.bean.eiss.idc.NetworkLine
     */
    private long networkLine;


    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("[");
        info.append("countryId = ").append(this.countryId).append(",");
        info.append("country = ").append(this.country).append(",");
        info.append("areaId = ").append(this.areaId).append(",");
        info.append("area = ").append(this.area).append(",");
        info.append("provinceId = ").append(this.provinceId).append(",");
        info.append("province = ").append(this.province).append(",");
        info.append("cityId = ").append(this.cityId).append(",");
        info.append("city = ").append(this.city).append(",");
        info.append("countyId = ").append(this.countyId).append(",");
        info.append("county = ").append(this.county).append(",");
        info.append("ispId = ").append(this.ispId).append(",");
        info.append("isp = ").append(this.isp).append(",");
        info.append("ip = ").append(this.ip).append(",");
        info.append("networkLine = ").append(this.networkLine);
        info.append("]");

        return info.toString();
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getCountyId() {
        return countyId;
    }

    public void setCountyId(long countyId) {
        this.countyId = countyId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public long getIspId() {
        return ispId;
    }

    public void setIspId(long ispId) {
        this.ispId = ispId;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getNetworkLine() {
        return networkLine;
    }

    public void setNetworkLine(long networkLine) {
        this.networkLine = networkLine;
    }

    public boolean isInNetworkLine() {
        return networkLine != 0L;
    }
}
