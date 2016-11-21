package com.sjdf.platform.common.utils;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.springframework.util.StringUtils;

import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 2013-1-14 下午2:51:11
 * 域名解析工具类
 *
 * @author frank
 */
public class ParseDomain {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ParseDomain.class);
    //默认未解析的域名
    public static final String DEFAULT_DOMAIN = "0.0.0.0";
    //域名数组
    private String[] domainArray;
    //域名解析IP数组
    private String[] domainParseIpArray;

    /**
     * @param domainListOfComma 以逗号形式组成的域名字符串
     */
    public ParseDomain(String domainListOfComma) {
        if (Tools.stringIsNotNullAndEmpty(domainListOfComma)) {
            domainArray = domainListOfComma.split(",");
            //解析 域名
            parseDomainArrayToIpArray();
        }
    }

    /**
     * @param domainList 集合形式
     */
    public ParseDomain(List<String> domainList) {
        domainArray = domainList.toArray(new String[]{});
        //解析 域名
        parseDomainArrayToIpArray();
    }

    /**
     * @param domainArray 数组形式
     */
    public ParseDomain(String[] domainArray) {
        this.domainArray = domainArray;
        //解析 域名
        parseDomainArrayToIpArray();
    }

    /**
     * 多线程执行解析多个域名，解析后将解析IP存入domainParseIpArray数组中
     */
    public void parseDomainArrayToIpArray() {
        try {
            domainParseIpArray = new String[domainArray.length];
            CountDownLatch countDownLatch = new CountDownLatch(domainArray.length);
            for (int sequence = 0; sequence < domainArray.length; sequence++) {
                if (Tools.stringIsNotNullAndEmpty(domainArray[sequence])) {
                    ParseDomainInnerThread parseDomainInnerThread = new ParseDomainInnerThread(domainArray[sequence]);
                    parseDomainInnerThread.setCountDownLatch(countDownLatch);
                    parseDomainInnerThread.setIpAddress(domainParseIpArray);
                    parseDomainInnerThread.setSequence(sequence);
                    parseDomainInnerThread.start();
                }
            }
            countDownLatch.await();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 获取域名的解析IP
     *
     * @return 解析IP 类型：List
     */
    public List<String> getParseIpListOfDomainArray() {
        return Arrays.asList(domainParseIpArray);
    }

    /**
     * 获取域名解析IP
     *
     * @return 去重后的解析IP 类型：字符串
     */
    public String getParseIpString() {
        StringBuilder domainParseIpBuilder = new StringBuilder();
        boolean isDuplicate = true;
        String onlyIp = null;
        if (domainParseIpArray == null) {
            return "0.0.0.0";
        }
        for (String tempIp : domainParseIpArray) {
            onlyIp = tempIp;
            if (Tools.stringIsNotNullAndEmpty(tempIp)) {
                if (Tools.stringIsNotNullAndEmpty(domainParseIpBuilder.toString()) && domainParseIpBuilder.indexOf(tempIp) == -1) {
                    isDuplicate = false;
                }
                domainParseIpBuilder.append(tempIp).append(",");
            }
        }
        //如果全部重复
        if (isDuplicate) {
            return onlyIp;
        } else if (domainParseIpBuilder.indexOf(",") != -1) {
            //去掉最后一位字符
            return domainParseIpBuilder.delete(domainParseIpBuilder.length() - 1, domainParseIpBuilder.length()).toString();
        }
        return null;
    }

    /**
     * 获取域名解析IP
     *
     * @return 解析IP 类型：domain1:IP1,domain2:IP2
     */
    public String getParseIpOfDomainString() {
        StringBuilder sb = new StringBuilder();
        for (int sequence = 0; sequence < domainParseIpArray.length; sequence++) {
            if (Tools.stringIsNotNullAndEmpty(sb.toString()) && sb.indexOf(domainParseIpArray[sequence]) != -1) {
                continue;
            }
            sb.append(domainArray[sequence]).append(":").append(domainParseIpArray[sequence]).append(",");
        }
        //去掉最后一位字符
        if (sb.indexOf(",") != -1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * 使用Java INetAddress 解析域名
     *
     * @param domain 需要被解析的域名
     * @return 域名的解析IP ，如果域名为空或者解析失败，返回 0.0.0.0
     */
    public static String getParseDomainIpINetAddress(String domain) {
        if (!Tools.stringIsNotNullAndEmpty(domain)) {
            return DEFAULT_DOMAIN;
        }
        String parseIp = DEFAULT_DOMAIN;
        try {
            parseIp = InetAddress.getByName(IDN.toASCII(domain)).getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                parseIp = InetAddress.getByName(IDN.toASCII("www." + domain)).getHostAddress();
            } catch (Exception th) {
                LOGGER.error(th.getMessage(), th);
            }
        }
        return parseIp;
    }

    /**
     * 使用多线程解析域名
     */
    class ParseDomainInnerThread extends Thread {

        /**
         * 需要被解析的域名
         */
        private String domain;
        /**
         * 域名所对应的解析IP
         */
        private String domainParseIp;
        /**
         * 域名解析后解析IP存放的数组
         */
        private String[] ipAddress;
        /**
         * 域名所在的数组标号
         */
        private int sequence;
        /**
         * 线程等待器
         */
        private CountDownLatch countDownLatch;

        /**
         * 初始化 线程内的需要被解析的域名
         */
        public ParseDomainInnerThread(String domain) {
            this.domain = domain;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getDomainParseIp() {
            return domainParseIp;
        }

        public void setDomainParseIp(String domainParseIp) {
            this.domainParseIp = domainParseIp;
        }

        public String[] getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String[] ipAddress) {
            this.ipAddress = ipAddress;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                domainParseIp = getParseDomainIpINetAddress(domain);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (!StringUtils.hasText(domainParseIp)) {
                //线程内多次解析后没有解析IP，默认为"0.0.0.0"
                domainParseIp = "0.0.0.0";
            }
            ipAddress[sequence] = domainParseIp;

            countDownLatch.countDown();
        }
    }

    /**
     * 获取解析后的域名
     *
     * @return 解析后的域名数组，其顺序与传入的域名的顺序一致
     */
    public String[] getDomainParseIpArray() {
        return domainParseIpArray;
    }
}
