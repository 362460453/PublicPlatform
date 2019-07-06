package com.elasticjob.springboot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.elasticjob.springboot.service.SimpleJobA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yanlin
 * @version v1.0
 * @className JavaSimpleJobB
 * @description 定时任务B
 * @date 2019-07-02 10:00 AM
 **/
@Service
public class JavaSimpleJobB implements SimpleJob {
    @Autowired
    SimpleJobA simpleJobA;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date())
                + " JavaSimpleJobB当前分片项 : " + shardingContext.getShardingItem()
                + " JavaSimpleJobB总片数 : " + shardingContext.getShardingTotalCount());
        System.out.println(simpleJobA);
    }
}
