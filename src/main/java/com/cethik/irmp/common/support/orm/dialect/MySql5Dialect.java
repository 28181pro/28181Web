package com.cethik.irmp.common.support.orm.dialect;

/**
 * MySQL数据库方言
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public class MySql5Dialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MySql5PageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MySql5PageHepler.getCountString(sql);
    }
}
