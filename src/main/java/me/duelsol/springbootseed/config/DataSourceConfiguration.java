package me.duelsol.springbootseed.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import me.duelsol.springbootseed.framework.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 冯奕骅
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.main")
    public DataSource mainDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("main", mainDataSource());
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

}
