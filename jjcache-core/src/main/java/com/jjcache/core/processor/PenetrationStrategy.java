package com.jjcache.core.processor;

import com.jjcache.core.model.Cache;

/**
 * 缓存穿透解决策略 - 抽象类
 * @author jiangcx
 * @create 2021 - 09 - 10 - 16:55
 */
public abstract class PenetrationStrategy {

    public abstract void resolvePentration(Cache cache);

}
