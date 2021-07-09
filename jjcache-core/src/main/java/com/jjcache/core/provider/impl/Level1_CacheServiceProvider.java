package com.jjcache.core.provider.impl;

import com.jjcache.common.constant.CacheConstant;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.model.Cache;
import com.jjcache.core.provider.CacheServiceProvider;

import java.util.*;

/**
 * 一级缓存服务提供类
 *
 * @author jiangcx
 * @create 2021 - 07 - 07 - 17:17
 */
public class Level1_CacheServiceProvider extends CacheServiceProvider {

    private static final Integer level = CacheConstant.CACHE_LEVEL_ONE; // 缓存服务提供类 - 一级缓存
    private JjCacheConfig jjCacheConfig;

    private final Map<Object, Cache> l1CacheMap;

    /**
     * 缓存服务提供者 构造器
     * 初始化
     * @param cacheConfig
     */
    public Level1_CacheServiceProvider(JjCacheConfig cacheConfig) {
        l1CacheMap = new HashMap<>(64);
        init(cacheConfig);
    }

    /**
     * 获取缓存级别
     * @return
     */
    @Override
    public Integer getLevel() {
        return level;
    }

    /**
     * 缓存服务提供类 - 初始化
     */
    @Override
    public void init(JjCacheConfig cacheConfig) {
        this.jjCacheConfig = cacheConfig;
    }


    @Override
    public Object getValue(String key) {
        Cache cache = getCache(key);
        if (Objects.isNull(cache)) {
            return null;
        }
        return cache.getValue();
    }

    @Override
    public <K, V> void setCache(Cache<K, V> cache) {
        cache.setLevel(level);
        Object value = cache.getValue();
        cache.setType(getType(value));
        l1CacheMap.put(cache.getKey(), cache);
    }

    @Override
    public Cache getCache(String key) {
        Cache cache = l1CacheMap.get(key);
        if (Objects.isNull(cache)) {
            return null;
        } else {
            return cache;
        }
    }


}
