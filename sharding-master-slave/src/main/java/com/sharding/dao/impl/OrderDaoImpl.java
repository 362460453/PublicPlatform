package com.sharding.dao.impl;


import com.sharding.dao.OrderDao;
import com.sharding.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-05-22 3:36 PM
 * @since v8.0
 **/
@Service
public class OrderDaoImpl implements OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Order> getOrderListByUserId(Integer userId) {
//        StringBuilder sqlBuilder = new StringBuilder();
//        sqlBuilder
//                .append("select order_id, user_id from order where user_id=? ");
//        return jdbcTemplate.query(sqlBuilder.toString(), new Object[]{userId},
//                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<Order>(
//                        Order.class));
        return null;

    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select order_id, user_id from order_0 ";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(
                Order.class));
    }

    @Override
    public void createOrder(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into order_0(user_id, order_id)");
        sb.append("values(");
        sb.append(order.getUserId()).append(",");
        sb.append(order.getOrderId());
        sb.append(")");
        jdbcTemplate.update(sb.toString());
    }
}
