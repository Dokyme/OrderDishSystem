package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.service.OrderDishService;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
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
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderDishService orderDishService, OrderMapper orderMapper) {
        this.orderDishService = orderDishService;
        this.orderMapper = orderMapper;
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
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public List<Order> queryStaistics(@RequestParam String time) {
        Date startTime, endTime;
        Calendar cStartTime = Calendar.getInstance();
        Calendar cEndTime = Calendar.getInstance();

        try {
            SimpleDateFormat simpleDateFormat;
            if (time.length() == 4) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
                startTime = simpleDateFormat.parse(time);
                endTime = simpleDateFormat.parse(time);
                cStartTime.setTime(startTime);
                cStartTime.set(cStartTime.get(Calendar.YEAR), Calendar.JANUARY, 1);
                cEndTime.setTime(endTime);
                cEndTime.set(cEndTime.get(Calendar.YEAR), Calendar.DECEMBER, 31);
            } else if (time.length() == 7) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                startTime = simpleDateFormat.parse(time);
                endTime = simpleDateFormat.parse(time);
                cStartTime.setTime(startTime);
                cStartTime.set(Calendar.DATE, 1);
                cEndTime.setTime(endTime);
                cEndTime.set(Calendar.DATE, cEndTime.getActualMaximum(Calendar.DATE));
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                startTime = simpleDateFormat.parse(time);
                cStartTime.setTime(startTime);
                endTime = simpleDateFormat.parse(time);
                cEndTime.setTime(endTime);
            }
            return queryOrdersWithRange(cStartTime.getTime(), cEndTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> queryOrdersWithRange(Date startTime, Date endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        startTime = calendar.getTime();
        calendar.setTime(endTime);
        calendar.set(Calendar.HOUR, 24);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        endTime = calendar.getTime();

        OrderExample example = new OrderExample();
        example.createCriteria().andTimeBetween(startTime, endTime);
        return orderMapper.selectByExample(example);
    }
}
