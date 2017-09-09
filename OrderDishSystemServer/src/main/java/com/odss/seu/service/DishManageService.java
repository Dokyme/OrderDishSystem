package com.odss.seu.service;

import com.odss.seu.vo.Dish;

import java.util.List;

public interface DishManageService {
    List<Dish> queryAllDishes();

    Dish queryDishDetail(Integer dishId);

    void updateDish(Dish dish);

    void deleteDish(Integer dishId);

    Dish addDish(Dish dish);
}
