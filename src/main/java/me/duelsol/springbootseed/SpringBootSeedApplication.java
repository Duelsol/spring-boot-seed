package me.duelsol.springbootseed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
public class SpringBootSeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSeedApplication.class, args);
    }

}
