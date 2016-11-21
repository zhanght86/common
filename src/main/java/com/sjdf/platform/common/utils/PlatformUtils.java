package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.bean.MatchPattern;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * User: ketqi Date: 2013-05-24 11:38
 * <p/>
 * 平台辅助器;为了保证在各个平台上使用,禁止引入除jdk以外的包(com.sjdf.platform.*包除外)
 */
public abstract class PlatformUtils {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PlatformUtils.class);
    /** 分隔符 */
    public static final String DELIMITERS = ",; \t\n";

    /**
     * 将字符串按照分隔符分开
     * @param source    带分割的字符串
     * @param separator 分隔符
     * @return
     */
    public static List<String> parse2StrList(String source, String separator) {
        if (!PlatformUtils.hasText(source)) {
            return new ArrayList<>();
        }
        List<String> itemList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source, separator);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (PlatformUtils.hasText(token)) {
                itemList.add(token.trim());
            }
        }
        return itemList;
    }
    
    /**
     * @param source 字符串
     * @return 将以[,; \t\n]分隔的字符串转换为List
     */
    public static List<String> parse2StrList(String source) {
        if (!PlatformUtils.hasText(source)) {
            return new ArrayList<>();
        }

        List<String> itemList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source, DELIMITERS);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (PlatformUtils.hasText(token)) {
                itemList.add(token.trim());
            }
        }
        return itemList;
    }

    /**
     * @param source 数字字符串
     * @return 将以[,; \t\n]分隔的数字字符串转换为List
     */
    public static List<Long> parse2LongList(String source) {
        if (!PlatformUtils.hasText(source)) {
            return new ArrayList<>();
        }
        List<Long> itemList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source, DELIMITERS);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (PlatformUtils.hasText(token)) {
                itemList.add(Long.parseLong(token.trim()));
            }
        }

        return itemList;
    }

    /**
     * @param list 对象列表
     * @return 将对象列表转换为以逗号分隔的字符串
     */
    public static <T> String parse2String(Collection<T> list) {
        return parse2String(list, ",");
    }

    /**
     * @param list      对象列表
     * @param separator 分隔符
     * @return 将对象列表转换为以指定分隔的字符串
     */
    public static <T> String parse2String(Collection<T> list, String separator) {
        if (list != null && !list.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (T t : list) {
                builder.append(t).append(separator);
            }

            int length = builder.length();
            if (length > 0) {
                builder.delete(length - separator.length(), length);
            }
            return builder.toString();
        }
        return "";
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
    public static String[] parse2Array(String str) {
        if (!PlatformUtils.hasText(str)) {
            return new String[0];
        }

        String strs = str.replace("\r\n", ";");
        strs = strs.replace("\n", ";");
        return strs.split(";");
    }

    /**
     * @param sourceList 待分离的集合
     * @param targetList 参考集合
     * @param <T>        比较的对象
     * @return 分离sourceList中不包含在targetList中的数据
     */
    public static <T extends Comparable<T>> List<T> exclude(List<T> sourceList, List<T> targetList) {
        List<T> list = new ArrayList<>();
        for (T t : sourceList) {
            if (!targetList.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * @param str 源字符串
     * @return 判断str是否为空
     */
    public static boolean hasText(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * 空值转化为""字符
     *
     * @param str 源字符串
     * @return 字符串
     */
    public static String nullToEmpty(String str) {
        if (str == null || "null".equalsIgnoreCase(str)) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 保证原字符串长度在250范围内
     *
     * @param str 源字符串
     * @return str
     */
    public static String assureStringLengthLt250(String str) {
        String temp = str;
        if (hasText(temp)) {
            temp = temp.trim();
            if (temp.length() > CommonPlatformConstant.LENGTH_250) {
                return temp.substring(0, CommonPlatformConstant.LENGTH_250);
            }
        }
        return temp;
    }

    /**
     * @param str1 源字符串1
     * @param str2 源字符串2
     * @return boolean
     * 比较两个字符串是否相等
     */
    public static boolean equals(String str1, String str2) {
        if (hasText(str1)) {
            return str1.equals(str2);
        } else if (hasText(str2)) {
            return str2.equals(str1);
        }
        return true;
    }

    /**
     * 集合是否为空
     * @param coll 集合对象
     * @return  是否为空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Map是否为空
     * @param map map对象
     * @return  是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 集合是否不为空
     * @param coll 集合对象
     * @return  是否不为空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return coll != null && !coll.isEmpty();
    }

    /**
     * Map是否不为空
     * @param map map对象
     * @return  是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 比较字符串的大小
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 比较结果
     */
    public static int compareTo(String str1, String str2) {
        boolean b1 = hasText(str1);
        boolean b2 = hasText(str2);

        if (b1 && !b2) {
            return 1;
        }

        if (!b1 && b2) {
            return -1;
        }

        if (b1) {
            return str1.compareTo(str2);
        }
        return 0;
    }

    /**
     * 将"bool:true;message:xxxxxx"的字符串转化为map;
     *
     * @param source 字符串
     */
    public static Map<String, String> parse2Map(String source) {
        if (!hasText(source)) {
            return Collections.emptyMap();
        }

        List<String> strs = parse2StrList(source);
        if (strs.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();
        for (String item : strs) {
            if (!hasText(item)) {
                continue;
            }
            int indexof = item.indexOf(':');
            if (indexof == -1) {
                continue;
            }
            map.put(item.substring(0, indexof), item.substring(indexof + 1));
        }

        return map;
    }

    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    /**
     * @param str 源字符串
     * @return 避免xss漏洞, 将特殊符号转换
     */
    public static String xssEncodeUrl(String str) {
        if (!hasText(str)) {
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
     * @param t 堆栈异常
     * @return 堆栈信息字符串化
     * 将堆栈信息字符串化
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = "";
        if (t != null) {
            StringWriter stringWriter = null;
            PrintWriter pw = null;
            try {
                stringWriter = new StringWriter();
                pw = new PrintWriter(stringWriter, true);
                t.printStackTrace(pw);
                stackTrace = stringWriter.toString();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (pw != null) {
                    pw.close();
                }
                if (stringWriter != null) {
                    try {
                        stringWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
        return stackTrace;
    }

    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     *
     * @param c 待判断的字符
     * @return true or false
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 完整的判断中文汉字和符号
     *
     * @param strName 待判断的字符串
     * @return true or false
     */
    public static boolean isChinese(String strName) {
        if (!PlatformUtils.hasText(strName)) {
            return true;
        }

        char[] ch = strName.toCharArray();
        for (char c : ch) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在进行多线程运行时,切分任务
     *
     * @param list 源列表
     * @param num  切分个数
     * @param <T>  资源类型
     * @return 子资源列表
     */
    public static <T> List<List<T>> subList(List<T> list, int num) {
        List<List<T>> resultList = new ArrayList<>();
        if (num <= 1) {
            resultList.add(list);
            return resultList;
        }

        int size = num;
        int total = list.size();
        int page = total / size;
        if (page * (size + 0.5) < total) {
            size = num + 1;
        }

        for (int i = 1; i <= size; i++) {
            if (i == size) {
                resultList.add(list.subList((i - 1) * page, total));
            } else {
                resultList.add(list.subList((i - 1) * page, i * page));
            }
        }
        return resultList;
    }

    /**
     * 根据域名获取ip地址
     *
     * @param domainName 域名名称
     */
    public static String getIp(String domainName) {
        try {
            return InetAddress.getByName(IDN.toASCII(domainName)).getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 检测是否是IP
     *
     * @param ip IP地址
     * @return bool
     */
    public static boolean isIpAddress(String ip) {
        String regex = ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.IP);
        return PlatformUtils.hasText(ip) && Pattern.compile(regex).matcher(ip).matches();

    }

    /**
     * 判断域名或者ip解析是否一致
     *
     * @param domain1 域名或ip
     * @param domain2 域名或ip
     * @return bool
     */
    public static boolean domainEquals(String domain1, String domain2) {
        if (!PlatformUtils.hasText(domain1) || !PlatformUtils.hasText(domain2)) {
            return false;
        }

        if (domain1.equalsIgnoreCase(domain2)) {
            return true;
        }

        boolean isIp1 = isIpAddress(domain1);
        if (isIp1 && domain1.equalsIgnoreCase(getIp(domain2))) {
            return true;
        }

        boolean isIp2 = isIpAddress(domain2);
        return isIp2 && domain2.equalsIgnoreCase(getIp(domain1));

    }

    /**
     * 校验电话号码有效性
     *
     * @param phone 电话号码
     * @return 是否有效
     */
    public static boolean isPhone(String phone) {
        String phonePattern = ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.MOBILE_PHONE_NUMBER);
        return Pattern.compile(phonePattern).matcher(phone).matches();
    }

    /**
     * 校验QQ号码有效性
     *
     * @param qq QQ号码
     * @return 是否有效
     */
    public static boolean isQQ(String qq) {
        String regex = ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.QQ);
        return Pattern.compile(regex).matcher(qq).matches();
    }

    /**
     * 将str转换为long
     *
     * @param longStr str
     * @return long
     */
    public static long parse2Long(String longStr) {
        long num = 0L;
        try {
            num = Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 判断请求是否来自手机
     * @param userAgent 请求头中的User-Agent
     * @return
     */
    public static boolean isMobileAgent(String userAgent) {
        if (!hasText(userAgent)) {
            return false;
        }
        userAgent = userAgent.toLowerCase();
        if (!userAgent.contains("windows nt") || (userAgent.contains("windows nt") && userAgent.contains("compatible; msie 9.0;"))) {
            // 排除 苹果桌面系统
            if (!userAgent.contains("windows nt") && !userAgent.contains("macintosh")) {
                String mobileAgent = ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.MOBILE_TERMINAL_USER_AGENT);
                if (hasText(mobileAgent)) {
                    List<String> agentList = parse2StrList(mobileAgent);
                    for (String item : agentList) {
                        if (userAgent.contains(item.toLowerCase())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
