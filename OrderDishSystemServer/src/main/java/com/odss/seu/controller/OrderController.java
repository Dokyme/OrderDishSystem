package com.odss.seu.controller;


import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

//    提交订单_点餐结束后提交订单
    @RequestMapping(value="/submit",method= RequestMethod.PUT)
    public void submitOrder(OrderInfo orderInfo)
    {
        return;
    }

//    查询订单——客户点餐之后自己查询订单
    @RequestMapping(value="/{orderinfo}",method= RequestMethod.GET)
    public List<OrderInfo> queryOrder(@PathVariable OrderInfo orderinfo)
    {
        return new ArrayList<>();
    }

//    查询订单-管理员之后查订单
    @RequestMapping(value="/manager",method=RequestMethod.GET)
    public  List<Order> queryAllOrder()
    {
        return new ArrayList<>();
    }

//    修改订单_结账+评价更新
    @RequestMapping(value="/change",method=RequestMethod.POST)
    public void changeOrder(Order order)
    {
        return ;
    }

//    删除订单
    @RequestMapping(method=RequestMethod.DELETE)
    public void deleteOrder()
    {
        return;
    }

}
