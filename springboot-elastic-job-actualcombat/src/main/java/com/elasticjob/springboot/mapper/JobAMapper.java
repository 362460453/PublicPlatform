package com.elasticjob.springboot.mapper;

import com.elasticjob.springboot.mapper.sqlprovider.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yanlin
 * @version v1.0
 * @className JobAMapper
 * @description TODO
 * @date 2019-07-01 1:29 PM
 **/
@Mapper
public interface JobAMapper {
    //查询当前片下对数据
    @Select("SELECT id FROM job WHERE mod(id,#{shardingTotalCount})=#{shardingItem} and state=0")
    List<Integer> selectId(@Param("shardingTotalCount") Integer count, @Param("shardingItem") Integer item);

    //修改当前片的数据
    @SelectProvider(type = SqlProvider.class, method = "updateStateList")
    void updateState(@Param("idList") List<Integer> id);
}
