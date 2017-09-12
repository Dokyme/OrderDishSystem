package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.DishManageService;
import com.odss.seu.vo.Dish;
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
@RequestMapping(value = "/dish")
public class DishManageController {

    private DishManageService dishManageService;

    @Autowired
    public DishManageController(DishManageService dishManageService) {
        this.dishManageService = dishManageService;
    }

    //顾客或服务员或管理员界面，罗列所有菜品概要信息。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Dish> queryDishes() {
        return dishManageService.queryAllDishes();
    }

//    //顾客或服务员或管理员界面，罗列所有菜品详细信息。
//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    @JsonView(ViewLevel.SummaryWithDetail.class)
//    public List<Dish> queryDishesDetail() {
//        return dishManageService.queryAllDishes();
//    }

    //管理员，按id查询菜品详细信息。
    @RequestMapping(value = "/{dishId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public Dish queryDishDetailByDishId(@PathVariable Integer dishId) {
        return dishManageService.queryDishDetail(dishId);
    }

    //管理员修改菜品信息。
    @RequestMapping(method = RequestMethod.POST)
    public void updateDish(@RequestParam Dish dish) {
        dishManageService.updateDish(dish);
    }

    //管理员添加菜品。
    @RequestMapping(method = RequestMethod.PUT)
    @JsonView(ViewLevel.Summary.class)
    public Dish addNewDish(@RequestParam Dish dish, HttpServletRequest request) {
        Object picturePath = request.getSession().getAttribute("picture");
        if (picturePath != null) {
            dish.setPicture(picturePath.toString());
            request.getSession().removeAttribute("picture");
        }
        return dishManageService.addDish(dish);
    }

    //管理员删除菜品。
    @RequestMapping(value = "/{dishId}", method = RequestMethod.DELETE)
    public void deleteDishById(@PathVariable Integer dishId) {
        dishManageService.deleteDish(dishId);
    }

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public void uploadPicture(@RequestPart("picture") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String filepath = request.getSession().getServletContext().getRealPath("/") + "upload/" + UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
                file.transferTo(new File(filepath));
                request.getSession().setAttribute("picture", filepath);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
