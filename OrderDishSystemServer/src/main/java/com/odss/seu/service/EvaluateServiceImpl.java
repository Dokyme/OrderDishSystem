package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Evaluation;
import com.odss.seu.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    public EvaluateServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    //顾客提交评价。
    public void commitEvaluate(int id, Evaluation evaluation) {
        Order order = new Order();
        order.setId(id);
        order.setCookspeed(evaluation.getCookSpeed());
        order.setDishquality(evaluation.getDishQuality());
        order.setServeattitude(evaluation.getServeAttitude());

        orderMapper.updateByPrimaryKeySelective(order);
    }

}
