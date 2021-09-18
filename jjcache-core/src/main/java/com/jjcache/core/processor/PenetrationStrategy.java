package com.jjcache.core.processor;

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

    public Cache resolvePentration(String key) {
        return null;
    }

}
