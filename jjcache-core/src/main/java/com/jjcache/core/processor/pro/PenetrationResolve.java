package com.jjcache.core.processor.pro;

import com.jjcache.core.model.Cache;
import com.jjcache.core.processor.PenetrationStrategy;

/**
 * 缓存穿透解决方案
 * @author jiangcx
 * @create 2021 - 09 - 10 - 16:05
 */
public interface PenetrationResolve {

    /**
     * 获取根据枚举 {@code PenetrationConstant} 获取对应的解决方案.
     * @param strategyName
     * @return
     */
    public PenetrationStrategy getStrategy(String strategyName);

    //public void resolvePenetration(Cache cache);

}
