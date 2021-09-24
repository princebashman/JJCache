package com.jjcache.core.processor;

import com.jjcache.core.model.Cache;

/**
 * @author jiangcx
 * @create 2021 - 09 - 24 - 10:30
 */
public class PenetrationBloomFilterCacheStrategy extends PenetrationStrategy {

    @Override
    public Cache resolvePentration(String key) {
        return null;
    }

    @Override
    void checkInit() {

    }
}
