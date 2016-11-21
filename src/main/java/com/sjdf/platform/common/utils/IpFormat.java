package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create at 2012-09-28
 * <p/>
 * 无敌IP地址格式解析工具，支持以下格式的IP地址
 * <p/>
 * 【1.2.3.4】单IP 【1.2.3.4,192.168.77.66,10.0.0.5】多IP，以逗号分割
 * 【1.2.3.0-255】IP地址范围，结束地址以单数字表示 【1.2.3.0-1.2.3.255】IP地址范围，结束地址以完整IP表示
 * 【1.2.3.255-0】IP地址范围，结束地址以单数字表示，但结束地址小于开始地址
 * 【1.2.3.255-1.2.3.0】IP地址范围，结束地址以完整IP表示，但结束地址小于开始地址
 *
 * @author Chen Mohan
 */
public abstract class IpFormat {

    public static List<String> ipFormat(String ipAddress) {
        List<String> retList = new ArrayList<>();
        if (PlatformUtils.hasText(ipAddress)) {
            String[] ipSplit = ipAddress.split(",");
            for (String ip : ipSplit) {
                String ipTrim = ip.trim();
                if (ipTrim.indexOf('-') != -1) {
                    String[] ipRange = ipTrim.split("-");
                    if (ipRange.length == CommonPlatformConstant.LENGTH_2) {
                        String ipStart = ipRange[0].trim();
                        String ipEnd = ipRange[1].trim();
                        if (isIpAddress(ipStart)) {
                            if (isIpAddress(ipEnd)) {
                                buildIpRange(retList, ipStart, ipEnd);
                            } else {
                                if (isNumeric(ipEnd) && ipEnd.length() <= CommonPlatformConstant.LENGTH_3 && Integer.valueOf(ipEnd) >= 0 && Integer.valueOf(ipEnd) <= CommonPlatformConstant.LENGTH_255) {
                                    ipEnd = ipStart.substring(0, ipStart.lastIndexOf(".")) + "." + ipEnd;
                                    buildIpRange(retList, ipStart, ipEnd);
                                }
                            }
                        }
                    }
                } else {
                    if (isIpAddress(ipTrim)) {
                        retList.add(ipTrim);
                    }
                }
            }
        }
        return retList;
    }

    private static boolean isIpAddress(String ipAddress) {
        String regEx = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(ipAddress);
        return m.matches();
    }

    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param ipaddress
     * @return
     * @category IP->long
     */
    public static long ip2long(String ipaddress) {
        long[] ip = new long[CommonPlatformConstant.LENGTH_4];
        int position1 = ipaddress.indexOf('.');
        int position2 = ipaddress.indexOf('.', position1 + 1);
        int position3 = ipaddress.indexOf('.', position2 + 1);
        ip[0] = Long.parseLong(ipaddress.substring(0, position1));
        ip[1] = Long.parseLong(ipaddress.substring(position1 + 1, position2));
        ip[CommonPlatformConstant.LENGTH_2] = Long.parseLong(ipaddress.substring(position2 + 1, position3));
        ip[CommonPlatformConstant.LENGTH_3] = Long.parseLong(ipaddress.substring(position3 + 1));
        return (ip[0] << CommonPlatformConstant.LENGTH_24) + (ip[1] << CommonPlatformConstant.LENGTH_16) + (ip[CommonPlatformConstant.LENGTH_2] << CommonPlatformConstant.LENGTH_8) + ip[CommonPlatformConstant.LENGTH_3];
    }

    /**
     * @param ipaddress
     * @return
     * @category long->IP
     */
    public static String long2ip(long ipaddress) {
        StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_32);
        sb.append(String.valueOf(ipaddress >>> CommonPlatformConstant.LENGTH_24));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> CommonPlatformConstant.LENGTH_16));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> CommonPlatformConstant.LENGTH_8));
        sb.append(".");
        sb.append(String.valueOf(ipaddress & 0x000000FF));
        return sb.toString();
    }

    private static void buildIpRange(List<String> retList, String ipStart, String ipEnd) {
        long ipStartNum = ip2long(ipStart);
        long ipEndNum = ip2long(ipEnd);
        if (ipEndNum > ipStartNum) {
            for (long i = ipStartNum; i <= ipEndNum; i++) {
                retList.add(long2ip(i));
            }
        } else {
            for (long i = ipStartNum; i >= ipEndNum; i--) {
                retList.add(long2ip(i));
            }
        }
    }
}