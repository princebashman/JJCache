package com.jjcache.core.builder;

import com.jjcache.core.model.Cache;

/**
 * 缓存构建接口
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:53
 */
public interface CacheBulider {

    /**
     * 空构造器
     * 拥有key
     * 拥有key && value
     * 拥有key && value && expiretime
     */

    <K, V> Cache<K, V> buildCache();

    <K, V> Cache<K, V> buildCache(K key);

    <K, V> Cache<K, V> buildCache(K key, V value);

    <K, V> Cache<K, V> buildCache(K key, V value, long expiretime);

}
