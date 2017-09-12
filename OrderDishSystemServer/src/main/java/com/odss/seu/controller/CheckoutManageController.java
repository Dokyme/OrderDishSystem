package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.CheckoutManageService;
import com.odss.seu.vo.Checkout;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutManageController {

    private CheckoutManageService checkoutManageService;

    @Autowired
    public CheckoutManageController(CheckoutManageService checkoutManageService) {
        this.checkoutManageService = checkoutManageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Checkout> queryAllCheckouts(){
        return checkoutManageService.queryCheckouts();
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.GET)
    public void deleteOrder(@PathVariable int orderId){
        checkoutManageService.deleteCheckout(orderId);
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.POST)
    public void confirmOrder(@PathVariable int orderId){
        checkoutManageService.confirmCheckouted(orderId);
    }

}
