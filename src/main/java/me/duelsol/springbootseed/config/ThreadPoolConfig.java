package me.duelsol.springbootseed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 冯奕骅
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(60);
        return executor;
    }

}
