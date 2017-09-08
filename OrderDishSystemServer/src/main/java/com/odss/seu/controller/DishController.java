package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.mapper.DishMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dish")
public class DishController {

    @Autowired
    private DishMapper dishMapper;

    @RequestMapping(value = "/{dishId}",method = RequestMethod.GET) //查询菜品信息，如果有typeId则返回对应类别的菜品列表，如果没有则返回所有菜品列表。
    public @JsonView
    List<Dish> queryDishesByDishId(@PathVariable Integer dishId){
        System.out.println("lsdjfosj");
        if(dishId == null){
            List<Dish> dishList = dishMapper.selectByExample(null);
            return dishList;
        }
        DishExample example =new DishExample();
        DishExample.Criteria criteria =example.createCriteria();
        criteria.andIdEqualTo(dishId);
        List<Dish> dishList = dishMapper.selectByExample(example);
        return dishList;
    } 

    @RequestMapping(method = RequestMethod.POST) //修改菜品信息
    public void updateDish(@RequestParam Dish redish){
        dishMapper.updateByPrimaryKey(redish);
        return;
    }

    @RequestMapping(method = RequestMethod.PUT) //新增菜品
    public void addNewDish(@RequestParam Dish newdish){
        dishMapper.insert(newdish);
        return;
    }

    @RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE) //删除菜品
    public void deleteDishById(@PathVariable Integer dishId){
        dishMapper.deleteByPrimaryKey(dishId);
        return;
    }

}
