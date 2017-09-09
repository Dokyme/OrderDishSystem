package com.odss.seu.service;

import com.odss.seu.vo.OrderInfo;

public interface ArrangeCookingService {
    void confirmNewDishCooking(Integer orderInfoId);

    void cancelDishCooking(Integer orderInfoId);
}
