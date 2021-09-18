package com.jjcache.core.processor;

import com.jjcache.core.builder.CacheBulider;
import com.jjcache.core.builder.impl.SimpleCacheBuilder;
import com.jjcache.core.model.Cache;

/**
 * 缓存穿透解决策略 - 缓存空值
 * @author jiangcx
 * @create 2021 - 09 - 10 - 17:00
 */
public class PenetrationEmptyCacheStrategy extends PenetrationStrategy {

    public Long emptyCacheExpire;

    /**
     * 默认的空缓存过期时间
     */
    public static final Long DEFAULT_EMPTY_CACHE_EXPIRE = 3600000L;

    // TODO NULL 未初始化
    public CacheBulider cacheBulider;

    public PenetrationEmptyCacheStrategy() {
    //    TODO 初始化空缓存配置
        emptyCacheExpire = DEFAULT_EMPTY_CACHE_EXPIRE;
    }


    @Override
    public Cache resolvePentration(String key) {
        return cacheBulider.buildCache(key, null, emptyCacheExpire);
    }
}
