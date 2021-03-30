package me.duelsol.springbootseed.service;

import me.duelsol.springbootseed.entity.BaseEntity;

/**
 * @author 冯奕骅
 */
public interface CacheService {

    /**
     * 根据传入的实体对缓存进行放入。
     *
     * @param entity 实体
     * @param <T> BaseEntity的子类
     * @return 放入缓存的值
     */
    <T extends BaseEntity> Object put(T entity);

    /**
     * 根据传入的实体对缓存进行清除。
     *
     * @param entity 实体
     * @param <T> BaseEntity的子类
     */
    <T extends BaseEntity> void evict(T entity);

    /**
     * 用来判断该实体的数据更新是否是表达逻辑删除，如果是，则在更新实体时会对缓存进行清除。
     *
     * @param entity 实体
     * @param <T> BaseEntity的子类
     * @return 是否是逻辑删除
     */
    default <T extends BaseEntity> boolean isLogicalDelete(T entity) {
        return false;
    }

}
