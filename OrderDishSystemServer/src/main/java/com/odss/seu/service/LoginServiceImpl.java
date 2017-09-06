package com.odss.seu.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

//登陆功能的实现
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public int login(String username, String password) {
        if (!username.equals("admin")) {
            return LoginService.USERNAME_HOT_FOUND;
        } else if (!password.equals("123")) {
            return LoginService.PASSWORD_WRONG;
        } else {
            return LoginService.SUCCESS;
        }
    }

    @Override
    public int logout(HttpSession session) {
        return 0;
    }
}
