package com.jjcache.core.model;


import com.jjcache.common.constant.CacheConstant;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
        this(key, null);
    }

    public SimpleCache(K key, V value) {
        this(key, value, DEFAULT_EXPIRE_TIME);
    }

    public SimpleCache(K key, V value, long expiretime) {
        this.cacheKey = key;
        this.cacheValue = value;
        this.LastUseTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.expireTime = (expiretime <= 0) ? DEFAULT_EXPIRE_TIME : (System.currentTimeMillis() + expiretime);
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
