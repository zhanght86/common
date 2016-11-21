package com.sjdf.platform.common.verify;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.vo.IdCardAddrVo;
import com.sjdf.platform.common.vo.IpAddrVo;
import com.sjdf.platform.common.vo.MobileAddrVo;
import com.sjdf.platform.dictionary.bean.EncodeType;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 移动电话工具类
 *
 * @author frank
 */
public abstract class MobileVerify {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(MobileVerify.class);

    /**
     * 通过手机号码获取归属地信息
     *
     * @param mobileNum 手机号码
     * @return MobileAddrVo 手机号码归属地对象
     */
    public static MobileAddrVo getMobileVoByMobileNum(String mobileNum) {
        MobileAddrVo addrVo = null;
        try {
            String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.QUERY_PHONE_NUM_PLACE_URL) + mobileNum;
            HttpSocket httpSocket = new HttpSocket();
            httpSocket.setReceiveCharsetName(ConfigManager.getInstance()
                    .getValue(EncodeType.class, EncodeType.UTF8));
            httpSocket.setUrl(url);
            httpSocket.doGet();
            String jsonResult = httpSocket.getResponseData();
            JSONObject object = JSONObject.fromObject(jsonResult);// 转化为JSON类
            long code = object.getLong("error_code"); // 得到错误码
            long resultcode = object.getLong("resultcode"); // 得到返回码
            String reason = object.getString("reason"); // 得到返回原因
            // 错误码判断
            if (code == 0 && resultcode == CommonPlatformConstant.LENGTH_200) {
                addrVo = new MobileAddrVo();
                // 根据需要取得数据
                JSONObject jsonObject = object.getJSONObject("result");
                addrVo.setAreaCode(jsonObject.getString("areacode"));
                addrVo.setCard(jsonObject.getString("card"));
                addrVo.setCity(jsonObject.getString("city"));
                addrVo.setCorp(jsonObject.getString("company"));
                addrVo.setPostCode(jsonObject.getString("zip"));
                addrVo.setProvince(jsonObject.getString("province"));
            } else {
                String error = "error_code:" + code + ",resultcode:" + resultcode + ",reason:" + reason;
                throw new Exception(error);
            }
        } catch (Exception e) {
            logger.error("通过手机号码获取归属地信息:" + mobileNum, e);
        }

        return addrVo;
    }

    /**
     * 根据手机号码获取手机号码的归属地省份名称
     *
     * @param mobileNum 手机号码
     */
    public static String getProNameByMobileByApi(String mobileNum) {
        MobileAddrVo vo = getMobileVoByMobileNum(mobileNum);
        if (vo != null) {
            return vo.getProvince();
        } else {
            return null;
        }
    }

    /**
     * 根据手机号码获取手机号码的归属地城市名称
     *
     * @param mobileNum 手机号码
     */
    public static String getCityNameByMobileNum(String mobileNum) {
        MobileAddrVo vo = getMobileVoByMobileNum(mobileNum);
        if (vo != null) {
            return vo.getCity();
        } else {
            return null;
        }
    }

    /**
     * 根据IP地址获取地址等相关信息
     *
     * @param ip ip地址
     * @throws Exception
     */
    public static IpAddrVo getIpAddrVoByIp(String ip) throws Exception {
        IpAddrVo vo = null;
        try {
            String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.QUERY_IP_PLACE_URL) + ip;
            HttpSocket httpSocket = new HttpSocket();
            httpSocket.setUrl(url);
            httpSocket.setReceiveCharsetName(ConfigManager.getInstance().getValue(EncodeType.class, EncodeType.GBK));
            httpSocket.doGet();
            String reulstData = httpSocket.getResponseData();

            // 解析数据
            JSONObject json = JSONObject.fromObject(reulstData);
            if (json != null) {
                long code = json.getLong("code");
                if (code == 0) {
                    String data = json.getString("data");
                    if (StringUtils.hasText(data)) {
                        JSONObject jsonData = JSONObject.fromObject(data);
                        if (jsonData != null) {
                            vo = new IpAddrVo();
                            vo.setCountryId(jsonData.getString("country_id"));
                            vo.setCountry(jsonData.getString("country"));
                            if (StringUtils.hasText(jsonData.getString("area_id"))) {
                                vo.setAreaId(jsonData.getLong("area_id"));
                            }
                            vo.setArea(jsonData.getString("area"));
                            if (StringUtils.hasText(jsonData.getString("region_id"))) {
                                vo.setProvinceId(jsonData.getLong("region_id"));
                            }
                            vo.setProvince(jsonData.getString("region"));
                            if (StringUtils.hasText(jsonData.getString("city_id"))) {
                                vo.setCityId(jsonData.getLong("city_id"));
                            }
                            vo.setCity(jsonData.getString("city"));
                            if (StringUtils.hasText(jsonData.getString("county_id"))) {
                                vo.setCountyId(jsonData.getLong("county_id"));
                            }
                            vo.setCounty(jsonData.getString("county"));
                            if (StringUtils.hasText(jsonData.getString("isp_id"))) {
                                vo.setIspId(jsonData.getLong("isp_id"));
                            }
                            vo.setIsp(jsonData.getString("isp"));
                            vo.setIp(jsonData.getString("ip"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("根据IP地址获取地址等相关信息", e);
        }

        return vo;
    }

    /**
     * 根据身份证号码获取相关信息
     */
    public static IdCardAddrVo getIdCardAddrVoByCard(String cardNum) {
        IdCardAddrVo vo = null;
        try {
            String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.QUERY_ID_CARD_NUM_PLACE_URL) + cardNum;
            HttpSocket httpSocket = new HttpSocket();
            httpSocket.setUrl(url);
            httpSocket.doGet();
            String reulstData = httpSocket.getResponseData();

            // 解析数据
            JSONObject json = JSONObject.fromObject(reulstData);
            if (json != null) {
                long code = json.getLong("success");
                if (code == 1) {
                    String result = json.getString("result");
                    if (StringUtils.hasText(result)) {
                        JSONObject jsonResult = JSONObject.fromObject(result);
                        if (jsonResult != null) {
                            vo = new IdCardAddrVo();
                            vo.setIdCard(jsonResult.getString("idcard"));
                            vo.setAtt(jsonResult.getString("att"));
                            vo.setBorn(jsonResult.getString("born"));
                            vo.setSex(jsonResult.getString("sex"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("根据IP地址获取地址等相关信息", e);
        }

        return vo;
    }
}
