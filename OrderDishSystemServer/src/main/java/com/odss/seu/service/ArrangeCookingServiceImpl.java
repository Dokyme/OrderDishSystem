package com.odss.seu.service;

import com.odss.seu.mapper.OrderInfoMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.OrderInfoExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArrangeCookingServiceImpl implements ArrangeCookingService {

    public static Logger lo = Logger.getLogger(ArrangeCookingServiceImpl.class);
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
        lo.info("确认ID为" + orderInfoId + "的菜品开始制作");
    }

    //后厨查询所有需要烹饪和正在烹饪（为1）的菜品列表。
    @Override
    public List<OrderInfo> queryAllCookingTasks() {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andDishstateIn(Arrays.asList(DishState.WAITING_COOKING.ordinal(), DishState.COOKING.ordinal()));
        lo.info("查询所有需要烹饪和正在烹饪（为1）的菜品列表");
        return orderInfoMapper.selectByExample(example);

    }

    //后厨撤销某道菜的烹饪，0为已取消。
    @Override
    public void cancelDishCooking(Integer orderInfoId) {
        lo.info("后厨撤销ID为" + orderInfoId + "菜品的烹饪");
        orderInfoMapper.deleteByPrimaryKey(orderInfoId);
    }
}
