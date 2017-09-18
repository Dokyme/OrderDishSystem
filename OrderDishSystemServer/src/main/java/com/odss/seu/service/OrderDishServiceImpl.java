package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDishServiceImpl implements OrderDishService {
    public static Logger lo = Logger.getLogger(OrderDishServiceImpl.class);

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
        lo.info("查询所有菜品成功");
        return dishMapper.selectByExample(new DishExample());
    }

    @Override
    public void commitNewOrder(Order order) {
        order.setTime(new Date());
        orderMapper.insert(order);
        for (int i = 0; i < order.getDishes().size(); i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder(order);
            orderInfo.setDish(order.getDishes().get(i));
            orderInfoMapper.insert(orderInfo);
        }
        lo.info("追加菜品成功");
    }

    @Override
    public Order queryOrderByTable(Integer tableNum) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andTableEqualTo(tableNum).andStateEqualTo(OrderState.WAITING_CHECKOUT.ordinal());
        return orderMapper.selectByExample(orderExample).get(0);
    }

    @Override
    public Order queryOrder(Integer orderId) {
        lo.info("查询ID为" + orderId + "的菜品成功");
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
