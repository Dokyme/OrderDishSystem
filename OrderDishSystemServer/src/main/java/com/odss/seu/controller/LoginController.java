package com.odss.seu.controller;

import com.odss.seu.service.AuthenticService;
import com.odss.seu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private int HAS_ALREADY_LOGIN = 10;
    private int CAPTCHA_WRONG = 11;
    private LoginService loginService;
    private AuthenticService authenticService;

    @Autowired
    public LoginController(LoginService loginService, AuthenticService authenticService) {
        this.loginService = loginService;
        this.authenticService = authenticService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String renderLoginPage(HttpSession session) {
        AuthenticService.State state = authenticService.check(session);
        switch (state) {
            //如果已经登陆过，则直接返回该角色对应的页面。
            case CUSTOMER:
                break;
            case COOKER:
                break;
            case ADMIN:
                break;
            case WAITER:
                break;
            case NONE:
                return "login";
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    int login(@RequestParam(value = "username") String username,
              @RequestParam(value = "password") String password,
              @RequestParam(value = "captcha") String captcha,
              HttpSession session) {
        AuthenticService.State state = authenticService.check(session);
        if (state != AuthenticService.State.NONE)//检验是否已经登陆过，如果登陆过，说明该用户发送了非法的请求
            return HAS_ALREADY_LOGIN;
        if (!session.getAttribute("captcha").toString().equals(captcha))//检验登陆验证码
            return CAPTCHA_WRONG;
        return loginService.login(username, password);//返回登陆结果
    }
}
