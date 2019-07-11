package cn.mapper;

import cn.model.Goods;
import org.apache.ibatis.annotations.*;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-03-04 4:19 PM
 * @since v8.0
 **/

public interface GoodsMapper {
    @Insert("insert into goods (name, price, create_time, update_time)"
            + "values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    Long save(Goods goods);

    @Select("select * from goods where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    Goods findById(@Param("id") Long id);
}
