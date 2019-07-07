package com.lock.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * 分布式锁的操作
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-04-13 2:27 PM
 * @since v8.0
 **/
@Component
public class RedisLock {
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
// ex ： seconds 秒
//    px :   milliseconds 毫秒
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private DefaultRedisScript<Number> redisLockLuaScript;

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = jedisPool.getResource();
        System.out.println("uuid+++++++++++++"+requestId);
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis jedis = jedisPool.getResource();
        Object result = jedis.eval(redisLockLuaScript.getScriptAsString(), Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
