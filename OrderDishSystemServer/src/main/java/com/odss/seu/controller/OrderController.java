package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.service.OrderRepository;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    public void submitOrder(OrderInfo orderInfo) {
        return;
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public Order queryOrderById(@PathVariable Integer orderId) {
        return orderRepository.queryOrderById(orderId);
    }

    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public Order queryOrderDetailById(@PathVariable Integer orderId) {
        return orderRepository.queryOrderById(orderId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Order> queryAllOrders() {
        return orderRepository.queryAllOrders();
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public List<Order> queryAllOrder() {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void changeOrder(Order order) {
        return;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrder() {
        return;
    }

}
