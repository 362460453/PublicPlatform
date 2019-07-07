package com.bloom.client;

import com.config.BloomFilterHelper;
import com.google.common.hash.Funnels;
import com.redisbloom.RedisBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * @author yanlin
 * @version v1.0
 * @className Application
 * @description TODO
 * @date 2019-07-07 1:49 PM
 **/
@SpringBootApplication
@ComponentScan(value = {"com.annotaion", "com.bloom", "com.config", "com.redisbloom"})

public class Application implements ApplicationRunner {
    private static BloomFilterHelper<CharSequence> bloomFilterHelper;
    @Autowired
    RedisBloomFilter redisBloomFilter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @PostConstruct
    public void init() {
        bloomFilterHelper = new BloomFilterHelper<>(Funnels.stringFunnel(Charset.defaultCharset()), 1000, 0.1);
    }

    public void run(ApplicationArguments args) throws Exception {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            redisBloomFilter.addByBloomFilter(bloomFilterHelper, "bloom", i + "");
        }
        for (int i = 0; i < 1000; i++) {
            boolean result = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, "bloom", i + "");
            if (!result) {
                j++;
            }
        }
        System.out.println("漏掉了" + j + "个");
    }
}
