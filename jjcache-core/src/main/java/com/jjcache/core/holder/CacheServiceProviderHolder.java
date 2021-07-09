package com.jjcache.core.holder;

import com.jjcache.core.builder.CacheServiceProviderBuilder;
import com.jjcache.core.builder.impl.SimpleCacheServiceProviderBuilder;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.provider.CacheServiceProvider;
import com.jjcache.core.provider.impl.Level1_CacheServiceProvider;

import java.util.Objects;

/**
 * @author jiangcx
 * @create 2021 - 07 - 07 - 17:10
 */
public class CacheServiceProviderHolder {

    CacheServiceProviderBuilder cacheServiceProviderBuilder;
    CacheServiceProvider level1_cacheServiceProvider;
    CacheServiceProvider level2_cacheServiceProvider;
    JjCacheConfig cacheConfig;

    /**
     * 缓存服务提供管理者构造器
     * 初始化
     */
    public CacheServiceProviderHolder() {
        cacheServiceProviderBuilder = new SimpleCacheServiceProviderBuilder();
        initCacheServiceProvider();
    }

    public void initCacheServiceProvider() {
        if (Objects.isNull(level1_cacheServiceProvider)) {
            synchronized (this) {
                level1_cacheServiceProvider = cacheServiceProviderBuilder.bulidLevelOneProvider(cacheConfig);
            }
        }
    }

    /**
     *
     * @return
     */
    public CacheServiceProvider getLevel1Provider() {

        return level1_cacheServiceProvider;
    }

}
