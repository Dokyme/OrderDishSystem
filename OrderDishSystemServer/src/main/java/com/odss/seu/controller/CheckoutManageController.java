package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.CheckoutManageService;
import com.odss.seu.vo.Checkout;
import com.odss.seu.vo.ViewLevel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutManageController {

    private CheckoutManageService checkoutManageService;

    public CheckoutManageController(CheckoutManageService checkoutManageService) {
        this.checkoutManageService = checkoutManageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(ViewLevel.Summary.class)
    public List<Checkout> queryAllCheckouts(){
        return checkoutManageService.queryCheckouts();
    }

}
