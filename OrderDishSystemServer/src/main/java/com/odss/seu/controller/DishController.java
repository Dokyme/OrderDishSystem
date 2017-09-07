package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.vo.Dish;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {

    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    public @JsonView
    List<Dish> queryAllDish(@PathVariable Integer typeId) {
        return new ArrayList<>();
        org.apache.ibatis.session.B
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateDish(@RequestParam Dish dish){
        return;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void addNewDish(@RequestParam Dish dish){
        return;
    }

    @RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE)
    public void deleteDish(@PathVariable Integer dishId){
        return;
    }
}
