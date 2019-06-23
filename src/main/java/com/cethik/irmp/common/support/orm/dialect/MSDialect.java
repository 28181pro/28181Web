package com.cethik.irmp.common.support.orm.dialect;

/**
 * MSSQL 数据库方言
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public class MSDialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MSPageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MSPageHepler.getCountString(sql);
    }
}
