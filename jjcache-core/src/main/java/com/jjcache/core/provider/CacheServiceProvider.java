package com.jjcache.core.provider;

import com.jjcache.common.constant.CacheConstant;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.model.Cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 缓存提供服务类
 *
 * @author jiangcx
 * @create 2021 - 07 - 07 - 16:10
 */
public abstract class CacheServiceProvider {

    /**
     * 初始化
     * @param cacheConfig
     */
    public abstract void init(JjCacheConfig cacheConfig);

    /**
     * 获取缓存提供类级别
     * @return
     */
    public abstract Integer getLevel();

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public abstract Cache getCache(String key);

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    public abstract Object getValue(String key);

    /**
     * 设置缓存
     * @param cache
     * @param <K>
     * @param <V>
     */
    public abstract <K, V> void setCache(Cache<K, V> cache);

    /**
     * 删除缓存
     * @param cache 缓存对象
     * @return
     */
    protected abstract boolean deleteCache(Cache cache);

    /**
     * 删除缓存
     * @param key 缓存键
     * @return
     */
    protected abstract boolean deleteCache(String key);

    /**
     * 检查过期时间
     * @param cache
     * @return
     */
    protected abstract <K, V> boolean checkExpire(Cache<K, V> cache);

    /**
     * 获取缓存继承属性 - type
     * @param value
     * @return
     */
    public static Integer getType(Object value) {
        if (value instanceof String) return CacheConstant.CacheTypeEnum.CACHE_TYPE_STRING.getType();
        else if (value instanceof List) return CacheConstant.CacheTypeEnum.CACHE_TYPE_LIST.getType();
        else if (value instanceof Map) return CacheConstant.CacheTypeEnum.CACHE_TYPE_MAP.getType();
        else if (value instanceof Set) return CacheConstant.CacheTypeEnum.CACHE_TYPE_SET.getType();
        else if (value instanceof Number) return CacheConstant.CacheTypeEnum.CACHE_TYPE_NUMBER.getType();
        else if (value != null) return CacheConstant.CacheTypeEnum.CACHE_TYPE_OBJECT.getType();
        return CacheConstant.CacheTypeEnum.CACHE_TYPE_NULL.getType();
    }
}
