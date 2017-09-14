package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.OrderDishService;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.SellingStatistics;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderDishService orderDishService;

    @Autowired
    public OrderController(OrderDishService orderDishService) {
        this.orderDishService = orderDishService;
    }

    //顾客或服务员提交订单。
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public void submitOrder(@RequestBody Order order) {
        orderDishService.commitNewOrder(order);
    }

    //管理员或顾客查询订单详情。
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public Order queryOrderDetailById(@PathVariable Integer orderId) {
        return orderDishService.queryOrder(orderId);

    }

    //管理员查询所有订单统计数据。
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Order> queryStaistics(@RequestParam String time) {
        Calendar calendar = Calendar.getInstance();
        Date startTime, endTime;
        try {
            SimpleDateFormat simpleDateFormat;
            if (time.length() == 4) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
                startTime = simpleDateFormat.parse(time);
                startTime.setMonth(1);
                startTime.setDate(1);
                endTime = simpleDateFormat.parse(time);
                endTime.setMonth(12);
                endTime.setDate(31);
            } else if (time.length() == 7) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                startTime = simpleDateFormat.parse(time);
                startTime.setDate(1);
                endTime = simpleDateFormat.parse(time);
                endTime.setDate(31);
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                startTime = simpleDateFormat.parse(time);
                endTime = simpleDateFormat.parse(time);
            }
            return queryOrdersWithRange(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> queryOrdersWithRange(Date startTime, Date endTime) {
        return null;
    }

}
