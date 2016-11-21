package com.sjdf.platform.common.utils;

/**
 * 远程ip地址
 * User: ketqi
 * Date: 2014-11-11 11:13
 */
public final class RemoteIpAddressContext {
    private ThreadLocal<String> cache = new ThreadLocal<>();

    private static RemoteIpAddressContext instance = new RemoteIpAddressContext();

    public static RemoteIpAddressContext getInstance() {
        return instance;
    }

    private RemoteIpAddressContext() {
    }

    public void set(String ipAddress) {
        cache.set(ipAddress);
    }

    public String get() {
        String ip = cache.get();
        if (!PlatformUtils.hasText(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
