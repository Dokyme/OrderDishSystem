package com.odss.seu.service;

import com.odss.seu.vo.User;

import javax.servlet.http.HttpSession;

public interface LoginService {

    User login(String username, String password);
    void logout(HttpSession session);
}
