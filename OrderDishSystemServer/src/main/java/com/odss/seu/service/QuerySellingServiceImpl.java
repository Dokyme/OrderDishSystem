package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuerySellingServiceImpl implements QuerySellingService {

    private OrderMapper orderMapper;

    @Autowired
    public QuerySellingServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> queryAllOrders() {
//        return orderMapper.selectByExample(new OrderExample());
        return null;
    }

    @Override
    public Order queryOrderDetail(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
