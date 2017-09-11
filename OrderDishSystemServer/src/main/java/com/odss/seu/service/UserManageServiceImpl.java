package com.odss.seu.service;

import com.odss.seu.mapper.UserMapper;
import com.odss.seu.vo.User;
import com.odss.seu.vo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    private UserMapper userMapper;

    @Autowired
    public UserManageServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public User queryUserDetail(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User addNewUser(User user) {
        userMapper.insert(user);
        return user;
    }
}
