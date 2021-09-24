package com.jjcache.core.processor;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.model.Cache;

/**
 * 缓存穿透解决策略 - 抽象类
 * @author jiangcx
 * @create 2021 - 09 - 10 - 16:55
 */
public abstract class PenetrationStrategy {

    /**
     * 缓存解决方案 【钩子】
     */

    public abstract Cache resolvePentration(String key);

    void checkInit(String name) {
        throw new CacheConfigExcepiton("The " + name + " is not configured.");
    }

    abstract void checkInit();

}
