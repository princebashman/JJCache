package com.jjcache.core.processor;

import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.listener.CacheListener;
import com.jjcache.core.model.Cache;
import com.jjcache.core.processor.pro.StampedingResolve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 缓存雪崩解决方案
 * @author jiangcx
 * @create 2021 - 09 - 09 - 16:40
 */
public class StampedingCacheProcessor extends AbstractCacheProcessor implements StampedingResolve {

    private final static Logger logger = LoggerFactory.getLogger(StampedingCacheProcessor.class);

    public static final Long DEFAULT_FLOAT_EXPIRE = 5000L; // 默认浮动时间 毫秒

    public static final Long MAX_LONG = Long.MAX_VALUE;

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static Long dynamicFloatExpire; // 读取配置文件

    public StampedingCacheProcessor(JjCacheConfig cacheConfig) {
        initProcessor(cacheConfig);
    }

    @Override
    void initProcessor(JjCacheConfig cacheConfig) {

    }

    /**
     * 浮动后时间不能小于当前时间，不然无效缓存
     * @param cache
     */
    @Override
    public void resolveStampeding(Cache cache) {
        long expire = cache.getExpireTime();
        Boolean check = limitCheck(expire);
        if (check) {
            long local = System.currentTimeMillis();

        }
    }

    /**
     * 校验时间边界
     * @param expire
     * @return
     */
    public Boolean limitCheck(Long expire) {
        Long difference = MAX_LONG - expire;
        if (difference > dynamicFloatExpire) { // 超出范围了
            logger.info("expire too long , difference is " + difference);
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }


}
