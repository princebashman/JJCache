package com.jjcache.core.processor;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.builder.CacheBulider;
import com.jjcache.core.builder.impl.SimpleCacheBuilder;
import com.jjcache.core.model.Cache;

import java.util.Objects;

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

    public CacheBulider cacheBulider;

    public PenetrationEmptyCacheStrategy(CacheBulider cacheBulider) {
        this.cacheBulider = cacheBulider;
    //    TODO 初始化空缓存配置
        emptyCacheExpire = DEFAULT_EMPTY_CACHE_EXPIRE;
    }

    /**
     * 缓存解决方案执行方法
     * @param key
     * @return
     */
    @Override
    public Cache resolvePentration(String key) {
        checkInit();
        return cacheBulider.buildCache(key, null, emptyCacheExpire);
    }

    /**
     * 检查是否初始化
     */
    @Override
    void checkInit() {
        if (Objects.isNull(cacheBulider)) {
            checkInit(PenetrationEmptyCacheStrategy.class.getName());
        }
    }

}
