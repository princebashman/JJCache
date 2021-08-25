package com.jjcache.core.builder;

import com.jjcache.core.CacheClient;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.holder.CacheServiceProviderHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 初始化
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 10:55
 */
public class JjCacheBuilder {

    private final static Logger logger = LoggerFactory.getLogger(JjCacheBuilder.class);

    private CacheServiceProviderHolder cacheServiceProviderHolder;
    private JjCacheConfig jjCacheConfig;
    private volatile CacheClient cacheClient;

    /**
     * JjCache客户端工厂 构造器
     * 做初始化
     * @param cacheConfig
     */
    public JjCacheBuilder(JjCacheConfig cacheConfig) {
        initCacheServiceProviderHolder();
        this.jjCacheConfig = cacheConfig;
    }


    public static JjCacheBuilder init(JjCacheConfig cacheConfig) {
        return new JjCacheBuilder(cacheConfig);
    }

    public void initCacheServiceProviderHolder() {
        if (cacheServiceProviderHolder == null) {
            synchronized (JjCacheBuilder.class) {
                CacheServiceProviderHolder cacheServiceProviderHolder = new CacheServiceProviderHolder();
                this.cacheServiceProviderHolder = cacheServiceProviderHolder;
            }
        }
    }

    /**
     * if entity is null, init CacheServiceProviderHolder,else return cacheServiceProviderHolder.
     * @return cacheServiceProviderHolder
     */
    private CacheServiceProviderHolder getCacheServiceProviderHolder() {

        return cacheServiceProviderHolder;
    }


    /**
     * 返回缓存操作客户端对象 && 初始化
     * @return
     */
    public CacheClient getClient() {

        if (Objects.isNull(cacheClient)) {
            synchronized (JjCacheBuilder.class) {
                if (Objects.isNull(cacheClient)) {
                    cacheClient = new CacheClient(cacheServiceProviderHolder, jjCacheConfig);
                }
            }
        }

        return cacheClient;
    }
}
