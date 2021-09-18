package com.jjcache.core.processor;

import com.jjcache.core.conf.JjCacheConfig;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiangcx
 * @create 2021 - 09 - 09 - 16:37
 */
public abstract class AbstractCacheProcessor {

    public String cacheProcessor;

    /**
     * 进行操作计数使用，为了上下浮动而设计
     */
    public AtomicInteger doCount = new AtomicInteger(0);

    abstract void initProcessor(JjCacheConfig cacheConfig);


}
