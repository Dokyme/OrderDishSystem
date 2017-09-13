package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.odss.seu.service.AuthenticService;
import com.odss.seu.service.LoginService;
import com.odss.seu.service.exception.CaptchaWrongException;
import com.odss.seu.service.exception.InvalidRequestException;
import com.odss.seu.vo.User;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping(value = "/identity")
public class IdentityController {

    private LoginService loginService;
    private AuthenticService authenticService;
    private Producer producer;

    @Autowired
    public IdentityController(LoginService loginService, AuthenticService authenticService, Producer producer) {
        this.loginService = loginService;
        this.authenticService = authenticService;
        this.producer = producer;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @JsonView(ViewLevel.Summary.class)
    public User login(@RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "captcha") String captcha,
                      HttpSession session)
    {
        System.out.println(username + password + captcha);
        AuthenticService.State state = authenticService.check(session);
        if (state != AuthenticService.State.NONE)//检验是否已经登陆过，如果登陆过，说明该用户发送了非法的请求
            throw new InvalidRequestException();
        if (!session.getAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY).toString().equals(captcha))//检验登陆验证码
            throw new CaptchaWrongException();

        User user=loginService.login(username, password);//返回登陆结果
        session.setAttribute("user",user.getType());//设置用户的身份
        if(user.getType()==2)//假设waiter是2
        {
            session.setAttribute("dishState","notbusy");//设置当前状态为空闲，可以上菜
        }
        return user;
    }


    @RequestMapping(method = RequestMethod.POST)
    @JsonView(ViewLevel.Summary.class)
    public  void logout(HttpSession session)
    {
        //System.out.println(username + password + captcha);
        AuthenticService.State state = authenticService.check(session);
        if (state == AuthenticService.State.NONE)//检验是否已经登陆过，如果没有登陆过，说明该用户发送了非法的请求
            throw new InvalidRequestException();

        if(session.getAttribute("user")=="waiter")
        {
            session.removeAttribute("dishState");//设置当前状态为空闲，可以上菜
        }
        session.removeAttribute("user");
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public @ResponseBody
    void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String capText = this.producer.createText();
        BufferedImage bi = this.producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        try {
            session.setAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY, capText);//在该用户的session中记录他的验证码值
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIO.write(bi, "jpeg", out);
    }
}
