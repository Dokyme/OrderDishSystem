package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Checkout;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutManageServiceImpl implements CheckoutManageService{

    private OrderMapper orderMapper;

    @Autowired
    public CheckoutManageServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Checkout> queryCheckouts() {
        OrderExample example=new OrderExample();
        example.createCriteria().andStateEqualTo(OrderState.WAITING_CHECKOUT.ordinal());
        List<Order> orders=orderMapper.selectByExample(example);
        List<Checkout>checkouts = new ArrayList<Checkout>();
        for(int i = 0; i<checkouts.size();i++){
            Checkout checkout = new Checkout();
            checkout.setId(orders.get(i).getId());
            checkout.setState(orders.get(i).getState());
            checkout.setTable(orders.get(i).getTable());
            checkout.setTime(orders.get(i).getTime());
            checkout.setTotal(calculate(orders.get(i)));
            checkouts.add(checkout);
        }

        return checkouts;
    }

    @Override
    public void confirmCheckouted(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setState(OrderState.CHECKOUT.ordinal());
        orderMapper.updateByPrimaryKey(order);
    }

    public void deleteCheckout(Integer orderId){
        orderMapper.deleteByPrimaryKey(orderId);
    }

    public float calculate(Order orders){
        List<Dish>list = orders.getDishes();
        float total = 0;
        for(int i = 0; i<list.size();i++){
            total = total + list.get(i).getPrice();
        }
        return total;
    }

}
