package com.reidsson.redislock;

import com.reidsson.config.RedissonManager;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-04-21 1:48 PM
 * @since v8.0
 **/
@Component
public class RedissonLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLock.class);

    private static RedissonClient redissonClient = RedissonManager.getRedisson();


    public void lock(String lockName) {
        String key = lockName;
        RLock myLock = redissonClient.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁
        myLock.lock(2, TimeUnit.SECONDS);
        // 1. 最常见的使用方法
        //lock.lock();
        // 2. 支持过期解锁功能,10秒以后自动解锁, 无需调用unlock方法手动解锁
        //lock.lock(10, TimeUnit.SECONDS);
        // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
//        try {
//            boolean res = mylock.tryLock(3, 10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.err.println("======lock======" + Thread.currentThread().getName());
    }

    public void unLock(String lockName) {
        String key = lockName;
        RLock myLock = redissonClient.getLock(key);
        myLock.unlock();
        System.err.println("======unlock======" + Thread.currentThread().getName());
    }
}
