package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.UserManageService;
import com.odss.seu.vo.User;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserManageController {

    private UserManageService userManageService;

    @Autowired
    public UserManageController(UserManageService userManageService) {
        this.userManageService = userManageService;
    }

    //管理员查询所有用户概要信息的列表。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<User> queryAllUser() {
        List<User> users= userManageService.queryAllUsers();
        return users;
    }

    //管理员修改用户信息。
    @RequestMapping(method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userManageService.updateUser(user);
    }

    //管理员添加用户。
    @RequestMapping(method = RequestMethod.PUT)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public User addNewUser(@RequestBody User user) {
        return userManageService.addNewUser(user);
    }

    //管理员删除用户。
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer userId) {
        userManageService.deleteUser(userId);
    }
}
