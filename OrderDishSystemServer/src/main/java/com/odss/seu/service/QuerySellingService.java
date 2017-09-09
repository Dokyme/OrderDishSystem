package com.odss.seu.service;

import com.odss.seu.vo.Order;

import java.util.List;

public interface QuerySellingService {

    List<Order> queryAllOrders();

    Order queryOrderDetail(Integer orderId);

}
