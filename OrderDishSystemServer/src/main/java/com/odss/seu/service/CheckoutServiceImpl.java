package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private OrderMapper orderMapper;

    @Autowired
    public CheckoutServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void confirmCheckout(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setState(OrderState.CHECKOUT.ordinal());
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public void onlineCheckout() {

    }
}
