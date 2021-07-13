package com.jjcache.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:04
 */
public interface Cache<K, V> extends Cloneable, Serializable {

    /**
     * 获取缓存键
     * @return
     */
    public K getKey();

    /**
     * 获取缓存值
     * @return
     */
    public V getValue();

    /**
     * 设置缓存值
     * @param value
     */
    public void setValue(V value);


    /**
     * 获取缓存类型
     * @return
     */
    public String getType();

    /**
     * 设置缓存类型
     * @param type
     */
    public void setType(Integer type);

    /**
     * 获取缓存级别
     * @return
     */
    public Integer getLevel();

    /**
     * 设置缓存级别
     * @param level
     */
    public void setLevel(Integer level);

    /**
     * 获取格式化过期时间
     * @return
     */
    public LocalDateTime getExpirationTimeFormat();

    /**
     * 获取过期时间
     * @return
     */
    public long getExpireTime();

    /**
     * 设置过期时间
     */
    public void setExpireTime(long expireTime);

    /**
     * 获取剩余过期时间
     * @return
     */
    public long getRemainingExpireTime();


}
