package com.odss.seu.service;

import com.odss.seu.vo.OrderInfo;

import java.util.List;

public interface ArrangeCookingService {
    void confirmNewDishCooking(Integer orderInfoId);

    void cancelDishCooking(Integer orderInfoId);

    List<OrderInfo> queryAllCookingTasks();
}
