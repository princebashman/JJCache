package com.jjcache.core.processor;

import com.jjcache.common.constant.PenetrationConstant;
import com.jjcache.core.conf.JjCacheConfig;
import com.jjcache.core.model.Cache;
import com.jjcache.core.processor.pro.PenetrationResolve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存穿透解决方案
 *
 * 1. 过滤校验
 * 2. 缓存空值
 * 3. 布隆过滤器
 *
 * @author jiangcx
 * @create 2021 - 09 - 10 - 16:05
 */
public class PenetrationCacheProcessor extends AbstractCacheProcessor implements PenetrationResolve {

    private final static Logger logger = LoggerFactory.getLogger(PenetrationCacheProcessor.class);

    /**
     * 策略数量
     */
    public static final Integer strategiesCount;

    /**
     * 策略字典 : 缓存空值、简单过滤校验、布隆过滤器
     */
    private static final Map<String, PenetrationStrategy> strategies;

    /**
     * 初始化解决策略
     */
    static {
        strategiesCount = PenetrationConstant.PenetrationEnum.getCount(); // init count.
        strategies = new HashMap<>(strategiesCount); // init map.
        // 缓存空值
        strategies.put(PenetrationConstant.PenetrationEnum.EMPTY_VALUE.getName(), new PenetrationEmptyCacheStrategy());
        // 过滤校验
        // 布隆过滤器
    }

    public PenetrationCacheProcessor(JjCacheConfig cacheConfig) {
        initProcessor(cacheConfig);
    }

    @Override
    void initProcessor(JjCacheConfig cacheConfig) {
        // TODO 从配置中获取策略
    }

    @Override
    public PenetrationStrategy getStrategy(String strategyName) {
        return strategies.get(strategyName);
    }

    @Override
    public void resolvePenetration(Cache cache) {

    }
}
