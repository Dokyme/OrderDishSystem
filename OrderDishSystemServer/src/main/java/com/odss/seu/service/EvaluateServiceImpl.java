package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Evaluation;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {
    public static Logger lo = Logger.getLogger(EvaluateServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    public EvaluateServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    //顾客提交评价。
    public void commitEvaluate(int table, Evaluation evaluation) {

        OrderExample example = new OrderExample();
        example.createCriteria().andTableEqualTo(table).andDishqualityIsNull();
        List<Order> orders = orderMapper.selectByExample(example);
        Order latestOrder = orders.get(0);
        for (int i = 1; i < orders.size(); i++) {
            if (latestOrder.getTime().before(orders.get(i).getTime()))
                latestOrder = orders.get(i);
        }
        latestOrder.setCookspeed(evaluation.getCookSpeed());
        latestOrder.setDishquality(evaluation.getDishQuality());
        latestOrder.setServeattitude(evaluation.getServeAttitude());

        orderMapper.updateByPrimaryKeySelective(latestOrder);
        lo.info("提交评价成功");
    }

}
