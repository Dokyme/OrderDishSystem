package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.ServeDishService;
import com.odss.seu.vo.OrderInfo;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    //服务员轮询是否需要上菜
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public OrderInfo polling(HttpServletRequest request) {
        Object busy = request.getSession().getAttribute("busy");
        if (busy != null && busy.equals(Boolean.FALSE)) {
            return serveDishService.fetchOne();
        }
        return null;
    }

    //服务员确认已经上菜
    @RequestMapping(value = "/{orderInfoId}", method = RequestMethod.POST)
    public void confirmServing(@PathVariable Integer orderInfoId, HttpSession session) {
        session.setAttribute("busy", Boolean.FALSE);
        serveDishService.confirmDishServing(orderInfoId);
    }
}
