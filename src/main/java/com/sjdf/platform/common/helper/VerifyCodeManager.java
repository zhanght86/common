package com.sjdf.platform.common.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.cache.AbstractCache;
import com.sjdf.platform.cache.CacheManager;
import com.sjdf.platform.common.utils.*;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.bean.eiss.EissVariable;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.net.HttpSocket;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create at 2012-10-17
 * 验证码管理
 *
 * @author ketqi
 */
public final class VerifyCodeManager extends AbstractCache<String> {
    private static final ConcurrentHashMap<String, Date> CACHE = new ConcurrentHashMap<>();
    private static VerifyCodeManager instance = new VerifyCodeManager();
    private static final String CONNECTION_PASSWORD = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
    private static final String VERIFY_CODE_TAG = "&verifyCode=";

    private VerifyCodeManager() {
        // 注册当前对象到缓存管理器
        CacheManager.getInstance().registerCache(this);
    }

    public static VerifyCodeManager getInstance() {
        return instance;
    }

    /**
     * 生成验证码
     */
    public String makeCode() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - CommonPlatformConstant.LENGTH_30);
        // 清理过期数据
        for (Map.Entry<String, Date> entry : CACHE.entrySet()) {
            if (entry.getValue().compareTo(c.getTime()) <= 0) {
                CACHE.remove(entry.getKey());
            }
        }
        String code = RandomUtils.getRandomString(CommonPlatformConstant.LENGTH_32);
        CACHE.put(code, new Date());
        // 同步验证码
        updateCache(true, code);
        return code;
    }

    /**
     * 校验验证码是否存在
     */
    public boolean exist(String code) {
        if (!StringUtils.hasText(code)) {
            return false;
        }

        if (CACHE.containsKey(code)) {
            CACHE.remove(code);
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * productClass:productIdx
     * ProductClass.CHOST:hostId
     * ProductClass.DATABASE:dbName
     * ProductClass.MAIL:domain
     * ProductClass.VHOST:siteName
     * </pre>
     * <p/>
     * 根据产品类型生成连接
     *
     * @param productClass 产品类型
     * @param productIdx   产品标识
     */
    public synchronized String makeLink(long productClass, String productIdx, String... params) {
        return makeLink(productClass, productIdx, false, params);
    }

    /**
     * <pre>
     * productClass:productIdx
     * ProductClass.CHOST:hostId
     * ProductClass.DATABASE:dbName
     * ProductClass.MAIL:domain
     * ProductClass.VHOST:siteName
     * </pre>
     * <p/>
     * 根据产品类型生成连接
     *
     * @param productClass 产品类型
     * @param productIdx   产品标识
     */
    public synchronized String makeLink(long productClass, String productIdx, boolean moniFlag, String... params) {
        StringBuilder link = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        String vtime = DateUtils.formatDateTimestamp(new Date());
        String checksum = MD5.md5(productIdx + CONNECTION_PASSWORD + vtime);

        link.append("http://");
        // 主站
        if (CompanyClass.getCurrentCompanyClass() == CompanyClass.SJDF) {
            if (ProductClass.CHOST == productClass) {
                if (params == null || params.length == 0) {
                    link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_CHOST_DOMAIN));
                    link.append("/conpanel/chost/conPanelChostLoginAction!login.action?");
                    link.append("conPanelChostVo.hostId=").append(productIdx);
                    link.append("&conPanelChostVo.vertime=").append(vtime);
                    link.append("&conPanelChostVo.vercode=").append(checksum);
                    link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
                } else {
                    link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.AGENT_CONPANEL_CHOST_DOMAIN));
                    link.append("/chost/conpanelLoginAction!login.action?");
                    link.append("account=").append(productIdx);
                    link.append("&password=").append(Tools.escape(params[0]));
                    link.append("&vertime=").append(vtime);
                    link.append("&vercode=").append(checksum);
                    link.append(VERIFY_CODE_TAG).append(params[1]);
                }
            } else if (ProductClass.DATABASE == productClass) {
                link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_DATABASE_DOMAIN));
                link.append("/conpanel/database/ConPanelDBAction!getDBInfo.action?");
                link.append("databaseBean.dbName=").append(productIdx);
                link.append("&vtime=").append(vtime);
                link.append("&checksum=").append(checksum);
                link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
            } else if (ProductClass.MAIL == productClass) {
                link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_MAIL_DOMAIN));
                link.append("/conpanel/mail/ConPanelMailAction!getMailInfo.action?");
                link.append("mailBean.domain=").append(productIdx);
                link.append("&vtime=").append(vtime);
                link.append("&checksum=").append(checksum);
                link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
            } else if (ProductClass.VHOST == productClass) {
                link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_VHOST_DOMAIN));
                link.append("/conpanel/vhost/ConPanelVHAction!getVHInfo.action?");
                link.append("vhostBean.siteName=").append(productIdx);
                link.append("&vtime=").append(vtime);
                link.append("&checksum=").append(checksum);
                link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
            } else if (ProductClass.DOMAIN == productClass) {
                link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_DOMAIN_DOMAIN));
                link.append("/conpanel/domain/ConPanelDomainLoginAction!login.action?");
                link.append("domainId=").append(productIdx);
                link.append("&vertime=").append(vtime);
                link.append("&checksum=").append(checksum);
                link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
            }
        } else if (CompanyClass.getCurrentCompanyClass() == CompanyClass.YGF) { // 云工坊
            link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.YGF_CONPANEL_DOMAIN));
            link.append("/ygf/LoginAction!login.action?");
            link.append("productName=").append(productIdx);
            link.append("&productClass=").append(productClass);
            link.append("&vertime=").append(vtime);
            link.append("&checksum=").append(checksum);
            link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
        }
        if (moniFlag) {
            link.append("&moniFlag=").append(WhetherState.YES);
        }

        return link.toString();
    }

    /**
     * 生成通用网址登录控制面板连接
     *
     * @return 连接地址
     */
    public synchronized String makeWebsiteLink(long websiteId) {
        StringBuilder link = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        String vtime = DateUtils.formatDateTimestamp(new Date());
        String checksum = MD5.md5(websiteId + CONNECTION_PASSWORD + vtime);

        link.append("http://");
        link.append(ConfigManager.getInstance().getValue(ConpanelDomain.class, ConpanelDomain.CONPANEL_DOMAIN_DOMAIN));
        link.append("/conpanel/domain/ConPanelWebsiteLoginAction!login.action?");
        link.append("domainId=").append(websiteId);
        link.append("&vertime=").append(vtime);
        link.append("&checksum=").append(checksum);
        link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());

        return link.toString();
    }

    /**
     * 根据用户名生成主站登录链接
     *
     * @param userName 用户名
     */
    public synchronized String makeHomeLink(String userName, String userPwd) {
        String successUrl = "/";
        StringBuilder link = new StringBuilder(CommonPlatformConstant.LENGTH_512).append("LoginAction.action?");
        String vtime = DateUtils.formatDateTimestamp(new Date());
        String checksum = MD5.md5(userName + CONNECTION_PASSWORD + vtime);
        link.append("userName=").append(userName);
        if (PlatformUtils.hasText(userPwd)) {
            link.append("&userPass=").append(userPwd);
        }
        link.append("&vertime=").append(vtime);
        link.append("&checksum=").append(checksum);
        link.append(VERIFY_CODE_TAG).append(VerifyCodeManager.getInstance().makeCode());
        link.append("&successUrl=").append(successUrl);
        return link.toString();
    }

    /**
     * 获取链接登录验证码（跨平台使用）
     */
    public synchronized String getVerifyCode(String url) {
        String result;
        // post数据
        Map<String, String> postData = new HashMap<>();
        // MD5(MD5(连接密码)+时间戳)
        StringBuilder md5Source = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        md5Source.append(MD5.md5(connpwd)).append(vertime);
        // 验证用参数
        postData.put("vercode", MD5.md5(md5Source.toString()));
        postData.put("vertime", vertime);
        try {
            HttpSocket httpSocket = new HttpSocket(url, postData);
            // Socket 连接
            httpSocket.doPost();
            result = httpSocket.getSocketReceive();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        if (result.contains("Status:0;Result:")) {
            String[] tempArray = result.split("Status:0;Result:");
            if (tempArray.length != CommonPlatformConstant.LENGTH_2) {
                return null;
            } else {
                return tempArray[1];
            }
        } else {
            return null;
        }
    }

    /**
     * 非世纪东方获取通过管理中心模拟登陆控制面板的链接
     *
     * @param productName 产品名称;虚拟主机:siteName;数据库:dbName;邮局:domain;云主机:orderNum
     * @param urlFlag     使用控制面板URL标识
     * @see com.sjdf.platform.dictionary.bean.ProductClass;
     */
    public synchronized String makeLinkByApi(long productClass, String productName, String urlFlag) {
        String cpLinked = null;
        HttpSocket httpSocket = new HttpSocket();
        final String connectPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.OLD_CONNECTION_PASSWORD);
        final String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONPANEL_LOGIN_API);
        final String userName = ConfigManager.getInstance().getValue(EissVariable.class, EissVariable.ACTIVE_MQ_MEMBER);
        String vtime = DateUtils.formatDateTimestamp(new Date());

        StringBuilder md5Source = new StringBuilder(CommonPlatformConstant.LENGTH_100);
        md5Source.append(userName);
        md5Source.append(productClass);
        md5Source.append(productName);
        md5Source.append(connectPwd);
        md5Source.append(vtime);
        String checksum = MD5.md5(md5Source.toString());

        Map<String, String> postDataMap = new HashMap<>();
        postDataMap.put("username", userName);
        postDataMap.put("productClass", String.valueOf(productClass));
        postDataMap.put("productName", productName);
        if (PlatformUtils.hasText(urlFlag)) {
            postDataMap.put("urlFlag", urlFlag);
        }
        postDataMap.put("vtime", vtime);
        postDataMap.put("checksum", checksum);
        httpSocket.setPostData(postDataMap);
        httpSocket.setUrl(url);

        try {
            httpSocket.doPost();
            String socketResult = httpSocket.getResponseData();

            List<Map<String, String>> list = parseXML2Map(socketResult, null, null);
            if (!list.isEmpty()) {
                Map<String, String> map = list.get(0);
                cpLinked = map.get("url");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            cpLinked = null;
        }

        return cpLinked;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> parseXML2Map(String xml, String parentNodeName, String nodeName) throws Exception {
        Document doc = DocumentHelper.parseText(xml);
        Node returnCode = doc.selectSingleNode("/interface/return_code");
        if (Long.valueOf(returnCode.getText()) != CommonPlatformConstant.LENGTH_200) {
            Node returnMsg = doc.selectSingleNode("/interface/return_msg");
            throw new Exception(returnMsg.getText());
        }
        List<Map<String, String>> maps = new ArrayList<>();
        List<Node> list = null;
        // 父节点和子节点都不为空
        if (PlatformUtils.hasText(parentNodeName) && PlatformUtils.hasText(nodeName)) {
            list = doc.selectNodes("/interface/" + parentNodeName + "/" + nodeName);
        } else if (PlatformUtils.hasText(parentNodeName)) {
            list = doc.selectNodes("/interface/" + parentNodeName);
        } else if (PlatformUtils.hasText(nodeName)) {
            list = doc.selectNodes("/interface/" + nodeName);
        } else {
            list = doc.selectNodes("/interface");
        }
        for (Node node : list) {
            Map<String, String> map = new HashMap<>();
            Element clement = (Element) node;
            List<Element> elements = clement.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getText());
            }
            maps.add(map);
        }
        return maps;
    }

    @Override
    public void updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys) {
        if (keys != null) {
            for (Serializable key : keys) {
                CACHE.put((String) key, new Date());
            }
        }

    }

    @Override
    public void updateCache(boolean notifyCacheReplicators, List<String> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Serializable[] keys = new Serializable[size];
            for (int i = 0; i < size; i++) {
                keys[i] = list.get(i);
            }
            if (notifyCacheReplicators) {
                CacheManager.getInstance().addNotifyEvent(this, false, keys);
            }
        }

    }

    @Override
    public void removeCache(boolean notifyCacheReplicators, List<String> list) {
        throw new RuntimeException("不支持该功能");

    }

    @Override
    public String get(Serializable key) {
        throw new RuntimeException("不支持该功能");
    }

}
