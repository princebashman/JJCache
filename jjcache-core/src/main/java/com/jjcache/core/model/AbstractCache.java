package com.jjcache.core.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 缓存抽象
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:36
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    public K cacheKey; // 缓存key
    public volatile V cacheValue; // 缓存value
    public Integer level; // 缓存级别

    public volatile long expireTime; // 缓存过期时间
    public volatile long LastUseTime; // 缓存最后使用时间

    public static final long DEFAULT_EXPIRE_TIME = -1;


    /**
     * 获取缓存key
     * @return
     */
    @Override
    public K getKey() {
        if (Objects.isNull(cacheKey)) {
            return null;
        }
        return cacheKey;
    }

    /**
     * 获取缓存value
     * @return
     */
    @Override
    public V getValue() {
        long now = System.currentTimeMillis();
        V value = doget();
        if (Objects.isNull(value)) {
            return null;
        }
        this.LastUseTime = now;
        return value;
    }

    /**
     * 获取缓存value
     * @return
     */
    protected abstract V doget();

    /**
     * 获取缓存类型
     * @return
     */
    @Override
    public abstract String getType();

    /**
     * 获取缓存级别
     * @return
     */
    @Override
    public Integer getLevel() {
        return this.level;
    }

    /**
     * 设置缓存级别
     * @param level
     */
    @Override
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取格式化过期时间
     * @return
     */
    @Override
    public LocalDateTime getExpirationTimeFormat() {
        return null;
    }

    /**
     * 获取过期时间
     * @return
     */
    @Override
    public long getExpireTime() {
        return this.expireTime;
    }

    /**
     * 获取缓存剩余时间
     * @return
     * 当缓存没有过期时间时，返回-1;
     * 当缓存已过期时，返回0；
     * 否则返回缓存剩余过期时间
     */
    @Override
    public long getRemainingExpireTime() {
        long now = System.currentTimeMillis(), expireTime = getExpireTime();
        if (expireTime == DEFAULT_EXPIRE_TIME) return expireTime;
        if (now >= expireTime) return 0;
        else return expireTime - now;
    }

    /**
     * 设置缓存过期时间
     * @param expireTime
     */
    @Override
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }


}
