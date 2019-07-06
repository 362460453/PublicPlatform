package com.test.dao.impl;

import com.test.dao.OrderDao;
import com.test.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
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
