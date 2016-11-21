package com.sjdf.platform.location.vo;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.location.bean.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Create at 2012-11-19 上午9:31:01
 * 省，市，县（区）的相关信息(代码,邮编,区号)值对象
 *
 * @author KETQI
 */
public class LocationVo implements Comparable<LocationVo>{
    private long id;
    /** 代码 */
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
    /** 下级节点 */
    private Map<String, LocationVo> childMap = new TreeMap<>();

    public LocationVo() {
    }

    public LocationVo(long id, String code, String cnName, String enName, String postal, String areaCode) {
        this.id = id;
        this.code = code;
        this.cnName = cnName;
        this.enName = enName;
        this.postal = postal;
        this.areaCode = areaCode;
    }

    public LocationVo(Location location) {
        this(location.getId(), location.getCode(), location.getCnName(), location.getEnName(), location.getPostal(), location.getAreaCode());
        this.abbreviation = location.getAbbreviation();
    }

    public void reset(Location location) {
        this.code = location.getCode();
        this.cnName = location.getCnName();
        this.enName = location.getEnName();
        this.postal = location.getPostal();
        this.areaCode = location.getAreaCode();
        this.abbreviation = location.getAbbreviation();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Map<String, LocationVo> getChildMap() {
        return childMap;
    }

    public void setChildMap(Map<String, LocationVo> childMap) {
        this.childMap = childMap;
    }

    public LocationVo getChildren(String code) {
        return childMap.get(code);
    }

    public void addChildren(String code, LocationVo vo) {
        childMap.put(code, vo);
    }

    public List<LocationVo> getAllChild() {
        return new ArrayList<>(childMap.values());
    }

    @Override
    public int hashCode() {
        final int prime = CommonPlatformConstant.LENGTH_31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        LocationVo other = (LocationVo) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    public String toJson() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        builder.append("{");
        builder.append("id:").append(id).append(",");
        builder.append("code:").append(code).append(",");
        builder.append("cnName:").append(cnName).append(",");
        builder.append("enName:").append(enName).append(",");
        builder.append("postal:").append(postal).append(",");
        builder.append("areaCode:").append(areaCode).append(",");
        builder.append("abbreviation:").append(abbreviation);
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        builder.append("LocationVo [id=");
        builder.append(id);
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
        builder.append("]");
        return builder.toString();
    }

    /**
     * 默认根据英文名称排序
     */
    @Override
    public int compareTo(LocationVo o) {
        if (o == null || !PlatformUtils.hasText(this.getEnName()) || !PlatformUtils.hasText(o.getEnName())) {
            return 0;
        }
        String otherEnName = o.getEnName().trim().toLowerCase();
        String thisEnName = this.getEnName().trim().toLowerCase();
        return thisEnName.compareTo(otherEnName);
    }
}
