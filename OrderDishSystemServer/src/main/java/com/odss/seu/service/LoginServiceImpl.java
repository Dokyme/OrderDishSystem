package com.odss.seu.service;

import com.odss.seu.mapper.UserMapper;
import com.odss.seu.service.exception.PasswordWrongException;
import com.odss.seu.service.exception.UsernameNotFoundException;
import com.odss.seu.vo.DishExample;
import com.odss.seu.vo.User;
import com.odss.seu.vo.UserExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

//登陆功能的实现
@Service
public class LoginServiceImpl implements LoginService {

    public static Logger lo= Logger.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) throws RuntimeException {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andAccountEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);

        if (userList == null||userList.size() == 0) {
            lo.info(username+"登录失败");
            throw new UsernameNotFoundException();
        } else if (!password.equals(userList.get(0).getPasword())) {
            lo.info(username+"登录失败");
            throw new PasswordWrongException();
        } else {
            //用户名和密码正确
            lo.info(username+"登录成功");
            return userList.get(0);
        }
    }

    @Override
    public void logout(HttpSession session) throws RuntimeException {
        lo.info("登出成功");
    }
}
