package com.sjdf.platform.urlforward.service;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.urlforward.bean.UrlForwardBean;
import com.sjdf.platform.urlforward.util.SessionHelper;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import java.util.List;

/**
 * @author laberwu
 * @category 域名转发服务实现
 * @ClassName UrlForwardServiceImpl
 * @Created 2012 2012-10-12 上午10:25:19
 */
public class UrlForwardServiceImpl implements UrlForwardService {

    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(UrlForwardService.class);

    @Override
    public String findUrlByDomain(String domain) {
        String url = null;
        try {
            UrlForwardBean urlBean = this.findUrlForwardBeanByDomain(domain);
            if (urlBean != null) {
                url = urlBean.getUrl();
            }
        } catch (Exception e) {
            logger.error("通过域名获得转发url地址", e);
        }

        return url;
    }

    /**
     * @param domain
     * @return
     * @category 通过域名查询
     * @ReturnType UrlForwardBean
     * @author laberwu
     * @Created 2012 2012-10-12上午11:53:18
     */
    private UrlForwardBean findUrlForwardBeanByDomain(String domain) {
        UrlForwardBean urlBean = null;
        Session session = null;

        try {
            session = SessionHelper.openSession();
            Query query = session.createQuery("from UrlForwardBean where domain = ?");
            query.setString(0, domain);
            @SuppressWarnings("unchecked")
            List<UrlForwardBean> urlList = query.list();
            if (urlList != null && !urlList.isEmpty()) {
                urlBean = urlList.get(0);
            }
        } catch (Exception e) {
            logger.error("通过域名获得转发url地址", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return urlBean;
    }

    @Override
    public void saveOrUpdateUrlByDomain(UrlForwardBean urlBean) {
        // 查询
        UrlForwardBean old = this.findUrlForwardBeanByDomain(urlBean.getDomain());

        Session session = null;
        Transaction transaction = null;

        try {
            session = SessionHelper.openSession();
            transaction = session.beginTransaction();
            if (old != null) {
                old.setUrl(urlBean.getUrl());
                session.saveOrUpdate(old);
            } else {
                session.saveOrUpdate(urlBean);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error("通过域名保存或者更新url地址", e);
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void deleteUrlbyDomain(String domain) {
        // 查询
        UrlForwardBean urlBean = this.findUrlForwardBeanByDomain(domain);

        if (urlBean != null) {
            Session session = null;
            Transaction transaction = null;

            try {
                session = SessionHelper.openSession();
                transaction = session.beginTransaction();
                session.delete(urlBean);
                transaction.commit();
            } catch (Exception e) {
                logger.error("通过域名删除url地址", e);
                transaction.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }
}
