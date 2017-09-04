package com.odss.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginPage {

    @RequestMapping(method = RequestMethod.GET)
    public String renderLoginPage() {
        return "login";
    }
}
