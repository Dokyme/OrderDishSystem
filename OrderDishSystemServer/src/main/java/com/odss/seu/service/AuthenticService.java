package com.odss.seu.service;

import javax.servlet.http.HttpSession;

public interface AuthenticService {
    enum State {COOKER,ADMIN,WAITER,CUSTOMER,NONE}

    State check(HttpSession session);
}
