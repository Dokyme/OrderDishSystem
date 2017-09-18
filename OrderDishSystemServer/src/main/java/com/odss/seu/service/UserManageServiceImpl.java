package com.odss.seu.service;

import com.odss.seu.mapper.UserMapper;
import com.odss.seu.vo.User;
import com.odss.seu.vo.UserExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    public static Logger lo= Logger.getLogger(UserManageServiceImpl.class);

    private UserMapper userMapper;

    @Autowired
    public UserManageServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryAllUsers() {
        List<User> users= userMapper.selectByExample(new UserExample());
        lo.info("查询所有员工信息成功");
        return users;
    }

    @Override
    public User queryUserDetail(Integer userId) {
        lo.info("查询ID为"+userId+"的员工信息成功");
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(User user) {
        lo.info("更新ID为"+user.getId()+"的员工信息成功");
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        lo.info("删除ID为"+userId+"的员工信息成功");
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User addNewUser(User user) {
        userMapper.insert(user);
        lo.info("添加ID为"+user.getId()+"的员工信息成功");
        return user;
    }
}
