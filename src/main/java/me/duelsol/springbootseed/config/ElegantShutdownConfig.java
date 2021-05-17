package me.duelsol.springbootseed.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 冯奕骅
 */
@Slf4j
public class ElegantShutdownConfig implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        connector.pause();
        Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                int timeout = 60;
                if (!threadPoolExecutor.awaitTermination(timeout, TimeUnit.SECONDS)) {
                    log.warn(String.format("线程池自动关闭超时（%d秒），请尝试手动强制关闭。", timeout));
                }
            } catch (InterruptedException e) {
                log.error("线程池关闭时发生异常。", e);
                Thread.currentThread().interrupt();
            }
        }
    }

}
