package com.jjcache.core.builder.impl;

import com.jjcache.core.builder.CacheBulider;
import com.jjcache.core.model.Cache;
import com.jjcache.core.model.SimpleCache;

/**
 * @author jiangcx
 * @create 2021 - 07 - 08 - 15:13
 */
public class SimpleCacheBuilder implements CacheBulider {

    @Override
    public <K, V> Cache<K, V> buildCache() {
        return new SimpleCache<K, V>();
    }

    @Override
    public <K, V> Cache<K, V> buildCache(K key) {
        return new SimpleCache<K, V>(key);
    }
}
