package com.odss.seu.service;

import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.OrderInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArrangeCookingServiceImpl implements ArrangeCookingService {

    private OrderInfoMapper orderInfoMapper;

    @Autowired
    public ArrangeCookingServiceImpl(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    //确认这道菜开始烹饪,2为开始烹饪。
    @Override
    public void confirmNewDishCooking(Integer orderInfoId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoId);
        orderInfo.setDishstate(DishState.COOKING.ordinal());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }

    //后厨查询所有需要烹饪（为1）的菜品列表。
    @Override
    public List<OrderInfo> queryAllCookingTasks() {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andDishstateEqualTo(DishState.WAITING_COOKING.ordinal());
        return orderInfoMapper.selectByExample(example);
    }

    //后厨撤销某道菜的烹饪，0为已取消。
    @Override
    public void cancelDishCooking(Integer orderInfoId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoId);
        orderInfo.setDishstate(DishState.CANCELED.ordinal());
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }
}
