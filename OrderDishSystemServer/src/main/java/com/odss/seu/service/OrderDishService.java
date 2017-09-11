package com.odss.seu.service;

import com.odss.seu.vo.Dish;
import com.odss.seu.vo.Order;

import java.util.List;

public interface OrderDishService {
    List<Dish> queryAllDish();

    void commitNewOrder(Order order);

    Order queryOrder(Integer orderId);
}
