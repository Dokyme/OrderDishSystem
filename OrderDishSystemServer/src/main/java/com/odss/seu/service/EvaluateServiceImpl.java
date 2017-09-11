package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl {

    private OrderMapper orderMapper;

    @Autowired
    public EvaluateServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    //顾客提交评价。
    public void submitEvaluation(Order order) {

    }

}
