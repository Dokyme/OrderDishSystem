package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDishServiceImpl implements OrderDishService {

    private DishMapper dishMapper;
    private OrderMapper orderMapper;
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    public OrderDishServiceImpl(DishMapper dishMapper, OrderMapper orderMapper, OrderInfoMapper orderInfoMapper) {
        this.dishMapper = dishMapper;
        this.orderMapper = orderMapper;
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    public List<Dish> queryAllDish() {
        return dishMapper.selectByExample(new DishExample());
    }

    @Override
    public void commitNewOrder(Order order) {
        order.setTime(new Date());
        orderMapper.insert(order);
        for(int i = 0; i < order.getDishes().size(); i++){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder(order);
            orderInfo.setDish(order.getDishes().get(i));
            orderInfoMapper.insert(orderInfo);
        }
    }

    @Override
    public Order queryOrder(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
