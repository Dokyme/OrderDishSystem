package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.code.kaptcha.Constants;
import com.odss.seu.service.AuthenticService;
import com.odss.seu.service.LoginService;
import com.odss.seu.service.exception.CaptchaWrongException;
import com.odss.seu.service.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

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
    public @JsonView
    User login(@RequestParam(value = "username") String username,
               @RequestParam(value = "password") String password,
               @RequestParam(value = "captcha") String captcha,
               HttpSession session) {
        System.out.println(username + password + captcha);
        AuthenticService.State state = authenticService.check(session);
        if (state != AuthenticService.State.NONE)//检验是否已经登陆过，如果登陆过，说明该用户发送了非法的请求
            throw new InvalidRequestException();
        if (!session.getAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY).toString().equals(captcha))//检验登陆验证码
            throw new CaptchaWrongException();
        return loginService.login(username, password);//返回登陆结果
    }
}
