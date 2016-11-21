package com.sjdf.platform.firewall;

import com.sjdf.platform.dictionary.bean.FirewallList;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.firewall.impl.KindonFirewallImpl;

import java.util.List;

/**
 * Create at 2012-08-02
 * 硬件防火墙管理器
 *
 * @author Chen Mohan
 */
public class FirewallManager {

    private FirewallList firewallList;

    public FirewallManager(long attr) {
        firewallList = ConfigManager.getInstance().getDictionary(FirewallList.class, attr);
    }

    /** 导入域名 */
    public void importDomain(List<String> domainList) throws Exception {
        new KindonFirewallImpl(firewallList).importDomain(domainList);
    }
}
