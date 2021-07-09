package com.jjcache.core.builder;

import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.provider.CacheServiceProvider;

/**
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:57
 */
public interface CacheServiceProviderBuilder {

    /**
     * 构建一级缓存服务提供类
     * @return
     */
    CacheServiceProvider bulidLevelOneProvider(JjCacheConfig cacheConfig);

    /**
     * 构建二级缓存提供类
     * @return
     */
    CacheServiceProvider bulidLevelTwoProvider(JjCacheConfig cacheConfig);
}
