package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author yanlin
 * @version v1.0
 * @className Commons
 * @description TODO
 * @date 2019-07-07 12:14 PM
 **/
@Component
public class Commons {
    @Bean
    public DefaultRedisScript<Number> redisluaScript() {
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        //读取 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
        redisScript.setResultType(Number.class);
        return redisScript;
    }

    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
