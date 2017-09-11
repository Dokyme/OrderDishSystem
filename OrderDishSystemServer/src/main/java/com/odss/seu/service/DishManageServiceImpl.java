package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishManageServiceImpl implements DishManageService {

    private DishMapper dishMapper;

    @Autowired
    public DishManageServiceImpl(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    @Override
    public List<Dish> queryAllDishes() {
        return dishMapper.selectByExample(new DishExample());
    }

    @Override
    public Dish queryDishDetail(Integer dishId) {
        return dishMapper.selectByPrimaryKey(dishId);
    }

    @Override
    public Dish addDish(Dish dish) {
        dishMapper.insert(dish);
        return dish;
    }

    @Override
    public void updateDish(Dish dish) {
        dishMapper.updateByPrimaryKey(dish);
    }

    @Override
    public void deleteDish(Integer dishId) {
        dishMapper.deleteByPrimaryKey(dishId);
    }
}
