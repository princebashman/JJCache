package com.jjcache.core.processor;

import com.jjcache.core.conf.JjCacheConfig;

/**
 * @author jiangcx
 * @create 2021 - 09 - 09 - 16:37
 */
public abstract class AbstractCacheProcessor {

    abstract void initProcessor(JjCacheConfig cacheConfig);

}
