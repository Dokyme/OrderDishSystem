package com.odss.seu.service;

import com.odss.seu.vo.Dish;

import java.util.List;

public interface DishManageService {
    List<Dish> queryAllDish();

    void updateDish(Dish dish);

    void deleteDishById(int id);

    void insertDish(Dish dish);
}
