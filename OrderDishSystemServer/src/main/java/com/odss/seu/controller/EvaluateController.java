package com.odss.seu.controller;


import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/evaluate")
public class EvaluateController {

    //提交评论——不需要，在订单修改里面就实现了
    @RequestMapping(method =RequestMethod.POST)
    public void updateEvaluate(Order order)
    {
        return;
    }

    //管理员查看评论——可能还需要输入时间
    @RequestMapping(value="/{orderinfo}",method=RequestMethod.GET)
    public List<Order> queryEvaluate(@PathVariable OrderInfo orderinfo)
    {
        return new ArrayList<>();
    }


}
