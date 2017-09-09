package com.odss.seu.service;

import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.service.exception.ResourcesNotFoundException;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderRepository {

    private OrderMapper orderMapper;
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    public OrderRepositoryImpl(OrderMapper orderMapper, OrderInfoMapper orderInfoMapper) {
        this.orderMapper = orderMapper;
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderMapper.selectByExample(new OrderExample());
    }

    @Override
    public Order queryOrderById(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            throw new ResourcesNotFoundException();
        }
        return order;
    }

    @Override
    public void submitNewOrder(Order order) {
        orderMapper.insert(order);
        orderInfoMapper.insertOrderDishes(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderMapper.deleteByPrimaryKey(order.getId());
    }
}
