package com.jjcache.core;

/*
                                                      JjcacheBuilder
                                                            | build
                                                            v
                                                      JjCacheClient
                                                           | doCache
                                                           v
                                ---------------------------------------------------------------
                               |
                              | set
                              v
                      CacheServiceProviderHolder
                      -----------------------
                     |                       |
                    | l1_Cache               |
                    v                        v
        level1_CacheServiceProvider    level2_xx


1. 多级缓存策略
    默认是一级缓存，开启二级缓存后，可以通过接入redis实现二级缓存

2. 缓存的备份策略，提供rdb功能和aof功能

3. 缓存框架重启的过程中，写入备份的缓存文件做初始化

4. 【分布式】可以通过RMI、可插入API等方式进行分布式缓存

5. 具有缓存和缓存管理器的监听接口
    CacheManager && CacheListener
    (缓存的生命周期、缓存的增加删除、缓存的失效策略、【内存过大时的删除策略】)

    缓存的失效策略 :
        1. 定期删除
        2. 惰性删除

6. 注解支持


 */
