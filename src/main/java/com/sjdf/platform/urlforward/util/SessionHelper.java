package com.sjdf.platform.urlforward.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * @author sjdf
 * @category 转发服务器Session
 */
public abstract class SessionHelper {

    private static SessionFactory sessionFactory;

    /**
     * @return
     * @category 获取日志session
     */
    public static Session openSession() {
        synchronized (SessionHelper.class) {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure(SessionHelper.class.getResource("/hibernate-urlforward.cfg.xml")).buildSessionFactory();
            }
        }
        return sessionFactory.openSession();
    }
}
