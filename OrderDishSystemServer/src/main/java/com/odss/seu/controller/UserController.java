package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(value="/{userId}",method= RequestMethod.GET)
            public @JsonView
    List<User> queryAllUser(@PathVariable Integer userId)
    {
        return new ArrayList<>();
    }

    @RequestMapping(method=RequestMethod.POST)
    public void updateUser(@RequestParam User user)
    {
        return;
    }

    @RequestMapping(method=RequestMethod.PUT)
    public void addNewUser(@RequestParam User user)
    {
        return;
    }

    @RequestMapping(value="/{userId}",method=RequestMethod.DELETE)
    public void deleteUser(@RequestParam Integer userId)
    {
        return;
    }
}

//", method = RequestMethod.GET)
//    public @JsonView
//    List<Dish> queryAllDish(@PathVariable Integer typeId) {
//        return new ArrayList<>();
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void updateDish(@RequestParam Dish dish){
//        return;
//    }
//
//    @RequestMapping(method = RequestMethod.PUT)
//    public void addNewDish(@RequestParam Dish dish){
//        return;
//    }
//
//    @RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE)
//    public void deleteDish(@PathVariable Integer dishId){
//        return;
//    }
//}
