package com.odss.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dishManage")
public class DishManagePage {

    @RequestMapping(method = RequestMethod.GET)
    public String renderDishManagePage() {
        return "dishManage";
    }
}