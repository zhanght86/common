/**
 *
 */
package com.sjdf.platform.common.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP辅助类
 * 2013-1-6 下午5:31:25
 *
 * @author ketqi
 */
public abstract class IPHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(IPHelper.class);

    /**
     * 判断一个ip地址是不是ipv4
     *
     * @param ipAddress ip地址
     * @return 指定的字符串是否为ipv4地址
     */
    public static boolean isIPV4(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    /**
     * 检测IPV4地址格式是否正确
     *
     * @param ipv4Address ip地址
     * @return bool
     */
    public static boolean checkIPV4(String ipv4Address) {
        String ip = ipv4Address;
        try {
            String number = ip.substring(0, ip.indexOf('.'));
            if (Integer.parseInt(number) > CommonPlatformConstant.LENGTH_255) {
                return false;
            }
            ip = ip.substring(ip.indexOf('.') + 1);
            number = ip.substring(0, ip.indexOf('.'));
            if (Integer.parseInt(number) > CommonPlatformConstant.LENGTH_255) {
                return false;
            }
            ip = ip.substring(ip.indexOf('.') + 1);
            number = ip.substring(0, ip.indexOf('.'));
            if (Integer.parseInt(number) > CommonPlatformConstant.LENGTH_255) {
                return false;
            }
            number = ip.substring(ip.indexOf('.') + 1);
            return Integer.parseInt(number) <= CommonPlatformConstant.LENGTH_255;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据ip获取该ip所在省份
     *
     * @param ip IP地址
     * @return 省份
     */
    public static String getProvinceByIp(String ip) {
        return IpSearchManager.getInstance().getProvinceByIp(ip);
    }

    /**
     * 根据ip获取该ip所在国家
     *
     * @param ip IP地址
     * @return 国家
     */
    public static String getStateByIp(String ip) {
        return IpSearchManager.getInstance().getStateByIp(ip);
    }

}
