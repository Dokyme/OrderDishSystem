package com.odss.seu.service;

import com.odss.seu.vo.Dish;

import java.util.List;

public interface DishRepository {
    List<Dish> queryAllDishes();

    Dish queryDishesById(Integer dishId) throws RuntimeException;
}
