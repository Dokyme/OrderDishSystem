package com.odss.seu.service;

import com.odss.seu.vo.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> queryAllOrders();

    Order queryOrderById(Integer orderId);

    void submitNewOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Order order);
}
