package com.cethik.irmp.common.support.orm.dialect;

/**
 * 数据库方言抽象类
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public abstract class Dialect {

    /**
     * 得到分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getLimitString(String sql, int offset, int limit);

    /**
     * 得到分页sql
     * @param sql
     * @return
     */
    public abstract String getCountString(String sql);

}
