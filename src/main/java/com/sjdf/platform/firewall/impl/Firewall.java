package com.sjdf.platform.firewall.impl;

import java.util.List;

/**
 * Create at 2012-08-02
 * 硬件防火墙接口
 *
 * @author Chen Mohan
 */
public abstract class Firewall {

    /**
     * 导入域名到防火墙
     *
     * @param domainList 域名列表，必须包含所有导入的域名
     * @throws Exception
     */
    public abstract void importDomain(List<String> domainList) throws Exception;
}
