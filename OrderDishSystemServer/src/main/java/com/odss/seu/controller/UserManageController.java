package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.code.kaptcha.Producer;
import com.odss.seu.service.UploadPictureService;
import com.odss.seu.service.UserManageService;
import com.odss.seu.vo.User;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserManageController {

    private UserManageService userManageService;
    private Producer producer;
    private UploadPictureService uploadPictureService;

    @Autowired
    public UserManageController(UserManageService userManageService, Producer producer, UploadPictureService uploadPictureService) {
        this.userManageService = userManageService;
        this.producer = producer;
        this.uploadPictureService = uploadPictureService;
    }

    //管理员查询所有用户概要信息的列表。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<User> queryAllUser() {
        List<User> users = userManageService.queryAllUsers();
        return users;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public User queryUserDetail(@PathVariable Integer userId) {
        return userManageService.queryUserDetail(userId);
    }

    //管理员修改用户信息。
    @RequestMapping(method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userManageService.updateUser(user);
    }

    //管理员添加用户。
    @RequestMapping(method = RequestMethod.PUT)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public User addNewUser(@RequestBody User user, HttpSession session) {
        Object photo = session.getAttribute("photo");
        if (photo != null)
            user.setPhoto(photo.toString());
        return userManageService.addNewUser(user);
    }

    //管理员删除用户。
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer userId) {
        userManageService.deleteUser(userId);
    }

    //管理员管理照片
    @RequestMapping(value = "/photo")
    public void uploadPhoto(@RequestPart("photo") MultipartFile multipartFile, HttpServletRequest request) {
        String relativeFilepath = uploadPictureService.upload(multipartFile);
        if (relativeFilepath != null)
            request.getSession().setAttribute("photo", relativeFilepath);
    }

}
