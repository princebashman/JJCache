package com.jjcache.common.exception;

/**
 * 缓存通用异常
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 9:13
 */
public class CacheExcepiton extends RuntimeException {

    public CacheExcepiton() {
        super();
    }

    public CacheExcepiton(String s) {
        super(s);
    }

    public CacheExcepiton(String s, Throwable e) {
        super(s, e);
    }

    public CacheExcepiton(Throwable e) {
        super(e);
    }

    public static CacheExcepiton buildCacheExcepiton(String s) {
        return new CacheExcepiton(s);
    }

}
