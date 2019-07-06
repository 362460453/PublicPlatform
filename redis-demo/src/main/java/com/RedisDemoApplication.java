package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-05-15 10:13 AM
 * @since v8.0
 **/
@SpringBootApplication
public class RedisDemoApplication implements ApplicationRunner {
    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Bean
    ReactiveStringRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveStringRedisTemplate(factory);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//        Mono mono1 = hashOps.put("apple", "x", "6000");
//        mono1.subscribe(System.out::println);
//        Mono mono2 = hashOps.put("apple", "xr", "5000");
//        mono2.subscribe(System.out::println);
//        Mono mono3 = hashOps.put("apple", "xs max", "8000");
//        mono3.subscribe(System.out::println);
        redisCacheTemplate.opsForHash().put("apple", "x", "6000");
        redisCacheTemplate.opsForHash().put("apple", "xr", "5000");
        redisCacheTemplate.opsForHash().put("apple", "xs max","8000");

        System.out.print(redisCacheTemplate.opsForHash().get("apple", "xs max"));
    }
}
