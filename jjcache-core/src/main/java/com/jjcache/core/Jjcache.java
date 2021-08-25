package com.jjcache.core;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.builder.JjCacheBuilder;
import com.jjcache.core.conf.JjCacheConfig;

import java.io.IOException;

/**
 * Jjcache 缓存入口
 *
 * @author jiangcx
 * @create 2021 - 07 - 08 - 10:53
 */
public class Jjcache {

    private final static String CONFIG_FILE = "/Jjcache.properties";

    private final static JjCacheConfig cacheConfig;
    private final static JjCacheBuilder builder;


    static {
        try {
            cacheConfig = JjCacheConfig.initConfig(CONFIG_FILE);
            builder = JjCacheBuilder.init(cacheConfig);
        } catch (IOException e) {
            throw new CacheConfigExcepiton("Failed to load jjcache configuration \"" + CONFIG_FILE + "\"", e);
        }
    }

    /**
     * 获取 JjcacheClient
     * @return JjcacheClient
     */
    public static CacheClient getClient() {
        return builder.getClient();
    }

}
