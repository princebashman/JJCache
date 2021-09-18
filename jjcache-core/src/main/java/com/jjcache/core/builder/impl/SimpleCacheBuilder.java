package com.jjcache.core.builder.impl;

import com.jjcache.core.builder.CacheBulider;
import com.jjcache.core.model.Cache;
import com.jjcache.core.model.SimpleCache;

/**
 * 简单缓存构建器
 * @author jiangcx
 * @create 2021 - 07 - 08 - 15:13
 */
public class SimpleCacheBuilder implements CacheBulider {

    /**
     * 构建 空构造器的缓存 {@code SimpleCache}
     * @param <K>
     * @param <V>
     * @return
     */
    @Override
    public <K, V> Cache<K, V> buildCache() {
        return new SimpleCache<K, V>();
    }

    /**
     * 构建 拥有key值，但value值为null的缓存
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    @Override
    public <K, V> Cache<K, V> buildCache(K key) {
        return new SimpleCache<K, V>(key);
    }

    /**
     * 构建 拥有key值和value值的缓存
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    @Override
    public <K, V> Cache<K, V> buildCache(K key, V value) {
        return new SimpleCache<>(key, value);
    }

    /**
     * 构建 拥有key值和value值的缓存，并且缓存存在过期时间
     * @param key
     * @param value
     * @param expiretime
     * @param <K>
     * @param <V>
     * @return
     */
    @Override
    public <K, V> Cache<K, V> buildCache(K key, V value, long expiretime) {
        return new SimpleCache<>(key, value, expiretime);
    }

}
