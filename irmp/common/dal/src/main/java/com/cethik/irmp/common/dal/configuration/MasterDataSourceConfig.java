package com.cethik.irmp.common.dal.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author daniel.yu
 * @Title: MasterDataSourceConfig
 * @Description: 主数据源配置
 * @Version:1.0.0
 * @date 2018年8月31日
 */
@Configuration
@ImportResource(locations = {"classpath:druid-bean.xml"})
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    static final String PACKAGE = "com.cethik.irmp.common.dal.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/**/*.xml";

    @Value("${spring.datasource.master.url}")
    private String dbUrl;
    @Value("${spring.datasource.master.username}")
    private String username;
    @Value("${spring.datasource.master.password}")
    private String password;
    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.druid.filters}")
    private String filters;
    @Value("${spring.datasource.druid.connectionProperties}")
    private String connectionProperties;
    @Value("${spring.datasource.druid.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private int maxEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.keepAlive}")
    private boolean keepAlive;


    @Bean(name = "masterDataSource")
//    @Bean(name = "masterDataSource",destroyMethod = "close",initMethod = "init")
  //@Bean(destroyMethod = "close",initMethod = "init")
    @Primary //标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
    public DataSource masterDataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        //具体配置
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        dataSource.setFilters(filters);
        dataSource.setConnectionProperties(connectionProperties);

        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        dataSource.setKeepAlive(keepAlive);

        return dataSource;
    }



    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() throws Exception {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
//        //分页插件
//        Interceptor interceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        //数据库
//        properties.setProperty("helperDialect", "mysql");
//        //是否将参数offset作为PageNum使用
//        properties.setProperty("offsetAsPageNum", "true");
//        //是否进行count查询
//        properties.setProperty("rowBoundsWithCount", "true");
//        //是否分页合理化
//        properties.setProperty("reasonable", "false");
//        interceptor.setProperties(properties);
//        sessionFactory.setPlugins(new Interceptor[]{interceptor});

        return sessionFactory.getObject();
    }
}





//
//package com.cethik.irmp.common.dal.configuration;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.github.pagehelper.PageInterceptor;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * @author daniel.yu
// * @Title: MasterDataSourceConfig
// * @Description: 主数据源配置
// * @Version:1.0.0
// * @date 2018年8月31日
// */
//@Configuration
//@ImportResource(locations = {"classpath:druid-bean.xml"})
//@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
//public class MasterDataSourceConfig {
//
//    static final String PACKAGE = "com.cethik.irmp.common.dal.dao.master";
//    static final String MAPPER_LOCATION = "classpath:mapper/master/**/*.xml";
//
//    @Value("${spring.datasource.master.url}")
//    private String dbUrl;
//    @Value("${spring.datasource.master.username}")
//    private String username;
//    @Value("${spring.datasource.master.password}")
//    private String password;
//    @Value("${spring.datasource.master.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.druid.initialSize}")
//    private int initialSize;
//    @Value("${spring.datasource.druid.minIdle}")
//    private int minIdle;
//    @Value("${spring.datasource.druid.maxActive}")
//    private int maxActive;
//    @Value("${spring.datasource.druid.maxWait}")
//    private int maxWait;
//    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//    @Value("${spring.datasource.druid.validationQuery}")
//    private String validationQuery;
//    @Value("${spring.datasource.druid.testWhileIdle}")
//    private boolean testWhileIdle;
//    @Value("${spring.datasource.druid.testOnBorrow}")
//    private boolean testOnBorrow;
//    @Value("${spring.datasource.druid.testOnReturn}")
//    private boolean testOnReturn;
//    @Value("${spring.datasource.druid.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//    @Value("${spring.datasource.druid.filters}")
//    private String filters;
//    @Value("${spring.datasource.druid.connectionProperties}")
//    private String connectionProperties;
//    @Value("${spring.datasource.druid.useGlobalDataSourceStat}")
//    private boolean useGlobalDataSourceStat;
//    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
//    private int maxEvictableIdleTimeMillis;
//    @Value("${spring.datasource.druid.keepAlive}")
//    private boolean keepAlive;
//
//
//    @Bean(name = "masterDataSource")
////    @Bean(name = "masterDataSource",destroyMethod = "close",initMethod = "init")
//    //@Bean(destroyMethod = "close",initMethod = "init")
//    @Primary //标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
//    public DataSource masterDataSource() throws Exception {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driverClassName);
//
//        //具体配置
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(maxWait);
//        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        dataSource.setValidationQuery(validationQuery);
//        dataSource.setTestWhileIdle(testWhileIdle);
//        dataSource.setTestOnBorrow(testOnBorrow);
//        dataSource.setTestOnReturn(testOnReturn);
//        dataSource.setPoolPreparedStatements(poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//
//        dataSource.setFilters(filters);
//        dataSource.setConnectionProperties(connectionProperties);
//
//        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
//        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
//        dataSource.setKeepAlive(keepAlive);
//
//        return dataSource;
//    }
//
//
//
//    @Bean(name = "masterTransactionManager")
//    @Primary
//    public DataSourceTransactionManager masterTransactionManager() throws Exception {
//        return new DataSourceTransactionManager(masterDataSource());
//    }
//
//
//    @Bean(name="masterSqlSessionFactory")
//    @Primary
//    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return sqlSessionFactoryBean;
//    }
//
//
//}