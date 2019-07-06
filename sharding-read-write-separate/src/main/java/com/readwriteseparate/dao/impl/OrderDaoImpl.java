package com.readwriteseparate.dao.impl;


import com.readwriteseparate.dao.OrderDao;
import com.readwriteseparate.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.annotation.Resource;
import java.sql.Types;
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
                .append("select order_id, user_id from order where user_id=? ");
        return jdbcTemplate.query(sqlBuilder.toString(), new Object[]{userId},
                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<Order>(
                        Order.class));
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select user_id,order_id  from order ";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(
                Order.class));
    }

    @Override
    public void createOrder(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into order(user_id, order_id)");
        sb.append("values(");
        sb.append(order.getUserId()).append(",");
        sb.append(order.getOrderId());
        sb.append(")");
        jdbcTemplate.update(sb.toString());

    }
}
