package com.odss.seu.service;

import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.OrderInfoExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServeDishServiceImpl implements ServeDishService {

    public static Logger lo = Logger.getLogger(ServeDishServiceImpl.class);
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    public ServeDishServiceImpl(OrderInfoMapper orderInfoMapper)
    {
        this.orderInfoMapper = orderInfoMapper;
    }

    //提醒服务员上菜
    @Override
    public void requestServeDish(Integer orderInfoId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoId);
        orderInfo.setDishstate(DishState.WAITING_SERVING.ordinal());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        lo.info("提醒服务员上菜");
    }

    @Override
    public OrderInfo fetchOne() {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andDishstateEqualTo(DishState.WAITING_SERVING.ordinal());
        OrderInfo orderInfo = orderInfoMapper.selectByExample(example).get(0);
        orderInfo.setDishstate(DishState.SERVING.ordinal());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        lo.info("        ");
        return orderInfo;
    }

    @Override
    public void confirmDishServing(Integer orderInfoId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoId);
        orderInfo.setDishstate(DishState.SERVED.ordinal());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        lo.info("传菜成功");
    }
}
