package com.jjcache.core.processor;

import com.jjcache.common.exception.InitProcessorExcepiton;
import com.jjcache.core.conf.JjCacheConfig;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决缓存问题处理器 - 抽象类
 * @author jiangcx
 * @create 2021 - 09 - 09 - 16:37
 */
public abstract class AbstractCacheProcessor {

    public String cacheProcessor;

    /**
     * 进行操作计数使用，为了上下浮动而设计
     */
    public AtomicInteger doCount = new AtomicInteger(0);

    /**
     * 初始化 【钩子】
     * @param cacheConfig
     */
    void initProcessor(JjCacheConfig cacheConfig) {
        throw new InitProcessorExcepiton("Failed to initialize processor.");
    }


}
