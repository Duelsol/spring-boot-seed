package me.duelsol.springbootseed;

import me.duelsol.springbootseed.config.ElegantShutdownConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableElasticsearchRepositories(basePackages = "me.duelsol.springbootseed")
@MapperScan("me.duelsol.springbootseed.mapper")
@EnableCaching
@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class SpringBootSeedApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootSeedApplication.class, args);
        run.registerShutdownHook();
    }

    @Bean
    public ElegantShutdownConfig elegantShutdownConfig() {
        return new ElegantShutdownConfig();
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(elegantShutdownConfig());
        return tomcat;
    }

}
