package com.elasticjob.springboot.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elasticjob.springboot.job.JavaSimpleJob;
import com.elasticjob.springboot.job.JavaSimpleJobB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yanlin
 * @version v1.0
 * @className SimpleJobConfig
 * @description 简单定时任务配置
 * @date 2019-07-01 12:11 PM
 **/
@Configuration
public class SimpleJobConfig {
    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public JavaSimpleJob simpleJob() {
        return new JavaSimpleJob();
    }

    @Bean
    public JavaSimpleJobB simpleJobB() {
        return new JavaSimpleJobB();
    }


    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final JavaSimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron,
                                           @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount) {
        return new SpringJobScheduler(simpleJob, regCenter, getSimpleJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount));
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobBScheduler(final JavaSimpleJobB simpleJobB, @Value("${simpleJob.b.cron}") final String cron,
                                            @Value("${simpleJob.b.shardingTotalCount}") final int shardingTotalCount) {
        return new SpringJobScheduler(simpleJobB, regCenter, getSimpleJobConfiguration(simpleJobB.getClass(), cron, shardingTotalCount));
    }

    /**
     * 简单定时任务配置
     *
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @return
     */
    private LiteJobConfiguration getSimpleJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }

}
