package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.service.exception.ResourcesNotFoundException;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishRepositoryImpl implements DishRepository {

    private DishMapper dishMapper;

    @Autowired
    public DishRepositoryImpl(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    @Override
    public Dish queryDishesById(Integer dishId) throws RuntimeException {
        Dish dish = dishMapper.selectByPrimaryKey(dishId);
        if (dish == null) {
            throw new ResourcesNotFoundException();
        }
        return dish;
    }

    @Override
    public List<Dish> queryAllDishes() {
        DishExample example = new DishExample();
        return dishMapper.selectByExample(example);
    }
}
