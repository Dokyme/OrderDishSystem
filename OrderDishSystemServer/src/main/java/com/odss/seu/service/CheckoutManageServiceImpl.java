package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Checkout;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutManageServiceImpl implements CheckoutManageService {

    private OrderMapper orderMapper;

    @Autowired
    public CheckoutManageServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Checkout> queryCheckouts() {
        OrderExample example=new OrderExample();
        example.createCriteria().andStateEqualTo(OrderState.WAITING_CHECKOUT.ordinal());
        List<Order> orders=orderMapper.selectByExample()
    }

    @Override
    public void confirmCheckouted(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setState(OrderState.CHECKOUT.ordinal());
        orderMapper.updateByPrimaryKey(order);
    }
}
