package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * IP辅助工具
 * User: ketqi
 * Date: 2016-09-06 09:54
 */
public abstract class IPUtils {
    /**
     * 将标准格式IP转换为数字
     *
     * @param ip IP地址
     * @return ip地址的数字表示
     */
    public static long ip2num(String ip) {
        if (!PlatformUtils.isIpAddress(ip)) {
            throw new java.lang.IllegalArgumentException("Wrong IP format");
        }
        long ipNum = 0;
        String ips[] = ip.split("[.]");
        for (int i = 0; i < ips.length; i++) {
            int k = Integer.parseInt(ips[i]);
            ipNum = ipNum + k * (1L << ((CommonPlatformConstant.LENGTH_3 - i) * CommonPlatformConstant.LENGTH_8));
        }

        return ipNum;
    }

    /**
     * 将数字格式IP转换为标志格式字符串
     *
     * @param longIp ip地址的数字
     * @return ip地址
     */
    public static String num2ip(long longIp) {
        StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_15);
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> CommonPlatformConstant.LENGTH_24)));
        sb.append('.');
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> CommonPlatformConstant.LENGTH_16));
        sb.append('.');
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> CommonPlatformConstant.LENGTH_8));
        sb.append('.');
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    /**
     * 将标准格式子网掩码转换为数字
     *
     * @param netMask 掩码
     */
    public static int netMask2num(String netMask) {
        if (!PlatformUtils.isIpAddress(netMask)) {
            throw new java.lang.IllegalArgumentException("Wrong netmask format");
        }
        String ips[] = netMask.split("\\.");
        String binMask = "";
        for (String ip : ips) {
            binMask += Integer.toBinaryString(Integer.parseInt(ip));
        }
        return binMask.lastIndexOf("1") + 1;
    }

    /**
     * 将数字格式IP转换为标志格式字符串
     *
     * @param netMask 掩码数字表示
     * @return 掩码
     */
    public static String num2NetMask(int netMask) {
        int mask = -1 << (CommonPlatformConstant.LENGTH_32 - netMask);
        int partsNum = CommonPlatformConstant.LENGTH_4;
        int bitsOfPart = CommonPlatformConstant.LENGTH_8;
        int maskParts[] = new int[partsNum];
        int selector = 0x000000ff;

        for (int i = 0; i < maskParts.length; i++) {
            int pos = maskParts.length - 1 - i;
            maskParts[pos] = (mask >> (i * bitsOfPart)) & selector;
        }

        StringBuilder result = new StringBuilder(CommonPlatformConstant.LENGTH_15);
        result.append(maskParts[0]);
        for (int i = 1; i < maskParts.length; i++) {
            result.append(".").append(maskParts[i]);
        }
        return result.toString();
    }

    /**
     * Gets the subnet address.
     *
     * @param ip      the ip
     * @param netMask the net mask
     * @return the subnet address
     */
    public static String getSubnetAddress(String ip, String netMask) {
        byte[] ipRaw = IPUtils.toIpAddrBytes(ip);
        byte[] maskRaw = IPUtils.toIpAddrBytes(netMask);
        int unsignedByteFilter = 0x000000ff;
        int[] resultRaw = new int[ipRaw.length];
        for (int i = 0; i < resultRaw.length; i++) {
            resultRaw[i] = (ipRaw[i] & maskRaw[i] & unsignedByteFilter);
        }

        // make result string
        StringBuilder result = new StringBuilder(CommonPlatformConstant.LENGTH_15);
        result.append(resultRaw[0]);
        for (int i = 1; i < resultRaw.length; i++) {
            result.append(".").append(resultRaw[i]);
        }
        result.append("/").append(IPUtils.netMask2num(netMask));
        return result.toString();
    }

    /**
     * 根据子网地址和子网掩码计算可用IP地址.
     *
     * @param ipAddress  子网任意IP地址
     * @param subnetMask 子网掩码
     * @return ip地址列表
     */
    public static List<String> getSubnetIpList(String ipAddress, String subnetMask) {
        return getSubnetIpList(ipAddress, netMask2num(subnetMask));
    }

    /**
     * 获取ip地址列表
     *
     * @param ipAddress ip地址
     * @param netmask   掩码类型
     * @return ip地址列表
     */
    public static List<String> getSubnetIpList(String ipAddress, int netmask) {
        int totalNum = getSubnetIpSize(netmask);
        long numIp = ip2num(ipAddress);
        List<String> ips = new ArrayList<>(totalNum);
        for (int i = 0; i < totalNum; i++) {
            ips.add(num2ip(numIp + i));
        }
        return ips;
    }

    /**
     * 获取ip地址列表
     *
     * @param ipAddress ip地址/掩码字符串,如:10.229.0.0/27
     * @return ip地址列表
     */
    public static List<String> getSubnetIpList(String ipAddress) {
        if (!PlatformUtils.hasText(ipAddress)) {
            return Collections.emptyList();
        }
        String[] chunks = ipAddress.split("/");
        if (chunks.length != CommonPlatformConstant.LENGTH_2) {
            return Collections.emptyList();
        }
        String ip = chunks[0];
        if (!PlatformUtils.isIpAddress(ip)) {
            return Collections.emptyList();
        }

        int netmask;
        try {
            netmask = Integer.parseInt(chunks[1]);
        } catch (Exception e) {
            return Collections.emptyList();
        }

        return getSubnetIpList(ip, netmask);
    }

    /**
     * 判断该IP是否是网络关和广播地址
     *
     * @param ipAddress ip地址
     * @return bool
     */
    public static boolean isNetworkNumber(String ipAddress) {
        int last = Integer.parseInt(ipAddress.substring(ipAddress.lastIndexOf('.') + 1));
        return last == 0 || last == CommonPlatformConstant.LENGTH_255;
    }

    /**
     * 获得子网有效IP地址数量.
     * 去掉网络号、广播地址
     *
     * @param subnetMask 子网掩码
     * @return ip数量
     */
    public static int getSubnetIpSize(String subnetMask) {
        return getSubnetIpSize(netMask2num(subnetMask));
    }

    /**
     * 获取ip地址个数
     *
     * @param netmask 掩码类型
     * @return ip地址数量
     */
    public static int getSubnetIpSize(int netmask) {
        return (1 << (CommonPlatformConstant.LENGTH_32 - netmask));
    }

    /**
     * 获取指定字符串的子串
     *
     * @param str   字符串
     * @param start 起始索引
     * @param end   结束索引
     * @return 字符串
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 缩写的MAC地址(无冒号)转换为全地址.
     *
     * @param mac MAC地址
     * @return MAC全地址
     */
    public static String mac2Full(String mac) {
        int count = 2;
        int length = mac.length();
        int n = length / count;
        int point = (length % count) > 0 ? 1 : 0;    //小数位
        int total = n + point;
        String[] ret = new String[total];
        for (int i = 0; i < total; i++) {
            ret[i] = substring(mac, i * count, (i + 1) * count);
        }
        StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_20);
        for (String string : ret) {
            sb.append(string).append(":");
        }
        return sb.toString();
    }

    /**
     * 检查IP是否在指定的IP段中.
     *
     * @param startIp 起始IP
     * @param endIp   结束IP
     * @param ip      IP地址
     * @return true, if checks is between
     */
    public static boolean isBetween(String startIp, String endIp, String ip) {
        if (!PlatformUtils.isIpAddress(startIp)) {
            throw new java.lang.IllegalArgumentException("Wrong begin IP format");
        }
        if (!PlatformUtils.isIpAddress(endIp)) {
            throw new java.lang.IllegalArgumentException("Wrong end IP format");
        }
        if (!PlatformUtils.isIpAddress(ip)) {
            throw new java.lang.IllegalArgumentException("Wrong IP format");
        }

        long startIpNum = ip2num(startIp);
        long endIpNum = ip2num(endIp);
        long ipNum = ip2num(ip);

        return ipNum >= startIpNum && ipNum <= endIpNum;
    }

    /**
     * 检查IP是否在指定的IP段中.
     *
     * @param domainIp 待*的ip地址
     * @param ip       IP地址
     * @return true, if checks is between
     */
    public static boolean isInDomain(String domainIp, String ip) {
        String startIp = domainIp.replaceAll("[*]", "0");
        String endIp = domainIp.replaceAll("[*]", "255");

        if (startIp.length() > 0 && startIp.charAt(0) == '0') {    //IP不能以0开头
            startIp = startIp.replaceFirst("0", "1");
        }

        if (!PlatformUtils.isIpAddress(startIp)) {
            throw new java.lang.IllegalArgumentException("Wrong begin IP format");
        }
        if (!PlatformUtils.isIpAddress(endIp)) {
            throw new java.lang.IllegalArgumentException("Wrong end IP format");
        }
        if (!PlatformUtils.isIpAddress(ip)) {
            throw new java.lang.IllegalArgumentException("Wrong IP format");
        }

        long startIpNum = ip2num(startIp);
        long endIpNum = ip2num(endIp);
        long ipNum = ip2num(ip);

        return ipNum >= startIpNum && ipNum <= endIpNum;
    }

    /**
     * <p>getInetAddress</p>
     *
     * @param ipAddrOctets an array of byte.
     * @return a {@link java.net.InetAddress} object.
     */
    public static InetAddress getInetAddress(byte[] ipAddrOctets) {
        try {
            return InetAddress.getByAddress(ipAddrOctets);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IPAddress " + Arrays.toString(ipAddrOctets) + " with length " + ipAddrOctets.length);
        }
    }

    /**
     * <p>getInetAddress</p>
     *
     * @param dottedNotation a {@link java.lang.String} object.
     * @return a {@link java.net.InetAddress} object.
     */
    public static InetAddress getInetAddress(String dottedNotation) {
        try {
            return InetAddress.getByName(dottedNotation);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IPAddress " + dottedNotation);
        }
    }

    /**
     * <p>toIpAddrBytes</p>
     *
     * @param dottedNotation a {@link java.lang.String} object.
     * @return an array of byte.
     */
    public static byte[] toIpAddrBytes(String dottedNotation) {
        return getInetAddress(dottedNotation).getAddress();
    }

    /**
     * <p>toIpAddrString</p>
     *
     * @param addr an array of byte.
     * @return a {@link java.lang.String} object.
     */
    public static String toIpAddrString(byte[] addr) {
        return getInetAddress(addr).getHostAddress();
    }

    /**
     * Given a list of IP addresses, return the lowest as determined by the
     * numeric representation and not the alphanumeric string.
     *
     * @param addresses a {@link java.util.List} object.
     * @return a {@link java.net.InetAddress} object.
     */
    public static InetAddress getLowestInetAddress(List<InetAddress> addresses) {
        if (addresses == null) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }

        InetAddress lowest = null;
        byte[] lowestBytes = new byte[]{0, 0, 0, 0};
        ByteArrayComparator comparator = new ByteArrayComparator();
        for (InetAddress temp : addresses) {
            byte[] tempBytes = temp.getAddress();

            if (comparator.compare(tempBytes, lowestBytes) < 0) {
                lowestBytes = tempBytes;
                lowest = temp;
            }
        }

        return lowest;
    }

    public static boolean isInetAddressInRange(final String addrString, final String beginString, final String endString) {
        final ByteArrayComparator comparator = new ByteArrayComparator();
        final byte[] addr = IPUtils.toIpAddrBytes(addrString);
        final byte[] begin = IPUtils.toIpAddrBytes(beginString);
        if (comparator.compare(addr, begin) > 0) {
            final byte[] end = IPUtils.toIpAddrBytes(endString);
            return comparator.compare(addr, end) <= 0;
        } else {
            return comparator.compare(addr, begin) == 0;
        }
    }

    public static boolean isInetAddressInRange(final byte[] addr, final byte[] begin, final byte[] end) {
        final ByteArrayComparator comparator = new ByteArrayComparator();
        if (comparator.compare(addr, begin) > 0) {
            return comparator.compare(addr, end) <= 0;
        } else {
            return comparator.compare(addr, begin) == 0;
        }
    }

    public static InetAddress convertBigIntegerIntoInetAddress(BigInteger i) throws UnknownHostException {
        if (i.compareTo(new BigInteger("0")) < 0) {
            throw new IllegalArgumentException("BigInteger is negative, cannot convert into an IP address: " + i.toString());
        } else {
            // Note: This function will return the two's complement byte array so there will always
            // be a bit of value '0' (indicating positive sign) at the first position of the array
            // and it will be padded to the byte boundry. For example:
            //
            // 255.255.255.255 => 00 FF FF FF FF (5 bytes)
            // 127.0.0.1 => 0F 00 00 01 (4 bytes)
            //
            byte[] bytes = i.toByteArray();

            if (bytes.length == 0) {
                return InetAddress.getByAddress(new byte[]{0, 0, 0, 0});
            } else if (bytes.length <= CommonPlatformConstant.LENGTH_4) {
                // This case covers an IPv4 address with the most significant bit of zero (the MSB
                // will be used as the two's complement sign bit)
                byte[] addressBytes = new byte[CommonPlatformConstant.LENGTH_4];
                int k = CommonPlatformConstant.LENGTH_3;
                for (int j = bytes.length - 1; j >= 0; j--, k--) {
                    addressBytes[k] = bytes[j];
                }
                return InetAddress.getByAddress(addressBytes);
            } else if (bytes.length <= CommonPlatformConstant.LENGTH_5 && bytes[0] == 0) {
                // This case covers an IPv4 address (4 bytes + two's complement sign bit of zero)
                byte[] addressBytes = new byte[CommonPlatformConstant.LENGTH_4];
                int k = CommonPlatformConstant.LENGTH_3;
                for (int j = bytes.length - 1; j >= 1; j--, k--) {
                    addressBytes[k] = bytes[j];
                }
                return InetAddress.getByAddress(addressBytes);
            } else if (bytes.length <= CommonPlatformConstant.LENGTH_16) {
                // This case covers an IPv6 address with the most significant bit of zero (the MSB
                // will be used as the two's complement sign bit)
                byte[] addressBytes = new byte[CommonPlatformConstant.LENGTH_16];
                int k = CommonPlatformConstant.LENGTH_15;
                for (int j = bytes.length - 1; j >= 0; j--, k--) {
                    addressBytes[k] = bytes[j];
                }
                return InetAddress.getByAddress(addressBytes);
            } else if (bytes.length <= CommonPlatformConstant.LENGTH_17 && bytes[0] == 0) {
                // This case covers an IPv6 address (16 bytes + two's complement sign bit of zero)
                byte[] addressBytes = new byte[CommonPlatformConstant.LENGTH_16];
                int k = CommonPlatformConstant.LENGTH_15;
                for (int j = bytes.length - 1; j >= 1; j--, k--) {
                    addressBytes[k] = bytes[j];
                }
                return InetAddress.getByAddress(addressBytes);
            } else {
                throw new IllegalArgumentException("BigInteger is too large to convert into an IP address: " + i.toString());
            }
        }
    }

    /**
     * 将20000000C9BA2A00转成20:00:00:00::C9:BA:2A:00
     */
    public static String createPhysicalAddress(String str) {
        StringBuilder result = new StringBuilder(str);

        for (int i = str.length() - CommonPlatformConstant.LENGTH_2; i > 0; i -= CommonPlatformConstant.LENGTH_2) {
            result.insert(i, ':');
        }

        return result.toString();
    }

    /**
     * Comparator that is used to compare byte arrays. This should be used to compare
     * IP addresses using {@link java.net.InetAddress#getAddress()} and can be used to
     * compare any pair of IPv4 and/or IPv6 addresses.
     */
    public static class ByteArrayComparator implements Comparator<byte[]> {

        public int compare(byte[] a, byte[] b) {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return -1;
            } else if (b == null) {
                return 1;
            } else {
                // Make shorter byte arrays "less than" longer arrays
                int comparison = new Integer(a.length).compareTo(b.length);
                if (comparison == 0) {
                    // Compare byte-by-byte
                    for (int i = 0; i < a.length; i++) {
                        int byteComparison = new Long(unsignedByteToLong(a[i])).compareTo(unsignedByteToLong(b[i]));
                        if (byteComparison != 0) {
                            return byteComparison;
                        }
                    }
                    // OK both arrays are the same length and every byte is identical so they are equal
                    return 0;
                } else {
                    return comparison;
                }
            }
        }

        private static long unsignedByteToLong(byte b) {
            return b < 0 ? ((long) b) + CommonPlatformConstant.LENGTH_256 : ((long) b);
        }
    }

    /**
     * 判断字符串是否是一个段ip(125.65.112.0/24)
     *
     * @param str ip段
     * @return bool
     */
    public static boolean isValidChunkIp(String str) {
        if (str == null) {
            return false;
        }
        String[] chunks = str.split("/");
        if (chunks.length != CommonPlatformConstant.LENGTH_2) {
            return false;
        }
        String ip = chunks[0];
        if (!PlatformUtils.isIpAddress(ip)) {
            return false;
        }

        int netmask;
        try {
            netmask = Integer.parseInt(chunks[1]);
        } catch (Exception e) {
            return false;
        }

        return netmask >= CommonPlatformConstant.LENGTH_6 && netmask <= CommonPlatformConstant.LENGTH_32;
    }

    /**
     * 将ip列表封装为段ip列表
     *
     * @param ipList ip地址列表
     * @return 段ip列表
     */
    public static List<String> wrapChunkIpList(Collection<String> ipList) {
        List<String> resultList = new ArrayList<>();
        if (PlatformUtils.isEmpty(ipList)) {
            return resultList;
        }
        if (ipList.size() == 1) {
            resultList.addAll(ipList);
            return resultList;
        }

        Map<Long, String> map = new HashMap<>();
        for (String ip : ipList) {
            map.put(ip2num(ip), ip);
        }
        List<Long> longList = new ArrayList<>(map.keySet());
        Collections.sort(longList);

        //比较筛选
        String ip = null;
        int counter = 0;
        long longIp = 0L;
        List<String> tempList = new ArrayList<>();
        for (int i = 0, size = longList.size(); i < size; i++) {
            long currentNumIp = longList.get(i);
            if (i == 0) {
                longIp = currentNumIp;
                ip = map.get(longIp);
                tempList.add(ip);
            } else {
                if (longIp + 1 == currentNumIp) {
                    counter++;
                    longIp++;
                    tempList.add(map.get(currentNumIp));
                } else {
                    if (counter == 0) {
                        resultList.add(ip);
                    } else {
                        resultList.addAll(wrapChunkIpList2(tempList));
                    }

                    longIp = currentNumIp;
                    ip = map.get(longIp);
                    counter = 0;
                    tempList = new ArrayList<>();
                    tempList.add(ip);
                }

                //最后一个ip地址处理
                if (i == size - 1) {
                    if (counter == 0) {
                        resultList.add(ip);
                    } else {
                        resultList.addAll(wrapChunkIpList2(tempList));
                    }
                }
            }
        }

        return resultList;
    }

    private static List<String> wrapChunkIpList2(List<String> ipList) {
        List<String> resultList = new ArrayList<>();

        List<String> tempList = ipList;
        while (true) {
            if (tempList.isEmpty()) {
                break;
            }

            int size = tempList.size();
            if (size == 1) {
                resultList.add(tempList.get(0));
                break;
            }

            int power = (int) (Math.log(size) / Math.log(2));
            int counter = (int) Math.pow(2, power);
            resultList.add(String.format("%s/%d", tempList.get(0), (32 - power)));
            tempList = tempList.subList(counter, size);
        }

        return resultList;
    }

    public static void main(String[] args) {
        List<String> list = getSubnetIpList("192.168.1.0/24");
        for (String ip : list) {
            System.out.println(ip);
        }
    }
}
