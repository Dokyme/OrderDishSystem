package com.odss.seu.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

//身份验证功能的实现
@Service
public class AuthenticServiceImpl implements AuthenticService {

    @Override
    public State check(HttpSession session) {
        Object user = session.getAttribute("user");
        return State.NONE;
    }
}
