package com.sjdf.platform.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.PriceType;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * Create at 2012-07-26
 * 工具类
 *
 * @author 王正伟
 */
public abstract class Tools {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(Tools.class);

    /**
     * @return 避免xss漏洞, 将特殊符号转换为全角
     */
    public static String xssEncode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length() + CommonPlatformConstant.LENGTH_16);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '>':
                    // HTML转义大于号
                    sb.append("&gt;");
                    break;
                case '<':
                    // HTML转义小于号
                    sb.append("&lt;");
                    break;
                case '\'':
                    // HTML转义单引号
                    sb.append("&apos;");
                    break;
                case '\"':
                    // HTML转义双引号
                    sb.append("&quot;");
                    break;
                case '&':
                    // HTML转义和
                    sb.append("&amp;");
                    break;
                case '©':
                    // HTML转义版权
                    sb.append("&copy;");
                    break;
                case '®':
                    // HTML转义已注册商标
                    sb.append("&reg;");
                    break;
                case '™':
                    // HTML转义商标（美国）
                    sb.append("&trade;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * @param string 转义后的字符
     * @return 反转后的参数字符串
     * 将经过转义后的字符反转（该方法只是针对特殊需要反转的Action传递参数）
     */
    public static String xssDecode(String string) {
        String str = string;
        if (str == null || "".equals(str)) {
            return "";
        }
        str = str.replace("&gt;", ">").replace("&lt;", "<").replace("&apos;", "\'").replace("&quot;", "\"").replace("&amp;", "&").replace("&copy;", "©").replace("&reg;", "®").replace("&trade;", "™");
        return str;
    }

    /**
     * @return 比较两个字符串是否相等
     */
    public static boolean equals(String str1, String str2) {
        if (StringUtils.hasText(str1)) {
            return str1.equals(str2);
        } else if (StringUtils.hasText(str2)) {
            return str2.equals(str1);
        }
        return true;
    }

    /**
     * @return 比较两个字符串是否相等
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        String str1 = s1, str2 = s2;
        if (StringUtils.hasText(str1)) {
            str1 = str1.replace(" ", "");
        }
        if (StringUtils.hasText(str2)) {
            str2 = str2.replace(" ", "");
        }
        if (StringUtils.hasText(str1)) {
            return str1.equalsIgnoreCase(str2);
        } else if (StringUtils.hasText(str2)) {
            return str2.equalsIgnoreCase(str1);
        }
        return true;
    }

    /**
     * @return 获取当前访问者IP地址
     */
    public static String getIpAddress() {
        boolean success;
        String ipAddress = null;

        // 通过Struts获取IP地址
        try {
            ipAddress = getIpAddress(ServletActionContext.getRequest());
            success = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            success = false;
        }
        if (success) {
            return ipAddress;
        } else {
            return "0.0.0.0";
        }
    }

    /** 获取当前访问者真实IP地址 */
    private static String getIpAddress(HttpServletRequest request) {
        String address = request.getHeader("X-Forwarded-For");

        if (address != null && address.length() > 0 && !"unknown".equalsIgnoreCase(address)) {
            return address;
        }

        address = request.getHeader("Proxy-Client-IP");
        if (address != null && address.length() > 0 && !"unknown".equalsIgnoreCase(address)) {
            return address;
        }

        address = request.getHeader("WL-Proxy-Client-IP");
        if (address != null && address.length() > 0 && !"unknown".equalsIgnoreCase(address)) {
            return address;
        }

        return request.getRemoteAddr();
    }

    /**
     * @param parameterString 传入参数
     * @return 判断的结果
     * 三元运算判断字符串不为空和空串
     */
    public static boolean stringIsNotNullAndEmpty(String parameterString) {
        return parameterString != null && !"".equals(parameterString);
    }

    /**
     * @param parameterString 需要判断的字符串
     * @return true:表示 字符串为空，false：字符串不为空
     * 引用apache提供的工具类
     */
    public static boolean isEmpty(String parameterString) {
        return !StringUtils.hasText(parameterString);
    }

    /**
     * @param num1 参数1
     * @param num2 参数2
     * @return 比较长整型公共方法
     */
    public static boolean compareLong(Long num1, Long num2) {
        return num1 == null && num2 == null || !(num1 == null || num2 == null) && num1.compareTo(num2) == 0;
    }

    /**
     * @return 三元运算判断long的变量不为null和0
     */
    public static boolean longIsNotNUllAndZero(Long parameterLong) {
        return parameterLong != null && parameterLong != 0L;
    }

    /**
     * @param t 堆栈异常
     * @return 堆栈信息字符串化
     * 将堆栈信息字符串化
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = "";
        StringWriter stringWriter = null;
        PrintWriter pw = null;
        try {
            stringWriter = new StringWriter();
            pw = new PrintWriter(stringWriter, true);
            t.printStackTrace(pw);
            stackTrace = stringWriter.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return stackTrace;
    }

    public static boolean customException(Throwable t) {
        String stackTrace = getStackTrace(t);
        String firstLine = stackTrace.substring(0, stackTrace.indexOf("\r\n"));
        String err = firstLine.substring(0, !firstLine.contains(":") ? firstLine.length() : firstLine.indexOf(":"));
        String[] str = firstLine.split("Exception:");
        if (str.length == CommonPlatformConstant.LENGTH_3) {
            err = str[1];
        }
        return err.contains("com.sjdf");
    }

    public static String customExceptionInfo(Throwable t) {
        if (customException(t)) {
            String stackTrace = getStackTrace(t);
            String firstLine = stackTrace.substring(0, stackTrace.indexOf("\r\n"));
            String[] str = firstLine.split("Exception:");
            String err = str[1];
            if (str.length == CommonPlatformConstant.LENGTH_3) {
                err = str[CommonPlatformConstant.LENGTH_2];
            }
            return err;
        } else {
            return "系统错误，请联系管理员";
        }
    }

    /**
     * @param expireDate     当前过期日期
     * @param priceUnitParam 价格单位
     * @param priceTypeParam 价格类型
     * @throws Exception
     */
    public static Calendar calculateExpireDate(String expireDate, long priceUnitParam, long priceTypeParam) throws Exception {
        int priceUnit = (int) priceUnitParam;
        // 计算过期日期
        Calendar calendar = Calendar.getInstance();
        if (PlatformUtils.hasText(expireDate)) {
            // 有过期日期（续费或升级等）
            calendar.setTime(DateUtils.parseDateTime(expireDate));
        }
        if (priceTypeParam == PriceType.DAY) {
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + priceUnit);
        } else if (priceTypeParam == PriceType.WEEK) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(calendar.getTime());
            int week = cl.get(Calendar.WEEK_OF_YEAR);
            cl.add(Calendar.DAY_OF_MONTH, -CommonPlatformConstant.LENGTH_7);
            boolean isAddOneYear = false;
            if (week < cl.get(Calendar.WEEK_OF_YEAR)) {
                isAddOneYear = true;
            }
            calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + priceUnit);
            if (isAddOneYear) {
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            }
        } else if (priceTypeParam == PriceType.MONTH) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + priceUnit);
        } else if (priceTypeParam == PriceType.QUARTER) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + (priceUnit * CommonPlatformConstant.LENGTH_3));
        } else if (priceTypeParam == PriceType.HALF_YEAR) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + (priceUnit * CommonPlatformConstant.LENGTH_6));
        } else if (priceTypeParam == PriceType.YEAR) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + priceUnit);
        } else {
            throw new Exception("价格类型不正确");
        }
        return calendar;
    }

    /**
     * @param email 邮箱地址
     * @return 邮箱格式验证
     */
    public static boolean checkEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }

        Pattern regex = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        return regex.matcher(email).matches();
    }

    /**
     * 邮政编码格式验证
     *
     * @param postalCode 邮政编码
     */
    public static boolean checkPostalCode(String postalCode) {
        if (postalCode == null || "".equals(postalCode)) {
            return false;
        }

        Pattern regex = Pattern.compile("[1-9]\\d{5}(?!\\d)");
        return regex.matcher(postalCode).matches();
    }

    /**
     * 大写字母格式验证
     *
     * @param str 待检测字符串
     */
    public static boolean checkUppercase(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }

        Pattern regex = Pattern.compile("^[A-Z]+$");
        return regex.matcher(str).matches();
    }

    /**
     * 小写字母格式验证
     *
     * @param str 待检测字符串
     */
    public static boolean checkLowercase(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }

        Pattern regex = Pattern.compile("^[a-z]+$");
        return regex.matcher(str).matches();
    }

    /**
     * 是字符串转变为大写字母前面带空格的大写；如：传入 dsDadasDdasZdasd , 得到: ds Dadas Ddas
     * Zdasd
     *
     * @param str 待检测字符串
     * @return 字符串
     */
    public static String getStringHasSpace(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, " " + m.group());
        }
        m.appendTail(sb);
        String result = sb.toString();
        if (" ".equals(result.substring(0, 1))) {
            result = result.substring(1);
        }
        return result;
    }

    /**
     * String转化为List<String>
     *
     * @param str 源字符串
     * @return List<String> 结果
     */
    public static List<String> getStringListByString(String str) {
        List<String> resultList = new ArrayList<>();
        if (StringUtils.hasText(str)) {
            String[] strSplit = str.split(",|;");
            Collections.addAll(resultList, strSplit);
        }

        return resultList;
    }

    /**
     * String转化为List<Long>
     *
     * @param str 源字符串
     * @return List<String> 结果
     */
    public static List<Long> getLongListByString(String str) {
        List<Long> resultList = new ArrayList<>();
        if (StringUtils.hasText(str)) {
            String[] strSplit = str.split(",|;");
            for (String s : strSplit) {
                resultList.add(Long.parseLong(s.trim()));
            }
        }

        return resultList;
    }

    /**
     * TELNET一台机器的端口
     *
     * @param ip   IP地址
     * @param port 端口
     */
    public static boolean telnet(String ip, int port) {
        try {
            Socket server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ip, port);
            server.connect(address, CommonPlatformConstant.LENGTH_5000);
            server.close();
            return server.isConnected();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 创建更新日志信息
     *
     * @param oldt 旧bean信息
     * @param newt 待更新的bean信息
     * @return 更新详细信息
     */
    public static <T> String createUpdateLogInfo(T oldt, T newt) {
        if (oldt == null || newt == null) {
            return "";
        }
        StringBuilder log = new StringBuilder();
        Field[] oldFields = oldt.getClass().getDeclaredFields();
        Field[] newFields = newt.getClass().getDeclaredFields();
        try {
            for (Field oldField : oldFields) {
                oldField.setAccessible(true);
                for (Field f : newFields) {
                    f.setAccessible(true);
                    if (oldField.getName().equals(f.getName()) && oldField.get(oldt) != null && !oldField.get(oldt).equals(f.get(newt)) || f.get(newt) != null && !f.get(newt).equals(oldField.get(oldt))) {
                        log.append(f.getName()).append(":")
                                .append(oldField.get(oldt))
                                .append("->").append(f.get(newt))
                                .append(",");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (log.length() > 0) {
            log.deleteCharAt(log.length() - 1);
        }
        return log.toString();
    }

    /**
     * 字符串转换MAP
     *
     * @param s 传入字符串，格式 ：xxxx:200;yyy:500;Status:成功
     */
    public static Map<String, String> str2Map(String s) {
        Map<String, String> result = new HashMap<>();
        String[] a1 = s.split(";");
        for (String b : a1) {
            String[] b1 = b.split(":");
            if (b1.length >= CommonPlatformConstant.LENGTH_2) {
                result.put(b1[0], b.substring(b1[0].length() + 1));
            }
        }
        return result;
    }

    /**
     * 用Calendar类来计算传入的两个字符串相差的天数
     *
     * @param dateStart 起始时间（如果为null，则代表当前系统时间）
     * @param dateEnd   结束时间（如果为null，则代表当前系统时间）
     * @return 间隔的天数
     */
    public static long calendarDiff(String dateStart, String dateEnd) {
        long days = 0;
        try {
            Calendar startCalendar = Calendar.getInstance();
            if (PlatformUtils.hasText(dateStart)) {
                Date startDate = DateUtils.parseDateTime(dateStart);
                if (startDate == null) {
                    startDate = new Date();
                }
                startCalendar.setTime(startDate);
            }
            Calendar endCalendar = Calendar.getInstance();
            if (PlatformUtils.hasText(dateEnd)) {
                Date endDate = DateUtils.parseDateTime(dateEnd);
                if (endDate == null) {
                    endDate = new Date();
                }
                endCalendar.setTime(endDate);
            }

            long startTime = startCalendar.getTimeInMillis();
            long endTime = endCalendar.getTimeInMillis();

            days = (endTime - startTime) / (CommonPlatformConstant.DAY_OF_MILLISECOND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return days;
    }

    /** 将字节转换成易读字串 */
    public static String byte2ChangeString(long number) {

        DecimalFormat df = new DecimalFormat("###.##");

        float f;
        String value;
        long length1024 = CommonPlatformConstant.LENGTH_1024;

        if (number < length1024 * length1024) {
            f = (float) number / (float) length1024;
            value = (df.format(new Float(f).doubleValue()) + " KB");
        } else if (number < length1024 * length1024 * length1024) {
            f = (float) number / (float) (length1024 * length1024);
            value = (df.format(new Float(f).doubleValue()) + " MB");
        } else if (number < length1024 * length1024 * length1024 * length1024) {
            f = (float) number / (float) (length1024 * length1024 * length1024);
            value = (df.format(new Float(f).doubleValue()) + " GB");
        } else {
            f = (float) number / (float) (length1024 * length1024 * length1024 * length1024);
            value = (df.format(new Float(f).doubleValue()) + " TB");
        }

        return value;
    }

    /**
     * Java Post 特殊字符编码
     *
     * @return 编码后的字符
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuilder tmp = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        tmp.ensureCapacity(src.length() * CommonPlatformConstant.LENGTH_6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                tmp.append(j);
            } else if (j < CommonPlatformConstant.LENGTH_256) {
                tmp.append("%");
                if (j < CommonPlatformConstant.LENGTH_16) {
                    tmp.append("0");
                }
                tmp.append(Integer.toString(j, CommonPlatformConstant.LENGTH_16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, CommonPlatformConstant.LENGTH_16));
            }
        }
        return tmp.toString();
    }

    /**
     * Java Post 编码特殊字符串解码
     *
     * @return 解码后的字符
     */
    public static String unescape(String src) {
        StringBuilder tmp = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + CommonPlatformConstant.LENGTH_2, pos + CommonPlatformConstant.LENGTH_6), CommonPlatformConstant.LENGTH_16);
                    tmp.append(ch);
                    lastPos = pos + CommonPlatformConstant.LENGTH_6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + CommonPlatformConstant.LENGTH_3), CommonPlatformConstant.LENGTH_16);
                    tmp.append(ch);
                    lastPos = pos + CommonPlatformConstant.LENGTH_3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 字符串转换数组
     *
     * @param str 字符串，格式（换行）：
     *            baidu.com
     *            qq.com
     *            51web.com
     * @return 数组
     */
    public static String[] string2Array(String str) {
        if (PlatformUtils.hasText(str)) {
            String strs = str.replace("\r\n", ";");
            strs = strs.replace("\n", ";");
            return strs.split(";");
        } else {
            return new String[0];
        }
    }

    /**
     * 验证是否为合法的ipv6地址
     */
    public static boolean validataIpv6(String host) {
        if (PlatformUtils.hasText(host)) {
            InetAddress address;
            try {
                address = InetAddress.getByName(host);
                if (address instanceof Inet6Address) {
                    return true;
                }
            } catch (UnknownHostException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

    /**
     * 验证是否为合法的ipv4地址
     */
    public static boolean validataIpv4(String host) {
        if (PlatformUtils.hasText(host)) {
            InetAddress address;
            try {
                address = InetAddress.getByName(host);
                if (address instanceof Inet4Address) {
                    return true;
                }
            } catch (UnknownHostException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }
        }
        return false;
    }
}
