package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * User: ketqi
 * Date: 2014-07-17 11:11
 * 数据库类型
 */
@Entity
@DiscriminatorValue("DATABASE_TYPE")
@BeanName(name = "数据库类型")
public class DatabaseType extends Dictionary {
    private static final long serialVersionUID = -7473242620656771052L;

    private static Set<Long> mysql, mssql;

    @BeanAttrInfo(cnName = "MySQL 4")
    public static final long MYSQL_4 = 2;

    @BeanAttrInfo(cnName = "MySQL 5")
    public static final long MYSQL_5 = 3;

    @BeanAttrInfo(cnName = "Microsoft SQL Server 2000")
    public static final long SQL_SERVER_2000 = 4;

    @BeanAttrInfo(cnName = "Microsoft SQL Server 2005")
    public static final long SQL_SERVER_2005 = 5;

    @BeanAttrInfo(cnName = "Microsoft SQL Server 2008")
    public static final long SQL_SERVER_2008 = 12;

    static {
        mysql = new HashSet<>();
        mysql.add(MYSQL_4);
        mysql.add(MYSQL_5);

        mssql = new HashSet<>();
        mssql.add(SQL_SERVER_2000);
        mssql.add(SQL_SERVER_2005);
        mssql.add(SQL_SERVER_2008);
    }

    public static Set<Long> getMysqlSet() {
        return mysql;
    }

    public static Set<Long> getMssqlSet() {
        return mssql;
    }

    /**
     * @param dbType
     * @return
     * @category 是否mysql
     */
    public static boolean isMySql(long dbType) {
        return getMysqlSet().contains(dbType);
    }

    /**
     * @param dbType
     * @return
     * @category 释放mssql
     */
    public static boolean isMssql(long dbType) {
        return getMssqlSet().contains(dbType);
    }
}
