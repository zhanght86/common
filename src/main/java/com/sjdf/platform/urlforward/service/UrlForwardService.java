package com.sjdf.platform.urlforward.service;

import com.sjdf.platform.urlforward.bean.UrlForwardBean;


/**
 * @author laberwu
 * @category 转发服务器服务接口
 * @ClassName UrlForwardService
 * @Created 2012 2012-10-12 上午10:13:21
 */
public interface UrlForwardService {

    /**
     * @param domain
     * @return
     * @category 通过域名获得转发url地址
     * @ReturnType String
     * @author laberwu
     * @Created 2012 2012-10-12上午10:22:59
     */
    String findUrlByDomain(String domain);

    /**
     * @param domain
     * @category 通过域名保存或者更新url地址
     * @ReturnType void
     * @author laberwu
     * @Created 2012 2012-10-12上午11:34:12
     */
    void saveOrUpdateUrlByDomain(UrlForwardBean urlBean);

    /**
     * @param domain
     * @category 通过域名删除url记录
     * @ReturnType void
     * @author laberwu
     * @Created 2012 2012-10-12上午11:55:43
     */
    void deleteUrlbyDomain(String domain);

}
