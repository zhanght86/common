package com.sjdf.platform.location.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.vo.LocationVo;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Create at 2012-11-16 下午4:37:09
 * 地理位置信息辅助器
 *
 * @author KETQI
 */
public abstract class LocationHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LocationHelper.class);
    private static Map<String, LocationVo> provinceCacheMap;
    private static Map<String, LocationVo> cityCacheMap;
    private static Map<String, LocationVo> countyCacheMap;

    public static void init(String xmlData) {
        // 解析xml数据
        Map<String, Location> tempMap = LocationHelper.parse(xmlData);

        // 封装省份数据数据
        provinceCacheMap = LocationHelper.wrap(tempMap);

        // 封装城市数据
        cityCacheMap = new TreeMap<>();
        for (LocationVo vo : getProvinceList()) {
            cityCacheMap.putAll(vo.getChildMap());
        }

        // 封装区县数据
        countyCacheMap = new TreeMap<>();
        for (LocationVo vo : cityCacheMap.values()) {
            countyCacheMap.putAll(vo.getChildMap());
        }
    }

    /**
     * @return 获取所有省份
     */
    public static List<LocationVo> getProvinceList() {
        return new ArrayList<>(provinceCacheMap.values());
    }

    /**
     * @param provinceCode 省份代码
     * @return 获取指定省份下的所有城市
     */
    public static List<LocationVo> getCityList(String provinceCode) {
        if (provinceCode != null && !"".equals(provinceCode)) {
            LocationVo province = provinceCacheMap.get(provinceCode);
            if (province != null) {
                return province.getAllChild();
            }
        }
        return Collections.emptyList();
    }

    /**
     * @param cityCode 城市代码
     * @return 获取指定城市下的所有区县
     */
    public static List<LocationVo> getCountyList(String cityCode) {
        if (cityCode != null && !"".equals(cityCode)) {
            LocationVo city = cityCacheMap.get(cityCode);
            if (city != null) {
                return city.getAllChild();
            }
        }
        return Collections.emptyList();
    }

    /**
     * @param countyCode 区县代码
     * @return 获取指定的区县
     */
    public static LocationVo getCounty(String countyCode) {
        if (countyCode == null || "".equals(countyCode)) {
            return null;
        }
        return countyCacheMap.get(countyCode);
    }

    /**
     * @param code 代码
     * @return 根据指定代码获取数据
     */
    public static LocationVo getLocation(String code) {
        if (code == null || "".equals(code)) {
            return null;
        }

        LocationVo vo = provinceCacheMap.get(code);
        if (vo == null) {
            vo = cityCacheMap.get(code);
            if (vo == null) {
                vo = countyCacheMap.get(code);
            }
        }
        return vo;
    }

    /**
     * 添加地理位置信息
     *
     * @param map Map<code,Location>
     */
    public static void add(Map<String, Location> map) {
        for (Location location : map.values()) {
            if (location.getProvince() == null && location.getCity() == null) {
                // 省份
                LocationVo province = provinceCacheMap.get(location.getCode());
                if (province == null) {
                    provinceCacheMap.put(location.getCode(), new LocationVo(location));
                }
            } else if (location.getProvince() != null && location.getCity() == null) {
                // 城市
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    province.addChildren(location.getCode(), new LocationVo(location));
                    cityCacheMap.put(location.getCode(), new LocationVo(location));
                }
            } else {
                // 区县
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    // 城市代码
                    String cityCode = location.getCity().getCode();
                    LocationVo city = province.getChildren(cityCode);
                    if (city != null) {
                        city.addChildren(location.getCode(), new LocationVo(location));
                        countyCacheMap.put(location.getCode(), new LocationVo(location));
                    }
                }
            }
        }
    }

    /**
     * 删除地理位置信息
     *
     * @param map Map<code,LocationVo>
     */
    public static void delete(Map<String, Location> map) {
        for (Location location : map.values()) {
            // 省份
            if (location.getProvince() == null && location.getCity() == null) {
                LocationVo province = provinceCacheMap.get(location.getCode());
                if (province != null && province.getChildMap().isEmpty()) {
                    provinceCacheMap.remove(location.getCode());
                }
            } else if (location.getProvince() != null && location.getCity() == null) {
                // 城市
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    LocationVo city = province.getChildren(location.getCode());
                    if (city != null && city.getChildMap().isEmpty()) {
                        city.getChildMap().remove(location.getCode());
                        cityCacheMap.remove(location.getCode());
                    }
                }
            } else {
                // 区县
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    // 城市代码
                    String cityCode = location.getCity().getCode();
                    LocationVo city = province.getChildren(cityCode);
                    if (city != null) {
                        LocationVo county = city.getChildren(location.getCode());
                        if (county != null) {
                            city.getChildMap().remove(location.getCode());
                            countyCacheMap.remove(location.getCode());
                        }
                    }
                }
            }
        }
    }

    /**
     * 修改地理位置信息
     *
     * @param map Map<code,LocationVo>
     */
    public static void modify(Map<String, Location> map) {
        for (Location location : map.values()) {
            // 省份
            if (location.getProvince() == null && location.getCity() == null) {
                for (LocationVo vo : provinceCacheMap.values()) {
                    if (vo != null && vo.getId() == location.getId()) {
                        vo.reset(location);
                        break;
                    }
                }
            } else if (location.getProvince() != null && location.getCity() == null) {
                // 城市
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    for (LocationVo vo : province.getAllChild()) {
                        if (vo != null && vo.getId() == location.getId()) {
                            vo.reset(location);
                            break;
                        }
                    }
                }
            } else {
                // 区县
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = provinceCacheMap.get(provinceCode);
                if (province != null) {
                    // 城市代码
                    String cityCode = location.getCity().getCode();
                    LocationVo city = province.getChildren(cityCode);
                    if (city != null && !city.getChildMap().isEmpty()) {
                        for (LocationVo vo : city.getAllChild()) {
                            if (vo != null && vo.getId() == location.getId()) {
                                vo.reset(location);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param locationList 地理位置信息列表
     * @return 将对象转化为xml
     */
    public static String parse(List<Location> locationList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_10);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<locationList>");
        if (locationList != null && !locationList.isEmpty()) {
            for (Location location : locationList) {
                xml.append(location.toXML());
            }
        }
        xml.append("</locationList>");
        return xml.toString();
    }

    /**
     * @param xml xml
     * @return Map<code,Location>
     * 将xml转换为对象
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Location> parse(String xml) {
        Map<String, Location> map = new TreeMap<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/locationList");
            List<Element> xmlDictionaryList = root.elements();
            if (xmlDictionaryList != null && !xmlDictionaryList.isEmpty()) {
                for (Element element : xmlDictionaryList) {
                    // location node
                    List<Element> xmlDictionary = element.elements();
                    Location location = new Location();
                    for (Element e : xmlDictionary) {
                        if ("id".equals(e.getName())) {
                            location.setId(Long.parseLong(e.getData().toString()));
                        } else if ("code".equals(e.getName())) {
                            location.setCode(e.getData().toString());
                        } else if ("cnName".equals(e.getName())) {
                            location.setCnName(e.getData().toString());
                        } else if ("enName".equals(e.getName())) {
                            location.setEnName(e.getData().toString());
                        } else if ("postal".equals(e.getName())) {
                            location.setPostal(e.getData().toString());
                        } else if ("areaCode".equals(e.getName())) {
                            location.setAreaCode(e.getData().toString());
                        } else if ("abbreviation".equals(e.getName())) {
                            location.setAbbreviation(e.getData().toString());
                        } else if ("provinceCode".equals(e.getName()) && StringUtils.hasText(e.getData().toString())) {
                            Location province = new Location();
                            province.setCode(e.getData().toString());
                            location.setProvince(province);
                        } else if ("cityCode".equals(e.getName()) && StringUtils.hasText(e.getData().toString())) {
                            Location city = new Location();
                            city.setCode(e.getData().toString());
                            location.setCity(city);
                        }
                    }
                    map.put(location.getCode(), location);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return map;
    }

    /**
     * 封装数据
     *
     * @param tempMap 数据源,一般来源于parse(String)
     */
    public static Map<String, LocationVo> wrap(Map<String, Location> tempMap) {
        Map<String, LocationVo> map = new TreeMap<>();
        for (Location location : tempMap.values()) {
            // 省份
            if (location.getProvince() == null && location.getCity() == null) {
                LocationVo province = map.get(location.getCode());
                if (province == null) {
                    map.put(location.getCode(), new LocationVo(location));
                }
            } else if (location.getProvince() != null && location.getCity() == null) {
                // 城市
                // 如果在map中没有找到省份Vo,则先添加省份Vo再添加旗下的城市Vo
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = map.get(provinceCode);
                if (province == null) {
                    Location p = tempMap.get(provinceCode);
                    province = new LocationVo(p);
                    map.put(provinceCode, province);
                }
                province.addChildren(location.getCode(), new LocationVo(location));
            } else {
                // 区县
                // 如果在map中没有找到省份Vo,则先添加省份Vo再添加旗下的城市Vo
                // 省份代码
                String provinceCode = location.getProvince().getCode();
                LocationVo province = map.get(provinceCode);
                if (province == null) {
                    Location p = tempMap.get(provinceCode);
                    province = new LocationVo(p);
                    map.put(provinceCode, province);
                }

                // 如果省份下的城市map中没有找到该城市Vo,则先添加城市Vo再添加旗下的区县Vo
                // 城市代码
                String cityCode = location.getCity().getCode();
                LocationVo city = province.getChildren(cityCode);
                if (city == null) {
                    Location c = tempMap.get(cityCode);
                    city = new LocationVo(c);
                    province.addChildren(cityCode, city);
                }

                // 最后添加区县
                city.addChildren(location.getCode(), new LocationVo(location));
            }
        }
        return map;
    }

}
