package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.mapper.DishMapper;
import com.odss.seu.service.DishRepository;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dish")
public class DishController {

    private DishRepository dishRepository;

    private DishMapper dishMapper;

    @Autowired
    public DishController(DishMapper dishMapper, DishRepository dishRepository) {
        this.dishMapper = dishMapper;
        this.dishRepository = dishRepository;
    }

    //查询所有菜品概要信息。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Dish> queryDishes() {
        return dishRepository.queryAllDishes();
    }

    //查询所有菜品详细信息。
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public List<Dish> queryDishesDetail() {
        return dishRepository.queryAllDishes();
    }

    //按id查询菜品概要信息。
    @RequestMapping(value = "/{dishId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public Dish queryDishesByDishId(@PathVariable Integer dishId) {
        return dishRepository.queryDishesById(dishId);
    }

    //按id查询菜品详细信息。
    @RequestMapping(value = "/detail/{dishId}", method = RequestMethod.GET)
    @JsonView(ViewLevel.SummaryWithDetail.class)
    public Dish queryDishesDetailByDishId(@PathVariable Integer dishId) {
        return dishRepository.queryDishesById(dishId);
    }

    @RequestMapping(method = RequestMethod.POST) //修改菜品信息
    public void updateDish(@RequestParam Dish dish) {
//        dishMapper.updateByPrimaryKey(dish);
        return;
    }

    @RequestMapping(method = RequestMethod.PUT) //新增菜品
    public void addNewDish(@RequestParam Dish dish) {
//        dishMapper.insert(dish);
        return;
    }

    @RequestMapping(value = "/{dishId}", method = RequestMethod.DELETE) //删除菜品
    public void deleteDishById(@PathVariable Integer dishId) {
//        dishMapper.deleteByPrimaryKey(dishId);
        return;
    }

}
