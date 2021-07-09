package com.jjcache.core;

import com.jjcache.core.model.Cache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JjcacheCoreApplicationTests {

    @Test
    void contextLoads() {
        CacheClient client = Jjcache.getClient();
        client.set("小刚", "小刚");
        Cache<String, String> cache = client.get("小刚");
        System.out.println(cache.getValue());
        client.set("小刚", "小强");
        cache = client.get("小刚");
        System.out.println(cache.getValue());
        Cache<String, Object> cacheDemo = client.get("小智");
        System.out.println(cacheDemo);
        System.out.println(cacheDemo.getValue());
        System.out.println(cacheDemo.getLevel());
        System.out.println(cacheDemo.getType());
        Cache<String, Object> cacheDemo2 = client.get("小智");
        System.out.println(cacheDemo == cacheDemo2);



    }

}
