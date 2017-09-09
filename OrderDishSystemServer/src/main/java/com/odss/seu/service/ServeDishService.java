package com.odss.seu.service;

import com.odss.seu.vo.OrderInfo;

public interface ServeDishService {
    void requestServeDish(Integer orderInfoId);

    void confirmDishServing(Integer orderInfoId);
}
