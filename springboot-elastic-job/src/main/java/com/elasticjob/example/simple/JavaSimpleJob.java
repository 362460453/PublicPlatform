package com.elasticjob.example.simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-04 3:59 PM
 * @since v8.0
 **/
public class JavaSimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date())
                + " JavaSimpleJob当前分片项 : " + shardingContext.getShardingItem()
                + " JavaSimpleJob总片数 : " + shardingContext.getShardingTotalCount());

        switch (shardingContext.getShardingItem()) {
            case 0:
                System.out.println("JavaSimpleJob do something by sharding item 0");
                break;
            case 1:
                System.out.println("JavaSimpleJob do something by sharding item 1");
                break;
            case 2:
                System.out.println("JavaSimpleJob do something by sharding item 2");
                break;
            // case n: ...
            //动态查询该分片下要执行的用户
            //SELECT * FROM lfp_user WHERE mod(id,#{shardingTotalCount})=#{shardingItem};
        }
        System.out.println("execute方法----------------");
        init();


    }
    public void init() {
        System.out.println("init方法----------------");
    }
}
