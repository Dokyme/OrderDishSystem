package com.odss.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/seatManage")
public class SeatManagePage {

    @RequestMapping(method = RequestMethod.GET)
    public String renderSeatManagePage() {
        return "seatManage";
    }
}