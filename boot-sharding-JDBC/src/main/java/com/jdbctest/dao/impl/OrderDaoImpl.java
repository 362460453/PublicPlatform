package com.jdbctest.dao.impl;


import com.jdbctest.dao.OrderDao;
import com.jdbctest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
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

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("select order_id, user_id from t_order where user_id=? ");
        return jdbcTemplate.query(sqlBuilder.toString(), new Object[]{userId},
                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<Order>(
                        Order.class));
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select order_id, user_id from t_order ";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(
                Order.class));
    }

    @Override
    public void createOrder(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into t_order(user_id, order_id)");
        sb.append("values(");
        sb.append(order.getUserId()).append(",");
        sb.append(order.getOrderId());
        sb.append(")");
        jdbcTemplate.update(sb.toString());

    }
}
