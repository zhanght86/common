package com.sjdf.platform.common.dispatcher;

import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.init.StrutsStartupInitable;
import com.sjdf.platform.common.utils.clazz.ClassUtils;
import com.sjdf.platform.common.utils.clazz.LocalClassFinder;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Create at 2013年12月9日 下午2:20:45
 * 业务平台启动初始化
 *
 * @author KETQI
 */
public class PlatformFilterDispatcher extends StrutsPrepareAndExecuteFilter {
    protected SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(PlatformFilterDispatcher.class);
    private FilterConfig filterConfig;

    /**
     * 在初始化前被调用, 禁止操作数据库相关的操作.如:设置当前系统类型等
     */
    protected void beforeInit() {
        // Do nothing because of sub class impl.
    }

    @Override
    protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
        logger.info("base system platform init begin");

        // 在初始化前被调用,如:设置当前系统类型等
        beforeInit();

        super.postInit(dispatcher, filterConfig);
        logger.info("base system platform init end");
        logger.info("available processor:" + Runtime.getRuntime().availableProcessors());
        // 初始化相应信息
        logger.info("system platform Initialization procedure begin");
        initStartupInitable(dispatcher, filterConfig);
        logger.info("system platform Initialization procedure end");
    }

    /**
     * @param dispatcher   分发器
     * @param filterConfig 配置
     *                     系统平台启动后, 初始化系统初始化时需要初始化的信息, 此时可以进行数据库操作
     */
    protected void initStartupInitable(Dispatcher dispatcher, FilterConfig filterConfig) {
        LocalClassFinder classFinder = new LocalClassFinder();
        List<StrutsStartupInitable> initableList = ClassUtils.initializeClass(classFinder.findSubClass(StrutsStartupInitable.class));

        // order it by order value
        Collections.sort(initableList, new Comparator<StrutsStartupInitable>() {
            public int compare(StrutsStartupInitable o1, StrutsStartupInitable o2) {
                return Integer.compare(o2.getOrder(), o1.getOrder());
            }
        });

        logger.info("init StrutsStartupInitable size :" + initableList.size());
        for (StrutsStartupInitable initable : initableList) {
            logger.info(initable.getOrder() + ":" + initable.getClass().getCanonicalName() + " begin startup!");
        }

        long currentSystemType = SystemType.getCurrentSystemType();
        SystemType systemType = ConfigManager.getInstance().getDictionary(SystemType.class, currentSystemType);
        for (StrutsStartupInitable initable : initableList) {
            if (initable.systemType() == currentSystemType) {
                initable.init(filterConfig.getServletContext(), dispatcher);
            } else if (currentSystemType != SystemType.EISS_COMMON && initable.systemType() == SystemType.OTHER) {
                initable.init(filterConfig.getServletContext(), dispatcher);
            } else if (systemType != null && systemType.getRef() != null
                    && systemType.getRef().getAttr() == SystemType.EISS
                    && currentSystemType != SystemType.EISS_COMMON
                    && initable.systemType() == SystemType.EISS) { // 当前系统基于主站系统，需要初始化主站系统的缓存
                initable.init(filterConfig.getServletContext(), dispatcher);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (ApplicationContextManager.isApplicationContextInjected()) {
            super.init(filterConfig);
        }
    }

    public void restart() throws ServletException {
        init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (ApplicationContextManager.isApplicationContextInjected()) {
            super.doFilter(req, res, chain);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        if (ApplicationContextManager.isApplicationContextInjected()) {
            super.destroy();
        }
    }
}
