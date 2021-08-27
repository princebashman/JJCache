package com.jjcache.core.builder.impl;

import com.jjcache.core.builder.CacheServiceProviderBuilder;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.listener.CacheListener;
import com.jjcache.core.provider.CacheServiceProvider;
import com.jjcache.core.provider.impl.Level1_CacheServiceProvider;

/**
 * @author jiangcx
 * @create 2021 - 07 - 08 - 10:41
 */
public class SimpleCacheServiceProviderBuilder implements CacheServiceProviderBuilder {

    @Override
    public CacheServiceProvider bulidLevelOneProvider(JjCacheConfig cacheConfig) {
        Level1_CacheServiceProvider serviceProvider = new Level1_CacheServiceProvider(cacheConfig);
        serviceProvider.addCacheListener(new CacheListener(cacheConfig));
        return serviceProvider;
    }

    @Override
    public CacheServiceProvider bulidLevelTwoProvider(JjCacheConfig cacheConfig) {
        return null; // TODO 二级缓存暂定
    }
}
