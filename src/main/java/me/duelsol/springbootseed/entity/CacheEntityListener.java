package me.duelsol.springbootseed.entity;

import me.duelsol.springbootseed.service.CacheService;
import me.duelsol.springbootseed.util.ApplicationContextUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA数据操作监听，用于在操作数据后同步操作相关的缓存。
 * @author 冯奕骅
 */
@Component
public class CacheEntityListener {

    @PostPersist
    public void postPersist(Object target) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                BaseEntity entity = target instanceof BaseEntity ? (BaseEntity) target : null;
                getCacheServiceByEntity(entity).forEach(s -> s.put(entity));
            }
        });
    }

    @PostUpdate
    public void postUpdate(Object target) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                BaseEntity entity = target instanceof BaseEntity ? (BaseEntity) target : null;
                getCacheServiceByEntity(entity).forEach(s -> {
                    if (s.isLogicalDelete(entity)) {
                        s.evict(entity);
                    } else {
                        s.put(entity);
                    }
                });
            }
        });
    }

    @PostRemove
    public void postRemove(Object target) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                BaseEntity entity = target instanceof BaseEntity ? (BaseEntity) target : null;
                getCacheServiceByEntity(entity).forEach(s -> s.evict(entity));
            }
        });
    }

    private List<CacheService> getCacheServiceByEntity(BaseEntity entity) {
        List<CacheService> result = new ArrayList<>();
        if (entity == null) {
            return result;
        }
        Class<?> clazz = entity.getClass();
        CacheListeners cacheListeners = clazz.getDeclaredAnnotation(CacheListeners.class);
        if (cacheListeners == null) {
            return result;
        }
        Class<? extends CacheService>[] values = cacheListeners.value();
        for (Class<? extends CacheService> value : values) {
            result.add(ApplicationContextUtils.getBean(value));
        }
        return result;
    }

}
