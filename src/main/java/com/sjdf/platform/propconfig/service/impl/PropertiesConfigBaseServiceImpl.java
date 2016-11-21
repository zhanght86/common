package com.sjdf.platform.propconfig.service.impl;

import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.propconfig.dao.PropertiesConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2015-08-28
 * 属性配置基础service
 *
 * @author wangpeng
 */
@Service
public class PropertiesConfigBaseServiceImpl extends BaseServiceImpl {

    @Autowired
    protected PropertiesConfigDao configDao;
}
