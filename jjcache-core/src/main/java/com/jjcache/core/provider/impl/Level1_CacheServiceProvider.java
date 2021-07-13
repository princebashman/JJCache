package com.jjcache.core.provider.impl;

import com.jjcache.common.constant.CacheConstant;
import com.jjcache.common.exception.CacheExcepiton;
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

    /**
     * 获取缓存值
     * @param key 缓存键
     * @return
     */
    @Override
    public Object getValue(String key) {
        Cache cache = getCache(key);
        if (Objects.isNull(cache)) {
            return null;
        }
        return cache.getValue();
    }

    /**
     * 设置缓存对象
     * @param cache
     * @param <K>
     * @param <V>
     */
    @Override
    public <K, V> void setCache(Cache<K, V> cache) {
        cache.setLevel(level);
        Object value = cache.getValue();
        cache.setType(getType(value));
        l1CacheMap.put(cache.getKey(), cache);
    }

    /**
     * 获取缓存对象
     * @param key 缓存键
     * @return
     */
    @Override
    public Cache getCache(String key) {
        Cache cache = l1CacheMap.get(key);
        if (checkExpire(cache)) {
            l1CacheMap.remove(key); // TODO 惰性删除缓存
            cache = null;
        }
        return Objects.isNull(cache) ? null : cache;
    }

    @Override
    protected boolean deleteCache(Cache cache) {
        return l1CacheMap.remove(cache.getKey()) == null ? false : true;
    }

    @Override
    protected boolean deleteCache(String key) {
        return l1CacheMap.remove(key) == null ? false : true;
    }

    /**
     * 校验一级缓存的剩余缓存时间
     * @param cache
     * @param <K>
     * @param <V>
     * @return 为true时，缓存已过期，为false时，缓存未过期
     */
    @Override
    protected <K, V> boolean checkExpire(Cache<K, V> cache) {
        if (Objects.isNull(cache)) throw new CacheExcepiton("the incoming cache to check is empty");
        boolean flag = cache.getLevel() == 1;
        if (flag) flag = cache.getRemainingExpireTime() == 0;
        return flag;
    }


}
