package com.elasticjob.example.simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yanlin
 * @version v1.0
 * @className JavaSimpleJobB
 * @description TODO
 * @date 2019-06-26 3:11 PM
 **/
public class JavaSimpleJobB implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date())
                + " JavaSimpleJobB当前分片项 : " + shardingContext.getShardingItem()
                + " JavaSimpleJobB总片数 : " + shardingContext.getShardingTotalCount());

        switch (shardingContext.getShardingItem()) {
            case 0:
                System.out.println("JavaSimpleJobB do something by sharding item 000000");
                break;
            case 1:
                System.out.println("JavaSimpleJobB do something by sharding item 111111");
                break;
            case 2:
                System.out.println("JavaSimpleJobB do something by sharding item 222222");
                break;
            // case n: ...
            //动态查询该分片下要执行的用户
            //SELECT * FROM lfp_user WHERE mod(id,#{shardingTotalCount})=#{shardingItem};
        }

    }
}
