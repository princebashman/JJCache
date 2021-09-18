package com.jjcache.core.processor.pro;


/**
 * 缓存雪崩解决方案
 * @author jiangcx
 * @create 2021 - 09 - 09 - 16:43
 */
public interface StampedingResolve {

    public long resolveStampeding(long expire);

}
