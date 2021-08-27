package com.jjcache.core;

import com.jjcache.core.builder.CacheBulider;
import com.jjcache.core.builder.impl.SimpleCacheBuilder;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.holder.CacheServiceProviderHolder;
import com.jjcache.core.model.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 缓存客户端 - 缓存操作入口
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 10:49
 */
public class CacheClient {

    private final static Logger logger = LoggerFactory.getLogger(CacheClient.class);

    /**
     * 给对应key值加锁，防止在缓存失效时，多个线程访问缓存穿透至数据库，
     * 在生成缓存时，所有进程CAS操作，等待缓存生成之后，统一返回缓存
     */
    private Map<String, Object> keyLocks =new ConcurrentHashMap<>();

    private CacheServiceProviderHolder cacheServiceProviderHolder;
    private JjCacheConfig cacheConfig;
    private CacheBulider cacheBulider;

    private Boolean cacheEmptyValue;
    private Boolean openLevel2;

    public CacheClient(CacheServiceProviderHolder cacheServiceProviderHolder,JjCacheConfig cacheConfig) {
        init(cacheServiceProviderHolder, cacheConfig);
    }

    /**
     * 缓存客户端初始化
     * @param cacheServiceProviderHolder
     * @param cacheConfig
     */
    private void init(CacheServiceProviderHolder cacheServiceProviderHolder,JjCacheConfig cacheConfig) {
        this.cacheServiceProviderHolder = cacheServiceProviderHolder;
        this.cacheConfig = cacheConfig;
        this.cacheBulider = new SimpleCacheBuilder();

        this.cacheEmptyValue = cacheConfig.getCacheEmptyValue();
        this.openLevel2 = cacheConfig.getOpenLevel2();
    }


    /**
     * 获取缓存值，如果输入的key为空则返回null
     *
     * @param key 缓存键
     * @param cacheEmptyValue 是否缓存空值
     * @param <V> 值的泛型
     * @return
     */
    public <V>Cache get(String key, boolean cacheEmptyValue) {

        checkKey(key);

        // Get from first cache.
        Cache<String, V> cache = getLevel1Cache(key);

        if (Objects.isNull(cache)) {
            // lock.
            synchronized (keyLocks.computeIfAbsent(key, v -> new Object())) {
                // Get again.
                cache = getLevel1Cache(key);
                if (Objects.isNull(cache)) {
                    // TODO Get from second cache.
                    // y/n cache empty value.
                    if (cacheEmptyValue) {
                        Cache<String, V> emptyCache = cacheBulider.buildCache(key);
                        set(key, emptyCache);
                        cache = emptyCache;
                    }
                }
                // unlock.
                keyLocks.remove(key);
            }
        }
        return cache;
    }

    /**
     * 获取缓存值，如果输入的key为空则返回null
     * @param key
     * @return
     */
    public Cache get(String key) {
        return get(key, cacheEmptyValue);
    }

    private  <V>Cache set(String key, Cache cache) {
        Cache oldCache = getLevel1Cache(key);
        setLevel1Cache(cache);
        return oldCache;
    }

    public <V>Cache set(String key, V value) {
        return set(key, value, 0);
    }

    public <V>Cache set(String key, V value, long expiretime) {
        return set(key, value, expiretime, TimeUnit.MILLISECONDS);
    }

    /**
     * 按照指定时间单位存储过期缓存
     * @return
     */
    public <V>Cache set(String key, V value, long expiretime, TimeUnit timeUnit) {
        checkKey(key);

        if (!TimeUnit.MILLISECONDS.equals(timeUnit)) {
            expiretime = timeUnit.toMillis(expiretime);
        }

        Cache<String, V> oldCache = getLevel1Cache(key);
        Cache<String, V> cache = cacheBulider.buildCache(key, value, expiretime);
        setLevel1Cache(cache);
        // TODO 设置二级缓存
        return oldCache;
    }

    /**
     * 获取一级缓存
     * @param key
     * @return
     */
    private Cache getLevel1Cache(String key) {
        return cacheServiceProviderHolder.getLevel1Provider().getCache(key);
    }

    /**
     * 设置一级缓存
     * @param cache
     * @param <K>
     * @param <V>
     */
    private <K, V> void setLevel1Cache(Cache<K, V> cache) {
        cacheServiceProviderHolder.getLevel1Provider().setCache(cache);
    }

    /**
     * 检查缓存键
     * @param key
     */
    private void checkKey(Object key) {
        if (Objects.isNull(key)) throw new NullPointerException("disable caching empty key");
    }

}
