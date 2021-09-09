package com.jjcache.core.listener;

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


    private JjCacheConfig cacheConfig;
    private volatile AtomicBoolean do_delete_exit = new AtomicBoolean(false); // 是否正在执行
    // TODO 属性从配置中取 否则为默认值
    private Integer poolSize = 5;
    private Long do_last_time; // 最后执行的时间
    private Long do_cycle_delete_expire_time = 1000L; // 默认执行删除时间

    private Map<Long, Cache> expireMap;
    private Map<Object, Cache> cacheMap;

    private ScheduledExecutorService scheduledThreadPool;

    public CacheListener(JjCacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
        initScheduledThreadPool(); // 初始化线程池
        putCycleTask(); // 添加循环任务
    }

    public void initExpireMap(Map<Long, Cache> expireMap) {
        this.expireMap = expireMap;
    }

    public void initCacheMap(Map<Object, Cache> cacheMap) {
        this.cacheMap = cacheMap;
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
        scheduledThreadPool.scheduleWithFixedDelay(deleteExpireTask,do_cycle_delete_expire_time, do_cycle_delete_expire_time, TimeUnit.MILLISECONDS);
    }

    /**
     * 弹性垃圾回收策略
     *  1. 流量稳定的情况下
     *      每次缓存清理数量 / 缓存总数量 <= 阈值 时，垃圾回收时间可以延缓一定的时间，小于最大时间
     *      (说明这段时间的缓存数量)
     *      每次缓存清理数量 / 缓存总数量 > 阈值 时，垃圾回收时间提前一定的时间，大于最小时间
     */

    /**
     * 1.采用redis的抽查机制
     * 2.弹性垃圾回收
     * 删除过期缓存
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
                    cacheMap.remove(entry.getValue().getKey());
                    expireCount++;
                } else {
                    break;
                }
            }

            do_delete_exit.compareAndSet(true, false);
            do_last_time = System.currentTimeMillis();
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
