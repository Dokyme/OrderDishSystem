package com.odss.seu.service;

import com.odss.seu.service.exception.PasswordWrongException;
import com.odss.seu.service.exception.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

//登陆功能的实现
@Service
public class LoginServiceImpl implements LoginService {
//
//    @Override
//    public User login(String username, String password) throws RuntimeException {
//        if (!username.equals("admin")) {
//            throw new UsernameNotFoundException();
//        } else if (!password.equals("123")) {
//            throw new PasswordWrongException();
//        } else {
//            //用户名和密码正确
//            return User.mock();
//        }
//    }
//
//    @Override
//    public void logout(HttpSession session) throws RuntimeException {
//
//    }
}
