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
    public static final Integer strategiesCount = PenetrationConstant.PenetrationEnum.getCount(); // init count.;

    /**
     * 策略字典 : 缓存空值、简单过滤校验、布隆过滤器
     */
    private static final Map<String, PenetrationStrategy> strategies  = new HashMap<>(strategiesCount); // init map.


    public PenetrationCacheProcessor(JjCacheConfig cacheConfig) {
        initProcessor(cacheConfig);
    }

    @Override
    void initProcessor(JjCacheConfig cacheConfig) {
        // 缓存空值
        strategies.put(PenetrationConstant.PenetrationEnum.EMPTY_VALUE.getName(), new PenetrationEmptyCacheStrategy());
        // 过滤校验
        // 布隆过滤器
        // TODO 从配置中获取策略
    }

    /**
     * 获取对应的缓存解决方案策略执行器
     * @param strategyName
     * @return
     */
    @Override
    public PenetrationStrategy getStrategy(String strategyName) {
        return strategies.get(strategyName);
    }
}
