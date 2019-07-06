package com.elasticjob.springboot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.elasticjob.springboot.service.SimpleJobA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 任务
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-06-04 3:59 PM
 * @since v8.0
 **/
@Service
public class JavaSimpleJob implements SimpleJob {

    @Autowired
    SimpleJobA simpleJobA;

    @Override
    public void execute(ShardingContext shardingContext) {

        List<Integer> idS = simpleJobA.selectId(shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
        String ids = Arrays.toString(idS.toArray());
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date())
                + " JavaSimpleJob当前分片项 : " + shardingContext.getShardingItem()
                + " JavaSimpleJob总片数 : " + shardingContext.getShardingTotalCount() + " 当前片查询出来的数据是: " + ids);
        simpleJobA.updateState(idS);

    }

}
