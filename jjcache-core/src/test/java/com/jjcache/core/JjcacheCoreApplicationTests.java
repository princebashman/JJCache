package com.jjcache.core;

import com.jjcache.core.model.Cache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JjcacheCoreApplicationTests {

    @Test
    void contextLoads() {
        CacheClient client = Jjcache.getClient();

        for (int i = 0; i < 100000; i++) {

            client.set(i + "",i, 4000);
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println(client.get(i + "").getValue());
        }



    }

}
