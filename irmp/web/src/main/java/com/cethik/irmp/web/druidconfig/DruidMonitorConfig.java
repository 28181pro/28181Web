//package com.cethik.irmp.web.druidconfig;
//
//
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * druid 配置,配置监控统计功能.
// *
// * 这样的方式不需要添加注解：@ServletComponentScan
// * @author Administrator
// *
// */
//
//@Configuration
//public class DruidMonitorConfig {
//
////    @Value("${druidDatasource.loginUsername}")
////    private String loginUsername;
////    @Value("${druidDatasource.loginPassword}")
////    private String loginPassword;
//
//    /**
//     * 注册ServletRegistrationBean
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean registrationBean() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        /** 初始化参数配置，initParams**/
//        bean.addInitParameter("allow", "");// IP白名单 (没有配置或者为空，则允许所有访问)
//     //   bean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
////        bean.addInitParameter("loginUsername", loginUsername);
////        bean.addInitParameter("loginPassword", loginPassword);
//        //是否能够重置数据.
//        bean.addInitParameter("resetEnable", "false");
//        return bean;
//    }
//
//    /**
//     * 注册FilterRegistrationBean
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean druidStatFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
//        //添加过滤规则.
//        bean.addUrlPatterns("/*");
//        //添加不需要忽略的格式信息.
//        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return bean;
//    }
//
//
//}
