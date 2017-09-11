package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import com.odss.seu.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDishServiceImpl implements OrderDishService {

    private DishMapper dishMapper;
    private OrderMapper orderMapper;

    @Autowired
    public OrderDishServiceImpl(DishMapper dishMapper, OrderMapper orderMapper) {
        this.dishMapper = dishMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Dish> queryAllDish() {
        return dishMapper.selectByExample(new DishExample());
    }

    @Override
    public void commitNewOrder(Order order) {

    }

    @Override
    public Order queryOrder(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
