package com.odss.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dataView")
public class DataViewPage {

    @RequestMapping(method = RequestMethod.GET)
    public String renderDataViewPage() {
        return "dataView";
    }
}