package com.sjdf.platform.common.utils;

import com.sjdf.platform.dictionary.bean.MatchPattern;
import com.sjdf.platform.dictionary.bean.eiss.domain.ResolveDomainSuffix;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create at 2013-5-10 上午10:09:28
 * <p/>
 * 获取顶级域名，返回0，表示域名后缀不存在
 *
 * @author frank
 */
public abstract class ResolveDomain {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ResolveDomain.class);

    /**
     * 获取顶级域名
     *
     * @param domain 需要被解析的域名
     * @return 如果符合配置文件(resolvedomain.conf已移到配置库ResolveDomainSuffix)的域名后缀，将返回顶级域名，否则返回截取后的域名后缀
     */
    public static String getDomain(String domain) {
        // 域名后缀集合
        List<String> SUFFIX_LIST = PlatformUtils.parse2StrList(ConfigManager.getInstance().getValue(ResolveDomainSuffix.class, ResolveDomainSuffix.SUFFIX));

        // 返回0，域名后缀不存在
        if (SUFFIX_LIST.isEmpty()) {
            return "0";
        }
        // 前缀
        String prefix = "";
        // 后缀
        String suffix = "";
        // 结果
        String returnDate;
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (String info : SUFFIX_LIST) {
            if (domain.endsWith(info)) {
                // 获取前缀
                prefix = domain.substring(0, domain.length() - info.length());
                map.put(info, prefix);
                map2.put(info, prefix);
            } else {
                prefix = domain;
            }
        }
        if (!map.isEmpty() && !map2.isEmpty()) {
            if (map.size() > 1 && map2.size() > 1) {
                for (Entry<String, String> entry : map.entrySet()) {
                    for (Entry<String, String> entry2 : map2.entrySet()) {
                        int len = entry.getKey().length();
                        int len2 = entry2.getKey().length();
                        if (len > len2) {
                            // 获取后缀
                            suffix = entry.getKey();
                            prefix = entry.getValue();
                            break;
                        }
                    }
                }
            } else {
                for (Entry<String, String> entry : map.entrySet()) {
                    suffix = entry.getKey();
                    prefix = entry.getValue();
                }
            }
        } else {
            suffix = "";
        }
        returnDate = prefix.substring(prefix.lastIndexOf(".") + 1) + suffix;
        return returnDate;
    }

    /**
     * 获取不可更改的域名后缀List
     *
     * @return 不可更改的域名后缀List
     */
    public static List<String> getSuffixList() {
        // 域名后缀集合
        List<String> SUFFIX_LIST = PlatformUtils.parse2StrList(ConfigManager.getInstance().getValue(ResolveDomainSuffix.class, ResolveDomainSuffix.SUFFIX));

        return Collections.unmodifiableList(SUFFIX_LIST);
    }

    /**
     * 通过正则表达式验证域名格式是否正确
     *
     * @param domain 需要被验证的域名
     * @return true:域名格式正确;false:域名格式不正确。
     */
    public static boolean checkDomainFormat(String domain) {
        Pattern p = Pattern.compile(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.DOMAIN_FORMAT));
        Matcher m = p.matcher(domain);
        return m.matches();
    }
}
