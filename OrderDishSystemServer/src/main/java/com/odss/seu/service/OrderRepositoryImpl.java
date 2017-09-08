package com.odss.seu.service;

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

    @Autowired
    public OrderRepositoryImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderMapper.selectByExample(new OrderExample());
    }

    @Override
    public Order queryOrderById(Integer orderId) throws RuntimeException {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            throw new ResourcesNotFoundException();
        }
        return order;
    }
}
