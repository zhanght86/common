package com.sjdf.platform.cache;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.*;

/**
 * <pre>
 * 分布式缓存配置文件cache.properties:
 *      cache.local.monitor.port:本地监听端口
 *      cache.remote.receive.address:远程接受数据地址列表
 *
 * </pre>
 * <p/>
 * Create at 2013年12月18日 下午3:31:29
 * <p/>
 * 分布式缓存管理器
 *
 * @author KETQI
 */
public final class CacheManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(CacheManager.class);
    private static final String RMI_REMOTE_NAME = "cache";
    /** 远程接受数据地址列表;对应cache.properties中的cache.remote.receive.address */
    private ArrayList<String> remoteAddressList = new ArrayList<>();
    /** 标识该缓存管理器管理的对象是否是集群 */
    private volatile boolean isCluster = false;
    /** 缓存实例列表<Cache.className,cache> */
    private Map<String, Cache<?>> cacheMap = new HashMap<>();

    private static final CacheManager INSTANCE = new CacheManager();

    private CacheManager() {
        init();
    }

    public static CacheManager getInstance() {
        return CacheManager.INSTANCE;
    }

    /**
     * 注册通讯路径
     */
    public <T extends Cache<?>> void registerCache(T cache) {
        // 判断是否使用分布式缓存
        if (!isCluster) {
            return;
        }

        String name = cache.getName();
        cacheMap.put(name, cache);
        LOGGER.info("register cache:" + name);
    }

    /**
     * 获取缓存实例
     *
     * @param className 缓存实例的class全名称
     * @return 缓存实例
     */
    public Cache<?> getCache(String className) {
        return cacheMap.get(className);
    }

    /**
     * 添加通知事件
     *
     * @param cache    事件所对应的缓存
     * @param keys     事件对应的数据key列表
     * @param isRemove 是删除或者添加更新事件
     */
    public <T extends Cache<?>> void addNotifyEvent(T cache, boolean isRemove, Serializable... keys) {
        if (keys != null) {
            NotifyEvent event = new NotifyEvent(cache, isRemove, keys);
            NotifyEventDisruptor.getInstance().add(event);
        }
    }

    /**
     * 初始化缓存管理器
     */
    private void init() {
        Properties cacheProperty = new Properties();
        try (InputStream configInputStream = CacheManager.class.getResourceAsStream("/cache.properties")) {
            cacheProperty.load(configInputStream);
        } catch (IOException e) {
            LOGGER.error("load cache.properties fail", e);
        }

        //本地地址
        String localPortStr = cacheProperty.getProperty("cache.local.monitor.port");
        if (PlatformUtils.hasText(localPortStr)) {
            localPortStr = localPortStr.trim();
        }
        String localAddressStr = cacheProperty.getProperty("cache.local.monitor.address");
        if (PlatformUtils.hasText(localAddressStr)) {
            localAddressStr = localAddressStr.trim();
        }
        String localAddress = localAddressStr + ":" + localPortStr;

        String remoteAddresses = cacheProperty.getProperty("cache.remote.receive.address");
        int localMonitorPort = 0;
        if (PlatformUtils.hasText(localPortStr)) {
            // 解决多网卡无法更新数据问题：设置java.rmi.server.hostname（本地服务器IP）
            String localServerIp = null;
            try {
                localServerIp = InetAddress.getByName(localAddressStr).getHostAddress();
                System.setProperty("java.rmi.server.hostname", localServerIp);
                LOGGER.info("java.rmi.server.hostname:" + localServerIp);
            } catch (UnknownHostException e) {
                LOGGER.error("java.rmi.server.hostname", e);
                throw new RuntimeException(e);
            }
            // 注册通讯端口
            localMonitorPort = Integer.parseInt(localPortStr.trim());
            try {
                LocateRegistry.createRegistry(localMonitorPort);
            } catch (RemoteException e) {
                LOGGER.error("LocateRegistry.createRegistry(" + localPortStr + ")", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("CacheManager monitor port:" + localMonitorPort);

            String url = new StringBuilder(CommonPlatformConstant.LENGTH_64).append("rmi://").append(localAddress).append("/").append(RMI_REMOTE_NAME).toString();
            LOGGER.info(url);
            try {
                // rmi://127.0.0.1:6600/cache
                Naming.rebind(url, new RmiCacheImpl());
            } catch (RemoteException | MalformedURLException e) {
                LOGGER.error("Naming.rebind(" + url + ")", e);
                throw new RuntimeException(e);
            }
        }

        if (PlatformUtils.hasText(remoteAddresses)) {
            String[] as = remoteAddresses.trim().split(";");
            for (String s : as) {
                if (PlatformUtils.hasText(s)) {
                    s = s.trim();
                    if (!localAddress.equalsIgnoreCase(s)) {
                        remoteAddressList.add(s);
                    }
                }
            }
            remoteAddressList.trimToSize();
        }

        if (localMonitorPort != 0 && !remoteAddressList.isEmpty()) {
            isCluster = true;
        }
        LOGGER.info("CacheManager monitor remote address:" + remoteAddressList);
    }

    /**
     * 根据remoteAddressList生成rmi地址
     *
     * @return rmi注册通讯地址
     */
    public List<String> generateAddress() {
        List<String> urlList = new ArrayList<>(remoteAddressList.size());
        for (String address : remoteAddressList) {
            // rmi://127.0.0.1:6600/cache
            urlList.add(new StringBuilder(CommonPlatformConstant.LENGTH_64).append("rmi://").append(address).append("/").append(RMI_REMOTE_NAME).toString());
        }
        return urlList;
    }
}
