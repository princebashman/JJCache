package com.jjcache.core.builder;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.builder.impl.SimpleCacheBuilder;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.processor.PenetrationCacheProcessor;
import com.jjcache.core.processor.PenetrationStrategy;
import com.jjcache.core.processor.StampedingCacheProcessor;

import java.util.Objects;

/**
 * @author jiangcx
 * @create 2021 - 09 - 24 - 9:59
 */
public class ProcessorBuilder {

    private JjCacheConfig cacheConfig;

    private CacheBulider cacheBulider;

    /**
     * 缓存雪崩 解决方案
     */
    private volatile StampedingCacheProcessor stampedingCacheProcessor;

    /**
     * 缓存穿透 解决方案
     */
    private PenetrationCacheProcessor penetrationCacheProcessor;

    /**
     * 构造 cacheConf
     * @param cacheConfig
     */
    public ProcessorBuilder(JjCacheConfig cacheConfig){
        this.cacheConfig = cacheConfig;
        this.cacheBulider = new SimpleCacheBuilder(); // TODO 待定
    }

    /**
     * 构造 cacheConf & cacheBuilder
     * @param cacheConfig
     * @param cacheBulider
     */
    public ProcessorBuilder(JjCacheConfig cacheConfig, CacheBulider cacheBulider){
        this.cacheConfig = cacheConfig;
        this.cacheBulider = cacheBulider;
    }

    /**
     * 单例获取 缓存雪崩 解决方案
     * @return
     */
    public StampedingCacheProcessor getStampedingCacheProcessor() {
        if (cacheConfig.getStampeding()) {
            if (Objects.isNull(stampedingCacheProcessor)) {
                synchronized (StampedingCacheProcessor.class) {
                    stampedingCacheProcessor = new StampedingCacheProcessor(cacheConfig);
                }
            }
            return stampedingCacheProcessor;
        }
        throw new CacheConfigExcepiton("If you want to use this processor, turn it on in properties.");
    }

    /**
     * 单例获取 缓存穿透 解决方案
     * @return
     */
    public PenetrationCacheProcessor getPenetrationCacheProcessor() {
        if (cacheConfig.getPenetration()) {
            if (Objects.isNull(penetrationCacheProcessor)) {
                synchronized (PenetrationCacheProcessor.class) {
                    penetrationCacheProcessor = new PenetrationCacheProcessor(cacheConfig, cacheBulider);
                }
            }
            return penetrationCacheProcessor;
        }
        throw new CacheConfigExcepiton("If you want to use this processor, turn it on in properties.");
    }

}
