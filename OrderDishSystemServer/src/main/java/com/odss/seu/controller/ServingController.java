package com.odss.seu.controller;


import com.odss.seu.service.ServeDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//api checked
@RestController
@RequestMapping(value = "/serving")
public class ServingController {

    private ServeDishService serveDishService;

    @Autowired
    public ServingController(ServeDishService serveDishService) {
        this.serveDishService = serveDishService;
    }

    //厨师要求服务员上菜
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.PUT)
    public void requestServing(@PathVariable Integer orderInfoId) {
        serveDishService.requestServeDish(orderInfoId);
    }

    //服务员确认已经上菜
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.POST)
    public void confirmServing(@PathVariable Integer orderInfoId) {
        serveDishService.confirmDishServing(orderInfoId);
    }

}
