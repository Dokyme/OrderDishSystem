package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.ArrangeCookingService;
import com.odss.seu.service.ServeDishService;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cooking")
public class OrderInfoController {

    private ArrangeCookingService arrangeCookingService;
    private ServeDishService serveDishService;

    @Autowired
    public OrderInfoController(ArrangeCookingService arrangeCookingService, ServeDishService serveDishService) {
        this.arrangeCookingService = arrangeCookingService;
        this.serveDishService = serveDishService;
    }

    //后厨查询所有需要烹饪的菜品列表。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<OrderInfo> queryAllOrderInfos() {
        return arrangeCookingService.queryAllCookingTasks();
    }

    //后厨确认开始烹饪。
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.PUT)
    public void confirmStartCooking(@PathVariable Integer orderInfoId) {
        arrangeCookingService.confirmNewDishCooking(orderInfoId);
    }

    //后厨确认烹饪完成，要求上菜。
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.POST)
    public void confirmFinishCooking(@PathVariable Integer orderInfoId) {
        serveDishService.requestServeDish(orderInfoId);
    }

    //后厨认为食材不足，取消这道菜的烹饪任务。
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.DELETE)
    public void cancelCooking(@PathVariable Integer orderInfoId) {
        arrangeCookingService.cancelDishCooking(orderInfoId);
    }
}
