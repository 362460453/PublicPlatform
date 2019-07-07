package com.client;

import com.config.RedissonManager;
import com.redislock.RedissonLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanlin
 * @version v1.0
 * @className Application
 * @description TODO
 * @date 2019-07-07 12:42 PM
 **/
@SpringBootApplication
@ComponentScan(value = {"com.client", "com.config", "com.redislock"})
public class Application implements ApplicationRunner {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);​
    @Autowired
    RedissonLock redissonLock;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);​
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//******* Redis集群测试方法*********
​
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "线程就位，即将同时执行");
                        String key = "test123";
                        redissonLock.lock(key);
                        Thread.sleep(1000); //获得锁之后可以进行相应的处理
                        System.out.println(Thread.currentThread().getName() + "获取成功，并开始执行业务逻辑");
                        redissonLock.unLock(key);
                        System.out.println(Thread.currentThread().getName() + "释放成功");​
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }​
                }
            });
        }
        executorService.shutdown();
        Long result = RedissonManager.nextID();
        System.out.print("获取redis中的原子ID" + result);​

    }
}
