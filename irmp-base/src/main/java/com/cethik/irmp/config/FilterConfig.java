package com.cethik.irmp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author zhangshuhua@cetiti.com
 * @date 2018-9-10
 */
@Configuration
public class FilterConfig {

    @Value("${test.filter.patterns}")
    private String testFilterPatterns;

    @Bean(name = "testFilterRegistration")
    public FilterRegistrationBean<Filter> testFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(getTestFilter());
        registration.addUrlPatterns(testFilterPatterns);
        registration.setName("testFilter");
        return registration;
    }

    @Bean(name = "testFilter")
    public Filter getTestFilter() {
        return new TestFilter();
    }
}
