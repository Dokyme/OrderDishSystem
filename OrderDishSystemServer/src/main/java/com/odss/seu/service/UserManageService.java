package com.odss.seu.service;

import com.odss.seu.vo.User;

import java.util.List;

public interface UserManageService {
    List<User> queryAllUsers();

    User queryUserDetail(Integer userId);

    void updateUser(Integer userId, User user);

    void deleteUser(Integer userId);

    User addNewUser(User user);
}
