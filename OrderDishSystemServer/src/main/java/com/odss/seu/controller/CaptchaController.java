package com.odss.seu.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {

    private Producer kcaptchaProducer;

    @Autowired
    public CaptchaController(Producer kcaptchaProducer) {
        this.kcaptchaProducer = kcaptchaProducer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String capText = kcaptchaProducer.createText();
        BufferedImage bi = this.kcaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        System.out.println("================code is "+capText);
        try {
            session.setAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY, capText);//在该用户的session中记录他的验证码值
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIO.write(bi, "jpeg", out);
    }

}
