package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.UserManageService;
import com.odss.seu.vo.User;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

//api checked
@RestController
@RequestMapping(value = "/user")
public class UserManageController {

    private UserManageService userManageService;

    @Autowired
    public UserManageController(UserManageService userManageService) {
        this.userManageService = userManageService;
    }

    //管理员查询所有用户概要信息的列表。
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<User> queryAllUser() {
        List<User> users = userManageService.queryAllUsers();
        return users;
    }

    @RequestMapping(value = "/photo", method = RequestMethod.POST)
    public void uploadPhoto(@RequestPart("photo") MultipartFile multipartFile, HttpServletRequest request) {
        if (!multipartFile.isEmpty()) {
            try {
                String filepath = request.getSession().getServletContext().getRealPath("/") + "upload/" +
                        UUID.randomUUID().toString().replaceAll("-", "") + multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(filepath));
                request.getSession().setAttribute("photo", filepath);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    //管理员修改用户信息。
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public void updateUser(@PathVariable Integer userId, @RequestBody User user) {
        userManageService.updateUser(userId, user);
    }

    //管理员添加用户。
    @RequestMapping(method = RequestMethod.PUT)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public User addNewUser(@RequestBody User user, HttpServletRequest request) {
        Object photoPath = request.getSession().getAttribute("photo");
        if (photoPath != null) {
            user.setPhoto(photoPath.toString());
            request.removeAttribute("photo");
        }
        return userManageService.addNewUser(user);
    }

    //管理员删除用户。
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer userId) {
        userManageService.deleteUser(userId);
    }
}
