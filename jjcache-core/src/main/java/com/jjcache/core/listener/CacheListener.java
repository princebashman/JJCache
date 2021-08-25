package com.jjcache.core.listener;


import com.jjcache.core.builder.JjCacheBuilder;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.model.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 缓存监听器
 *
 * @author jiangcx
 * @create 2021 - 07 - 13 - 10:47
 */
public class CacheListener {

    private final static Logger logger = LoggerFactory.getLogger(CacheListener.class);


    private JjCacheConfig cacheConfig; // TODO 未初始化
    private volatile AtomicBoolean do_delete_exit = new AtomicBoolean(false); // 是否正在执行
    // TODO 属性从配置中取 否则为默认值
    private Integer poolSize = 5;
    private Long do_last_time; // 最后执行的时间
    private Long do_cycle_delete_expire_time = 5000L; // 默认执行删除时间

    private Map expireMap; // TODO 未初始化

    private ScheduledExecutorService scheduledThreadPool;

    public CacheListener() {
        initScheduledThreadPool(); // 初始化线程池
        putCycleTask(); // 添加循环任务
    }

    /**
     * 初始化定时任务线程池
     */
    private void initScheduledThreadPool() {
        scheduledThreadPool = Executors.newScheduledThreadPool(poolSize);
    }

    /**
     * 添加定时任务
     */
    private void putCycleTask() {
        putCycleDeleteExpireTask();
    }

    private void putCycleDeleteExpireTask() {
        Runnable deleteExpireTask = () -> cycleCheckExpire(expireMap);
        scheduledThreadPool.schedule(deleteExpireTask, do_cycle_delete_expire_time, TimeUnit.MILLISECONDS);
    }

    /**
     * 定期删除过期缓存
     * @param expireMap 过期缓存集合
     */
    public void cycleCheckExpire(Map<Long, Cache> expireMap) {
        if (isDoDelete()) return;
        if (do_delete_exit.compareAndSet(false, true)) {
            Integer expireCount = 0;
            Iterator<Map.Entry<Long, Cache>> iterator = expireMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Cache> entry = iterator.next();
                if (System.currentTimeMillis() > entry.getKey()) {
                    iterator.remove();
                    expireCount++;
                    // TODO l1CacheMap 删除缓存
                } else {
                    if (do_delete_exit.compareAndSet(true, false)) {
                        break;
                    }
                }
            }
            logger.info("clean expire cache, with " + expireCount + " counts");
        }
    }

    /**
     * 是否正在执行缓存删除
     * @return
     */
    private boolean isDoDelete() {
        return do_delete_exit.get();
    }


}
