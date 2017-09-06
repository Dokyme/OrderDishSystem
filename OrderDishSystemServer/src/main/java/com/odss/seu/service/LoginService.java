package com.odss.seu.service;

import javax.servlet.http.HttpSession;

public interface LoginService {

    int SUCCESS = 0;
    int USERNAME_HOT_FOUND = 1;
    int PASSWORD_WRONG = 2;

    int login(String username, String password);
    int logout(HttpSession session);
}
