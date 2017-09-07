package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.vo.Dish;
<<<<<<< HEAD
=======
import org.apache.ibatis.session.SqlSessionFactory;
>>>>>>> master
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD

=======
>>>>>>> master
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {

<<<<<<< HEAD
    @RequestMapping(value = "/{typeId}",method = RequestMethod.GET) //查询菜品信息，如果有typeId则返回对应类别的菜品列表，如果没有则返回所有菜品列表。
    public @JsonView List<Dish> queryDishesByTypeId(@PathVariable Integer typeId){
        return new ArrayList<>();
    } 

    @RequestMapping(method = RequestMethod.POST) //修改菜品信息
=======
    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    public @JsonView
    List<Dish> queryAllDish(@PathVariable Integer typeId) {
        return new ArrayList<>();
        org.apache.ibatis.session.B
    }

    @RequestMapping(method = RequestMethod.POST)
>>>>>>> master
    public void updateDish(@RequestParam Dish dish){
        return;
    }

<<<<<<< HEAD
    @RequestMapping(method = RequestMethod.PUT) //新增菜品
=======
    @RequestMapping(method = RequestMethod.PUT)
>>>>>>> master
    public void addNewDish(@RequestParam Dish dish){
        return;
    }

<<<<<<< HEAD
    @RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE) //删除菜品
    public void deleteDishById(@PathVariable Integer dishId){
        return;
    }

=======
    @RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE)
    public void deleteDish(@PathVariable Integer dishId){
        return;
    }
>>>>>>> master
}
