package com.test.controller;

import java.util.List;

import com.test.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Order;
import com.test.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping(path="/{userId}", method={RequestMethod.GET})
	public List<Order> getOrderListByUserId(@PathVariable("userId") Integer userId) {
		return orderService.getOrderListByUserId(userId);
	}
	
	@RequestMapping(path="/{userId}/{orderId}", method={RequestMethod.POST})
	public String createOrderRest(@PathVariable("userId") Integer userId, @PathVariable("orderId") Integer orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		orderService.createOrder(order);
		return "success";
	}
	
	@RequestMapping(path="/createOrder/{userId}/{orderId}", method={RequestMethod.GET})
	public String createOrder(@PathVariable("userId") Integer userId, @PathVariable("orderId") Integer orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		orderDao.createOrder(order);
		return "success";
	}

}
