package com.jjcache.core.processor;

import com.jjcache.common.util.MathUtils;
import com.jjcache.core.conf.JjCacheConfig;
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

    /**
     * 默认浮动时间 毫秒
     */
    public static final Long DEFAULT_FLOAT_EXPIRE = 5000L;

    /**
     * 随机生成线程安全random
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * 实际过期时间
     * tip : 读取配置文件
     */
    public static Long dynamicFloatExpire;

    public StampedingCacheProcessor(JjCacheConfig cacheConfig) {
        initProcessor(cacheConfig);
    }

    @Override
    void initProcessor(JjCacheConfig cacheConfig) {
        // TODO 校验是否配置浮动时间，没有则默认
        dynamicFloatExpire = DEFAULT_FLOAT_EXPIRE;
    }

    /**
     * 浮动后时间不能小于当前时间，不然无效缓存
     * @param expire
     */
    @Override
    public long resolveStampeding(long expire) {
        Boolean check = limitCheck(expire);
        if (check) { // 需要浮动时间
            long doExpire = expire;
            long local = System.currentTimeMillis();
            long randomL = RANDOM.nextLong(-dynamicFloatExpire, dynamicFloatExpire);

            if (isAddWithIncrement()) {
                MathUtils.add(doExpire, randomL);
            } else {
                MathUtils.subtract(doExpire, randomL);
            }

            // 无效缓存校验
            return local < doExpire ? doExpire : expire;
        }
        return expire;
    }

    /**
     * 校验时间边界
     * 直接返回布尔解释:
     *  不可能有缓存时间这么长，除非地球毁灭，这么长的缓存还是别浮动了
     * @param expire
     * @return
     */
    public Boolean limitCheck(Long expire) {
        return Long.MAX_VALUE - expire > dynamicFloatExpire ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     *
     * @return TRUE 为增 , FALSE 为减
     */
    public Boolean isAddWithIncrement() {
        return getAndIncrement() % 2 == 0;
    }

    /**
     * 获取并自增
     * @return
     */
    public final int getAndIncrement() {
        int current, next;
        do {
            current = this.doCount.get();
            next = current >= Integer.MAX_VALUE ? 0  : current + 1;
        } while (!this.doCount.compareAndSet(current, next));
        return next;
    }


}
