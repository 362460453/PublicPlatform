package com.jdbctest.dao;


import com.jdbctest.model.Order;

import java.util.List;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-05-22 3:36 PM
 * @since v8.0
 **/
public interface OrderDao {
    List<Order> getOrderListByUserId(Integer userId);

    List<Order> getOrderList();

    void createOrder(Order order);
}
