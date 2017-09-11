package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.CheckoutService;
import com.odss.seu.service.OrderDishService;
import com.odss.seu.service.QuerySellingService;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.SellingStatistics;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderDishService orderDishService;
    private QuerySellingService querySellingService;

    @Autowired
    public OrderController(OrderDishService orderDishService, QuerySellingService querySellingService) {
        this.orderDishService = orderDishService;
        this.querySellingService = querySellingService;
    }

    //查询某个时间段内的所有账单的详细信息。
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public List<Order> queryOrderStatistics(@RequestParam Date startTime, @RequestParam Date endTime) {
        return null;
    }

    //顾客或服务员提交订单。
    @RequestMapping(method = RequestMethod.PUT)
    public void submitOrder(@RequestBody Order order) {
        orderDishService.commitNewOrder(order);
    }

    //顾客查询订单详情。
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public Order queryOrderDetailById(@PathVariable Integer orderId) {
        return querySellingService.queryOrderDetail(orderId);
    }

//    //管理员查询所有订单概要信息的列表。
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @JsonView(ViewLevel.Summary.class)
//    public List<Order> queryAllOrders() {
//        return querySellingService.queryAllOrders();
//    }
}
