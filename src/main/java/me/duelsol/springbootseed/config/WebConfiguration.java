package me.duelsol.springbootseed.config;

import me.duelsol.springbootseed.framework.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author 冯奕骅
 */
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> xssFilter() {
        FilterRegistrationBean<Filter> xssFilter = new FilterRegistrationBean<>();
        xssFilter.setFilter(new XSSFilter());
        xssFilter.setName("xssFilter");
        xssFilter.setOrder(1);
        return xssFilter;
    }

}
