package com.jjcache.core.model;


import com.jjcache.common.constant.CacheConstant;

/**
 * 通用缓存对象
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:51
 */
public class SimpleCache<K, V> extends AbstractCache<K, V> {

    private Integer type;

    public SimpleCache() {
        this(null);
    }

    public SimpleCache(K key) {
        this.cacheKey = key;
    }

    @Override
    public V getValue() {
        return cacheValue;
    }

    @Override
    public void setValue(V value) {
        this.cacheValue = value;
    }

    @Override
    public String getType() {
        return CacheConstant.CacheTypeEnum.getType(type);
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected V doget() {
        return this.cacheValue;
    }

}
