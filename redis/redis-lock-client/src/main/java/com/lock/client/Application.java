package com.lock.client;

import com.lock.redislock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan(value = {"com.annotaion", "cn.springcloud", "com.config", "com.redislock"})

public class Application implements ApplicationRunner {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static final String uuid = UUID.randomUUID().toString();
        @Autowired
        RedisLock redisLock;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
//******* Redis单机测试方法*********
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "线程就位，即将同时执行");
                        boolean result = redisLock.tryGetDistributedLock("lock", uuid, 1);
                        if (result) {
                            System.out.println(Thread.currentThread().getName() + "获取成功，并开始执行业务逻辑");
                            result = redisLock.releaseDistributedLock("lock", uuid);
                            if (result) {
                                System.out.println(Thread.currentThread().getName() + "释放成功");
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + "获取失败");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        executorService.shutdown();

    }
}
