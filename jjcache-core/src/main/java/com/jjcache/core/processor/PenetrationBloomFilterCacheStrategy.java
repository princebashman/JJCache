package com.jjcache.core.processor;

import com.google.common.hash.BloomFilter;
import com.jjcache.core.model.Cache;

/**
 * @author jiangcx
 * @create 2021 - 09 - 24 - 10:30
 */
public class PenetrationBloomFilterCacheStrategy extends PenetrationStrategy {

    public PenetrationBloomFilterCacheStrategy() {
        init();
    }

    private void init() {
        initBloomFilter();
    }

    private void initBloomFilter() {

    }

    @Override
    public Cache resolvePentration(String key) {
        return null;
    }

    @Override
    void checkInit() {

    }
}
