package me.duelsol.springbootseed.entity;

import me.duelsol.springbootseed.service.CacheService;

import java.lang.annotation.*;

/**
 * 在ORM框架操作数据后，通知对应的缓存服务类，同步操作相关缓存。
 * @author 冯奕骅
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheListeners {

    Class<? extends CacheService>[] value();

}
